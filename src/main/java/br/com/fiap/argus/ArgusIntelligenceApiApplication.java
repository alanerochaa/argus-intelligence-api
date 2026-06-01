package br.com.fiap.argus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ArgusIntelligenceApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(
				ArgusIntelligenceApiApplication.class,
				args
		);

		System.out.println("""

==========================================================
🚀 ARGUS INTELLIGENCE API ATIVA
==========================================================

📌 Swagger:
http://localhost:8080/swagger-ui.html

📌 OpenAPI:
http://localhost:8080/v3/api-docs

📌 Actuator:
http://localhost:8080/actuator

📌 RabbitMQ:
http://localhost:15672

📌 Banco:
Oracle FIAP

📌 Status:
ONLINE ✅

==========================================================

""");

	}
}