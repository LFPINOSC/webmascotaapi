package com.mascotas.web.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mascotas.web.Entidades.Cita;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByMascotaId(Long mascotaId);
    List<Cita> findByVeterinarioCorreo(String correo);
    List<Cita> findByMascotaDueñoCorreo(String correo);
}
