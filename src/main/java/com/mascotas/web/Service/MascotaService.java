package com.mascotas.web.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.mascotas.web.Entidades.Mascota;
import com.mascotas.web.Repositorios.MascotaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MascotaService {

    private final MascotaRepository repo;

    public Mascota crear(Mascota m) {
        return repo.save(m);
    }

    public Mascota obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
    }

    public List<Mascota> listar() {
        return repo.findAll();
    }

    public List<Mascota> listarPorCorreoDueño(String correo) {
        return repo.findByDueñoCorreo(correo);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}

