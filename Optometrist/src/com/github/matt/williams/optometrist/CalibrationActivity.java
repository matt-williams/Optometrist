package com.github.matt.williams.optometrist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class CalibrationActivity extends Activity implements CalibrationView.OnCalibrationCompleteListener {

    private CalibrationView mCameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String action = intent.getAction();
        if ((action != null) &&
            (action.equals(Intents.ACTION_CALIBRATE))) {

        }


        setContentView(R.layout.activity_calibration);
        mCameraView = (CalibrationView)findViewById(R.id.calibrationView);
        mCameraView.requestFocus();
        mCameraView.setOnCalibrationCompleteListener(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // TODO Auto-generated method stub
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCameraView.onResume();
    }

    @Override
    protected void onPause() {
        mCameraView.onPause();
        super.onPause();
    }

    @Override
    public void onCalibrationComplete(float[] matrix) {
        Intent intent = new Intent();
        intent.putExtra(Intents.EXTRA_MATRIX, matrix);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
