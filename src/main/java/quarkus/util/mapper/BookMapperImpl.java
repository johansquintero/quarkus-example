package quarkus.util.mapper;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;
import quarkus.persistence.entity.BookEntity;
import quarkus.presentation.dto.book.BookCreateDTO;
import quarkus.presentation.dto.book.BookDTO;

@ApplicationScoped
public class BookMapperImpl implements IBookMapper{
    private ModelMapper mapper;
    public BookMapperImpl(){
        this.mapper = new ModelMapper();
    }

    @Override
    public BookDTO toBookDTO(PanacheEntityBase bookEntity) {
        return this.mapper.map(bookEntity,BookDTO.class);
    }

    @Override
    public BookEntity fromBookDTOtoBookEntity(BookDTO bookDTO) {
        return this.mapper.map(bookDTO,BookEntity.class);
    }

    @Override
    public BookEntity fromBookCreateDTOtoBookEntity(BookCreateDTO bookCreateDTO) {
        return this.mapper.map(bookCreateDTO,BookEntity.class);
    }
}
