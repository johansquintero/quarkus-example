package quarkus.service.implementation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import quarkus.persistence.entity.BookEntity;
import quarkus.presentation.advice.exception.BookException;
import quarkus.presentation.dto.BookDTO;
import quarkus.service.interfaces.IBookService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class BookServiceImpl implements IBookService {
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<BookDTO> getAll() {
        return BookEntity.listAll().stream().map(bookEntity -> modelMapper.map(bookEntity, BookDTO.class)).toList();
    }

    @Override
    public BookDTO save(BookDTO bookDTO) {
        BookEntity bookEntity = modelMapper.map(bookDTO, BookEntity.class);
        bookEntity.setPublishedDate(LocalDate.now());
        bookEntity.persist();
        return modelMapper.map(bookEntity, BookDTO.class);
    }

    @Override
    public BookDTO update(BookDTO bookDTO) {
        BookEntity bookEntity = (BookEntity) BookEntity
                .findByIdOptional(bookDTO.getId()).orElseThrow(()-> new BookException("The book doesn't exists"));
        bookEntity.setTitle(bookDTO.getTitle());
        bookEntity.setAuthor(bookDTO.getAuthor());
        bookEntity.persist();
        return modelMapper.map(bookEntity, BookDTO.class);
    }

    @Override
    public String delete(Long id) {
        BookEntity bookEntity = (BookEntity) BookEntity.findByIdOptional(id).orElseThrow(()-> new BookException("The book doesn't exists"));
        bookEntity.delete();
        return "The Book ".concat(bookEntity.getTitle()).concat(" was removed");
    }
}
