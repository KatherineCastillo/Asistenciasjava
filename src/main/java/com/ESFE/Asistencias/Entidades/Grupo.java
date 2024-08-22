package com.ESFE.Asistencias.Entidades;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "grupos")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //revisar porque me da error notblank
   @NotBlank(message = "ingresar nombre del grupo")
    private String nombre;
    @Nullable
    private String descripcion;

    @ManyToMany(mappedBy = "grupos")
    private Set<Docente> docentes = new HashSet<>();

    @ManyToMany(mappedBy = "grupos" )
    private Set<Estudiante> estudiantes = new HashSet<>();

    public Set<Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(Set<Docente> docentes) {
        this.docentes = docentes;
    }

    public Set<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Set<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "ingresar nombre del grupo") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "ingresar nombre del grupo") String nombre) {
        this.nombre = nombre;
    }

    @Nullable
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@Nullable String descripcion) {
        this.descripcion = descripcion;
    }
//poner getter and setter

}
