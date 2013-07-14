package com.github.matt.williams.optometrist;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preferences {

    private static final String PREFERENCES_FILENAME = "com.github.matt.williams.optometrist";
    private static final String PROPERTY_MATRIX = "matrix";
    private static final float[] DEFAULT_MATRIX = new float[] {1.0f, 0.0f, 0.0f, 0.0f,
                                                               0.0f, 1.0f, 0.0f, 0.0f,
                                                               0.0f, 0.0f, 1.0f, 0.0f,
                                                               0.0f, 0.0f, 0.0f, 1.0f};
    private final SharedPreferences mPreferences;

    public Preferences(Context context) {
        mPreferences = context.getSharedPreferences(PREFERENCES_FILENAME, Context.MODE_PRIVATE);
    }

    public void setMatrix(float[] matrix) {
        Editor editor = mPreferences.edit();
        for (int ii = 0; ii < matrix.length; ii++) {
            editor.putFloat(PROPERTY_MATRIX + "[" + ii + "]", matrix[ii]);
        }
        editor.commit();
    }

    public boolean hasMatrix() {
        boolean hasMatrix = true;
        for (int ii = 0; ii < DEFAULT_MATRIX.length; ii++) {
            hasMatrix &= mPreferences.contains(PROPERTY_MATRIX + "[" + ii + "]");
        }
        return hasMatrix;
    }

    public float[] getMatrix() {
        float[] matrix = new float[DEFAULT_MATRIX.length];
        for (int ii = 0; ii < DEFAULT_MATRIX.length; ii++) {
            matrix[ii] = mPreferences.getFloat(PROPERTY_MATRIX + "[" + ii + "]", DEFAULT_MATRIX[ii]);
        }
        return matrix;
    }
}
