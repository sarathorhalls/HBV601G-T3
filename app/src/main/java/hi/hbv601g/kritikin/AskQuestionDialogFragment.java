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

public class AskQuestionDialogFragment extends DialogFragment {
    public interface AskQuestionDialogListener {
        public void onAskQuestionDialogPositiveClick(View dialogView);
    }
    private AskQuestionDialogListener listener;

    public AskQuestionDialogFragment() {
        super();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (AskQuestionDialogListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Inflate custom content view
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_ask_question, null);

        builder.setTitle(R.string.ask_question_dialog_title)
               .setView(view)
               .setPositiveButton(R.string.submit_button_text, (d, id) ->
                       listener.onAskQuestionDialogPositiveClick(view)
               )
               .setNegativeButton(R.string.cancel_button_text, null);

        return builder.create();
    }
}
