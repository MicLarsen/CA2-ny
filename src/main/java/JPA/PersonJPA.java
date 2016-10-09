package JPA;

import RESTfacade.PersonFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import objects.Address;
import objects.CityInfo;
import objects.Hobby;
import objects.Person;
import objects.Phone;

/**
 *
 * @author Michael
 */
public class PersonJPA implements PersonFacade {

    private Person person;
    private Person aPerson;
    private List<Person> persons;
    private Phone phone;
    private List<Phone> phones;
    private CityInfo cityinfo;
    private Hobby hobby;
    private List<Hobby> hobbies;
    private Address address;
    private EntityManager em;
    private EntityManagerFactory emf;
    private List<String> hobbyList;
    private List<Object[]> PersonByHobby;
    private int id;

    public PersonJPA() {
        this.emf = Persistence.createEntityManagerFactory("CA2ny");
        this.em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @Override
    public Person getPersonById(int id) {
        try {
            Query personQuery = em.createQuery("SELECT u FROM Person u INNER JOIN InfoEntity i WHERE u.id = :id", Person.class);
            personQuery.setParameter("id", id);
            this.person = (Person) personQuery.getSingleResult();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return person;
    }

    public int getIdFromHobby(String name) {
        int hobid = 0;
        try {
            Query q = em.createQuery("SELECT u.id FROM Hobby u WHERE u.name = :name");
            q.setParameter("id", name);
            hobid = (int) q.getSingleResult();
            em.getTransaction().commit();
        } catch (Exception e) {
            //throw some SQLException
        }
        return hobid;
    }

    @Override
    public List<Person> getAllPersonWithHobby(String hobby) {
        System.out.println("hobbbbbby : " + hobby);
        try {
            
//            String q = "SELECT * FROM CA2.PERSON JOIN CA2.HOBBY JOIN CA2.PERSON_HOBBY WHERE CA2.HOBBY.NAME = ? AND CA2.PERSON_HOBBY.hobbies_ID = CA2.HOBBY.ID AND CA2.PERSON.ID = CA2.PERSON_HOBBY.persons_ID";
//            Query hobbyQuery = em.createQuery("SELECT p FROM Person p inner join p.Hobby h WHERE h.name = :hobby");
String hej = "gaming";
            Query hobbyQuery = em.createQuery("SELECT p FROM Person p join Hobby h WHERE h.name = 'gaming'", Person.class);
//            hobbyQuery.setParameter(1, "gaming");
//            hobbyQuery.setParameter(1, hobby);

            System.out.println("something1: " + hobbyQuery.getResultList().toString());

            this.persons = (List<Person>) hobbyQuery.getResultList();
//            System.out.println("hobbyId is : " + hobbies.get(0).getId());    
//            Query q = em.createQuery("SELECT p FROM Person p INNER JOIN Hobby h WHERE h.name = :hobby", Person.class);
//            q.setParameter("hobby", hobby);
//
//            this.persons = (List<Person>) q.getResultList();
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return persons;
    }

    @Override
    public List<Person> getAllPersons() {
        try {
            Query personQuery = em.createQuery("SELECT u FROM Person u", Person.class);
            this.persons = (List<Person>) personQuery.getResultList();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return persons;
    }

    @Override
    public List<String> getAllHobbies() {
        try {
            Query hobbyQuery = em.createQuery("SELECT u FROM Hobby u");

            this.hobbies = (List<Hobby>) hobbyQuery.getResultList();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        hobbyList = new ArrayList<String>();
        for (int i = 0; i < hobbies.size(); i++) {
            hobbyList.add(hobbies.get(i).getName());
        }
        return hobbyList;
    }

    @Override
    public Person getPersonByPhone(int phoneNum) {
        try {

            Query q = em.createQuery("SELECT c FROM Person c INNER JOIN Phone p WHERE p.number = :phone", Person.class);
            q.setParameter("phone", phone);

            this.person = (Person) q.getSingleResult();

            return person;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getPersonsByZip(int zipCode) {
        List<Person> somePersons;
        try {
            Query q = em.createQuery("SELECT p FROM Person p INNER JOIN Address a INNER JOIN CityInfo i WHERE i.ZIP=:zip", Person.class);
            q.setParameter("zip", zipCode);
            em.getTransaction().commit();
            somePersons = q.getResultList();

            return somePersons;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        } finally {
        }
    }

    @Override
    public int getHobbyCount(Hobby hobby) {
        int count = 0;
        try {
            Query q = em.createQuery("SELECT h.id FROM Hobby h WHERE h.name=:hobby");
            q.setParameter("hobby", hobby.getName());
            int id = (int) q.getSingleResult();
            em.getTransaction().commit();

            em.getTransaction().begin();
            Query q2 = em.createQuery("SELECT COUNT(p.id) FROM Person p INNER JOIN Hobby h WHERE h.id=:id");
            q2.setParameter("id", id);
            em.getTransaction().commit();
            count = (int) q.getSingleResult();

            return count;

        } catch (Exception e) {
            em.getTransaction().rollback();
            return count;
        } finally {
            em.close();
        }

    }

     @Override
    public Person deletePerson(int id) {
        Person personToRemove = em.find(Person.class, id);
        try {
            em.remove(personToRemove);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return personToRemove;
    }

    @Override
    public Person editPerson(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addPerson(Person person) {
        this.person = person;
        try {
            try {
                em.persist(person);
                em.getTransaction().commit();
            } catch (Exception e) {
                //throw new SQLException(e);
                return false;
            }
        } finally {
            em.close();
        }
        return true;
    }
}
