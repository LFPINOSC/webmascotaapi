package com.mascotas.web.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mascotas.web.Entidades.Mascota;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByDueñoId(Long dueñoId);
    List<Mascota> findByDueñoCorreo(String correo);
}