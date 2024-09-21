package quarkus.util.mapper.temperature;

import quarkus.persistence.entity.TemperatureEntity;
import quarkus.presentation.dto.temperature.TemperatureCreationDTO;
import quarkus.presentation.dto.temperature.TemperatureDTO;

public interface ITemperatureMapper {
    TemperatureDTO fromEntitytoDto(TemperatureEntity temperature);

    TemperatureEntity fromDtoToEntity(TemperatureDTO temperatureDTO);

    TemperatureEntity fromCreationDtoToEntity(TemperatureCreationDTO creationDTO);
}
