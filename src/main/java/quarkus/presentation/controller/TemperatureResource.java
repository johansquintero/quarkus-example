package quarkus.presentation.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import quarkus.Temperature;
import quarkus.service.interfaces.ITemperatureService;

import java.util.*;

@Path("/temperature")
public class TemperatureResource {
    private final ITemperatureService temperatureService;

    @Inject
    public TemperatureResource(ITemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response measurement() {
        Temperature temperature = new Temperature("Pamplona", 3, 24);
        return Response.ok(temperature).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response measurements() {
        return Response.ok(this.temperatureService.getAll()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Temperature temperature) {
        return Response.status(Response.Status.CREATED).entity(this.temperatureService.save(temperature)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Temperature update) {
        return Response.ok(this.temperatureService.update(update)).build();
    }

    @DELETE
    @Path("{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("city") String city) {
        return Response.ok()
                .entity(this.temperatureService.delete(city)).build();
    }
}
