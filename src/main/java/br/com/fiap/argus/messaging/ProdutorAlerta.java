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

        String mensagem =
                """
                =====================================
                 ALERTA AMBIENTAL - ARGUS
                =====================================

                ID................: %d
                TÍTULO............: %s
                NÍVEL.............: %s
                STATUS............: %s
                SCORE DE RISCO....: %.1f

                RECOMENDAÇÃO:
                %s

                FOCO DE CALOR.....: %d

                =====================================
                """
                        .formatted(
                                alerta.id(),
                                alerta.titulo(),
                                alerta.nivel(),
                                alerta.status(),
                                alerta.scoreRisco(),
                                alerta.recomendacaoOperacional(),
                                alerta.focoCalorId()
                        );

        rabbitTemplate.convertAndSend(
                MessagingConfig.FILA_ALERTAS,
                mensagem
        );

        System.out.println(
                "\n Mensagem enviada para RabbitMQ:"
        );

        System.out.println(
                mensagem
        );
    }

}