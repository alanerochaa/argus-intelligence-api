package br.com.fiap.argus.messaging;

import br.com.fiap.argus.dto.response.AlertaResponseDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumidorAlerta {

    @RabbitListener(queues = MessagingConfig.FILA_ALERTAS)
    public void consumirAlerta(
            AlertaResponseDTO alerta
    ) {
        System.out.println(
                "Alerta recebido pela mensageria: "
                        + alerta.titulo()
                        + " | Nível: "
                        + alerta.nivel()
        );
    }
}