package hi.hbv601g.kritikin;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AskQuestionDialogFragment extends DialogFragment {

    public AskQuestionDialogFragment() {
        super();
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
               .setPositiveButton(R.string.submit_button_text, (d, id) -> {
                   // Get values
                   EditText questionTextInput = view.findViewById(R.id.askQuestionTextInput);
                   // Create bundle
                   Bundle result = new Bundle();
                   result.putString("questionText", questionTextInput.getText().toString());
                   // Pass bundle to parent fragment
                   getParentFragmentManager().setFragmentResult("newQuestion", result);
               })
               .setNegativeButton(R.string.cancel_button_text, null);

        return builder.create();
    }
}
