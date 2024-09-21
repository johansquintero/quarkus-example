package quarkus.service.interfaces;

import quarkus.presentation.dto.temperature.TemperatureCreationDTO;
import quarkus.presentation.dto.temperature.TemperatureDTO;

import java.util.List;

public interface ITemperatureService {
    List<TemperatureDTO> getAll();

    List<TemperatureDTO> getAllByCity(String city);

    TemperatureDTO save(TemperatureCreationDTO temperatureDTO);

    TemperatureDTO update(TemperatureDTO temperatureDTO);

    String delete(Long id);
}
