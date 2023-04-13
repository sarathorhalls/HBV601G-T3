package hi.hbv601g.kritikin.services.implementation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;

import hi.hbv601g.kritikin.entities.Company;
import hi.hbv601g.kritikin.entities.Question;
import hi.hbv601g.kritikin.entities.Review;
import hi.hbv601g.kritikin.entities.User;
import hi.hbv601g.kritikin.services.CompanyService;
import hi.hbv601g.kritikin.services.NetworkManagerService;

public class CompanyServiceImplementation implements CompanyService {
    NetworkManagerService networkManagerService;

    public CompanyServiceImplementation() {
        networkManagerService = new NetworkManagerServiceImplementation();
    }

    @Override
    public Company createCompany(Company company) {
        //TODO: implement createCompany
        return null;
    }

    @Override
    public void removeCompany(Company company) {
        //TODO: implement removeCompany
    }

    @Override
    public List<Company> findAll() {
        String jsonCompany;
        try {
            jsonCompany = networkManagerService.doGET("/companies");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

        Gson g = new Gson();
        Type listType = new TypeToken<List<Company>>(){}.getType();
        return g.fromJson(jsonCompany, listType);
    }

    @Override
    public List<Company> findByName(String name) {
        String jsonCompany;
        try {
            jsonCompany = networkManagerService.doGET("/findCompany/" + name);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

        Gson g = new Gson();
        Type listType = new TypeToken<List<Company>>(){}.getType();
        return g.fromJson(jsonCompany, listType);
    }

    @Override
    public List<Review> findReviewsByCompanyId(Long id) {
        String jsonReviews;
        try {
            jsonReviews = networkManagerService.doGET("/company/" + id + "/reviews");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

        Gson g = new Gson();
        Type listType = new TypeToken<List<Review>>(){}.getType();
        return g.fromJson(jsonReviews, listType);
    }

    @Override
    public List<Question> findQuestionsByCompanyId(Long id) {
        String jsonQuestions;
        try {
            jsonQuestions = networkManagerService.doGET("/company/" + id + "/questions");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

        Gson g = new Gson();
        Type listType = new TypeToken<List<Question>>(){}.getType();
        return g.fromJson(jsonQuestions, listType);
    }

    @Override
    public void createReview(Review review, long companyId, User loggedInUser) {
        //Putting body into Key-Value pairs
        LinkedHashMap<String, String> reviewBody = new LinkedHashMap<>();
        reviewBody.put("starRating", Double.toString(review.getStarRating()));
        reviewBody.put("reviewText", review.getReviewText());

        //Putting header into Key-Value pair
        LinkedHashMap<String, String> authHeader = new LinkedHashMap<>();
        authHeader.put("Authorization", "Bearer " + loggedInUser.getAccess_token());

        try {
            //Send post request with review
            networkManagerService.doPOST("/company/" + companyId + "/review", reviewBody, authHeader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createQuestion(Question question, long companyId, User loggedInUser) {
        //Putting body into Key-Value pairs
        LinkedHashMap<String, String> reviewBody = new LinkedHashMap<>();
        reviewBody.put("reviewText", question.getQuestionText());

        //Putting header into Key-Value pair
        LinkedHashMap<String, String> authHeader = new LinkedHashMap<>();
        authHeader.put("Authorization", "Bearer " + loggedInUser.getAccess_token());

        try {
            //Send post request with review
            networkManagerService.doPOST("/company/" + companyId + "/question", reviewBody, authHeader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Company findById(Long id) {
        String jsonCompany;
        try {
            jsonCompany = networkManagerService.doGET("/company/" + id);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Gson g = new Gson();
        return g.fromJson(jsonCompany, Company.class);
    }
}
