package quarkus.presentation.advice;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import quarkus.presentation.advice.exception.TemperatureException;

import java.util.HashMap;
import java.util.Map;

@Provider
public class TemperatureExceptionMapper implements ExceptionMapper<TemperatureException> {

    @Override
    public Response toResponse(TemperatureException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("status_code", Response.Status.NOT_ACCEPTABLE);
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity(response).build();
    }
}
