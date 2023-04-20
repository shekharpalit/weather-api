package example.micronaut.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties("weather.api")
@Requires(property = "weather.api.prefix")
public class WeatherApiConfig {
    private String baseUrl;
}