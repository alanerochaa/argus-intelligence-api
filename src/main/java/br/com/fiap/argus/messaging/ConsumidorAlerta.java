//package br.com.fiap.argus.messaging;
//
//import br.com.fiap.argus.dto.messaging.AlertaMensagemDTO;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class ConsumidorAlerta {
//
//    @RabbitListener(
//            queues = MessagingConfig.FILA_ALERTAS
//    )
//    public void consumir(
//            AlertaMensagemDTO alerta
//    ) {
//
//        log.info(
//                """
//
//                =====================================
//                 ALERTA RECEBIDO - ARGUS
//                =====================================
//
//                ID................: {}
//                TÍTULO............: {}
//                NÍVEL.............: {}
//                SCORE.............: {}
//
//                REGIÃO............: {}
//
//                LATITUDE..........: {}
//                LONGITUDE.........: {}
//
//                SATÉLITE..........: {}
//
//                =====================================
//
//                """,
//
//                alerta.id(),
//                alerta.titulo(),
//                alerta.nivel(),
//                alerta.scoreRisco(),
//
//                alerta.regiaoNome(),
//
//                alerta.latitude(),
//                alerta.longitude(),
//
//                alerta.satelite()
//        );
//
//    }
//
//}