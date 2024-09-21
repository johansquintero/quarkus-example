package quarkus.util.mapper.book;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import quarkus.persistence.entity.BookEntity;
import quarkus.presentation.dto.book.BookCreateDTO;
import quarkus.presentation.dto.book.BookDTO;

public interface IBookMapper {
    BookDTO fromEntityDto(PanacheEntityBase bookEntity);
    BookEntity fromDtoToEntity(BookDTO bookDTO);
    BookEntity fromCreationDtoToEntity(BookCreateDTO bookCreateDTO);
}
