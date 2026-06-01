package br.com.fiap.argus.security;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class JwtService {

    private static final Long EXPIRATION = 86400000L;

    public String gerarToken(String username) {
        String payload = username + ":" + (System.currentTimeMillis() + EXPIRATION);

        return Base64.getEncoder()
                .encodeToString(payload.getBytes());
    }

    public String extrairUsuario(String token) {
        String decoded = new String(
                Base64.getDecoder().decode(token)
        );

        return decoded.split(":")[0];
    }

    public boolean validar(String token) {
        try {
            String decoded = new String(
                    Base64.getDecoder().decode(token)
            );

            String[] parts = decoded.split(":");

            if (parts.length < 2) {
                return false;
            }

            long expiration = Long.parseLong(parts[1]);

            return expiration > System.currentTimeMillis();

        } catch (Exception ex) {
            return false;
        }
    }
}