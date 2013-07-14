package com.github.matt.williams.optometrist;

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
}
