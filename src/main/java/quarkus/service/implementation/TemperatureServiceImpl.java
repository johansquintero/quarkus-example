package quarkus.service.implementation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import quarkus.presentation.dto.temperature.TemperatureCreationDTO;
import quarkus.presentation.dto.temperature.TemperatureDTO;
import quarkus.persistence.entity.TemperatureEntity;
import quarkus.persistence.repository.TemperatureCrudRepository;
import quarkus.presentation.advice.exception.TemperatureException;
import quarkus.service.interfaces.ITemperatureService;
import quarkus.util.mapper.temperature.ITemperatureMapper;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class TemperatureServiceImpl implements ITemperatureService {
    private final TemperatureCrudRepository temperatureCrudRepository;
    private final ITemperatureMapper mapper;

    @Inject
    public TemperatureServiceImpl(TemperatureCrudRepository temperatureCrudRepository, ITemperatureMapper temperatureMapper) {
        this.temperatureCrudRepository = temperatureCrudRepository;
        this.mapper = temperatureMapper;
    }

    @Override
    public List<TemperatureDTO> getAll() {
        return this.temperatureCrudRepository.listAll().stream().map(mapper::fromEntitytoDto).toList();
    }

    @Override
    public List<TemperatureDTO> getAllByCity(String city) {
        if (city == null || city.isEmpty()) {
            return this.getAll();
        }
        return this.temperatureCrudRepository.findAllByCity(city)
                .stream()
                .map(mapper::fromEntitytoDto)
                .toList();
    }

    @Override
    public TemperatureDTO save(TemperatureCreationDTO temperatureDTO) {
        TemperatureEntity newTemperature = this.mapper.fromCreationDtoToEntity(temperatureDTO);
        this.temperatureCrudRepository.persist(newTemperature);
        return this.mapper.fromEntitytoDto(newTemperature);
    }

    @Override
    public TemperatureDTO update(TemperatureDTO temperatureDTO) {
        TemperatureEntity found = this.temperatureCrudRepository.findByIdOptional(temperatureDTO.getId()).orElseThrow(() -> {
            throw new TemperatureException("The temperatureDTO doesn't exists");
        });
        found.setCity(temperatureDTO.getCity());
        found.setMin(temperatureDTO.getMin());
        found.setMax(temperatureDTO.getMax());
        this.temperatureCrudRepository.persist(found);
        return this.mapper.fromEntitytoDto(found);
    }

    @Override
    public String delete(Long id) {
        Optional<TemperatureEntity> found = this.temperatureCrudRepository.findByIdOptional(id);
        if (found.isEmpty()) {
            throw new TemperatureException("The temperatureDTO doesn't exists");
        }
        this.temperatureCrudRepository.delete(found.get());
        return "Temperature removed successfully";
    }
}
