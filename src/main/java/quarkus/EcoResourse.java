package quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/greetings")
public class EcoResourse {

    @GET
    public String greeteing() {
        return "Hello world! ";
    }

    @GET
    @Path("/morning")
    public String morning() {
        return "Hello world, good morning! ";
    }

    @GET
    @Path("/afternoon")
    public String afternoon(){
        return "Hello world, good afternoon!";
    }

    @GET
    @Path("/night")
    public String night(){
        return "Hello world, good night!";
    }
}
