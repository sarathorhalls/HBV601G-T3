package hi.hbv601g.kritikin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hi.hbv601g.kritikin.entities.Question;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private List<Question> localDataSet;

    /**
     * Provides a reference to the type of views used to display each list item
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView usernameText;
        private final TextView questionText;
        private final TextView questionAnswerText;

        public ViewHolder(View view) {
            super(view);
            usernameText = (TextView) view.findViewById(R.id.questionUsernameText);
            questionText = (TextView) view.findViewById(R.id.questionText);
            questionAnswerText = (TextView) view.findViewById(R.id.questionAnswerText);
        }

        public TextView getUsernameTextView() {
            return usernameText;
        }

        public TextView getQuestionTextView() {
            return questionText;
        }

        public TextView getQuestionAnswerTextView() {
            return questionAnswerText;
        }
    }

    /**
     * Initialize the adapter
     *
     * @param dataSet List containing the data to populate the views with
     */
    public QuestionAdapter(List<Question> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.question_list_item_view, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Question question = localDataSet.get(position);
        viewHolder.getUsernameTextView().setText(question.getUser().getUsername());
        viewHolder.getQuestionTextView().setText(question.getQuestionString());
        // Display answer if appropriate
        String answer = question.getAnswerString();
        if (answer != null) {
            TextView questionAnswerTextView = viewHolder.getQuestionAnswerTextView();
            questionAnswerTextView.setText(question.getAnswerString());
            questionAnswerTextView.setTextAppearance(androidx.appcompat.R.style.TextAppearance_AppCompat_Body2);
        }
    }

    // Return the size of the dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}