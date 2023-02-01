package com.example.qrscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class qr_code_generator_activity extends AppCompatActivity {

    private EditText inputString ;
    private ImageView outputCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_QRScanner);
        setContentView(R.layout.activity_qr_code_generator);

        Button qrCodeGenBtn = findViewById(R.id.generateQRCodeBtn);
        Button barCodeSave = findViewById(R.id.saveBtn);
        Button barCodeShare = findViewById(R.id.shareBtn);
        inputString = findViewById(R.id.myTextInput);
        outputCode  = findViewById(R.id.qrImage);

        qrCodeGenBtn.setOnClickListener(view -> generateBarCode());

        barCodeSave.setOnClickListener(view -> Toast.makeText(this, "This feature is coming sooon!!", Toast.LENGTH_SHORT).show());

        barCodeShare.setOnClickListener(view -> Toast.makeText(this, "This feature is coming sooon!!", Toast.LENGTH_SHORT).show());
    }

    private void generateBarCode() {

        String myText = inputString.getText().toString().trim();
        String myText2 = inputString.getText().toString();
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        if (myText2.equals("")) {
            Toast.makeText(this, "Found empty contents", Toast.LENGTH_SHORT).show();
        } else {
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(myText, BarcodeFormat.QR_CODE,400,400);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                outputCode.setImageBitmap(bitmap);
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(inputString.getApplicationWindowToken(), 0);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
    }
}