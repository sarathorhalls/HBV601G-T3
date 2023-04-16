package hi.hbv601g.kritikin;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

import hi.hbv601g.kritikin.entities.Company;
import hi.hbv601g.kritikin.entities.Question;
import hi.hbv601g.kritikin.entities.Review;
import hi.hbv601g.kritikin.entities.User;
import hi.hbv601g.kritikin.services.CompanyService;
import hi.hbv601g.kritikin.services.implementation.CompanyServiceImplementation;

public class CompanyFragment extends Fragment
                             implements WriteReviewDialogFragment.WriteReviewDialogListener,
                                        AskQuestionDialogFragment.AskQuestionDialogListener {
    private Main app;
    private CompanyService companyService;
    private Company company;

    private TextView companyNameText;
    private TextView companyDescriptionText;
    private TextView companyOpeningHoursText;
    private TextView noReviewsText;
    private TextView noQuestionsText;

    private Chip companyWebsiteChip;
    private Chip companyPhoneChip;
    private Chip companyAddressChip;

    private RatingBar companyRatingBar;

    private RecyclerView reviewsRecycler;
    private RecyclerView questionsRecycler;

    private Button writeReviewButton;
    private Button askQuestionButton;
    private Button requestAdminAccessButton;


    private View companyScrollView;
    private View companyProgressBar;

    public CompanyFragment(Company company) {
        this.company = company;
    }

    /**
     * Opens a URI in the appropriate application
     * @param uri URI of resource to be opened
     */
    private void openURI(String uri) {
        Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(viewIntent);
    }

    /**
     * Updates the company UI according to the current value of the company instance variable
     */
    private void updateCompanyUI() {
        // Set company info
        companyNameText.setText(company.getName());
        companyPhoneChip.setText(Integer.toString(company.getPhoneNumber()));
        companyAddressChip.setText(company.getAddress());
        companyRatingBar.setRating((float) company.getStarRating());
        companyDescriptionText.setText(company.getDescription());
        companyOpeningHoursText.setText(company.getOpeningHours());

        // Get reviews and questions
        List<Review> reviews = company.getReviews();
        List<Question> questions = company.getQuestions();

        // Connect reviews and questions
        reviewsRecycler.setAdapter(new ReviewAdapter(reviews));
        questionsRecycler.setAdapter(new QuestionAdapter(questions));

        // Hide no reviews text if reviews list is not empty
        if (!reviews.isEmpty()) {
            noReviewsText.setVisibility(View.GONE);
        }

        // Hide no questions text if questions list is not empty
        if (!questions.isEmpty()) {
            noQuestionsText.setVisibility(View.GONE);
        }

        // Add links to chips
        companyWebsiteChip.setOnClickListener(v -> openURI(company.getWebsite()));
        companyPhoneChip.setOnClickListener(v -> openURI("tel:" + company.getPhoneNumber()));
        companyAddressChip.setOnClickListener(v -> openURI("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(company.getAddress())));
    }

    /**
     * Gets company with ID id from the web service and displays it in the UI
     * @param id ID of company to display
     */
    private void getCompany(long id) {
        CompanyService companyService = new CompanyServiceImplementation();
        new Thread(() -> {
            // Get company from service and store in instance variable
            company = companyService.findById(id);
            // Display company info
            if (company != null) {
                // Update UI with new company
                getActivity().runOnUiThread(this::updateCompanyUI);
            }
        }).start();
    }

    /**
     * Gets reviews and questions for company from the web service,
     * adds to the company instance variable and updates the UI
     */
    private void getReviewsAndQuestions() {
        long id = company.getId();

        // Show progress bar while review and question info is being fetched
        showProgressBar();

        new Thread(() -> {
            // Get reviews from service and store in instance variable
            List<Review> reviews = companyService.findReviewsByCompanyId(id);
            company.setReviews(reviews);

            // Get Questions from service and store in instance variable
            List<Question> questions = companyService.findQuestionsByCompanyId(id);
            company.setQuestions(questions);

            // When done
            getActivity().runOnUiThread(() -> {
                // Update UI with new company
                updateCompanyUI();
                // Show company info
                showCompanyInfo();
            });
        }).start();
    }

    /**
     * Shows the company info UI and hides the progress bar
     */
    private void showCompanyInfo() {
        companyProgressBar.setVisibility(View.GONE);
        companyScrollView.setVisibility(View.VISIBLE);
    }

    /**
     * Hides the company UI and shows the progress bar
     */
    private void showProgressBar() {
        companyScrollView.setVisibility(View.GONE);
        companyProgressBar.setVisibility(View.VISIBLE);
    }

    /**
     * Displays the "write review" dialog fragment
     */
    private void showWriteReviewDialog() {
        DialogFragment writeReviewDialog = new WriteReviewDialogFragment();
        writeReviewDialog.show(getParentFragmentManager(), "review");
    }

    /**
     * Displays the "ask question" dialog fragment
     */
    private void showAskQuestionDialog() {
        DialogFragment askQuestionDialog = new AskQuestionDialogFragment();
        askQuestionDialog.show(getParentFragmentManager(), "question");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        companyService = new CompanyServiceImplementation();

        View view = inflater.inflate(R.layout.fragment_company, container, false);

        // Get components
        companyNameText = (TextView) view.findViewById(R.id.companyNameText);
        companyDescriptionText = (TextView) view.findViewById(R.id.companyDescriptionText);
        companyOpeningHoursText = (TextView) view.findViewById(R.id.companyOpeningHoursText);
        noReviewsText = (TextView) view.findViewById(R.id.noReviewsText);
        noQuestionsText = (TextView) view.findViewById(R.id.noQuestionsText);

        companyWebsiteChip = (Chip) view.findViewById(R.id.companyWebsiteChip);
        companyPhoneChip = (Chip) view.findViewById(R.id.companyPhoneChip);
        companyAddressChip = (Chip) view.findViewById(R.id.companyAddressChip);

        companyRatingBar = (RatingBar) view.findViewById(R.id.companyRatingBar);

        reviewsRecycler = (RecyclerView) view.findViewById(R.id.reviewsRecycler);
        questionsRecycler = (RecyclerView) view.findViewById(R.id.questionsRecycler);

        companyScrollView = view.findViewById(R.id.companyScrollView);
        companyProgressBar = view.findViewById(R.id.companyProgressBar);

        // Get the main class instance for getting login info
        app = (Main) getActivity().getApplication();

        // Write review dialog
        writeReviewButton = (Button) view.findViewById(R.id.writeReviewButton);
        writeReviewButton.setOnClickListener(v -> showWriteReviewDialog());

        // Ask question dialog
        askQuestionButton = (Button) view.findViewById(R.id.askQuestionButton);
        askQuestionButton.setOnClickListener(v -> showAskQuestionDialog());

        // If user is logged in, activate write review and ask question buttons
        if (app.getLoggedInUser() != null) {
            writeReviewButton.setEnabled(true);
            askQuestionButton.setEnabled(true);
        }

        // Request admin access button
        // TODO: give this a function
        requestAdminAccessButton = (Button) view.findViewById(R.id.requestAdminAccessButton);

        // Set empty adapters for recycler views to work
        reviewsRecycler.setAdapter(new ReviewAdapter(new ArrayList<>()));
        questionsRecycler.setAdapter(new QuestionAdapter(new ArrayList<>()));

        // Get reviews and questions from API (to get usernames)
        getReviewsAndQuestions();

        return view;
    }

    /**
     * Submits a new review to the current company and updates the UI
     * @param dialogView View containing review rating and text inside dialog fragment
     */
    @Override
    public void onWriteReviewDialogPositiveClick(View dialogView) {
        // Get UI items
        RatingBar ratingBar = (RatingBar) dialogView.findViewById(R.id.writeReviewRatingBar);
        EditText reviewText = (EditText) dialogView.findViewById(R.id.writeReviewTextInput);

        User user = app.getLoggedInUser();

        Review review = new Review(
                company,
                user,
                (double) ratingBar.getRating(),
                reviewText.getText().toString()
        );
        new Thread(() -> {
            // Create review via API
            companyService.createReview(review);
            // Set username for local display
            review.setUsername(user.getUsername());
            // Add new review to reviews list
            List<Review> reviews = company.getReviews();
            reviews.add(review);
            // Update UI
            getActivity().runOnUiThread(() -> reviewsRecycler.getAdapter().notifyItemInserted(reviews.size() - 1));
        }).start();
    }

    /**
     * Submits a new question to the current company and updates the UI
     * @param dialogView View containing question text inside dialog fragment
     */
    @Override
    public void onAskQuestionDialogPositiveClick(View dialogView) {
        EditText questionText = (EditText) dialogView.findViewById(R.id.askQuestionTextInput);
        User user = app.getLoggedInUser();

        Question question = new Question(
                company,
                user,
                questionText.getText().toString()
        );
        new Thread(() -> {
            // Create question via API
            companyService.createQuestion(question);
            // Set username for local display
            question.setUsername(user.getUsername());
            // Add new question to questions list
            List<Question> questions = company.getQuestions();
            questions.add(question);
            // Update UI
            getActivity().runOnUiThread(() -> questionsRecycler.getAdapter().notifyItemInserted(questions.size() - 1));
        }).start();
    }
}