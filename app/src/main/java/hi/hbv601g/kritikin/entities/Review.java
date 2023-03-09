package hi.hbv601g.kritikin.entities;

public class Review {
    private long id;
    private Company company;
    private User user;
    private Double starRating;
    private String reviewText;

    public Review() {

    }

    public Review(long id, Company company, User user, Double starRating, String reviewText) {
        this.id = id;
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

    public Double getStarRating() {
        return starRating;
    }

    public void setStarRating(Double starRating) {
        this.starRating = starRating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
