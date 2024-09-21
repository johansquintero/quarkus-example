package quarkus.util.mapper.temperature;

import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;
import quarkus.persistence.entity.TemperatureEntity;
import quarkus.presentation.dto.temperature.TemperatureCreationDTO;
import quarkus.presentation.dto.temperature.TemperatureDTO;

@ApplicationScoped
public class TemperatureMapperImpl implements ITemperatureMapper {

    private final ModelMapper mapper;

    public TemperatureMapperImpl() {
        this.mapper = new ModelMapper();
    }

    @Override
    public TemperatureDTO fromEntitytoDto(TemperatureEntity temperature) {
        return this.mapper.map(temperature, TemperatureDTO.class);
    }

    @Override
    public TemperatureEntity fromDtoToEntity(TemperatureDTO temperatureDTO) {
        return this.mapper.map(temperatureDTO, TemperatureEntity.class);
    }

    @Override
    public TemperatureEntity fromCreationDtoToEntity(TemperatureCreationDTO creationDTO) {
        return this.mapper.map(creationDTO, TemperatureEntity.class);
    }
}
