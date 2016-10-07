package objects;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Michael
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class InfoEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String email;
    
    @OneToMany(mappedBy = "infoEntity", cascade = CascadeType.PERSIST)
    protected List<Phone> phones;
                       
    @ManyToOne(cascade = CascadeType.PERSIST)
    protected Address address;
        
    public InfoEntity(){}
    
    public InfoEntity(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void addIEToPhone(){
        for(Phone p: phones){
            p.setInfoEntity(this);
        }
    }
    
}
