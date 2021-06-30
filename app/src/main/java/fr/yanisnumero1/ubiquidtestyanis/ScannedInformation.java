package fr.yanisnumero1.ubiquidtestyanis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ScannedInformation extends AppCompatActivity {

    private TextView infoScanned;
    private ImageButton btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanned_information);

        infoScanned= findViewById(R.id.infosScanned);
        btnHome= findViewById(R.id.btnHome);

        String result = getIntent().getStringExtra("keyResult");

        infoScanned.setText(result);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScannedInformation.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}