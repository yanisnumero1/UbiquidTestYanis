package fr.yanisnumero1.ubiquidtestyanis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ResultRateActivity extends AppCompatActivity {


    private TextView resultScan;

    BottomNavigationView mBottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_rate);

        resultScan=findViewById(R.id.result_received);

        String nbrScan= getIntent().getStringExtra("codeSent");

        resultScan.setText(nbrScan);

        // navbar implementation
        mBottomNavigationView=findViewById(R.id.bottom_nav);
        mBottomNavigationView.setSelectedItemId(R.id.mainActivity);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.mainActivity:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.homeActivity:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.settings_fgt:
                        return true;
                }
                return false;
            }
        });













    }
}