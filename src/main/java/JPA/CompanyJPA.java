package JPA;

import RESTfacade.CompanyFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import objects.Company;

/**
 *
 * @author Michael
 */
public class CompanyJPA implements CompanyFacade {

    EntityManagerFactory emf;

    public CompanyJPA() {
        emf = Persistence.createEntityManagerFactory("CA2ny");
    }

    @Override
    public Company getCompanyByCVR(int cvr) {
        EntityManager em = emf.createEntityManager();
        Company c;
        try {

            em.getTransaction().begin();
            Query q = em.createQuery("SELECT c FROM Company c WHERE c.cvr = :cvr", Company.class);
            q.setParameter("cvr", cvr);

            em.getTransaction().commit();
            c = (Company) q.getSingleResult();

            return c;

        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Company getCompanyByPhone(int phone) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT c FROM Company c INNER JOIN Phone p WHERE p.number=:phone", Company.class);
            q.setParameter("phone", phone);
            em.getTransaction().commit();

            List<Company> companies = q.getResultList();
            Company c = companies.get(0);

            return c;
        } catch (Exception e) {
            em.getTransaction().rollback();
            //return exception!
            return null;
        } finally{
            em.close();
        }

    }

    @Override
    public List<Company> getCompaniesWithXOrMoreEmpl(int num){
        EntityManager em = emf.createEntityManager();
        List<Company> companies;
        try {
            Query q = em.createQuery("SELECT c FROM Company c WHERE c.NumEmployees >= :num", Company.class);
            q.setParameter("num", num);
            
            companies = q.getResultList();
            
            
            return companies;
        } catch (Exception e) {
            em.getTransaction().rollback();
            //Return exception!
            return null;
        } finally {
            em.close();
        }
    }
    
    @Override
    public boolean deleteCompany(int cvr) {
        EntityManager em = emf.createEntityManager();
        try {
            Company c = em.find(Company.class, cvr);
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            //Return exception!
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean editCompany(Company comp) {
        EntityManager em = emf.createEntityManager();
        try {
            Company c = em.find(Company.class, comp.getCvr());
            em.getTransaction().begin();
            c.setCvr(comp.getCvr());
            c.setDescription(comp.getDescription());
            c.setMarketValue(comp.getMarketValue());
            c.setName(comp.getName());
            c.setNumEmployees(comp.getNumEmployees());
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            //return exception !!!
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean createCompany(Company comp) {
        EntityManager em = emf.createEntityManager();
        try {

            em.getTransaction().begin();
            em.persist(comp);
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }

    }

}
