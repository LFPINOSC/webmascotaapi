package com.mascotas.web.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.mascotas.web.Entidades.Cita;
import com.mascotas.web.Repositorios.CitaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaRepository repo;

    public Cita crear(Cita c) {
        return repo.save(c);
    }

    public Cita obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }

    public List<Cita> listar() {
        return repo.findAll();
    }

    public List<Cita> listarPorMascota(Long mascotaId) {
        return repo.findByMascotaId(mascotaId);
    }

    public Cita actualizar(Long id, Cita datos) {
        Cita existente = obtener(id);

        existente.setFecha(datos.getFecha());
        existente.setDescripcion(datos.getDescripcion());
        existente.setMascota(datos.getMascota());
        existente.setVeterinario(datos.getVeterinario());

        return repo.save(existente);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
    public List<Cita> listarPorVeterinario(String correo) {
        return repo.findByVeterinarioCorreo(correo);
    }

    public List<Cita> listarPorDueño(String correo) {
        return repo.findByMascotaDueñoCorreo(correo);
    }
}

