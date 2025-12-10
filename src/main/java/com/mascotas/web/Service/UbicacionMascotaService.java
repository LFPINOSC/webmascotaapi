package com.mascotas.web.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.mascotas.web.DTO.UbicacionMascotaRequest;
import com.mascotas.web.Entidades.Mascota;
import com.mascotas.web.Entidades.UbicacionMascota;
import com.mascotas.web.Repositorios.MascotaRepository;
import com.mascotas.web.Repositorios.UbicacionMascotaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UbicacionMascotaService {

    private final UbicacionMascotaRepository repo;
    private final MascotaRepository mascotaRepo;

    public UbicacionMascota crear(UbicacionMascota u) {
        return repo.save(u);
    }

    public UbicacionMascota obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ubicación no encontrada"));
    }

    public List<UbicacionMascota> listar() {
        return repo.findAll();
    }

    public List<UbicacionMascota> listarPorMascota(Long mascotaId) {
        return repo.findByMascotaId(mascotaId);
    }

    public UbicacionMascota actualizar(Long id, UbicacionMascota datos) {
        UbicacionMascota existente = obtener(id);

        existente.setLatitud(datos.getLatitud());
        existente.setLongitud(datos.getLongitud());
        existente.setFechaHora(datos.getFechaHora());
        existente.setMascota(datos.getMascota());

        return repo.save(existente);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
    public List<UbicacionMascota> listarSoloSiEsDueño(String correo, Long mascotaId) {

        boolean esDueño = repo.existsByMascotaIdAndMascotaDueñoCorreo(mascotaId, correo);

        if (!esDueño) {
            throw new RuntimeException("No tienes permiso para ver esta ubicación.");
        }

        return repo.findByMascotaId(mascotaId);
    }
    public UbicacionMascota crearParaMascota(Long mascotaId, UbicacionMascota u) {

        Mascota mascota = mascotaRepo.findById(mascotaId)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        u.setMascota(mascota);
        u.setFechaHora(LocalDateTime.now());

        return repo.save(u);
    }
    public UbicacionMascota crearUbicacion(Long idMascota, UbicacionMascotaRequest dto) {

        Mascota mascota = mascotaRepo.findById(idMascota)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        UbicacionMascota ubicacion = new UbicacionMascota();
        ubicacion.setMascota(mascota);
        ubicacion.setLatitud(dto.getLatitud());
        ubicacion.setLongitud(dto.getLongitud());
        ubicacion.setFechaHora(LocalDateTime.now()); // ← toma del sistema

        return repo.save(ubicacion);
    }
}

