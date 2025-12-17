package com.mascotas.web.Controladores;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.mascotas.web.Entidades.Cita;
import com.mascotas.web.Service.CitaService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService service;

    @PostMapping
    public Cita crear(@RequestBody Cita c) {
        return service.crear(c);
    }

    @GetMapping
    public List<Cita> listar(Authentication auth) {

        String rol = auth.getAuthorities().iterator().next().getAuthority();
        String correo = auth.getName();

        if (rol.equals("ROLE_ADMIN")) {
            return service.listar();
        }

        if (rol.equals("ROLE_VETERINARIO")) {
            return service.listarPorVeterinario(correo);
        }

        return service.listarPorDueño(correo);
    }

    @GetMapping("/{id}")
    public Cita obtener(@PathVariable Long id) {
        return service.obtener(id);
    }
    @GetMapping("/mascota/{mascotaId}")
    public List<Cita> obtenerPorMascota(@PathVariable Long mascotaId) {
        return service.obtenerPorMascota(mascotaId);
    }
}

