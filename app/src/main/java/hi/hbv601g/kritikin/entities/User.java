package hi.hbv601g.kritikin.entities;

import java.io.Serializable;

public class User implements Serializable {
    private String access_token;
    private String username;

    public User() {}

    public User(String username) {
        this.username = username;
    }

    public User(String username, String access_token) {
        this.username = username;
        this.access_token = access_token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String access_token) {
        this.access_token = access_token;
    }
}
