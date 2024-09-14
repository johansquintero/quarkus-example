package quarkus.service.implementation;

import jakarta.enterprise.context.ApplicationScoped;
import quarkus.Temperature;
import quarkus.service.interfaces.ITemperatureService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped

public class TemperatureServiceImpl implements ITemperatureService {
    private List<Temperature> temperatures = new ArrayList<>();
    @Override
    public List<Temperature> getAll() {
        return Collections.unmodifiableList(this.temperatures);
    }

    @Override
    public Temperature save(Temperature temperature) {
        Optional<Temperature> found = this.temperatures.stream()
                .filter(t -> t.getCity().equalsIgnoreCase(temperature.getCity()))
                .findFirst();
        if (found.isPresent()) {
            throw new RuntimeException("The temperature already exists");
        }
        this.temperatures.add(temperature);
        return temperature;
    }

    @Override
    public Temperature update(Temperature temperature) {
        Optional<Temperature> found = this.temperatures.stream()
                .filter(t -> t.getCity().equalsIgnoreCase(temperature.getCity()))
                .findFirst();
        if (found.isEmpty()) {
            this.temperatures.add(temperature);
        } else {
            this.temperatures = this.temperatures.stream()
                    .map(t -> {
                        if (t.getCity().equalsIgnoreCase(temperature.getCity())) {
                            t.setCity(temperature.getCity());
                            t.setMax(temperature.getMax());
                            t.setMin(temperature.getMin());
                        }
                        return t;
                    }).collect(Collectors.toList());
        }
        return temperature;
    }

    @Override
    public Temperature delete(String cityName) {
        Optional<Temperature> found = this.temperatures.stream()
                .filter(temperature -> temperature.getCity().equalsIgnoreCase(cityName))
                .findFirst();
        if (found.isPresent()) {
            this.temperatures = this.temperatures.stream().filter(temperature -> !temperature.getCity().equalsIgnoreCase(cityName))
                    .collect(Collectors.toList());
        }
        return found.orElse(new Temperature("dafult",1,1));
    }
}
