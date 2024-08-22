package com.ESFE.Asistencias.Controladores;

import com.ESFE.Asistencias.Entidades.Docente;
import com.ESFE.Asistencias.Entidades.DocenteGrupo;
import com.ESFE.Asistencias.Entidades.Grupo;
import com.ESFE.Asistencias.Servicios.Interfaces.IDocenteGrupoService;
import com.ESFE.Asistencias.Servicios.Interfaces.IGDocenteServices;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/docenteGrupos")
public class DocenteGrupoController {
    @Autowired
    IDocenteGrupoService _docenteGrupoService;
    @Autowired
    private IGDocenteServices _docenteServices;
    @Autowired
    private IGrupoServices _grupoServices;

    @GetMapping

    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size")Optional<Integer> size){
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(10);
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        Page<DocenteGrupo> docenteGrupo = _docenteGrupoService.BuscarTodosPaginados(pageable);
        model.addAttribute("DocenteGrupo",docenteGrupo);

        int totalPage = docenteGrupo.getTotalPages();
        if(totalPage > 0){
            List<Integer> pageNumbers=  IntStream.rangeClosed(1,totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);

        }
        return "docenteGrupo/index";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("docenteGrupo", new DocenteGrupo());
        model.addAttribute("docentes", _docenteServices.ObtenerTodos() );
        model.addAttribute("grupos", _grupoServices.ObtenerTodos());
        return "docenteGrupo/create";
    }
    @PostMapping("/save")
    public String save(@RequestParam Integer docenteId,
                       @RequestParam Integer grupoId,
                       @RequestParam Integer anio,
                       @RequestParam String ciclo,
                       RedirectAttributes attributes){
        Docente docente = _docenteServices.BuscarPorId(docenteId).get();
        Grupo grupo = _grupoServices.BuscarPorId(grupoId).get();
        if(docente !=null & grupo !=null){
            DocenteGrupo docenteGrupo = new DocenteGrupo();

            docenteGrupo.setDocente(docente);
            docenteGrupo.setGrupo(grupo);
            docenteGrupo.setAnio(anio);
            docenteGrupo.setCiclo(ciclo);

            _docenteGrupoService.CrearOeditar(docenteGrupo);
            attributes.addFlashAttribute("msg", "Asignacion guardada correctamente");

        } else {
            attributes.addFlashAttribute("error","no se pudo guardar la asignacion");
        }
        return  "redirect:/docenteGrupos";
    }
    @GetMapping("/details/{id}")
    public String details(@PathVariable("id")Integer id, Model model){
        DocenteGrupo docenteGrupo = _docenteGrupoService.BuscarPorId(id).get();
        model.addAttribute("docenteGrupos", _docenteServices.ObtenerTodos());
        return "docenteGrupo/details";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        DocenteGrupo docenteGrupo= _docenteGrupoService.BuscarPorId(id).get();

        model.addAttribute("docentes", _docenteServices.ObtenerTodos());
        model.addAttribute("grupos", _grupoServices.ObtenerTodos());
        model.addAttribute("docenteGrupo", docenteGrupo);
        return "docenteGrupo/edit";
    }
    @PostMapping("/update")
    public String update(@RequestParam Integer id, @RequestParam Integer docenteId,
                         @RequestParam Integer grupoId,
                         @RequestParam Integer anio, @RequestParam String ciclo,
                         RedirectAttributes attributes){
        Docente docente = _docenteServices.BuscarPorId(docenteId).get();
        Grupo grupo = _grupoServices.BuscarPorId(grupoId).get();

        if (docente !=null && grupo !=null){
            DocenteGrupo docenteGrupo = new DocenteGrupo();
            docenteGrupo.setId(id);
            docenteGrupo.setDocente(docente);
            docenteGrupo.setGrupo(grupo);
            docenteGrupo.setAnio(anio);
            docenteGrupo.setCiclo(ciclo);

            _docenteGrupoService.CrearOeditar(docenteGrupo);
            attributes.addFlashAttribute("msg", "Asignacion modificada correctamente");
        }
        return "redirect:/docenteGrupos";
    }
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        DocenteGrupo docenteGrupo = _docenteGrupoService.BuscarPorId(id).get();
        model.addAttribute("docenteGrupo", docenteGrupo);
        return "docenteGrupo/delete";
    }
    @PostMapping("/delete")
    public String delete(DocenteGrupo docenteGrupo, RedirectAttributes attributes){
        _docenteGrupoService.EliminarPorId(docenteGrupo.getId());
        attributes.addFlashAttribute("msg", "Grupo Eliminado correctamente");
        return "redirect:/docenteGrupos";
    }
}


