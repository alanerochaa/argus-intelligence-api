package br.com.fiap.argus.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

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
                                        # Inteligência Ambiental
    
                                        Plataforma responsável pelo monitoramento ambiental,
                                        análise territorial e suporte operacional utilizando dados espaciais.
    
                                        ---
    
                                        ## 🌎 Objetivo
    
                                        Centralizar informações ambientais e apoiar a tomada de decisão
                                        através de monitoramento contínuo e integração entre serviços.
   
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

                );
    }
}