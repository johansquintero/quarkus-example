package quarkus.service.interfaces;

import quarkus.Temperature;

import java.util.List;

public interface ITemperatureService {
    List<Temperature> getAll();
    Temperature save(Temperature temperature);
    Temperature update(Temperature temperature);
    Temperature delete(String cityName);
}
