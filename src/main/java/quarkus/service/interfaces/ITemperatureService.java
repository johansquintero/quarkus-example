package quarkus.service.interfaces;

import quarkus.TemperatureDTO;

import java.util.List;

public interface ITemperatureService {
    List<TemperatureDTO> getAll();
    TemperatureDTO save(TemperatureDTO temperatureDTO);
    TemperatureDTO update(TemperatureDTO temperatureDTO);
    String delete(Long id);
}
