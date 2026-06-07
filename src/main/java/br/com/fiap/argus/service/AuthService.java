package br.com.fiap.argus.service;

import br.com.fiap.argus.dto.request.LoginRequestDTO;
import br.com.fiap.argus.dto.response.LoginResponseDTO;
import br.com.fiap.argus.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.senha}")
    private String adminSenha;

    public LoginResponseDTO login(
            LoginRequestDTO request
    ) {

        boolean emailValido =
                adminEmail.equals(
                        request.email()
                );

        boolean senhaValida =
                adminSenha.equals(
                        request.senha()
                );

        if (
                !emailValido
                        ||
                        !senhaValida
        ) {

            throw new BadCredentialsException(
                    "Email ou senha inválidos"
            );

        }

        String token =
                jwtService.gerarToken(
                        request.email()
                );

        return new LoginResponseDTO(
                token
        );

    }

}