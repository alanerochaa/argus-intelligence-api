package br.com.fiap.argus.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    public static final String FILA_ALERTAS =
            "argus.alertas";

    @Bean
    public Queue filaAlertas() {

        return new Queue(
                FILA_ALERTAS,
                true
        );

    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {

        return new Jackson2JsonMessageConverter();

    }

}