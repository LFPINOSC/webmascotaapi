package com.mascotas.web.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Getter @Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correo;
    private String clave;

    @ManyToOne
    private TipoUsuario tipoUsuario;

    @OneToMany(mappedBy = "dueño", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Mascota> mascotas;

    @OneToMany(mappedBy = "veterinario", cascade = CascadeType.ALL)
    private List<Cita> citasAtendidas;
}

