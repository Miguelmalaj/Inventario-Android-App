package com.example.bd_inventario;

import android.view.ScaleGestureDetector;

import com.budiyev.android.codescanner.CodeScannerView;

public class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
    CodeScannerView codeScannerView;
    float scale = 1f;

    public ScaleListener(CodeScannerView codeScannerView){
        this.codeScannerView = codeScannerView;
    }

    public boolean onScale(ScaleGestureDetector detector) {
        if(scale > 0.84000000f){
            scale = 0.84000000f;
        }

        if(scale < 0.12000000f){
            scale = 0.12000000f;
        }

        if (scale <= 0.84000000f && scale >= 0.12000000f) {
            scale *= detector.getScaleFactor();
            codeScannerView.setFrameSize(scale);

        }
        return true;
    }
}
