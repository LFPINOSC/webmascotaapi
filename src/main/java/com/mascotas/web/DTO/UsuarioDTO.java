package com.mascotas.web.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String clave;       // solo úsalo para CREATE, no lo devuelvas en respuesta
    private Long tipoUsuarioId;
    private String tipoUsuario; // devuelto al listar
}