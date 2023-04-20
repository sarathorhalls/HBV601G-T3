package hi.hbv601g.kritikin.services;

import java.util.List;

import hi.hbv601g.kritikin.entities.Company;
import hi.hbv601g.kritikin.entities.Question;
import hi.hbv601g.kritikin.entities.Review;
import hi.hbv601g.kritikin.entities.User;

public interface CompanyService {
    Company createCompany(Company company);
    void removeCompany(Company company);
    List<Company> findAll();
    List<Company> findByName(String name);
    List<Review> findReviewsByCompanyId(Long id);
    List<Question> findQuestionsByCompanyId(Long id);
    void createReview(Review review);
    void createQuestion(Question question);
    Company findById(Long id);
    void redeemControlOfCompany(Long companyId, User authorizedUser);
}
