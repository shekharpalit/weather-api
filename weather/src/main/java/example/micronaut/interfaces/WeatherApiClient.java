package example.micronaut.interfaces;

import example.micronaut.models.ForecastResponse;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;

@Client("${weather.api.prefix}")
public interface WeatherApiClient {

    @Get("/points/{latitude},{longitude}")
    @Header(name = HttpHeaders.USER_AGENT, value = "Micronaut Weather Forecast App")
    String getGridData(@QueryValue double latitude, @QueryValue double longitude);

    @Get("/gridpoints/{gridId}/{gridX},{gridY}/forecast")
    @Header(name = HttpHeaders.USER_AGENT, value = "Micronaut Weather Forecast App")
    ForecastResponse getWeatherForecast(@QueryValue String gridId, @QueryValue int gridX, @QueryValue int gridY);
}

