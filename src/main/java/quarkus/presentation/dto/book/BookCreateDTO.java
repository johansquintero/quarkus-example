package quarkus.presentation.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateDTO {
    private String title;
    private String author;
    private LocalDate publishedDate;
    private String genre;
}
