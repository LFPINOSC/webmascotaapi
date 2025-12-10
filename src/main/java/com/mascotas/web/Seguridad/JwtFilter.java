package com.mascotas.web.Seguridad;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UsuarioDetailsService usuarioDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        System.out.println("🔎 JWT Filter - Path: " + path);

        // ver headers
        System.out.println("🔎 Headers: Authorization=" + request.getHeader("Authorization"));

        // rutas públicas
        if (path.startsWith("/api/auth") || path.startsWith("/api/ubicaciones")) {
            System.out.println("➡ Ruta pública, dejando pasar");
            filterChain.doFilter(request, response);
            return;
        }
        if (path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs")) {
            filterChain.doFilter(request, response);
            return;
        }

        // obtener token
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            System.out.println("❌ No hay Authorization");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        System.out.println("🔎 Token recibido: " + authHeader);

  
        

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);
            String correo = jwtUtil.getCorreo(token);

            if (correo != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = usuarioDetailsService.loadUserByUsername(correo);

                if (jwtUtil.esTokenValido(token, userDetails)) {

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}

