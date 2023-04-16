package hi.hbv601g.kritikin;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class WriteReviewDialogFragment extends DialogFragment {
    public interface WriteReviewDialogListener {
        public void onWriteReviewDialogPositiveClick(View dialogView);
    }
    private WriteReviewDialogListener listener;

    public WriteReviewDialogFragment() {
        super();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (WriteReviewDialogListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Inflate custom content view
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_write_review, null);

        builder.setTitle(R.string.write_review_dialog_title)
               .setView(view)
               .setPositiveButton(R.string.submit_button_text, (d, id) ->
                       listener.onWriteReviewDialogPositiveClick(view)
               )
               .setNegativeButton(R.string.cancel_button_text, null);

        return builder.create();
    }
}
