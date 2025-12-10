package com.mascotas.web.Controladores;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mascotas.web.Entidades.TipoUsuario;
import com.mascotas.web.Service.TipoUsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tipos-usuario")
@RequiredArgsConstructor
public class TipoUsuarioController {

    private final TipoUsuarioService service;

    @PostMapping
    public TipoUsuario crear(@RequestBody TipoUsuario t) {
        return service.crear(t);
    }

    @GetMapping
    public List<TipoUsuario> listar() {
        return service.listar();
    }
}

