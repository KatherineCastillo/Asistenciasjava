package com.ESFE.Asistencias.Servicios.Interfaces;

import com.ESFE.Asistencias.Entidades.Grupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import java.util.Optional;
import java.util.List;



public interface IGrupoServices {
    Page<Grupo> BuscarTodosPaginados(Pageable pageable);
    List<Grupo> ObtenerTodos();
    Optional<Grupo> BuscarPorId(Integer id);
    Grupo CrearOeditar(Grupo grupo);
    void EliminarPorId(Integer id);
}

