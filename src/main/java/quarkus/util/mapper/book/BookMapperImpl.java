package quarkus.util.mapper.book;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;
import quarkus.persistence.entity.BookEntity;
import quarkus.presentation.dto.book.BookCreateDTO;
import quarkus.presentation.dto.book.BookDTO;

@ApplicationScoped
public class BookMapperImpl implements IBookMapper{
    private final ModelMapper mapper;
    public BookMapperImpl(){
        this.mapper = new ModelMapper();
    }

    @Override
    public BookDTO fromEntityDto(PanacheEntityBase bookEntity) {
        return this.mapper.map(bookEntity,BookDTO.class);
    }

    @Override
    public BookEntity fromDtoToEntity(BookDTO bookDTO) {
        return this.mapper.map(bookDTO,BookEntity.class);
    }

    @Override
    public BookEntity fromCreationDtoToEntity(BookCreateDTO bookCreateDTO) {
        return this.mapper.map(bookCreateDTO,BookEntity.class);
    }
}
