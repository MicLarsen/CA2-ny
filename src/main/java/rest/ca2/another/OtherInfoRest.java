package rest.ca2.another;

import JPA.OtherInfoJPA;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import objects.CityInfo;

/**
 * REST Web Service
 *
 * @author Michael
 */
@Path("otherInfo")
public class OtherInfoRest {

    List<CityInfo> cityInfo;
    Gson gsonBuilder;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of OtherInfoRest
     */
    public OtherInfoRest() {
        this.gsonBuilder = new GsonBuilder().create();
    }

    /**
     * Retrieves representation of an instance of JPA.OtherInfoRest
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object getJson() {
        OtherInfoJPA o = new OtherInfoJPA();

        this.cityInfo = o.getCityInfo();
        return this.gsonBuilder.toJson(cityInfo);
    }
    
    /**
     * Retrieves representation of an instance of rest.ca2.CityinfoRest
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("city")
    public Object getCityinfo() {

        Object returnObject = null;

//        cityInfo = getCityInfo();
        for (CityInfo i : cityInfo) {
            if (i.getCity() == null || Integer.toString(i.getZipCode()) == null) {
                //throw new NotFoundException(city)
            }
        }
        if (cityInfo != null) {

            Gson gson = new GsonBuilder().create();

            returnObject = gson.toJson(cityInfo);

        } else {

            //throw new NotFoundException(city);x
        }
        return returnObject;
    }
}
