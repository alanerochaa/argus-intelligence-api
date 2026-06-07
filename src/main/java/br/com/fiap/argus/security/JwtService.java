package br.com.fiap.argus.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    // GERA TOKEN
    public String gerarToken(String usuario) {

        return Jwts.builder()

                .subject(usuario)

                .issuedAt(new Date())

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + expiration
                        )
                )

                .signWith(getKey())

                .compact();
    }

    // VALIDA TOKEN
    public boolean validar(String token) {

        try {

            extrairClaims(token);

            return true;

        } catch (Exception ex) {

            return false;

        }

    }

    // EXTRAI USUÁRIO
    public String extrairUsuario(String token) {

        return extrairClaims(token)

                .getSubject();

    }

    // EXTRAI CLAIMS
    private Claims extrairClaims(String token) {

        return Jwts.parser()

                .verifyWith(getKey())

                .build()

                .parseSignedClaims(token)

                .getPayload();

    }

    // CHAVE JWT
    private SecretKey getKey() {

        return Keys.hmacShaKeyFor(

                secret.getBytes(
                        StandardCharsets.UTF_8
                )

        );

    }

}