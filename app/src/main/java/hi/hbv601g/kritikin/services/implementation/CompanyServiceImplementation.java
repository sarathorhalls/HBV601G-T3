package hi.hbv601g.kritikin.services.implementation;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import hi.hbv601g.kritikin.entities.Company;
import hi.hbv601g.kritikin.entities.Question;
import hi.hbv601g.kritikin.entities.Review;
import hi.hbv601g.kritikin.services.CompanyService;
import hi.hbv601g.kritikin.services.NetworkManagerService;
//import hi.hbv601g.kritikin.services.helperClasses.jsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        List<Company> companies = g.fromJson(jsonCompany, listType);
        return companies;
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
        List<Company> companies = g.fromJson(jsonCompany, listType);
        return companies;
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
        List<Review> reviews = g.fromJson(jsonReviews, listType);
        return reviews;
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
        List<Question> questions = g.fromJson(jsonQuestions, listType);
        return questions;
    }

    @Override
    public void createReview(Review review) {
        //TODO: implement createReview
    }

    @Override
    public void createQuestion(Question question) {
        //TODO: implement createQuestion
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
        Company company = g.fromJson(jsonCompany, Company.class);
        return company;
    }
}
