package quarkus.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class BookEntity extends PanacheEntity {//active record
    private String title;
    private String author;
    @Column(name = "published_date")
    private LocalDate publishedDate;
    private String genre;
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDate lastUpdate;
}
