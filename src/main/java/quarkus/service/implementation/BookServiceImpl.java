package quarkus.service.implementation;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import quarkus.persistence.entity.BookEntity;
import quarkus.presentation.advice.exception.BookException;
import quarkus.presentation.dto.BookDTO;
import quarkus.presentation.dto.PaginatedResponse;
import quarkus.service.interfaces.IBookService;

import java.util.List;

@ApplicationScoped
@Transactional
public class BookServiceImpl implements IBookService {
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<BookDTO> getAll() {
        System.out.println("llego");
        return BookEntity.listAll().stream().map(bookEntity -> modelMapper.map(bookEntity, BookDTO.class)).toList();
    }

    @Override
    public PaginatedResponse<BookDTO> getAllByPage(int page) {
        Page p = new Page(page,5);
        PanacheQuery<BookEntity> pagination = BookEntity.findAll(Sort.descending("createdAt")).page(p);
        List<BookDTO> data = pagination.list().stream()
                .map(bookEntity -> modelMapper.map(bookEntity, BookDTO.class)).toList();
        return new PaginatedResponse<>(data,pagination.page().index,pagination.pageCount());
    }

    @Override
    public List<BookDTO> getAllByTitle(String title) {
        Sort sort = Sort.by("publishedDate").and("title").descending();
        String filter = "%" + title + "%";
        return title != null && !title.isEmpty()
                ? BookEntity.list("title ILIKE ?1", sort, filter)
                .stream()
                .map(bookEntity -> modelMapper.map(bookEntity, BookDTO.class)).toList()
                : this.getAll();
    }

    @Override
    public BookDTO save(BookDTO bookDTO) {
        BookEntity bookEntity = modelMapper.map(bookDTO, BookEntity.class);
        bookEntity.persist();
        return modelMapper.map(bookEntity, BookDTO.class);
    }

    @Override
    public BookDTO update(BookDTO bookDTO) {
        BookEntity bookEntity = (BookEntity) BookEntity
                .findByIdOptional(bookDTO.getId()).orElseThrow(() -> new BookException("The book doesn't exists"));
        bookEntity.setTitle(bookDTO.getTitle());
        bookEntity.setAuthor(bookDTO.getAuthor());
        bookEntity.persist();
        return modelMapper.map(bookEntity, BookDTO.class);
    }

    @Override
    public String delete(Long id) {
        BookEntity bookEntity = (BookEntity) BookEntity.findByIdOptional(id).orElseThrow(() -> new BookException("The book doesn't exists"));
        bookEntity.delete();
        return "The Book ".concat(bookEntity.getTitle()).concat(" was removed");
    }

}
