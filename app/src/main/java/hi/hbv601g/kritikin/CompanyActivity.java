package hi.hbv601g.kritikin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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

public class CompanyActivity extends AppCompatActivity {
    /**
     * Opens a URI in the appropriate application
     * @param uri URI of resource to be opened
     */
    private void openURI(String uri) {
        Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(viewIntent);
    }

    private Company company;

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
        company = new Company(
                companyId,
                "Test Company",
                4.5,
                "https://example.org",
                5555555,  // FIXME: phone number should not be an integer
                "This is a company description",
                "Hagatorg 1",
                "10:00–12:00",
                null,
                null
        );
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(new Review(5L, company, new User("testuser"), 3.5, "Good company"));
        reviewList.add(new Review(6L, company, new User("testuser2"), 2.0, "Not my favorite company but they are okay"));
        reviewList.add(new Review(7L, company, new User("testuser3"), 1.0, "Awful company. Will never go here again."));
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

        RecyclerView reviewsRecycler = (RecyclerView) findViewById(R.id.reviewsRecycler);
        RecyclerView questionsRecycler = (RecyclerView) findViewById(R.id.questionsRecycler);

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
}