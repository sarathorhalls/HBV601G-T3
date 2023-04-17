package hi.hbv601g.kritikin;

import android.app.Application;

import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.PreferenceDataStore;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava2.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava2.RxDataStore;

import com.auth0.android.jwt.JWT;

import hi.hbv601g.kritikin.entities.User;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class Main extends Application {
    private User loggedInUser;
    private RxDataStore<Preferences> dataStore;
    private Preferences.Key<String> USERNAME_KEY;
    private Preferences.Key<String> AUTH_TOKEN_KEY;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize DataStore
        dataStore = new RxPreferenceDataStoreBuilder(this, /*name=*/ "auth").build();
        USERNAME_KEY = PreferencesKeys.stringKey("username");
        AUTH_TOKEN_KEY = PreferencesKeys.stringKey("auth_token");

        // Get authentication info from DataStore
        Preferences preferences = dataStore.data().blockingFirst();
        String username = preferences.get(USERNAME_KEY);
        String auth_token = preferences.get(AUTH_TOKEN_KEY);

        // If authentication info is stored in DataStore
        if (username != null && auth_token != null) {
            JWT jwt = new JWT(auth_token);
            if (!jwt.isExpired(1800)) {
                // Set user as logged in if authentication token does not expire in the next 30 minutes
                setLoggedInUser(new User(username, auth_token));
            }
        }
    }

    /**
     * Returns the currently logged in user
     * @return User object for currently logged in user
     */
    public User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Sets the currently logged in user
     * @param loggedInUser User object to set as logged in
     */
    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        // Update authentication data in DataStore
        dataStore.updateDataAsync(prefsIn -> {
            MutablePreferences mutablePreferences = prefsIn.toMutablePreferences();
            if (loggedInUser == null) {
                // If user is logged out, remove info from DataStore
                mutablePreferences.remove(USERNAME_KEY);
                mutablePreferences.remove(AUTH_TOKEN_KEY);
            } else {
                // Else, update the info with the user's details
                mutablePreferences.set(USERNAME_KEY, loggedInUser.getUsername());
                mutablePreferences.set(AUTH_TOKEN_KEY, loggedInUser.getAccess_token());
            }
            return Single.just(mutablePreferences);
        });
    }
}
