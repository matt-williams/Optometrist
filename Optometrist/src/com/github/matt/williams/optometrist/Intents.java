package com.github.matt.williams.optometrist;

import android.content.Intent;

public final class Intents {
    private Intents() {};

    private final static String PREFIX = "com.github.matt.williams.optometrist.";

    private final static String PREFIX_ACTION = PREFIX + "action.";
    public final static String ACTION_CALIBRATE = PREFIX_ACTION + "CALIBRATE";

    private final static String PREFIX_EXTRA = PREFIX + "extra.";
    public final static String EXTRA_FORCE = PREFIX_EXTRA + "FORCE";
    public final static String EXTRA_Z_NEAR = PREFIX_EXTRA + "Z_NEAR";
    public final static String EXTRA_Z_FAR = PREFIX_EXTRA + "Z_FAR";
    public final static String EXTRA_Z_FOCUS = PREFIX_EXTRA + "Z_FOCUS";
    public final static String EXTRA_MATRIX = PREFIX_EXTRA + "MATRIX";

    public final static boolean DEFAULT_FORCE = false;
    public final static float DEFAULT_Z_NEAR = 0.01f;
    public final static float DEFAULT_Z_FAR = 100.0f;
    public final static float DEFAULT_Z_FOCUS = 1.0f;

    public static boolean isActionCalibrate(Intent intent) {
        String action = intent.getAction();
        return ((action != null) && (action.equals(ACTION_CALIBRATE)));
    }

    public static boolean isExtraForce(Intent intent) {
        return intent.getBooleanExtra(EXTRA_FORCE, DEFAULT_FORCE);
    }

    public static float getExtraZNear(Intent intent) {
        return intent.getFloatExtra(EXTRA_Z_NEAR, DEFAULT_Z_NEAR);
    }

    public static float getExtraZFar(Intent intent) {
        return intent.getFloatExtra(EXTRA_Z_FAR, DEFAULT_Z_FAR);
    }

    public static float getExtraZFocus(Intent intent) {
        return intent.getFloatExtra(EXTRA_Z_FOCUS, DEFAULT_Z_FOCUS);
    }

    public static void setExtraMatrix(Intent intent, float[] matrix) {
        intent.putExtra(EXTRA_MATRIX, matrix);
    }
}
