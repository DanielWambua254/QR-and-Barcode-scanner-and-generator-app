package com.example.qrscanner;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;


import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_QRScanner);
        setContentView(R.layout.activity_main);

        ImageButton barCodeScan = findViewById(R.id.barCodeScanBtn);
        ImageButton qrCodeScanBtn = findViewById(R.id.qrScanBtn);

        ImageButton qrGenBtn = findViewById(R.id.qrGenBtn);
        ImageButton bcGenBtn = findViewById(R.id.barCodeGenBtn);

        barCodeScan.setOnClickListener(view -> scanCode());
        qrCodeScanBtn.setOnClickListener(view -> scanCode());

        qrGenBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,qr_code_generator_activity.class);
            startActivity(intent);
        });

        bcGenBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,barcode_generator_activity.class);
            startActivity(intent);
        });
    }
    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setBeepEnabled(true);
        options.setPrompt("volume up button to turn on flash");
        options.setOrientationLocked(true);
        options.setTimeout(25000);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);

    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new
            ScanContract(),result ->{

        if (result != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setCancelable(true);
            builder.setTitle("Scan Results");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).show();
        }
    });
}