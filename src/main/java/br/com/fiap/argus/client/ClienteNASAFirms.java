package br.com.fiap.argus.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClienteNASAFirms {

    private static final String BASE_URL =
            "https://firms.modaps.eosdis.nasa.gov/api";

    private static final String SOURCE_VIIRS_SNPP_NRT = "VIIRS_SNPP_NRT";

    // Bounding box América do Sul / Brasil
    private static final String AREA_SOUTH_AMERICA = "-85,-57,-32,14";

    private final RestTemplate restTemplate;

    @Value("${nasa.firms.map-key}")
    private String mapKey;

    public ClienteNASAFirms() {
        this.restTemplate = new RestTemplate();
    }

    public String buscarFocosCalorUltimas24Horas() {
        return buscarFocosCalorPorArea(SOURCE_VIIRS_SNPP_NRT, AREA_SOUTH_AMERICA, 1);
    }

    public String buscarFocosCalorUltimos5Dias() {
        return buscarFocosCalorPorArea(SOURCE_VIIRS_SNPP_NRT, AREA_SOUTH_AMERICA, 5);
    }

    public String buscarFocosCalorPorArea(
            String source,
            String areaCoordinates,
            int dayRange
    ) {
        validarDayRange(dayRange);

        String url = BASE_URL
                + "/area/csv/"
                + mapKey + "/"
                + source + "/"
                + areaCoordinates + "/"
                + dayRange;

        return restTemplate.getForObject(url, String.class);
    }

    public String buscarFocosCalorPorAreaEData(
            String source,
            String areaCoordinates,
            int dayRange,
            String data
    ) {
        validarDayRange(dayRange);

        String url = BASE_URL
                + "/area/csv/"
                + mapKey + "/"
                + source + "/"
                + areaCoordinates + "/"
                + dayRange + "/"
                + data;

        return restTemplate.getForObject(url, String.class);
    }

    public String buscarDisponibilidadeDados() {
        String url = BASE_URL + "/data_availability/csv/" + mapKey;

        return restTemplate.getForObject(url, String.class);
    }

    public String buscarStatusDaChave() {
        String url = BASE_URL + "/map_key_status/" + mapKey;

        return restTemplate.getForObject(url, String.class);
    }

    private void validarDayRange(int dayRange) {
        if (dayRange < 1 || dayRange > 5) {
            throw new IllegalArgumentException("O dayRange da NASA FIRMS deve ser entre 1 e 5 dias.");
        }
    }
}