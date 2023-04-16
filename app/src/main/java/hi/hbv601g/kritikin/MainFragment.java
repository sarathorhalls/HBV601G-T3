package hi.hbv601g.kritikin;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import hi.hbv601g.kritikin.entities.Company;
import hi.hbv601g.kritikin.services.CompanyService;
import hi.hbv601g.kritikin.services.implementation.CompanyServiceImplementation;

public class MainFragment extends Fragment {
    private List<Company> searchResults;
    private CompanyService companyService;
    private SearchView searchView;
    private RecyclerView searchResultRecycler;

    /**
     * Searches for companies matching query from the web service and displays the results
     * @param query Query to search for
     */
    private void searchForCompanies(String query) {
        new Thread(() -> {
            // Get company list from service and store in instance variable
            searchResults = companyService.findByName(query);
            // Display search results on UI thread
            getActivity().runOnUiThread(() -> searchResultRecycler.setAdapter(new CompanyAdapter(searchResults)));
        }).start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Get search view and results list
        searchView = (SearchView) view.findViewById(R.id.searchView);
        searchResultRecycler = (RecyclerView) view.findViewById(R.id.searchResultRecycler);

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

        return view;
    }
}
