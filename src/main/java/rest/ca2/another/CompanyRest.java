package rest.ca2.another;

import JPA.CompanyJPA;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import objects.Company;

/**
 * REST Web Service
 *
 * @author Michael
 */
@Path("company")
public class CompanyRest {

    @Context
    private UriInfo context;
    CompanyJPA cjpa;
    Gson gsonBuilder;

    /**
     * Creates a new instance of CompanyRest!!!!
     */
    public CompanyRest() {
        cjpa = new CompanyJPA();
        gsonBuilder = new GsonBuilder().create();
    }

    /**
     * Retrieves representation of an instance of rest.ca2.CompanyRest
     * @param cvr
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{cvr}")
    public Object getCompanyByCVR(@PathParam("cvr") int cvr) {
        System.out.println("helllo");
        Company c = cjpa.getCompanyByCVR(cvr);
        if (c != null) {
            return gsonBuilder.toJson(c);
        } else {
            //cast exception!!!!!
            return null;
        }
    }

    /**
     *
     * @param phoneNum
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getByPhone")
    public Object getCompanyByPhone(@PathParam("phoneNum") int phoneNum){
        Company c = cjpa.getCompanyByPhone(phoneNum);
        if(c != null){
            return gsonBuilder.toJson(c);
        } else {
            //Cast exception
            return null;
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getMoreThan")
    public Object getCopmaniesWithXOrMoreEmpl(@PathParam("numEmployees") int num){
        List<Company> companies = cjpa.getCompaniesWithXOrMoreEmpl(num);
        return gsonBuilder.toJson(companies);
    }
    
    /**
     * PUT method for updating or creating an instance of CompanyRest
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("createNew")
    public void createCompany(String content) {
        System.out.println("hej med dig");
        System.out.println(content);
        Company c = new Gson().fromJson(content, Company.class);
        cjpa.createCompany(c);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("deleteCompany")
    public Company deleteCompany(String content) {
        Company c = new Gson().fromJson(content, Company.class);
        return c;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("editCompany")
    public Company editCompany(String content) {
        Company c = new Gson().fromJson(content, Company.class);
        return c;
    }
}
