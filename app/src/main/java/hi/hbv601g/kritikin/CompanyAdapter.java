package hi.hbv601g.kritikin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hi.hbv601g.kritikin.entities.Company;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {

    private List<Company> localDataSet;
    private static Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private long companyId;

        public ViewHolder(View view) {
            super(view);
            context = view.getContext();
            // Define click listener for the ViewHolder's View
            view.setOnClickListener(v -> {
                Intent companyIntent = new Intent(context, CompanyActivity.class);
                companyIntent.putExtra("companyId", companyId);
                context.startActivity(companyIntent);
            });
            textView = (TextView) view.findViewById(R.id.companyListItemNameText);
        }

        public TextView getTextView() {
            return textView;
        }

        public void setCompanyId(long id) {
            companyId = id;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public CompanyAdapter(List<Company> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
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
        viewHolder.setCompanyId(company.getId());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}