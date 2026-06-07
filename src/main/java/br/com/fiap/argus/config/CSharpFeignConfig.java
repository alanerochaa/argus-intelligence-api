package br.com.fiap.argus.config;

import br.com.fiap.argus.security.CSharpTokenProvider;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class CSharpFeignConfig {

    @Bean
    public RequestInterceptor csharpAuthInterceptor(
            CSharpTokenProvider tokenProvider
    ) {
        return template -> template.header(
                "Authorization",
                "Bearer " + tokenProvider.getToken()
        );
    }
}