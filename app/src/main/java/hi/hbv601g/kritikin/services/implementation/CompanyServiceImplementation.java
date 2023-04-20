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
import okhttp3.Response;

public class CompanyServiceImplementation implements CompanyService {
    final NetworkManagerService networkManagerService;

    public CompanyServiceImplementation() {
        networkManagerService = new NetworkManagerServiceImplementation();
    }

    @Override
    public Company createCompany(Company company) {
        // TODO: implement createCompany
        return null;
    }

    @Override
    public void removeCompany(Company company) {
        // TODO: implement removeCompany
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
    public void createReview(Review review) {
        // Put review into body key/value pairs
        LinkedHashMap<String, String> reviewBody = new LinkedHashMap<>();
        reviewBody.put("starRating", Double.toString(review.getStarRating()));
        reviewBody.put("reviewText", review.getReviewText());

        // Put access token into header key/value pair
        LinkedHashMap<String, String> authHeader = new LinkedHashMap<>();
        authHeader.put("Authorization", "Bearer " + review.getUser().getAccessToken());

        try {
            // Submit review via POST
            Response response = networkManagerService.doPOSTResponse("/company/" + review.getCompany().getId() + "/review", reviewBody, authHeader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createQuestion(Question question) {
        // Put question into body key/value pair
        LinkedHashMap<String, String> reviewBody = new LinkedHashMap<>();
        reviewBody.put("questionText", question.getQuestionText());

        // Put access token into header key/value pair
        LinkedHashMap<String, String> authHeader = new LinkedHashMap<>();
        authHeader.put("Authorization", "Bearer " + question.getUser().getAccessToken());

        try {
            // Submit question via POST
            networkManagerService.doPOST("/company/" + question.getCompany().getId() + "/question", reviewBody, authHeader);
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

    @Override
    public void redeemControlOfCompany(Long companyId, User authorizedUser) {
        // Put access token into header key/value pair
        LinkedHashMap<String, String> authHeader = new LinkedHashMap<>();
        authHeader.put("Authorization", "Bearer " + authorizedUser.getAccessToken());
        try {
            networkManagerService.doPOST("/companyUser/" + companyId, null, authHeader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
