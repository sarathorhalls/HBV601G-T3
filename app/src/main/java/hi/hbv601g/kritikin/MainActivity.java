package hi.hbv601g.kritikin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import hi.hbv601g.kritikin.entities.Company;
import hi.hbv601g.kritikin.services.CompanyService;
import hi.hbv601g.kritikin.services.implementation.CompanyServiceImplementation;

public class MainActivity extends AppCompatActivity {
    private List<Company> searchResults;
    private CompanyService companyService;
    private SearchView searchView;
    private RecyclerView searchResultRecycler;
    private Button loginButton;

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

        // Activate log in button
        loginButton = (Button) findViewById(R.id.showLoginActivityButton);
        loginButton.setOnClickListener(v -> {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        });
    }
}
