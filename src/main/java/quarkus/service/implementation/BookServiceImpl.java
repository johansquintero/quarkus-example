package quarkus.service.implementation;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import quarkus.persistence.entity.BookEntity;
import quarkus.presentation.advice.exception.BookException;
import quarkus.presentation.dto.book.BookCreateDTO;
import quarkus.presentation.dto.book.BookDTO;
import quarkus.presentation.dto.PaginatedResponse;
import quarkus.service.interfaces.IBookService;
import quarkus.util.mapper.book.IBookMapper;

import java.util.List;

@ApplicationScoped
@Transactional
public class BookServiceImpl implements IBookService {
    private final IBookMapper bookMapper;

    @Inject
    public BookServiceImpl(IBookMapper bookMapper){
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDTO> getAll() {
        System.out.println("llego");
        return BookEntity.listAll().stream().map(bookMapper::fromEntityDto).toList();
    }

    @Override
    public PaginatedResponse<BookDTO> getAllByPage(int page) {
        Page p = new Page(page,5);
        PanacheQuery<BookDTO> pagination = BookEntity.findAll(Sort.descending("createdAt")).page(p).project(BookDTO.class);

        return new PaginatedResponse<>(pagination);
    }

    @Override
    public List<BookDTO> getAllByTitle(String title) {
        Sort sort = Sort.by("publishedDate").and("title").descending();
        String filter = "%" + title + "%";
        return title != null && !title.isEmpty()
                ? BookEntity.list("title ILIKE ?1", sort, filter)
                .stream()
                .map(bookMapper::fromEntityDto).toList()
                : this.getAll();
    }

    @Override
    public BookDTO save(BookCreateDTO bookCreateDTO) {
        BookEntity bookEntity = bookMapper.fromCreationDtoToEntity(bookCreateDTO);
        bookEntity.persist();
        return this.bookMapper.fromEntityDto(bookEntity);
    }

    @Override
    public BookDTO update(BookDTO bookDTO) {
        BookEntity bookEntity = (BookEntity) BookEntity
                .findByIdOptional(bookDTO.getId()).orElseThrow(() -> new BookException("The book doesn't exists"));
        bookEntity.setTitle(bookDTO.getTitle());
        bookEntity.setAuthor(bookDTO.getAuthor());
        bookEntity.persist();
        return this.bookMapper.fromEntityDto(bookEntity);
    }

    @Override
    public String delete(Long id) {
        BookEntity bookEntity = (BookEntity) BookEntity.findByIdOptional(id).orElseThrow(() -> new BookException("The book doesn't exists"));
        bookEntity.delete();
        return "The Book ".concat(bookEntity.getTitle()).concat(" was removed");
    }

}
