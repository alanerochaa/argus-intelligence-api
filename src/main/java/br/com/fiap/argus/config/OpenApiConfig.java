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
    
                                        ---
    
                                        ## 🚀 Funcionalidades
    
                                        ### 🌿 Biomas
                                        Gerenciamento dos biomas monitorados.
    
                                        ### 📍 Regiões
                                        Controle das regiões monitoradas e classificação de risco.
    
                                        ### 🔥 Focos de Calor
                                        Monitoramento e ingestão de eventos detectados por satélite.
    
                                        ### 🚨 Alertas
                                        Geração e gerenciamento de alertas ambientais.
    
                                        ### 🛰️ Integrações
    
                                        • NASA FIRMS  
                                        • Weather API  
                                        • API Operations (.NET)  
                                        • API IA  
    
                                        ---
    
                                        ## 🛠️ Tecnologias
    
                                        Java • Spring Boot • Oracle Database  
                                        REST API • OpenAPI • Feign Client
    
                                        ---
    
                                        ## 🎓 Projeto Acadêmico
    
                                        FIAP — Global Solution 2026/1
    
                                        ### 👩‍💻 Grupo: CodeGirls
    
                                        • Alane Rocha da Silva — RM561052  
                                        • Anna Beatriz de Araujo Bonfim — RM559561  
                                        • Maria Eduarda Araujo Penas — RM560944
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