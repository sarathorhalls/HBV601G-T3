package hi.hbv601g.kritikin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import hi.hbv601g.kritikin.entities.Company;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        RecyclerView searchResultRecycler = (RecyclerView) findViewById(R.id.searchResultRecycler);
        ArrayList<Company> searchResults = new ArrayList<>();
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
        searchResultRecycler.setAdapter(new CompanyAdapter(searchResults));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        // System.out.println(query);

        // TODO: remove test code
        /*
        Intent companyIntent = new Intent(this, CompanyActivity.class);
        companyIntent.putExtra("companyId", 1L);
        startActivity(companyIntent);
        */
    }
}