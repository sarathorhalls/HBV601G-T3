package hi.hbv601g.kritikin.services;

import java.util.List;

import hi.hbv601g.kritikin.entities.Company;
import hi.hbv601g.kritikin.entities.Question;
import hi.hbv601g.kritikin.entities.Review;

public interface CompanyService {
    public Company createCompany(Company company);
    public void removeCompany(Company company);
    public List<Company> findAll();
    public List<Company> findByName(String name);
    public void createReview(Review review);
    public void createQuestion(Question question);
}
