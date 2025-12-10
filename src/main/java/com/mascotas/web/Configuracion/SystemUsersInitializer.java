package com.mascotas.web.Configuracion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mascotas.web.Entidades.TipoUsuario;
import com.mascotas.web.Entidades.Usuario;
import com.mascotas.web.Repositorios.TipoUsuarioRepository;
import com.mascotas.web.Repositorios.UsuarioRepository;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class SystemUsersInitializer implements CommandLineRunner {

    private final SystemUsersProperties props;
    private final UsuarioRepository usuarioRepo;
    private final TipoUsuarioRepository tipoUsuarioRepo;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {

        crearUsuarioSistema(props.getAdmin());
        crearUsuarioSistema(props.getCliente());
        crearUsuarioSistema(props.getVeterinario());
    }

    private void crearUsuarioSistema(SystemUsersProperties.UserConfig config) {

        // Crear rol si no existe
        TipoUsuario rol = tipoUsuarioRepo.findByNombre(config.getDefaultRol())
                .orElseGet(() -> {
                    TipoUsuario nuevo = new TipoUsuario();
                    nuevo.setNombre(config.getDefaultRol());
                    return tipoUsuarioRepo.save(nuevo);
                });

        // Crear usuario si no existe
        usuarioRepo.findByCorreo(config.getDefaultEmail())
                .orElseGet(() -> {
                    Usuario u = new Usuario();
                    u.setCorreo(config.getDefaultEmail());
                    u.setNombre(config.getDefaultRol() + " por defecto");
                    u.setClave(encoder.encode(config.getDefaultPassword()));
                    u.setTipoUsuario(rol);

                    System.out.println("🔐 Usuario creado: " +
                            config.getDefaultEmail() + " | Rol: " + config.getDefaultRol());

                    return usuarioRepo.save(u);
                });
    }
}

