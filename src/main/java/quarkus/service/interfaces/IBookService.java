package quarkus.service.interfaces;

import quarkus.presentation.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<BookDTO> getAll();

    BookDTO save(BookDTO bookDTO);

    BookDTO update(BookDTO bookDTO);

    String delete(Long id);
}
