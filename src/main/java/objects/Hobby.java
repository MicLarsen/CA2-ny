package objects;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Michael
 */
@Entity
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    
    private String name;
    private String description;
    
    @ManyToMany(mappedBy = "hobbies")
    private List<Person> persons;

    public Hobby(){}

    public Hobby(String name , String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
