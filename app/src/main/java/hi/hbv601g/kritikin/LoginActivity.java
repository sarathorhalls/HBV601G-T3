package hi.hbv601g.kritikin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hi.hbv601g.kritikin.entities.User;
import hi.hbv601g.kritikin.services.UserService;
import hi.hbv601g.kritikin.services.implementation.UserServiceImplementation;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginButton;
    private Button signupButton;
    private UserService userService;
    private User loginResult;

    private void login(String username, String password) {
        new Thread(() -> {
            loginResult = userService.login(username, password);
            if (loginResult == null || loginResult.getUsername() == null) {
                runOnUiThread(this::showLoginFailedMessage);
            } else {
                runOnUiThread(() -> showLoginSuccessMessage(loginResult.getUsername()));
                // TODO: save login
            }
        }).start();
    }

    private void signup(String username, String password) {
        new Thread(() -> {
           loginResult = userService.signUp(username, password);
           if (loginResult == null || loginResult.getUsername() == null) {
               runOnUiThread(this::showSignupFailedMessage);
           } else {
               runOnUiThread(() -> showLoginSuccessMessage(loginResult.getUsername()));
           }
        }).start();
    }

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailedMessage() {
        displayToast(getString(R.string.login_failed_text));
    }

    private void showSignupFailedMessage() {
        displayToast(getString(R.string.signup_failed_text));
    }

    private void showLoginSuccessMessage(String username) {
        String message = String.format(getString(R.string.login_success_text), username);
        displayToast(message);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get UI elements
        usernameInput = (EditText) findViewById(R.id.usernameInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        loginButton = (Button) findViewById(R.id.loginButton);
        signupButton = (Button) findViewById(R.id.signupButton);

        // Create user service instance
        userService = new UserServiceImplementation();

        loginButton.setOnClickListener(v -> {
            login(usernameInput.getText().toString(), passwordInput.getText().toString());
        });

        signupButton.setOnClickListener(v -> {
            signup(usernameInput.getText().toString(), passwordInput.getText().toString());
        });
    }
}