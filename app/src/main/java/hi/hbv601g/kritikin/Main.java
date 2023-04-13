package hi.hbv601g.kritikin;

import android.app.Application;

import hi.hbv601g.kritikin.entities.User;

public class Main extends Application {
    private User loggedInUser;
    private String authToken;

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
