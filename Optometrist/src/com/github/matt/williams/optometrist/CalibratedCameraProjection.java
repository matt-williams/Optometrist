package com.github.matt.williams.optometrist;

import android.opengl.Matrix;

import com.github.matt.williams.android.gl.Projection;

public class CalibratedCameraProjection extends Projection {
    private float[] mProjectionMatrix = new float[16];
    private float[] mTransformationMatrix = new float[16];

    public CalibratedCameraProjection() {
        Matrix.setIdentityM(mProjectionMatrix, 0);
        Matrix.setIdentityM(mTransformationMatrix, 0);
    }

    @Override
    public void setProjectionMatrix(float[] projectionMatrix) {
        mProjectionMatrix = projectionMatrix;
        updateProjectionMatrix();
    }

    public float[] getTransformationMatrix() {
        return mTransformationMatrix;
    }

    public void setTransformationMatrix(float[] transformationMatrix) {
        mTransformationMatrix = transformationMatrix;
        updateProjectionMatrix();
    }

    public void scale(float scaleX, float scaleY) {
        Matrix.scaleM(mTransformationMatrix, 0, scaleX, scaleY, 1.0f);
        updateProjectionMatrix();
    }

    public void translate(float translateX, float translateY) {
        Matrix.translateM(mTransformationMatrix, 0, translateX, translateY, 0);
        updateProjectionMatrix();
    }

    private void updateProjectionMatrix() {
        float[] temp = new float[16];
        Matrix.multiplyMM(temp, 0, mTransformationMatrix, 0, mProjectionMatrix, 0);
        super.setProjectionMatrix(temp);
    }
}