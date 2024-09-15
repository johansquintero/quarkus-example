package quarkus.persistence.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import quarkus.persistence.entity.TemperatureEntity;

@ApplicationScoped
public class TemperatureCrudRepository implements PanacheRepository<TemperatureEntity> {
}
