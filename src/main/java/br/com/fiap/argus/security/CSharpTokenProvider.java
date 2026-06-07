package br.com.fiap.argus.security;

import br.com.fiap.argus.client.AuthCSharpClient;
import br.com.fiap.argus.dto.request.CSharpLoginRequestDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class CSharpTokenProvider {

    private final AuthCSharpClient authCSharpClient;

    @Value("${csharp.admin.email}")
    private String email;

    @Value("${csharp.admin.senha}")
    private String senha;

    private String cachedToken;

    private Instant expiresAt;

    public synchronized String getToken() {

        if (
                cachedToken == null
                        ||
                        expiresAt == null
                        ||
                        Instant.now().isAfter(expiresAt)
        ) {

            var response = authCSharpClient.login(
                    new CSharpLoginRequestDTO(
                            email,
                            senha
                    )
            );

            cachedToken = response.token();

            expiresAt = response.expiraEm()
                    .toInstant()
                    .minus(Duration.ofMinutes(5));
        }

        return cachedToken;
    }
}