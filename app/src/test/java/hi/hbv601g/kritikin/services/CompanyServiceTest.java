package hi.hbv601g.kritikin.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

import hi.hbv601g.kritikin.entities.Company;
import hi.hbv601g.kritikin.entities.Review;
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
    public void JSONReviewsFromCompanyTest() {
        Company companyWithId1 = companyService.findById(1L);
        assertNotNull("Company null", companyWithId1);
        assertNotEquals("list empty", 0, companyWithId1.getReviews().size());
        assertEquals("Review text wrong", "Test", companyWithId1.getReviews().get(0).getReviewText());
    }

    @Test
    public void JSONReviewsFromReviewsTest() {
        List<Review> listOfReviews = companyService.findReviewsByCompanyId(1L);
        assertNotNull("List null", listOfReviews);
        assertNotEquals("list has no reviews", 0, listOfReviews.size());
        assertEquals("Review text wrong", "Test", listOfReviews.get(0).getReviewText());
    }
}
