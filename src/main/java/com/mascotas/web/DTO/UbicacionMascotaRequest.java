package com.mascotas.web.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UbicacionMascotaRequest {
    private double latitud;
    private double longitud;
}