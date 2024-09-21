package quarkus.presentation.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import quarkus.presentation.dto.book.BookCreateDTO;
import quarkus.presentation.dto.book.BookDTO;
import quarkus.service.interfaces.IBookService;

@Path("/book")
public class BookResource {
    @Inject
    private IBookService bookService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.status(Response.Status.OK).entity(this.bookService.getAll()).build();
    }

    @GET
    @Path("/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@PathParam("page") @DefaultValue("0") int page) {
        return Response.status(Response.Status.OK).entity(this.bookService.getAllByPage(page)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(BookCreateDTO bookCreateDTO) {
        return Response.status(Response.Status.CREATED).entity(this.bookService.save(bookCreateDTO)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(BookDTO bookDTO) {
        return Response.status(Response.Status.CREATED).entity(this.bookService.update(bookDTO)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        return Response.status(Response.Status.OK).entity(this.bookService.delete(id)).build();
    }

    @GET
    @Path("/search-by-title/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllByTitle(@PathParam("title") String title) {
        return Response.status(Response.Status.OK).entity(this.bookService.getAllByTitle(title)).build();
    }
}

