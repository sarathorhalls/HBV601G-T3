package hi.hbv601g.kritikin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import hi.hbv601g.kritikin.entities.User;

public class MainActivity extends AppCompatActivity {
    private Main app;
    private Toolbar actionBar;

    /**
     * Updates the UI in accordance with the user's current login state
     */
    public void updateLoginState() {
        // Get menu UI items
        MenuItem loginAction = (MenuItem) actionBar.getMenu().findItem(R.id.loginAction);
        MenuItem logoutAction = (MenuItem) actionBar.getMenu().findItem(R.id.logoutAction);

        User user = app.getLoggedInUser();
        if (user == null) {
            // If not logged in, display log in action
            loginAction.setVisible(true);
            logoutAction.setVisible(false);
        } else {
            // If logged in, set log out action title to include username
            String logoutActionTitle = String.format(getString(R.string.logout_button_text), user.getUsername());
            logoutAction.setTitle(logoutActionTitle);

            // Display log out action
            loginAction.setVisible(false);
            logoutAction.setVisible(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.loginAction) {
            Fragment loginFragment = new LoginFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainerView, loginFragment)
                    .addToBackStack("login")
                    .commit();
            return true;
        } else if (id == R.id.logoutAction) {
            // Save username
            String username = app.getLoggedInUser().getUsername();
            // Log out
            app.setLoggedInUser(null);
            updateLoginState();
            // Display toast message
            String message = String.format(getString(R.string.logged_out_text), username);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up action bar
        actionBar = (Toolbar) findViewById(R.id.actionBar);
        setSupportActionBar(actionBar);

        // Get the main class instance for getting login info
        app = (Main) getApplication();
    }
}