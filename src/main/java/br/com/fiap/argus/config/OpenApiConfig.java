package br.com.fiap.argus.config;

import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME =
            "bearerAuth";

    @Bean
    public OpenAPI argusOpenAPI() {

        return new OpenAPI()

                .info(

                        new Info()

                                .title(
                                        "🛰️ ARGUS INTELLIGENCE API"
                                )

                                .version(
                                        "1.0.0"
                                )

                                .description(
                                        """
                                        Plataforma de monitoramento ambiental e suporte operacional baseada em dados espaciais.
                                        
                                        🔐 Endpoints protegidos utilizam JWT.
                                        
                                        Para testar:
                                        1. Faça login em /api/auth/login
                                        2. Copie o token retornado
                                        3. Clique em Authorize
                                        4. Informe:
                                        
                                        Bearer SEU_TOKEN
                                        """
                                )

                                .contact(

                                        new Contact()

                                                .name(
                                                        "Grupo CodeGirls"
                                                )

                                )

                                .license(

                                        new License()

                                                .name(
                                                        "FIAP • Global Solution 2026/1"
                                                )

                                )

                )

                .addSecurityItem(

                        new SecurityRequirement()

                                .addList(
                                        SECURITY_SCHEME
                                )

                )

                .schemaRequirement(

                        SECURITY_SCHEME,

                        new SecurityScheme()

                                .name(
                                        "Authorization"
                                )

                                .type(
                                        SecurityScheme.Type.HTTP
                                )

                                .scheme(
                                        "bearer"
                                )

                                .bearerFormat(
                                        "JWT"
                                )

                );

    }

}