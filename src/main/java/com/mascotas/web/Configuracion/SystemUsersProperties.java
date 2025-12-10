package com.mascotas.web.Configuracion;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app")
public class SystemUsersProperties {

    private UserConfig admin;
    private UserConfig cliente;
    private UserConfig veterinario;

    @Getter @Setter
    public static class UserConfig {
        private String defaultEmail;
        private String defaultPassword;
        private String defaultRol;
    }
}
