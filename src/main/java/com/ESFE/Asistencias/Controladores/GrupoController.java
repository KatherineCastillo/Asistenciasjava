package com.ESFE.Asistencias.Controladores;

import com.ESFE.Asistencias.Entidades.Grupo;
import com.ESFE.Asistencias.Servicios.Interfaces.IGrupoServices;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/Grupos")
public class GrupoController {
    @Autowired
    private IGrupoServices grupoServices;
    @GetMapping

    public String index(Model model, @RequestParam("page")Optional<Integer>page,@RequestParam("size")Optional<Integer> size){
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(10);
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        Page<Grupo> grupos = grupoServices.BuscarTodosPaginados(pageable);
        model.addAttribute("grupos",grupos);

        int totalPage = grupos.getTotalPages();
        if(totalPage > 0){
            List<Integer> pageNumbers=  IntStream.rangeClosed(1,totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);

        }
        return "grupo/index";
    }
    @GetMapping("/create")
    public String create(Grupo grupo){
        return "grupo/create";
    }
    @PostMapping("/save")
    public String save(Grupo grupo, BindingResult result, Model model, RedirectAttributes atrributes){
        if(result.hasErrors()){
            model.addAttribute(grupo);
            atrributes.addFlashAttribute("error", "No se puede guardar debido a un error ");
            return "grupo/create";
        }
        grupoServices.CrearOeditar(grupo);
        atrributes.addFlashAttribute("msg", "Grupo creado correctamente");
        return "redirect:/Grupos";
    }
    @GetMapping("/details/{id}")
    public String details(@PathVariable("id")Integer id, Model model){
        Grupo grupo = grupoServices.BuscarPorId(id).get();
        model.addAttribute("grupo", grupo);
        return "grupo/details";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Grupo grupo= grupoServices.BuscarPorId(id).get();
        model.addAttribute("grupo", grupo);
        return "grupo/edit";
    }
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        Grupo grupo = grupoServices.BuscarPorId(id).get();
        model.addAttribute("grupo", grupo);
        return "grupo/delete";
    }
    @PostMapping("/delete")
    public String delete(Grupo grupo, RedirectAttributes attributes){
        grupoServices.EliminarPorId(grupo.getId());
        attributes.addFlashAttribute("msg", "Grupo Eliminado correctamente");
        return "redirect:/Grupos";
    }
}
