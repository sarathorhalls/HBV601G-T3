package hi.hbv601g.kritikin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import java.util.ArrayList;

import hi.hbv601g.kritikin.entities.Company;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get search view and results list
        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        RecyclerView searchResultRecycler = (RecyclerView) findViewById(R.id.searchResultRecycler);

        // Set search results adapter
        ArrayList<Company> searchResults = new ArrayList<>();
        searchResultRecycler.setAdapter(new CompanyAdapter(searchResults));

        // Handle search
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // TODO: implement actual search
                searchResults.add(new Company(
                        5L,
                        "Test Company",
                        4.5,
                        "https://example.org",
                        5555555,  // FIXME: phone number should not be an integer
                        "This is a company description",
                        "Hagatorg 1",
                        "10:00–12:00",
                        null,
                        null
                ));
                searchResults.add(new Company(
                        6L,
                        "Test Company 2",
                        3.0,
                        "https://example.org",
                        5555555,  // FIXME: phone number should not be an integer
                        "This is a company description",
                        "Hagatorg 1",
                        "10:00–12:00",
                        null,
                        null
                ));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }
}