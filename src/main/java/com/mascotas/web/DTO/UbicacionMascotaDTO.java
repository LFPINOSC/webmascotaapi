package com.mascotas.web.DTO;
import java.time.LocalDateTime;

import com.mascotas.web.Entidades.UbicacionMascota;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UbicacionMascotaDTO {
    private Long id;
    private Double latitud;
    private Double longitud;
    private LocalDateTime fechaHora;
    private Long mascotaId;
    private String dueñoCorreo;

    public UbicacionMascotaDTO() {}

    public UbicacionMascotaDTO(UbicacionMascota u) {
        this.id = u.getId();
        this.latitud = u.getLatitud();
        this.longitud = u.getLongitud();
        this.fechaHora = u.getFechaHora();
        this.mascotaId = (u.getMascota() != null) ? u.getMascota().getId() : null;
        this.dueñoCorreo = (u.getMascota() != null && u.getMascota().getDueño() != null)
                            ? u.getMascota().getDueño().getCorreo()
                            : null;
    }
}
