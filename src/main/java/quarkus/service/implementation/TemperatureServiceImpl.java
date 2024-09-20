package quarkus.service.implementation;

import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import quarkus.TemperatureDTO;
import quarkus.persistence.entity.TemperatureEntity;
import quarkus.persistence.repository.TemperatureCrudRepository;
import quarkus.presentation.advice.exception.TemperatureException;
import quarkus.service.interfaces.ITemperatureService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class TemperatureServiceImpl implements ITemperatureService {
    private final TemperatureCrudRepository temperatureCrudRepository;
    private final ModelMapper modelMapper;

    @Inject
    public TemperatureServiceImpl(TemperatureCrudRepository temperatureCrudRepository) {
        this.temperatureCrudRepository = temperatureCrudRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<TemperatureDTO> getAll() {
        return this.temperatureCrudRepository.listAll().stream().map(temperatureEntity -> {
            return modelMapper.map(temperatureEntity, TemperatureDTO.class);
        }).toList();
    }

    @Override
    public List<TemperatureDTO> getAllByCity(String city) {
        if (city==null || city.isEmpty()){
            return this.getAll();
        }
        return this.temperatureCrudRepository.findAllByCity(city)
                .stream()
                .map(temperatureEntity -> modelMapper.map(temperatureEntity, TemperatureDTO.class))
                .toList();
    }

    @Override
    public TemperatureDTO save(TemperatureDTO temperatureDTO) {
//        Optional<TemperatureEntity> found = this.temperatures.stream()
//                .filter(t -> t.getCity().equalsIgnoreCase(temperatureDTO.getCity()))
//                .findFirst();
//        if (found.isPresent()) {
//            throw new TemperatureException("The temperatureDTO already exists");
//        }
        temperatureDTO.setRegisteredDate(LocalDateTime.now());
        TemperatureEntity newTemperature = modelMapper.map(temperatureDTO, TemperatureEntity.class);
        this.temperatureCrudRepository.persist(newTemperature);
        return this.modelMapper.map(newTemperature, TemperatureDTO.class);
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
        return modelMapper.map(found, TemperatureDTO.class);
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
