package com.example.qrscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

public class barcode_generator_activity extends AppCompatActivity {

    private EditText inputString ;
    private ImageView outputCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_QRScanner);
        setContentView(R.layout.activity_barcode_generator);

        Button barCodeGenBtn = findViewById(R.id.generateBarCodeBtn);
        Button barCodeSave = findViewById(R.id.saveBtn);
        Button barCodeShare = findViewById(R.id.shareBtn);
        inputString = findViewById(R.id.myTextInput);
        outputCode  = findViewById(R.id.barcodeImage);

        barCodeGenBtn.setOnClickListener(view -> generateBarCode());

        barCodeSave.setOnClickListener(view -> Toast.makeText(this, "This feature is coming soon!!", Toast.LENGTH_SHORT).show());

        barCodeShare.setOnClickListener(view -> Toast.makeText(this, "This feature is coming soon!!", Toast.LENGTH_SHORT).show());

    }

    private void generateBarCode() {

        try {
            String productId = inputString.getText().toString();
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            Writer codeWriter;
            codeWriter = new Code128Writer();
            BitMatrix byteMatrix = codeWriter.encode(productId, BarcodeFormat.CODE_128,400, 200, hintMap);
            int width = byteMatrix.getWidth();
            int height = byteMatrix.getHeight();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    bitmap.setPixel(i, j, byteMatrix.get(i, j) ? Color.BLACK : Color.WHITE);
                }
            }
            outputCode.setImageBitmap(bitmap);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}