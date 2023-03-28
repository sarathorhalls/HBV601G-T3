package hi.hbv601g.kritikin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.chip.Chip;

import hi.hbv601g.kritikin.entities.Company;
import hi.hbv601g.kritikin.services.CompanyService;
import hi.hbv601g.kritikin.services.NetworkManagerService;
import hi.hbv601g.kritikin.services.implementation.CompanyServiceImplementation;
import hi.hbv601g.kritikin.services.implementation.NetworkManagerServiceImplementation;

public class CompanyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get extras
        Bundle extras = getIntent().getExtras();
        long companyId = extras.getLong("companyId");

        // Get company info from API
        CompanyService companyService = new CompanyServiceImplementation();
        Company company = companyService.findById(companyId);

        // Set content view
        setContentView(R.layout.activity_company);

        // Get components
        TextView companyNameText = (TextView) findViewById(R.id.companyNameText);
        Chip companyWebsiteChip = (Chip) findViewById(R.id.companyWebsiteChip);
        Chip companyPhoneChip = (Chip) findViewById(R.id.companyPhoneChip);
        Chip companyOpeningHoursChip = (Chip) findViewById(R.id.companyOpeningHoursChip);
        Chip companyAddressChip = (Chip) findViewById(R.id.companyAddressChip);
        RatingBar companyRatingBar = (RatingBar) findViewById(R.id.companyRatingBar);
        TextView companyDescriptionText = (TextView) findViewById(R.id.companyDescriptionText);
        TextView noReviewsText = (TextView) findViewById(R.id.noReviewsText);
        View reviewsView = findViewById(R.id.reviewsView);
        Button writeReviewButton = (Button) findViewById(R.id.writeReviewButton);
        TextView noQuestionsText = (TextView) findViewById(R.id.noQuestionsText);
        Button askQuestionButton = (Button) findViewById(R.id.askQuestionButton);
        Button requestAdminAccessButton = (Button) findViewById(R.id.requestAdminAccessButton);

        // Set company info
        companyNameText.setText(company.getName());
        // TODO: add links to chips
        companyRatingBar.setRating((float) company.getStarRating());
        companyDescriptionText.setText(company.getDescription());

        if (!company.getReviews().isEmpty()) {
            // TODO: add reviews to reviewsView
            reviewsView.setVisibility(View.VISIBLE);
            noReviewsText.setVisibility(View.GONE);
        }
        if (!company.getQuestions().isEmpty()) {
            // TODO: add questions to questionsView
            // TODO: make questionsView visible
            noQuestionsText.setVisibility(View.GONE);
        }
        // TODO: make buttons functional
    }
}