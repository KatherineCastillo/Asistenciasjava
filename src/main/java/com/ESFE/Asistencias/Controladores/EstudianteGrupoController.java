package com.ESFE.Asistencias.Controladores;

import com.ESFE.Asistencias.Entidades.*;
import com.ESFE.Asistencias.Servicios.Interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/estudianteGrupos")
public class EstudianteGrupoController {
    @Autowired
    IEstudianteGrupoService _estudianteGrupoService;
    @Autowired
    private IEstudianteServices _estudianteServices;
    @Autowired
    private IGrupoServices _grupoServices;

    @GetMapping

    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size")Optional<Integer> size){
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(10);
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        Page<EstudianteGrupo> estudianteGrupo = _estudianteGrupoService.BuscarTodosPaginados(pageable);
        model.addAttribute("EstudianteGrupo",estudianteGrupo);

        int totalPage = estudianteGrupo.getTotalPages();
        if(totalPage > 0){
            List<Integer> pageNumbers=  IntStream.rangeClosed(1,totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);

        }
        return "estudianteGrupo/index";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("estudianteGrupo", new EstudianteGrupo());
        model.addAttribute("estudiantes", _estudianteServices.ObtenerTodos() );
        model.addAttribute("grupos", _grupoServices.ObtenerTodos());
        return "estudianteGrupo/create";
    }
    @PostMapping("/save")
    public String save(@RequestParam Integer estudianteId,
                       @RequestParam Integer grupoId,
                       RedirectAttributes attributes){
        Estudiante estudiante = _estudianteServices.BuscarPorId(estudianteId).get();
        Grupo grupo = _grupoServices.BuscarPorId(grupoId).get();
        if(estudiante !=null & grupo !=null){
            EstudianteGrupo estudianteGrupo = new EstudianteGrupo();

            estudianteGrupo.setEstudiante(estudiante);
            estudianteGrupo.setGrupo(grupo);


            _estudianteGrupoService.CrearOeditar(estudianteGrupo);
            attributes.addFlashAttribute("msg", "Asignacion guardada correctamente");

        } else {
            attributes.addFlashAttribute("error","no se pudo guardar la asignacion");
        }
        return  "redirect:/estudianteGrupos";
    }
    @GetMapping("/details/{id}")
    public String details(@PathVariable("id")Integer id, Model model){
        EstudianteGrupo estudianteGrupo = _estudianteGrupoService.BuscarPorId(id).get();
        model.addAttribute("estudianteGrupos", _estudianteServices.ObtenerTodos());
        return "estudianteGrupo/details";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        EstudianteGrupo estudianteGrupo= _estudianteGrupoService.BuscarPorId(id).get();

        model.addAttribute("estudinates", _estudianteServices.ObtenerTodos());
        model.addAttribute("grupos", _grupoServices.ObtenerTodos());
        model.addAttribute("estudianteGrupo", estudianteGrupo);
        return "estudianteGrupo/edit";
    }
    @PostMapping("/update")
    public String update(@RequestParam Integer id,
                         @RequestParam Integer estudianteId,
                         @RequestParam Integer grupoId,

                         RedirectAttributes attributes){
        Estudiante estudiante = _estudianteServices.BuscarPorId(estudianteId).get();
        Grupo grupo = _grupoServices.BuscarPorId(grupoId).get();

        if (estudiante !=null && grupo !=null){
            EstudianteGrupo estudianteGrupo = new EstudianteGrupo();
            estudianteGrupo.setId(id);
            estudianteGrupo.setEstudiante(estudiante);
            estudianteGrupo.setGrupo(grupo);

            _estudianteGrupoService.CrearOeditar(estudianteGrupo);
            attributes.addFlashAttribute("msg", "Asignacion modificada correctamente");
        }
        return "redirect:/estudianteGrupos";
    }
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        EstudianteGrupo estudianteGrupo = _estudianteGrupoService.BuscarPorId(id).get();
        model.addAttribute("estudianteGrupo", estudianteGrupo);
        return "estudianteGrupo/delete";
    }
    @PostMapping("/delete")
    public String delete(EstudianteGrupo estudianteGrupo, RedirectAttributes attributes){
        _estudianteGrupoService.EliminarPorId(estudianteGrupo.getId());
        attributes.addFlashAttribute("msg", "Estudiante grupo Eliminado correctamente");
        return "redirect:/estudianteGrupos";
    }

}
