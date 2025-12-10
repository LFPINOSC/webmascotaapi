package com.mascotas.web.Service;


import org.springframework.stereotype.Service;

import com.mascotas.web.Entidades.TipoUsuario;
import com.mascotas.web.Repositorios.TipoUsuarioRepository;

import java.util.List;

@Service
public class TipoUsuarioService {

    private final TipoUsuarioRepository repository;

    public TipoUsuarioService(TipoUsuarioRepository repository) {
        this.repository = repository;
    }

    // Crear TipoUsuario
    public TipoUsuario crear(TipoUsuario tipo) {
        return repository.save(tipo);
    }

    // Listar todos
    public List<TipoUsuario> listar() {
        return repository.findAll();
    }

    // Buscar por ID
    public TipoUsuario buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Buscar por nombre (ADMIN, CLIENTE, VETERINARIO)
    public TipoUsuario buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre).orElse(null);
    }

    // Actualizar
    public TipoUsuario actualizar(Long id, TipoUsuario datos) {
        TipoUsuario tipo = buscarPorId(id);
        if (tipo == null) return null;

        tipo.setNombre(datos.getNombre());
        return repository.save(tipo);
    }

    // Eliminar
    public boolean eliminar(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}

