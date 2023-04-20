package example.micronaut.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Properties {
    private List<Period> periods;
}
