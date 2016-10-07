package Exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import objects.ErrorMessage;

/**
 *
 * @author Michael
 */
/**
 * Every instance is a throwable , so no matter what exception is called this,
 * will if the thrown exception is not mapped be called.
 * The hierachy is: if the exception thrown is mapped it will call it
 * , else if there is no explecit mapping for the exception this will be called.
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{
    
    static Gson gson = new GsonBuilder().create();

    @Override
    public Response toResponse(Throwable e) {
        
        System.out.println(e.getClass());
        e.printStackTrace();
         ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 500 , "");
        
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(gson.toJson(errorMessage))
                .build();
    }
}
