package com.github.matt.williams.optometrist;

import android.opengl.Matrix;

import com.github.matt.williams.android.gl.Projection;

public class CalibratedCameraProjection extends Projection {
    public float[] mProjectionMatrix = new float[16];
    public float mScaleX = 1.0f;
    public float mScaleY = 1.0f;
    public float mTranslateX = 0.0f;
    public float mTranslateY = 0.0f;

    @Override
    public void setProjectionMatrix(float[] projectionMatrix) {
        mProjectionMatrix = projectionMatrix;
        updateProjectionMatrix();
    }

    public void setScale(float scaleX, float scaleY) {
        mScaleX = scaleX;
        mScaleY = scaleY;
        updateProjectionMatrix();
    }

    public void setTranslation(float translateX, float translateY) {
        mTranslateX = translateX;
        mTranslateY = translateY;
        updateProjectionMatrix();
    }

    private void updateProjectionMatrix() {
        float[] temp = new float[16];
        System.arraycopy(mProjectionMatrix, 0, temp, 0, 16);
        Matrix.translateM(temp, 0, -mTranslateY, -mTranslateX, 0.0f);
        Matrix.scaleM(temp, 0, mScaleY, mScaleX, 1.0f);
        super.setProjectionMatrix(temp);
    }
}