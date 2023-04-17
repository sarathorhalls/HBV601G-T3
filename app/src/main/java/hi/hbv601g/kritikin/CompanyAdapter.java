package hi.hbv601g.kritikin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hi.hbv601g.kritikin.entities.Company;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {
    private final List<Company> localDataSet;
    private Context context;

    /**
     * Provides a reference to the type of views used to display each list item
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private Company company;

        public ViewHolder(View view) {
            super(view);
            context = view.getContext();
            // Define click listener for the ViewHolder's View
            view.setOnClickListener(v -> {
                Fragment companyFragment = new CompanyFragment(company);
                FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerView, companyFragment)
                           .addToBackStack("company")
                           .commit();
            });
            textView = view.findViewById(R.id.companyListItemNameText);
        }

        public TextView getTextView() {
            return textView;
        }

        public void setCompany(Company newCompany) {
            company = newCompany;
        }
    }

    /**
     * Initialize the adapter
     *
     * @param dataSet List containing the data to populate the views with
     */
    public CompanyAdapter(List<Company> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.company_list_item_view, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Company company = localDataSet.get(position);
        viewHolder.getTextView().setText(company.getName());
        viewHolder.setCompany(company);
    }

    // Return the size of the dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
