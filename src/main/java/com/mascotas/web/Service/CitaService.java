package com.mascotas.web.Service;


import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String correo = auth.getName();

        boolean esAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        boolean esVeterinario = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_VETERINARIO"));

        Cita cita = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        if (esAdmin) return cita;

        if (esVeterinario) {
            if (cita.getVeterinario() == null ||
                !cita.getVeterinario().getCorreo().equals(correo)) {
                throw new RuntimeException("No autorizado para ver esta cita");
            }
            return cita;
        }

        if (!cita.getMascota().getDueño().getCorreo().equals(correo)) {
            throw new RuntimeException("No autorizado para ver esta cita");
        }

        return cita;
    }

    public List<Cita> obtenerPorMascota(Long mascotaId) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String correo = auth.getName();

        boolean esAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        boolean esVeterinario = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_VETERINARIO"));

        if (esAdmin) {
            return repo.findByMascotaId(mascotaId);
        }

        if (esVeterinario) {
            return repo.findByMascotaIdAndVeterinarioCorreo(mascotaId, correo);
        }

        // DUEÑO
        return repo.findByMascotaIdAndMascotaDueñoCorreo(mascotaId, correo);
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

