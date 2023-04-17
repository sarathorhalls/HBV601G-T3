package hi.hbv601g.kritikin.entities;

import java.io.Serializable;

public class Question implements Serializable {
    private long id;
    private Company company;
    private User user;
    private String questionText;
    private String answerString;
    private String username;

    public Question() {}

    public Question(long id, Company company, User user, String questionText, String answerString, String username) {
        this.id = id;
        this.company = company;
        this.user = user;
        this.questionText = questionText;
        this.answerString = answerString;
        this.username = username;
    }

    public Question(Company company, User user, String questionText) {
        this.company = company;
        this.user = user;
        this.questionText = questionText;
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

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionString(String questionText) {
        this.questionText = questionText;
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
