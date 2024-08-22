package com.ESFE.Asistencias.Repositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ESFE.Asistencias.Entidades.DocenteGrupo;

public interface IDocenteGrupoRepository extends JpaRepository<DocenteGrupo,Integer>{
    Page<DocenteGrupo> findByOrderByDocenteDesc(Pageable pageable);
}
