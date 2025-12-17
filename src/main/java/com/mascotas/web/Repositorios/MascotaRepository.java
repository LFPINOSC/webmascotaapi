package com.mascotas.web.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mascotas.web.Entidades.Mascota;

import java.util.List;
import java.util.Optional;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByDueñoId(Long dueñoId);
    Optional<Mascota> findByIdAndDueñoCorreo(Long id, String correo);

    List<Mascota> findByDueñoCorreo(String correo);
}