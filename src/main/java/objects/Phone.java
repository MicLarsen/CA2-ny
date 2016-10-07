package objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Michael
 */
@Entity
public class Phone {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int id;
    private int number;
    private String description;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private InfoEntity infoEntity;
      
    public Phone(){}
    
    public Phone(int number,  String description) {
        this.number = number;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getInfoEntId(){
        return infoEntity.id;
    }
    
    public void setInfoEntity(InfoEntity inf){
        this.infoEntity = inf;
    }
}
