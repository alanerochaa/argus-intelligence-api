package br.com.fiap.argus.messaging;

import br.com.fiap.argus.dto.response.AlertaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProdutorAlerta {

    private final RabbitTemplate rabbitTemplate;

    public void enviarAlerta(
            AlertaResponseDTO alerta
    ) {
        rabbitTemplate.convertAndSend(
                MessagingConfig.FILA_ALERTAS,
                alerta
        );
    }
}