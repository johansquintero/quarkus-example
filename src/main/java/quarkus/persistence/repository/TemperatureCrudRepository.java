package quarkus.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import quarkus.persistence.entity.TemperatureEntity;

import java.util.List;

@ApplicationScoped
public class TemperatureCrudRepository implements PanacheRepository<TemperatureEntity> {
    public List<TemperatureEntity> findAllByCity(String city) {
        String cityLike = "%" + city + "%";
        return findAll().filter("city.like", Parameters.with("city", cityLike)).list();
    }
}
