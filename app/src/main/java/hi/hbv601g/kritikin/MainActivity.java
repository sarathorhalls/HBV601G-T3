package hi.hbv601g.kritikin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: remove test code
        Intent companyIntent = new Intent(MainActivity.this, CompanyActivity.class);
        companyIntent.putExtra("companyId", 1L);
        startActivity(companyIntent);
    }
}