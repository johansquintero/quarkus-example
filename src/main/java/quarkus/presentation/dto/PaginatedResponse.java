package quarkus.presentation.dto;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.util.List;

public record PaginatedResponse<E>(List<E> data, int page, int totalPages) {
    public PaginatedResponse(PanacheQuery<E> query){
        this(query.list(),query.page().index, query.pageCount());
        System.out.println("llego");
    }
}
