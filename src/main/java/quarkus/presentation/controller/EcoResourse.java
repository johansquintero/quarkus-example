package quarkus.presentation.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

@Path("/greetings")
public class EcoResourse {

    @GET
    public String greeteing(@QueryParam("message") String message) {
        return message!=null?">\t" + message:"I don't know what to say";
    }

    @GET
    @Path("/{name}")
    public String greeteingTo(@PathParam("name") String name) {
        return "Hi "+name;
    }

}
