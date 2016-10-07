package RESTfacade;

import java.util.List;
import objects.Hobby;
import objects.Person;

/**
 *
 * @author Michael
 */
public interface PersonFacade {

    public Person getPersonById(int id);

    public Person getPersonByPhone(int phoneNum);

    public List<Person> getAllPersonWithHobby(Hobby hobby);

    public List<Person> getPersonsByZip(int zipCode);

    public int getHobbyCount(Hobby hobby);

    public Person deletePerson(int id);

    public Person editPerson(int id);

    public boolean addPerson(Person person);
}
