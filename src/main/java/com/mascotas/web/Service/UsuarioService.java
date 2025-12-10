package com.mascotas.web.Service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mascotas.web.Entidades.Usuario;
import com.mascotas.web.Repositorios.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repo;
    private final PasswordEncoder encoder;

    public Usuario crear(Usuario u) {
        u.setClave(encoder.encode(u.getClave()));
        return repo.save(u);
    }

    public List<Usuario> listar() { return repo.findAll(); }
}