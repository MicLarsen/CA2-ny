package Exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import objects.ErrorMessage;

/**
 *  Class NoPersonFoundExceptionMapper implements the class ExceptionMapper from Jax-rs with the
 * genetic type (class) NoPersonFoundException
 * @author Michael
 * @Provider registers the mapper in jax service, so the jax service knows this class is there
 */
@Provider
public class NoPersonFoundExceptionMapper implements ExceptionMapper<NoPersonFoundException>{

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    /**
     * Jax service calls the toResponse response whenever the exception 
     * 'NoPersonFoundException' is thrown. 'toResponse() is gonna pass the exception 
     * and give you a costum made response back
     * Takes in an exception 
     * @param e is the exception toResponse takes in , that was thrown, which
     * is the exception we are mapping 
     * @return a Response to the thrown exception
     */
    @Override
    public Response toResponse(NoPersonFoundException e) {

        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404 , "");
        
        return Response.status(Status.NOT_FOUND)
                .entity(gson.toJson(errorMessage))
                .build();
        
    }
    
}


   