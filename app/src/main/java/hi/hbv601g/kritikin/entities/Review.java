package hi.hbv601g.kritikin.entities;

import java.io.Serializable;

public class Review implements Serializable {
    private long id;
    private Company company;
    private User user;
    private double starRating;
    private String reviewText;
    private String username;

    public Review() {

    }

    public Review(long id, Company company, User user, double starRating, String reviewText, String username) {
        this.id = id;
        this.company = company;
        this.user = user;
        this.starRating = starRating;
        this.reviewText = reviewText;
        this.username = username;
    }

    public Review(Company company, User user, double starRating, String reviewText) {
        this.company = company;
        this.user = user;
        this.starRating = starRating;
        this.reviewText = reviewText;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getStarRating() {
        return starRating;
    }

    public void setStarRating(double starRating) {
        this.starRating = starRating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
