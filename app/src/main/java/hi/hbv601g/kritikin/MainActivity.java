package hi.hbv601g.kritikin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import hi.hbv601g.kritikin.entities.Company;
import hi.hbv601g.kritikin.entities.User;
import hi.hbv601g.kritikin.services.CompanyService;
import hi.hbv601g.kritikin.services.implementation.CompanyServiceImplementation;

public class MainActivity extends AppCompatActivity {
    private Main app;
    private List<Company> searchResults;
    private CompanyService companyService;
    private SearchView searchView;
    private RecyclerView searchResultRecycler;
    private Button loginButton;
    private Button logoutButton;

    /**
     * Searches for companies matching query from the web service and displays the results
     * @param query Query to search for
     */
    private void searchForCompanies(String query) {
        new Thread(() -> {
            // Get company list from service and store in instance variable
            searchResults = companyService.findByName(query);
            // Display search results on UI thread
            runOnUiThread(() -> searchResultRecycler.setAdapter(new CompanyAdapter(searchResults)));
        }).start();
    }

    /**
     * Updates the UI in accordance with the user's current login state
     */
    private void updateLoginState() {
        User user = app.getLoggedInUser();
        if (user == null) {
            loginButton.setVisibility(View.VISIBLE);
            logoutButton.setVisibility(View.GONE);
        } else {
            loginButton.setVisibility(View.GONE);
            logoutButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateLoginState();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get search view and results list
        searchView = (SearchView) findViewById(R.id.searchView);
        searchResultRecycler = (RecyclerView) findViewById(R.id.searchResultRecycler);

        // Set empty adapter for recycler view to work
        searchResultRecycler.setAdapter(new CompanyAdapter(new ArrayList<>()));

        // Handle search
        companyService = new CompanyServiceImplementation();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchForCompanies(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (searchResults != null) {
                    searchResults.clear();
                }
                return false;
            }
        });

        // Get the main class instance for getting login info
        app = ((Main) getApplication());

        // Activate login button
        loginButton = (Button) findViewById(R.id.showLoginActivityButton);
        loginButton.setOnClickListener(v -> {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        });

        // Activate logout button
        logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(v -> {
            app.setLoggedInUser(null);
            updateLoginState();
        });
    }
}
