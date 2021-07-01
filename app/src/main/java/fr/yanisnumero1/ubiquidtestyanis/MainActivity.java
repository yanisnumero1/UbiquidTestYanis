package fr.yanisnumero1.ubiquidtestyanis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.Result;

public class MainActivity extends AppCompatActivity {

    private CodeScanner mCodeScanner;
    private CodeScannerView mCodeScannerView;
    private int codeScanned;

    BottomNavigationView mBottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        codeScanned=0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // navbar implementation
        mBottomNavigationView=findViewById(R.id.bottom_nav);
        mBottomNavigationView.setSelectedItemId(R.id.mainActivity);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.mainActivity:
                        return true;
                    case R.id.homeActivity:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.settings_fgt:
                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        // scanning
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA},123 );
        } else{
            startScanning();

        }
    }




    private void startScanning() {

        mCodeScannerView=findViewById(R.id.scanner_view);
        mCodeScanner= new CodeScanner(this, mCodeScannerView);
        mCodeScanner.startPreview();
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String resultat =result.getText();
                        codeScanned++;
                        Intent intent = new Intent(MainActivity.this, ScannedInformation.class);
                        intent.putExtra("keyResult", resultat);
                        startActivity(intent);

                        //Toast.makeText(MainActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        mCodeScannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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