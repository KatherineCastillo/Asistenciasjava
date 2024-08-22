package com.ESFE.Asistencias.Entidades;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "docentes")
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "ingresar nombre del docente")
    private String nombre;

    @Nullable
    private String apellido;

    @Email(message = "ingrese un email valido")
    @NotBlank(message = "el email es obligatorio")
    private String email;


    @NotNull(message = "el telefono es obligatorio")
    private String telefono;

    @NotNull(message = "la escuela es obligatoria")
    private String escuela;

    @ManyToMany
    @JoinTable(
            name = "docentes_grupos",
            joinColumns = @JoinColumn(name = "docente_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id")
    )
    private Set<Grupo> grupos = new HashSet<>();


    public Set<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(Set<Grupo> grupos) {
        this.grupos = grupos;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getApellido() {

        return apellido;
    }

    public void setApellido( String apellido) {

        this.apellido = apellido;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail( String email) {

        this.email = email;
    }

    @Nullable
    public String getTelefono() {

        return telefono;
    }

    public void setTelefono( String telefono) {

        this.telefono = telefono;
    }

    @Nullable
    public String getEscuela() {

        return escuela;
    }

    public void setEscuela( String escuela) {

        this.escuela = escuela;
    }

    public @NotBlank(message = "ingresar nombre del docente") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "ingresar nombre del docente") String nombre) {
        this.nombre = nombre;
    }
}
