package com.mascotas.web.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mascotas.web.Entidades.Mascota;
import com.mascotas.web.Entidades.Usuario;
import com.mascotas.web.Repositorios.MascotaRepository;
import com.mascotas.web.Repositorios.UsuarioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MascotaService {
    @Autowired
    private final MascotaRepository repo;
    @Autowired
    private UsuarioRepository usuarioRepo;

    public Mascota crear(Mascota m) {

        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String correo = auth.getName(); // viene del token

        Usuario dueño = usuarioRepo.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        m.setDueño(dueño);   // 🔗 ENLACE AUTOMÁTICO

        return repo.save(m);
    }

    public Mascota obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
    }

    public List<Mascota> listar() {
        return repo.findAll();
    }

    public List<Mascota> listarPorCorreoDueño(String correo) {
        return repo.findByDueñoCorreo(correo);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}

