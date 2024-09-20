package quarkus.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "temperatures")

@FilterDef(name = "city.like", parameters = @ParamDef(name = "city", type = String.class))
@Filter(name = "city.like", condition = "lower(city) LIKE lower(:city)")

public class TemperatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private Integer max;
    private Integer min;
    @Column(name = "registered_date")
    private LocalDateTime registeredDate;
}
