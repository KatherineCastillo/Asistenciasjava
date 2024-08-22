package com.ESFE.Asistencias.Entidades;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "estudiantes")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "ingresar nombre del estudiante")
    private String nombre;

    @Email(message = "ingrese un email valido")
    @NotBlank(message = "el email es obligatorio")
    private String email;

    @NotBlank(message = "el pin es obligatorio")
    private String pin;

    @ManyToMany
    @JoinTable(
            name = "estudiante_grupos",
            joinColumns = @JoinColumn(name = "estudiante_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id")
    )
    private Set<Grupo> grupos = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "ingresar nombre del estudiante") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "ingresar nombre del estudiante") String nombre) {
        this.nombre = nombre;
    }

    public @Email(message = "ingrese un email valido") @NotBlank(message = "el email es obligatorio") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "ingrese un email valido") @NotBlank(message = "el email es obligatorio") String email) {
        this.email = email;
    }

    public @NotBlank(message = "el pin es obligatorio") String getPin() {
        return pin;
    }

    public void setPin(@NotBlank(message = "el pin es obligatorio") String pin) {
        this.pin = pin;
    }
}
