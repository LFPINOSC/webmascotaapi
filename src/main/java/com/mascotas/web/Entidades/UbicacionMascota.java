package com.mascotas.web.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter @Setter
public class UbicacionMascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitud;
    private Double longitud;
    private LocalDateTime fechaHora;

    @ManyToOne
    @JsonBackReference("mascota-ubicaciones")
    private Mascota mascota;
}
