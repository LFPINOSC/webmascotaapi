package com.mascotas.web.Seguridad;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mascotas.web.DTO.LoginRequestDTO;
import com.mascotas.web.DTO.LoginResponseDTO;
import com.mascotas.web.Entidades.Usuario;
import com.mascotas.web.Repositorios.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwt;

    public LoginResponseDTO login(LoginRequestDTO dto) {

        Usuario usuario = usuarioRepo.findByCorreo(dto.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(dto.getClave(), usuario.getClave())) {
            throw new RuntimeException("Clave incorrecta");
        }

        String token = jwt.generarToken(
                usuario.getCorreo(),
                usuario.getTipoUsuario().getNombre()
        );

        return new LoginResponseDTO(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getCorreo(),
            usuario.getTipoUsuario().getNombre(),
            token
        );
    }
}

