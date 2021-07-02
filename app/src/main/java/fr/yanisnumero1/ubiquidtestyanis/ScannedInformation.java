package fr.yanisnumero1.ubiquidtestyanis;

import git adandroidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ScannedInformation extends AppCompatActivity {

    private TextView infoScanned;
    private BottomNavigationView mBottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanned_information);

        infoScanned = findViewById(R.id.infosScanned);
        String result = getIntent().getStringExtra("keyResult");
        infoScanned.setText(result);


        // nav bar implementation
        mBottomNavigationView = findViewById(R.id.bottom_nav);
        mBottomNavigationView.setSelectedItemId(R.id.homeActivity);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.mainActivity:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.homeActivity:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0, 0);

                        return true;
                    case R.id.settings_fgt:
                        startActivity(new Intent(getApplicationContext(), ResultRateActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });


    }
}