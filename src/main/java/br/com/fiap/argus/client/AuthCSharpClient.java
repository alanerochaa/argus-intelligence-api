package br.com.fiap.argus.client;

import br.com.fiap.argus.dto.request.CSharpLoginRequestDTO;
import br.com.fiap.argus.dto.response.CSharpLoginResponseDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "csharp-auth",
        url = "${csharp.api.url}"
)
public interface AuthCSharpClient {

    @PostMapping("/api/Auth/login")
    CSharpLoginResponseDTO login(
            @RequestBody CSharpLoginRequestDTO request
    );
}