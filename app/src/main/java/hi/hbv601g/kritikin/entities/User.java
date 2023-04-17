package hi.hbv601g.kritikin.entities;

import java.io.Serializable;

public class User implements Serializable {
    private String accessToken;
    private String username;

    public User() {}

    public User(String username) {
        this.username = username;
    }

    public User(String username, String accessToken) {
        this.username = username;
        this.accessToken = accessToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
