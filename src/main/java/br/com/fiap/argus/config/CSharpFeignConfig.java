package br.com.fiap.argus.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class CSharpFeignConfig {

    @Value("${csharp.api.token:}")
    private String token;

    @Bean
    public RequestInterceptor csharpRequestInterceptor() {
        return requestTemplate -> {
            if (!token.isBlank()) {
                requestTemplate.header(
                        "Authorization",
                        "Bearer " + token
                );
            }
        };
    }
}