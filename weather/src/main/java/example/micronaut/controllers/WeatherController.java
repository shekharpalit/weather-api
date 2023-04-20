package example.micronaut.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.micronaut.interfaces.WeatherApiClient;
import example.micronaut.models.ForecastResponse;
import example.micronaut.models.Period;
import io.micronaut.cache.annotation.Cacheable;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import jakarta.inject.Inject;

import java.io.IOException;

@Controller("/weather")
public class WeatherController {

    private final WeatherApiClient weatherApiClient;
    private final ObjectMapper objectMapper;

    @Inject
    public WeatherController(WeatherApiClient weatherApiClient, ObjectMapper objectMapper) {
        this.weatherApiClient = weatherApiClient;
        this.objectMapper = objectMapper;
    }

    @Get("/forecast")
    @Cacheable("weather-cache")
    public String getWeatherForecast(@QueryValue double latitude,
                                     @QueryValue double longitude,
                                     @QueryValue String dayForTemperature) {
        String gridData = weatherApiClient.getGridData(latitude, longitude);
        String gridId;
        int gridX;
        int gridY;

        try {
            JsonNode rootNode = objectMapper.readTree(gridData);
            JsonNode propertiesNode = rootNode.get("properties");
            gridId = propertiesNode.get("gridId").asText();
            gridX = propertiesNode.get("gridX").asInt();
            gridY = propertiesNode.get("gridY").asInt();
        } catch (IOException e) {
            return "Error while parsing grid data";
        }

        ForecastResponse forecastResponse = weatherApiClient.getWeatherForecast(gridId, gridX, gridY);
        return extractTemperatureForDay(forecastResponse, dayForTemperature);
    }

    private String extractTemperatureForDay(ForecastResponse forecastResponse, String dayForTemperature) {
        for (Period period : forecastResponse.getProperties().getPeriods()) {
            if (period.getName().equalsIgnoreCase(dayForTemperature)) {
                return String.format("Temperature on %s : %.1f %s", dayForTemperature,
                        period.getTemperature(), period.getTemperatureUnit());
            }
        }

        return String.format("Temperature for %s not found", dayForTemperature);
    }
}
