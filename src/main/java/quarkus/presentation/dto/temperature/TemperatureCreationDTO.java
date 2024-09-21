package quarkus.presentation.dto.temperature;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemperatureCreationDTO {
    @NotBlank
    private String city;
    @NotNull
    private Integer min;
    @NotNull
    private Integer max;
}
