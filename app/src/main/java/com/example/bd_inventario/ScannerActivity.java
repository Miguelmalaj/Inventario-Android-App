package com.example.bd_inventario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class ScannerActivity extends AppCompatActivity {
    CodeScanner codeScanner;
    CodeScannerView codeScannerView;
    ScaleGestureDetector detector;
    float xBegin = 0;
    float yBegin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        codeScannerView = findViewById(R.id.scannerView);
        codeScanner = new CodeScanner(this, codeScannerView);

        codeScannerView.getFrameSize();
        detector = new ScaleGestureDetector(this, new ScaleListener(codeScannerView));

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent regresar = new Intent(ScannerActivity.this, MainActivity.class);
                        regresar.putExtra("Nombre_usuario", getIntent().getStringExtra("Nombre_usuario"));
                        regresar.putExtra("Empresa", getIntent().getStringExtra("Empresa"));
                        regresar.putExtra("Sucursal", getIntent().getStringExtra("Sucursal"));
                        regresar.putExtra("Id_usuario", getIntent().getStringExtra("Id_usuario"));
                        regresar.putExtra("valorVIN", result.getText());
                        startActivity(regresar);
                    }
                });
            }
        });

    }

    public boolean onTouchEvent(MotionEvent event){
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestCamera();

    }

    private void requestCamera() {
        codeScanner.startPreview();
    }
}