package com.ESFE.Asistencias.Repositorio;
import com.ESFE.Asistencias.Entidades.DocenteGrupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ESFE.Asistencias.Entidades.EstudianteGrupo;
public interface IEstudianteGrupoRepository extends JpaRepository<EstudianteGrupo,Integer> {
    Page<EstudianteGrupo> findByOrderByEstudianteDesc(Pageable pageable);

}
