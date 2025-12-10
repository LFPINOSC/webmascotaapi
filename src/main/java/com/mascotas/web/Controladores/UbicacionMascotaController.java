package com.mascotas.web.Controladores;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.mascotas.web.DTO.UbicacionMascotaDTO;
import com.mascotas.web.DTO.UbicacionMascotaRequest;
import com.mascotas.web.Entidades.UbicacionMascota;
import com.mascotas.web.Service.UbicacionMascotaService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ubicaciones")
public class UbicacionMascotaController {

    private final UbicacionMascotaService service;

    @PostMapping("/{idMascota}")
    public UbicacionMascota crear(
            @PathVariable Long idMascota,
            @RequestBody UbicacionMascotaRequest dto) {

        return service.crearUbicacion(idMascota, dto);
    }

   @GetMapping("/{mascotaId}")
    public List<UbicacionMascotaDTO> listar(@PathVariable Long mascotaId) {
        // Traer todas las ubicaciones de la mascota
        List<UbicacionMascota> ubicaciones = service.listarPorMascota(mascotaId);

        // Convertir a DTO
        return ubicaciones.stream()
                        .map(UbicacionMascotaDTO::new)
                        .toList();
    }
}

