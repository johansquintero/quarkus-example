package quarkus.presentation.dto.book;


import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@RegisterForReflection
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private LocalDate publishedDate;
    private String genre;
}
