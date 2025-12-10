package com.mascotas.web.Repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mascotas.web.Entidades.TipoUsuario;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> { 
     Optional<TipoUsuario> findByNombre(String nombre);
}
