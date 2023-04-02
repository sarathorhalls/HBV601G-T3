package hi.hbv601g.kritikin.entities;

import java.io.Serializable;

public class Question implements Serializable {
    private long id;
    private Company company;
    private User user;
    private String questionString;
    private String answerString;
    private String username;

    public Question() {

    }

    public Question(long id, Company company, User user, String questionString, String answerString) {
        this.id = id;
        this.company = company;
        this.user = user;
        this.questionString = questionString;
        this.answerString = answerString;
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

    public String getQuestionString() {
        return questionString;
    }

    public void setQuestionString(String questionString) {
        this.questionString = questionString;
    }

    public String getAnswerString() {
        return answerString;
    }

    public void setAnswerString(String answerString) {
        this.answerString = answerString;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
