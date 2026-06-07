package br.com.fiap.argus.controller;

import br.com.fiap.argus.dto.request.LoginRequestDTO;
import br.com.fiap.argus.dto.response.LoginResponseDTO;
import br.com.fiap.argus.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(

            @RequestBody
            LoginRequestDTO request

    ) {

        return ResponseEntity.ok(

                authService.login(
                        request
                )

        );

    }

}