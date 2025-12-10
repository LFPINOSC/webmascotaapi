package com.mascotas.web.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter @Setter
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    private Raza raza;

    @ManyToOne
    @JsonBackReference("dueño-mascotas")
    private Usuario dueño;

    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL)
    @JsonManagedReference("mascota-citas")
    private List<Cita> citas;

    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL)
    @JsonManagedReference("mascota-ubicaciones")
    private List<UbicacionMascota> ubicaciones;
}
