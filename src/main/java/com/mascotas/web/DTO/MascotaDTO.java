package com.mascotas.web.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MascotaDTO {
    private Long id;
    private String nombre;

    private Long razaId;
    private String raza;       // solo para listar

    private Long dueñoId;
    private String dueño;      // solo para listar
}