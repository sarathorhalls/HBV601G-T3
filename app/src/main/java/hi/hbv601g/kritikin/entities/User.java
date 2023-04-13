package hi.hbv601g.kritikin.entities;

import java.io.Serializable;

public class User implements Serializable {
    private String authToken;
    private String username;

    public User() {

    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String authToken) {
        this.username = username;
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
