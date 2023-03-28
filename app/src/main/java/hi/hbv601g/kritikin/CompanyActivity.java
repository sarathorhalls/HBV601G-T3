package hi.hbv601g.kritikin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.chip.Chip;

import hi.hbv601g.kritikin.services.NetworkManagerService;
import hi.hbv601g.kritikin.services.implementation.NetworkManagerServiceImplementation;

public class CompanyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get extras
        Bundle extras = getIntent().getExtras();
        int companyId = extras.getInt("companyId");

        // Get company info from API
        // NetworkManagerService net = new NetworkManagerServiceImplementation();
        // String companyJson = net.doGET("/company/1", null);

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
        companyNameText.setText("Company id: " + companyId);
        reviewsView.setVisibility(View.VISIBLE);
        noReviewsText.setVisibility(View.GONE);
    }
}