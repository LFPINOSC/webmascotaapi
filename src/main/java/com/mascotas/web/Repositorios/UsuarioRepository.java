package com.mascotas.web.Repositorios;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mascotas.web.Entidades.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);
}
