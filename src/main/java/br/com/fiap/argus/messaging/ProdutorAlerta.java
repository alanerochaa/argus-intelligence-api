package br.com.fiap.argus.messaging;

import br.com.fiap.argus.dto.messaging.AlertaMensagemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProdutorAlerta {

    private final RabbitTemplate rabbitTemplate;

    public void enviarAlerta(
            AlertaMensagemDTO alerta
    ) {

        rabbitTemplate.convertAndSend(
                MessagingConfig.FILA_ALERTAS,
                alerta
        );

        System.out.println(
                "\n ALERTA ENVIADO PARA RABBITMQ"
        );

        System.out.println(
                alerta
        );
    }

}