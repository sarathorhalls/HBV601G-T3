package hi.hbv601g.kritikin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hi.hbv601g.kritikin.entities.Review;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private List<Review> localDataSet;

    /**
     * Provides a reference to the type of views used to display each list item
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView usernameText;
        private final RatingBar reviewRatingBar;
        private final TextView reviewText;

        public ViewHolder(View view) {
            super(view);
            usernameText = (TextView) view.findViewById(R.id.questionUsernameText);
            reviewRatingBar = (RatingBar) view.findViewById(R.id.reviewRatingBar);
            reviewText = (TextView) view.findViewById(R.id.questionText);
        }

        public TextView getUsernameTextView() {
            return usernameText;
        }

        public RatingBar getReviewRatingBar() {
            return reviewRatingBar;
        }

        public TextView getReviewTextView() {
            return reviewText;
        }
    }

    /**
     * Initialize the adapter
     *
     * @param dataSet List containing the data to populate the views with
     */
    public ReviewAdapter(List<Review> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.review_list_item_view, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Review review = localDataSet.get(position);
        viewHolder.getUsernameTextView().setText(review.getUsername());
        viewHolder.getReviewRatingBar().setRating((float) review.getStarRating());
        viewHolder.getReviewTextView().setText(review.getReviewText());

    }

    // Return the size of the dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}