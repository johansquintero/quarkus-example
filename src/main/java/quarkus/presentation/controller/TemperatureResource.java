package quarkus.presentation.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import quarkus.TemperatureDTO;
import quarkus.service.interfaces.ITemperatureService;

@Path("/temperature")
public class TemperatureResource {
    private final ITemperatureService temperatureService;

    @Inject
    public TemperatureResource(ITemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response measurements() {
        return Response.ok(this.temperatureService.getAll()).build();
    }

    @GET
    @Path("/get-by-city/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCity(@PathParam("city") String city) {
        return Response.ok(this.temperatureService.getAllByCity(city)).build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(TemperatureDTO temperatureDTO) {
        return Response.status(Response.Status.CREATED).entity(this.temperatureService.save(temperatureDTO)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(TemperatureDTO update) {
        return Response.ok(this.temperatureService.update(update)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        return Response.ok()
                .entity(this.temperatureService.delete(id)).build();
    }
}
