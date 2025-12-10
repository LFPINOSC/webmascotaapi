package com.mascotas.web.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CitaDTO {
    private Long id;
    private String fecha;

    private Long mascotaId;
    private String mascota;

    private Long veterinarioId;
    private String veterinario;

    private String descripcion;
}
