package hi.hbv601g.kritikin.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

import hi.hbv601g.kritikin.entities.Company;
import hi.hbv601g.kritikin.entities.Question;
import hi.hbv601g.kritikin.entities.Review;
import hi.hbv601g.kritikin.entities.User;
import hi.hbv601g.kritikin.services.implementation.CompanyServiceImplementation;
import hi.hbv601g.kritikin.services.implementation.UserServiceImplementation;

public class CompanyServiceTest {
    CompanyService companyService;
    User loggedInUser;

    @Before
    public void createCompanyServiceAndUser() {
        companyService = new CompanyServiceImplementation();
        loggedInUser = (new UserServiceImplementation()).login("test", "test");
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
        Review firstReview = listOfReviews.get(0);
        assertEquals("Review text wrong", "Test", firstReview.getReviewText());
        assertEquals("Username wrong", "test", firstReview.getUsername());
    }

    @Test
    public void JSONQuestionsFromQuestionsTest() {
        List<Question> listOfQuestions = companyService.findQuestionsByCompanyId(1L);
        assertNotNull("List null", listOfQuestions);
        assertNotEquals("List has no questions", 0, listOfQuestions.size());
        Question firstQuestion = listOfQuestions.get(0);
        assertEquals("Question text wrong", "Test", firstQuestion.getQuestionText());
        assertEquals("Username wrong", "test", firstQuestion.getUsername());
    }

    @Test
    public void addReviewToCompanyId2Test() {
        Company testCompany = new Company();
        testCompany.setId(2L);
        Review testReview = new Review(testCompany, loggedInUser, 3.5, "Test review from unit tests");
        companyService.createReview(testReview);
    }

    @Test
    public void addQuestionToCompanyId2Test() {
        Company testCompany = new Company();
        testCompany.setId(2L);
        Question question = new Question(testCompany, loggedInUser, "Test Question from unit tests");
        companyService.createQuestion(question);
        
    }
}
