package quarkus.service.interfaces;

import quarkus.presentation.dto.BookDTO;
import quarkus.presentation.dto.PaginatedResponse;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<BookDTO> getAll();

    PaginatedResponse<BookDTO> getAllByPage(int page);

    List<BookDTO> getAllByTitle(String title);

    BookDTO save(BookDTO bookDTO);

    BookDTO update(BookDTO bookDTO);

    String delete(Long id);
}
