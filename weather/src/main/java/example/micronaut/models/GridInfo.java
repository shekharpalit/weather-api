package example.micronaut.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GridInfo {
    private String gridId;
    private int gridX;
    private int gridY;
}
