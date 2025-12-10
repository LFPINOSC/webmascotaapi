package com.mascotas.web.Controladores;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mascotas.web.Entidades.Raza;
import com.mascotas.web.Service.RazaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/razas")
@RequiredArgsConstructor
public class RazaController {

    private final RazaService service;

    @PostMapping
    public Raza crear(@RequestBody Raza r) {
        return service.crear(r);
    }

    @GetMapping
    public List<Raza> listar() {
        return service.listar();
    }
}

