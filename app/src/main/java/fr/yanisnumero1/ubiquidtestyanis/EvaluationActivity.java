package fr.yanisnumero1.ubiquidtestyanis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.Result;

import java.util.Timer;
import java.util.TimerTask;

public class EvaluationActivity extends AppCompatActivity {

    private CodeScanner mCodeScanner;
    private CodeScannerView mCodeScannerView;
    private int codeScanned;
    private TextView codeCounted, chrono;
    private CountDownTimer countDownTimer;
    private Timer timer;
    private Button startTest;

    BottomNavigationView mBottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        codeScanned=0;

        chrono=findViewById(R.id.timer);
        startTest=findViewById(R.id.startEval);



        codeCounted= findViewById(R.id.code_counter);
        codeCounted.setText("");


        // navbar implementation
        mBottomNavigationView=findViewById(R.id.bottom_nav);
        mBottomNavigationView.setSelectedItemId(R.id.settings_fgt);
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

        // timer
        countDownTimer=new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                chrono.setText(millisUntilFinished/1000 + "sec restantes");

            }

            @Override
            public void onFinish() {
                chrono.setText("test fini");
                String codeStr= String.valueOf(codeScanned);

                Intent intent = new Intent(EvaluationActivity.this, ResultRateActivity.class);
                intent.putExtra("codeSent", codeStr);
                startActivity(intent);




            }
        };
        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Chrono", "bouton lancer le test pressé, lance le chrono");
                codeCounted.setText("0");
                countDownTimer.start();


            }
        });

        // permission for camera
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA},123 );
        } else{
            startScanning();

        }



    }



    private void startScanning() {
        Log.v("Scan", "Scan lancé");

        mCodeScannerView=findViewById(R.id.scanner_view);
        mCodeScanner= new CodeScanner(this, mCodeScannerView);

        mCodeScanner.startPreview();
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {

            // TODO liste qui stockent resultat et autoriser scan si resultat non compris dans la liste
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Log.v("Scan", "Un code a été détecté");

                        String resultat =result.getText();
                        codeScanned++;
                        codeCounted.setText(String.valueOf(codeScanned));



                       // intent.putExtra("keyResult", resultat);

                        mCodeScanner.startPreview();
                        //startActivity(intent);

                        //Toast.makeText(MainActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        mCodeScannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("ScannerView", "Relance du scan avec un click");
                mCodeScanner.startPreview();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 123){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission Autorisée", Toast.LENGTH_SHORT).show();
                startScanning();
            }else{
                Toast.makeText(this, "Permission refusée", Toast.LENGTH_SHORT).show();
            }
        }
    }
}