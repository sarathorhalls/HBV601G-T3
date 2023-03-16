package hi.hbv601g.kritikin.services.implementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hi.hbv601g.kritikin.entities.Company;
import hi.hbv601g.kritikin.entities.Question;
import hi.hbv601g.kritikin.entities.Review;
import hi.hbv601g.kritikin.services.CompanyService;
import hi.hbv601g.kritikin.services.NetworkManagerService;
import hi.hbv601g.kritikin.services.helperClasses.jsonParser;

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
            jsonCompany = networkManagerService.doGET("companies", new Object[0]);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

        try {
            JSONArray jsonArray = new JSONArray(jsonCompany);
            List<Company> companies = (List<Company>) jsonParser.toList(jsonArray);
            return companies;
        } catch (JSONException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Company> findByName(String name) {
        String jsonCompany;
        try {
            jsonCompany = networkManagerService.doGET("findCompany/" + name, new Object[0]);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

        try {
            JSONArray jsonArray = new JSONArray(jsonCompany);
            List<Company> companies = (List<Company>) jsonParser.toList(jsonArray);
            return companies;
        } catch (JSONException e) {
            System.out.println(e.getMessage());
            return null;
        }
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
            jsonCompany = networkManagerService.doGET("company/" + id, new Object[0]);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonCompany);
            Company company = (Company) jsonParser.toMap(jsonObject);
            return company;
        } catch (JSONException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
