package br.com.fiap.argus.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClienteWeather {

    private final RestTemplate restTemplate;

    @Value("${weather.api.url:https://api.open-meteo.com/v1/forecast}")
    private String weatherApiUrl;

    public ClienteWeather() {
        this.restTemplate = new RestTemplate();
    }

    public String buscarClima(
            Double latitude,
            Double longitude
    ) {

        String url =
                weatherApiUrl
                        + "?latitude=" + latitude
                        + "&longitude=" + longitude
                        + "&current=temperature_2m,relative_humidity_2m,wind_speed_10m";

        return restTemplate.getForObject(
                url,
                String.class
        );
    }

    public String buscarClimaSaoPaulo() {

        return buscarClima(
                -23.5505,
                -46.6333
        );
    }
}