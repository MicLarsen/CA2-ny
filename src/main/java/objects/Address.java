package objects;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Michael
 */
@Entity
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int id;
    private String Street;
    private String AdditionalInfo;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    private CityInfo cityInfo;
    
    @OneToMany(mappedBy = "address")
    private List<InfoEntity> infoEntity;
    
    public Address(){}
    
    public Address(String Street, String AdditionalInfo, CityInfo cityInfo) {
        this.cityInfo = cityInfo;
        this.Street = Street;
        this.AdditionalInfo = AdditionalInfo;
    }

    public int getId() {
        return id;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    public String getAdditionalInfo() {
        return AdditionalInfo;
    }

    public void setAdditionalInfo(String AdditionalInfo) {
        this.AdditionalInfo = AdditionalInfo;
    }
    
    
    
}
