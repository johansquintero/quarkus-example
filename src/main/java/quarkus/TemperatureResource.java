package quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/temperature")
public class TemperatureResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Temperature measurement() {
        return new Temperature("Pamplona", 3, 24);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Temperature> measurements() {
        return List.of(
                new Temperature("Pamplona", 3, 24),
                new Temperature("Cucuta", 20, 39),
                new Temperature("Bucaramanga", 16, 32)
        );
    }
}
