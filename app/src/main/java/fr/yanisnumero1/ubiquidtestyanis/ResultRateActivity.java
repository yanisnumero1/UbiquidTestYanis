package fr.yanisnumero1.ubiquidtestyanis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ResultRateActivity extends AppCompatActivity {


    private TextView resultScan, txtRating;
    private RatingBar rbRating;
    private Button btnSubmit;
    private BottomNavigationView mBottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_rate);


        // receive the data sent in putExtra
        resultScan = findViewById(R.id.result_received);
        String nbrScan = getIntent().getStringExtra("codeSent");
        resultScan.setText(nbrScan);

        // rating bar implementation

        txtRating = findViewById(R.id.txt_rating);
        rbRating = findViewById(R.id.ratingBar);
        btnSubmit = findViewById(R.id.btn_send_note);

        rbRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float ratingValue, boolean fromUser) {
                String rating = "Note attribuée : " + ratingValue;
                txtRating.setText(rating);
            }
        });

        // Alterdialog implementation

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ResultRateActivity.this);
                builder.setTitle("Envoyé !");
                builder.setMessage("Note attribuée : " + rbRating.getRating() + " Merci de votre participation ! ");
                builder.setCancelable(false);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                // passing the builder to the dialog and show it
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });


        // navbar implementation
        mBottomNavigationView = findViewById(R.id.bottom_nav);
        mBottomNavigationView.setSelectedItemId(R.id.mainActivity);
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
                        startActivity(new Intent(getApplicationContext(), EvaluationActivity.class));
                        overridePendingTransition(0, 0);

                        return true;
                }
                return false;
            }
        });


    }
}