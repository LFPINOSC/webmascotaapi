package com.mascotas.web.Repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mascotas.web.Entidades.UbicacionMascota;

import java.util.List;

public interface UbicacionMascotaRepository extends JpaRepository<UbicacionMascota, Long> {
    List<UbicacionMascota> findByMascotaId(Long mascotaId);
    
    boolean existsByMascotaIdAndMascotaDueñoCorreo(Long mascotaId, String correo);
}

