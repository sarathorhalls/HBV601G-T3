package hi.hbv601g.kritikin;

import android.app.Application;

import hi.hbv601g.kritikin.entities.User;

public class Main extends Application {
    private User loggedInUser;

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
