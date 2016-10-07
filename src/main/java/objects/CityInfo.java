package objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Michael
 */
@Entity
@Table(name = "CITYINFO")
public class CityInfo {

    @Id
    @Column(name = "ZIP")
    private int ZIP;
    private String CITY;

    public CityInfo() {
    }

    public CityInfo(int zipCode, String city) {
        this.ZIP = zipCode;
        this.CITY = city;
    }

    public int getZipCode() {
        return ZIP;
    }

    public String getCity() {
        return CITY;
    }
}
