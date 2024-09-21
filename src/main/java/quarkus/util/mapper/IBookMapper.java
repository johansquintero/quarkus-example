package quarkus.util.mapper;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import quarkus.persistence.entity.BookEntity;
import quarkus.presentation.dto.book.BookCreateDTO;
import quarkus.presentation.dto.book.BookDTO;

public interface IBookMapper {
    BookDTO toBookDTO(PanacheEntityBase bookEntity);
    BookEntity fromBookDTOtoBookEntity(BookDTO bookDTO);
    BookEntity fromBookCreateDTOtoBookEntity(BookCreateDTO bookCreateDTO);
}
