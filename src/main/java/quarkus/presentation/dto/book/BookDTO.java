package quarkus.presentation.dto.book;


import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@RegisterForReflection
public class BookDTO {
    @NotNull
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @Past
    private LocalDate publishedDate;
    @NotBlank
    private String genre;
}
