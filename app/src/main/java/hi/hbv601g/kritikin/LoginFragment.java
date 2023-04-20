package hi.hbv601g.kritikin;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hi.hbv601g.kritikin.entities.User;
import hi.hbv601g.kritikin.services.UserService;
import hi.hbv601g.kritikin.services.implementation.UserServiceImplementation;

public class LoginFragment extends Fragment {
    private Main app;
    private EditText usernameInput;
    private EditText passwordInput;
    private UserService userService;
    private User loginResult;

    /**
     * Logs in with specified credentials
     * @param username Username
     * @param password Password
     */
    private void login(String username, String password) {
        new Thread(() -> {
            loginResult = userService.login(username, password);
            if (loginResult == null || loginResult.getUsername() == null) {
                requireActivity().runOnUiThread(this::showLoginFailedMessage);
            } else {
                app.setLoggedInUser(loginResult);
                requireActivity().runOnUiThread(() -> {
                    ((MainActivity) requireActivity()).updateLoginState();
                    showLoginSuccessMessage(loginResult.getUsername());
                    requireActivity().getSupportFragmentManager().popBackStack();
                });
            }
        }).start();
    }

    /**
     * Creates a new user with specified credentials and logs in
     * @param username Username of user
     * @param password Password of user
     */
    private void signup(String username, String password) {
        new Thread(() -> {
           loginResult = userService.signUp(username, password);
           if (loginResult == null || loginResult.getUsername() == null) {
               requireActivity().runOnUiThread(this::showSignupFailedMessage);
           } else {
               app.setLoggedInUser(loginResult);
               requireActivity().runOnUiThread(() -> {
                   ((MainActivity) requireActivity()).updateLoginState();
                   showLoginSuccessMessage(loginResult.getUsername());
                   requireActivity().getSupportFragmentManager().popBackStackImmediate();
               });
           }
        }).start();
    }

    /**
     * Displays a message with a toast on the bottom of the screen
     * @param message Message to display
     */
    private void displayToast(String message) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Displays a message indicating the login failed
     */
    private void showLoginFailedMessage() {
        displayToast(getString(R.string.login_failed_text));
    }

    /**
     * Displays a message indicating the signup failed
     */
    private void showSignupFailedMessage() {
        displayToast(getString(R.string.signup_failed_text));
    }

    /**
     * Displays a message indicating the login was successful
     * @param username Username of logged in user
     */
    private void showLoginSuccessMessage(String username) {
        String message = String.format(getString(R.string.login_success_text), username);
        displayToast(message);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TransitionInflater inflater = TransitionInflater.from(requireContext());
        setEnterTransition(inflater.inflateTransition(R.transition.slide_right));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Get the main class instance for saving login info
        app = (Main) requireActivity().getApplication();

        // Get UI elements
        usernameInput = view.findViewById(R.id.usernameInput);
        passwordInput = view.findViewById(R.id.passwordInput);
        Button loginButton = view.findViewById(R.id.loginButton);
        Button signupButton = view.findViewById(R.id.signupButton);

        // Create user service instance
        userService = new UserServiceImplementation();

        loginButton.setOnClickListener(v -> login(usernameInput.getText().toString(), passwordInput.getText().toString()));

        signupButton.setOnClickListener(v -> signup(usernameInput.getText().toString(), passwordInput.getText().toString()));

        return view;
    }
}
