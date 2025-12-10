package com.mascotas.web.Seguridad;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mascotas.web.Entidades.Usuario;
import com.mascotas.web.Repositorios.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + correo));

        // El rol debe ser convertido a formato Spring: "ROLE_ADMIN", "ROLE_CLIENTE", etc.
        String rol = "ROLE_" + usuario.getTipoUsuario().getNombre().toUpperCase();

        return User.withUsername(usuario.getCorreo())
                .password(usuario.getClave())
                .roles(usuario.getTipoUsuario().getNombre().toUpperCase())
                .build();
    }
}

