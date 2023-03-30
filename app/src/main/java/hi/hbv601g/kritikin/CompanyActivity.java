package hi.hbv601g.kritikin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;

import hi.hbv601g.kritikin.entities.Company;

public class CompanyActivity extends AppCompatActivity {

    /**
     * Opens a URI in the appropriate application
     * @param uri URI of resource to be opened
     */
    private void openURI(String uri) {
        Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(viewIntent);
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
        // FIXME: phone number should not be an integer
        Company company = new Company(
                companyId,
                "Test Company",
                4.5,
                "https://example.org",
                5555555,
                "This is a company description",
                "Hagatorg 1",
                "10:00–12:00",
                new ArrayList<>(),
                new ArrayList<>()
        );

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
        View reviewsView = findViewById(R.id.reviewsView);

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
    }
}