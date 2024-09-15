package quarkus.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "temperatures")
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
