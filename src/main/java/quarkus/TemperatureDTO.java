package quarkus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemperatureDTO {

    private Long id;
    private String city;
    private Integer min;
    private Integer max;
    private LocalDateTime registeredDate;
}
