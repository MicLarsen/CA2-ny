package JPA;

import RESTfacade.OtherInfoFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import objects.CityInfo;

/**
 *
 * @author Michael
 */
public class OtherInfoJPA implements OtherInfoFacade {

    EntityManager em;
    List<CityInfo> cityInfoList;

    public OtherInfoJPA() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2ny");
        this.em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @Override
    public List<CityInfo> getCityInfo() {
        
        this.cityInfoList = new ArrayList<CityInfo>();
        try {

            Query cityQuery = em.createQuery("SELECT u FROM CityInfo u");
            cityInfoList = cityQuery.getResultList();
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return cityInfoList;
    }

}
