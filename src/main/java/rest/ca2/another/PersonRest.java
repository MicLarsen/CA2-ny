package rest.ca2.another;

import Exceptions.GenericInputException;
import JPA.PersonJPA;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import objects.Address;
import objects.ErrorMessage;
import objects.Hobby;
import objects.InfoEntity;
import static objects.InfoEntity_.id;
import objects.Person;
import objects.Phone;

/**
 * REST Web Service
 *
 * @author Michael
 */
@Path("person")
public class PersonRest {

    PersonJPA pjpa;
    Person person;
    Person aPerson;
    List<Person> somePersons;
    List<Object[]> hobbyPersons;
    List<Person> persons;
    List<Hobby> hobbies;
    List<Phone> phones;
    List<String> hobbyList;
    Gson gsonBuilder;
    InfoEntity ie;

    @Context
    private UriInfo context;

    public PersonRest() {
        this.ie = new InfoEntity();
        this.pjpa = new PersonJPA();
        this.hobbies = new ArrayList<>();
        this.phones = new ArrayList<>();
        this.persons = new ArrayList<>();
        this.gsonBuilder = new GsonBuilder().create();
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hobby/{hobby}")
    public Object getPersonsByHobby(@PathParam("hobby") String hobby) {
this.somePersons = pjpa.getAllPersonWithHobby(hobby);
        if (somePersons != null) {
            for (int i = 0; i < somePersons.size() ; i++) {        
                this.persons.add(new Person(somePersons.get(i).getId(), somePersons.get(i).getFirstName(),
                    somePersons.get(i).getLastName(), new Address(somePersons.get(i).getAddress().getStreet(),
                            somePersons.get(i).getAddress().getAdditionalInfo())));
            }
            return gsonBuilder.toJson(persons);
        } else {
            throw new GenericInputException(new ErrorMessage("", 500, "No person found with id " + id).toString());
        }
    }

    /**
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/hobbyList")
    public Object getPersonById() {

        this.hobbyList = pjpa.getAllHobbies();

        if (hobbyList != null) {

            return gsonBuilder.toJson(hobbyList);
        } else {
            throw new GenericInputException(new ErrorMessage("", 500, "No Hobbys was found").toString());
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Object getPersonById(@PathParam("id") int id) {

        this.aPerson = pjpa.getPersonById(id);

        if (aPerson != null) {

            this.person = new Person(aPerson.getId(), aPerson.getFirstName(),
                    aPerson.getLastName(), new Address(aPerson.getAddress().getStreet(),
                            aPerson.getAddress().getAdditionalInfo()));

            return gsonBuilder.toJson(person);
        } else {
            throw new GenericInputException(new ErrorMessage("", 500, "No person found with id " + id).toString());
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Object getAllPersons() {

        this.somePersons = pjpa.getAllPersons();
        if (somePersons != null) {

            for (int i = 0; i < somePersons.size(); i++) {

                this.persons.add(new Person(somePersons.get(i).getId(),
                        somePersons.get(i).getFirstName(), somePersons.get(i).getLastName(),
                        new Address(somePersons.get(i).getAddress().getStreet(),
                                somePersons.get(i).getAddress().getAdditionalInfo())));
            }

            return gsonBuilder.toJson(persons);
        } else {
            throw new GenericInputException(new ErrorMessage("", 500, "No person found with id " + id).toString());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getByPhone")
    public Object getPersonByPhone(@PathParam("phone") int phone) {
        Person p = pjpa.getPersonByPhone(phone);
        return gsonBuilder.toJson(p);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("add")
    public void addPerson(String newPerson) {

        Person aPerson = new Gson().fromJson(newPerson, Person.class);

        if (aPerson.getHobbies() != null) {

            for (int i = 0; i < aPerson.getHobbies().size(); i++) {

                Hobby h = aPerson.getHobbies().get(i);
                if (h.getName() == null) {
                    throw new GenericInputException("Hobby missing name property.");
                }
            }
        }

        if (aPerson.getPhones() != null) {
            for (int i = 0; i < aPerson.getPhones().size(); i++) {

                Phone ph = aPerson.getPhones().get(i);

                if (ph.getNumber() == 0) {
                    throw new GenericInputException("Phone number property missing number.");
                }

            }
        } else {
            throw new GenericInputException("Missing phone numbers array.");
        }
        if (aPerson.getFirstName() == null) {
            throw new GenericInputException("Missing first name property.");
        }
        if (aPerson.getLastName() == null) {
            throw new GenericInputException("Missing last name property.");
        }
        if (aPerson.getEmail() == null) {
            throw new GenericInputException("Missing e-mail property.");
        }
        if (aPerson.getAddress() == null) {
            throw new GenericInputException("Missing address property.");
        }

        pjpa.addPerson(aPerson);

    }

//    @DELETE
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("delete")
//    public boolean deletePerson(@QueryParam("id") int id) {
//
//        boolean isDeleted = true; //= pr.deletePerson(id);
//        if (isDeleted) {
//            return true;
//        } else {
//            //cast Exception 
//            return false;
//        }
//    }
    
       @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("delete")
    public void deletePerson(@QueryParam("id") int id) {
        Person deleted = pjpa.deletePerson(id);
    }
    

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("edit")
    public Person editPerson(@QueryParam("object") String person, @PathParam("id") int id) {

//        fp.editPerson(editedPerson);
        return null;
    }

}
