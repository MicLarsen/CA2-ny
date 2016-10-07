package RESTfacade;

import java.util.List;
import objects.Company;

/**
 *
 * @author Michael
 */
public interface CompanyFacade {

    public Company getCompanyByCVR(int cvr);

    public Company getCompanyByPhone(int phone);

    public List<Company> getCompaniesWithXOrMoreEmpl(int num);

    public boolean deleteCompany(int cvr);

    public boolean editCompany(Company comp);

    public boolean createCompany(Company comp);

}
