package br.com.fiap.argus.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumidorAlerta {

    @RabbitListener(
            queues = MessagingConfig.FILA_ALERTAS
    )
    public void consumir(
            String mensagem
    ) {

        log.info(
                "\nALERTA RECEBIDO DO RABBITMQ:\n{}",
                mensagem
        );

    }

}