package com.mascotas.web.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequestDTO {
    private String correo;
    private String clave;
}
