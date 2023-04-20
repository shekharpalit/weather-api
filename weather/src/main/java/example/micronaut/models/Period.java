package example.micronaut.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Period {
    private String name;
    private double temperature;
    private String temperatureUnit;
}
