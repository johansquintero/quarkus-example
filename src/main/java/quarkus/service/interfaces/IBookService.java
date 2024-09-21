package quarkus.service.interfaces;

import quarkus.presentation.dto.book.BookCreateDTO;
import quarkus.presentation.dto.book.BookDTO;
import quarkus.presentation.dto.PaginatedResponse;

import java.util.List;

public interface IBookService {
    List<BookDTO> getAll();

    PaginatedResponse<BookDTO> getAllByPage(int page);

    List<BookDTO> getAllByTitle(String title);

    BookDTO save(BookCreateDTO bookCreateDTO);

    BookDTO update(BookDTO bookDTO);

    String delete(Long id);
}
