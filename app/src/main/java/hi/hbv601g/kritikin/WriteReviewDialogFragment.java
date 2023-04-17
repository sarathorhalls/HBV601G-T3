package hi.hbv601g.kritikin;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class WriteReviewDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Inflate custom content view
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_write_review, null);

        builder.setTitle(R.string.write_review_dialog_title)
               .setView(view)
               .setPositiveButton(R.string.submit_button_text, (d, id) -> {
                   // Get values
                   RatingBar ratingBar = view.findViewById(R.id.writeReviewRatingBar);
                   EditText reviewTextInput = view.findViewById(R.id.writeReviewTextInput);
                   // Create bundle
                   Bundle result = new Bundle();
                   result.putFloat("starRating", ratingBar.getRating());
                   result.putString("reviewText", reviewTextInput.getText().toString());
                   // Pass bundle to parent fragment
                   getParentFragmentManager().setFragmentResult("newReview", result);
               })
               .setNegativeButton(R.string.cancel_button_text, null);

        return builder.create();
    }
}
