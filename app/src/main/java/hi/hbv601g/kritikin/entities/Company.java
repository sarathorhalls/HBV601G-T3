package hi.hbv601g.kritikin.entities;

import java.io.Serializable;
import java.util.List;

public class Company implements Serializable {
    private long id;
    private String name;
    private double starRating;
    private String website;
    private int phoneNumber;
    private String description;
    private String address;
    private String openingHours;

    private List<Review> reviews;
    private List<Question> questions;

    public Company() {}

    public Company(long id, String name, double starRating, String website, int phoneNumber,
                   String description, String address, String openingHours, List<Review> reviews,
                   List<Question> questions) {
        this.id = id;
        this.name = name;
        this.starRating = starRating;
        this.website = website;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.address = address;
        this.openingHours = openingHours;
        this.reviews = reviews;
        this.questions = questions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStarRating() {
        return starRating;
    }

    public void setStarRating(double starRating) {
        this.starRating = starRating;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
