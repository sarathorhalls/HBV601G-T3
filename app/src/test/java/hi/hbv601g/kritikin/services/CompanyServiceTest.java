package hi.hbv601g.kritikin.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import hi.hbv601g.kritikin.entities.Company;
import hi.hbv601g.kritikin.services.implementation.CompanyServiceImplementation;

public class CompanyServiceTest {
    CompanyService companyService;

    @Before
    public void createCompanyService() {
        companyService = new CompanyServiceImplementation();
    }

    @Test
    public void testFindById() {
        Company companyWithId1 = companyService.findById(1L);
        assertNotNull("Company null", companyWithId1);
        assertEquals("Name not matching", "Test", companyWithId1.getName());
    }

    @Test
    public void JSONReviewsTest() {
        Company companyWithId1 = companyService.findById(1L);
        assertNotNull("Company null", companyWithId1);
        assertNotEquals("list empty", 0, companyWithId1.getReviews().size());
    }
}
