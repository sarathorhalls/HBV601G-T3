package hi.hbv601g.kritikin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.LayoutDirection;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.resources.TextAppearance;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import hi.hbv601g.kritikin.entities.Company;
import hi.hbv601g.kritikin.entities.Question;
import hi.hbv601g.kritikin.entities.Review;
import hi.hbv601g.kritikin.entities.User;

public class CompanyActivity extends AppCompatActivity {

    private CardView createCard(String username, String text, double starRating, String answer, boolean isQuestion) {
        // Create views
        CardView card = new CardView(CompanyActivity.this);
        LinearLayout cardLayout = new LinearLayout(CompanyActivity.this);
        cardLayout.setOrientation(LinearLayout.VERTICAL);
        TextView usernameText = new TextView(CompanyActivity.this);
        TextView contentText = new TextView(CompanyActivity.this);

        // Set styles
        int dp4 = dpToPx(4);
        card.setContentPadding(dp4, dp4, dp4, dp4);
        LinearLayout.LayoutParams cardLayoutParams = new LinearLayout.LayoutParams(dpToPx(120), LinearLayout.LayoutParams.WRAP_CONTENT);
        cardLayoutParams.setMargins(0, 0, dpToPx(16), 0);
        card.setLayoutParams(cardLayoutParams);
        card.setMinimumHeight(dpToPx(60));

        usernameText.setTextAppearance(androidx.appcompat.R.style.TextAppearance_AppCompat_Body2);
        contentText.setTextAppearance(androidx.appcompat.R.style.TextAppearance_AppCompat_Body1);

        // Set text
        usernameText.setText(username);
        contentText.setText(text);

        // Add children to card
        cardLayout.addView(usernameText);
        cardLayout.addView(contentText);

        if (isQuestion) {
            // Add answer text if question
            TextView answerText = new TextView(CompanyActivity.this);
            if (answer == null) {
                // If no answer exists, display a message about that
                answerText.setText(R.string.question_not_answered_text);
                answerText.setTextAppearance(com.google.android.material.R.style.TextAppearance_AppCompat_Small);
            } else {
                // Otherwise, display the answer
                answerText.setText(answer);
                answerText.setTextAppearance(com.google.android.material.R.style.TextAppearance_AppCompat_Body2);
            }
            cardLayout.addView(answerText);
        } else {
            // Else add star rating below username
            RatingBar ratingBar = new RatingBar(new ContextThemeWrapper(CompanyActivity.this, androidx.appcompat.R.style.Widget_AppCompat_RatingBar_Small), null, 0);
            LinearLayout.LayoutParams ratingBarLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ratingBar.setLayoutParams(ratingBarLayoutParams);
            ratingBar.setRating((float) starRating);
            cardLayout.addView(ratingBar, 1);
        }

        // Add card layout to card
        card.addView(cardLayout);

        return card;
    }

    private CardView createReviewCard(Review review) {
        return createCard(review.getUser().getUsername(), review.getReviewText(), review.getStarRating(), null, false);
    }

    private CardView createQuestionCard(Question question) {
        return createCard(question.getUser().getUsername(), question.getQuestionString(), 0.0, question.getAnswerString(), true);
    }

    /**
     * Opens a URI in the appropriate application
     * @param uri URI of resource to be opened
     */
    private void openURI(String uri) {
        Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(viewIntent);
    }

    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get extras
        Bundle extras = getIntent().getExtras();
        long companyId = extras.getLong("companyId");

        // Get company info from API
        // TODO: put network request on worker thread and fetch company from service
        // CompanyService companyService = new CompanyServiceImplementation();
        // Company company = companyService.findById(companyId);
        Company company = new Company(
                companyId,
                "Test Company",
                4.5,
                "https://example.org",
                5555555,  // FIXME: phone number should not be an integer
                "This is a company description",
                "Hagatorg 1",
                "10:00–12:00",
                null,
                new ArrayList<>()
        );
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(new Review(5L, company, new User("testuser"), 3.5, "Good company"));
        reviewList.add(new Review(6L, company, new User("testuser2"), 2.0, "Not my favorite company but they are okay"));
        reviewList.add(new Review(6L, company, new User("testuser3"), 1.0, "Awful company. Will never go here again."));
        company.setReviews(reviewList);

        List<Question> questionList = new ArrayList<>();
        questionList.add(new Question(5L, company, new User("testuser"), "What is 2+2?", null));
        questionList.add(new Question(6L, company, new User("testuser2"), "What is 4+4?", "Four plus four is eight."));
        company.setQuestions(questionList);

        // Set content view
        setContentView(R.layout.activity_company);

        // Get components
        TextView companyNameText = (TextView) findViewById(R.id.companyNameText);
        TextView companyDescriptionText = (TextView) findViewById(R.id.companyDescriptionText);
        TextView noReviewsText = (TextView) findViewById(R.id.noReviewsText);
        TextView noQuestionsText = (TextView) findViewById(R.id.noQuestionsText);

        Chip companyWebsiteChip = (Chip) findViewById(R.id.companyWebsiteChip);
        Chip companyPhoneChip = (Chip) findViewById(R.id.companyPhoneChip);
        Chip companyOpeningHoursChip = (Chip) findViewById(R.id.companyOpeningHoursChip);
        Chip companyAddressChip = (Chip) findViewById(R.id.companyAddressChip);

        RatingBar companyRatingBar = (RatingBar) findViewById(R.id.companyRatingBar);

        ViewGroup reviewsView = findViewById(R.id.reviewsView);
        ViewGroup reviewsContainer = findViewById(R.id.reviewsContainer);
        ViewGroup questionsView = findViewById(R.id.questionsView);
        ViewGroup questionsContainer = findViewById(R.id.questionsContainer);

        // TODO: implement dialogs
        Button writeReviewButton = (Button) findViewById(R.id.writeReviewButton);
        Button askQuestionButton = (Button) findViewById(R.id.askQuestionButton);
        Button requestAdminAccessButton = (Button) findViewById(R.id.requestAdminAccessButton);

        // Set company info
        companyNameText.setText(company.getName());
        companyPhoneChip.setText(Integer.toString(company.getPhoneNumber()));
        companyOpeningHoursChip.setText(company.getOpeningHours());
        companyAddressChip.setText(company.getAddress());
        companyRatingBar.setRating((float) company.getStarRating());
        companyDescriptionText.setText(company.getDescription());

        // Add links to chips
        companyWebsiteChip.setOnClickListener(v -> openURI(company.getWebsite()));
        companyPhoneChip.setOnClickListener(v -> openURI("tel:" + company.getPhoneNumber()));
        companyAddressChip.setOnClickListener(v -> openURI("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(company.getAddress())));

        // Add reviews
        if (!company.getReviews().isEmpty()) {
            reviewsView.setVisibility(View.VISIBLE);
            noReviewsText.setVisibility(View.GONE);
            for (Review review : company.getReviews()) {
                // Create review card and add to reviews container
                CardView card = createReviewCard(review);
                reviewsContainer.addView(card);
            }
        }

        // Add questions
        if (!company.getQuestions().isEmpty()) {
            questionsView.setVisibility(View.VISIBLE);
            noQuestionsText.setVisibility(View.GONE);
            for (Question question : company.getQuestions()) {
                // Create question card and add to questions container
                CardView card = createQuestionCard(question);
                questionsContainer.addView(card);
            }
        }
    }
}