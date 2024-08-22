package com.ESFE.Asistencias.Entidades;
import jakarta.persistence.*;

@Entity
@Table(name = "estudiante_grupos")
public class EstudianteGrupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @ManyToOne
    @JoinColumn(name="estudiante_id")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
