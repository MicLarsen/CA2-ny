/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import static Exceptions.NoPersonFoundExceptionMapper.gson;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import objects.ErrorMessage;

/**
 *
 * @author nicolaicornelis
 */
@Provider
public class GenericInputExceptionMapper implements ExceptionMapper<GenericInputException> {

    @Override
    public Response toResponse(GenericInputException e) {
    ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404 , "");
        
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(gson.toJson(errorMessage))
                .build();
    
    }
    
}
