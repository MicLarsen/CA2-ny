package objects;

import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author Michael
 */
@Entity
public class Company extends InfoEntity {

    private String name;
    private String description;
    private int cvr;
    private int NumEmployees;
    private long marketValue;

    public Company() {
    }

    public Company(String name, String description, int cvr, int NumEmployees, long marketValue, List<Phone> phones, String email, Address address) {
        this.name = name;
        this.description = description;
        this.cvr = cvr;
        this.NumEmployees = NumEmployees;
        this.marketValue = marketValue;
        this.phones = phones;
        this.email = email;
        this.address = address;
    }

    public Address getAddress(){
        return address;
    }
    
    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public void setAddress(Address a) {
        this.address = a;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Phone> getPhones() {
        return phones;
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

    public int getCvr() {
        return cvr;
    }

    public void setCvr(int cvr) {
        this.cvr = cvr;
    }

    public int getNumEmployees() {
        return NumEmployees;
    }

    public void setNumEmployees(int NumEmployees) {
        this.NumEmployees = NumEmployees;
    }

    public long getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(long marketValue) {
        this.marketValue = marketValue;
    }

}
