package com.mascotas.web.Controladores;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.mascotas.web.Entidades.Mascota;
import com.mascotas.web.Entidades.Usuario;
import com.mascotas.web.Service.MascotaService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mascotas")
public class MascotaController {

    private final MascotaService service;

    // Crear mascota (dueño o admin)
    @PostMapping
    public Mascota crear(@RequestBody Mascota m) {
        return service.crear(m);
    }

    // Admin: ve todo | Cliente: ve solo las suyas
    @GetMapping
    public List<Mascota> listar(Authentication auth) {

        String rol = auth.getAuthorities().iterator().next().getAuthority();
        String correo = auth.getName();

        if (rol.equals("ROLE_ADMIN")) {
            return service.listar();
        }

        return service.listarPorCorreoDueño(correo);
    }
    

    
}

