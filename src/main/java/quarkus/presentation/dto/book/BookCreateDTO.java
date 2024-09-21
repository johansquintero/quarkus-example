package quarkus.presentation.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
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
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @Past
    private LocalDate publishedDate;
    @NotBlank
    private String genre;
}
