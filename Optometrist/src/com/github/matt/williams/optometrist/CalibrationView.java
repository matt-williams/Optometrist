package com.github.matt.williams.optometrist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.InputDevice.MotionRange;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.github.matt.williams.android.ar.CameraView;
import com.github.matt.williams.android.ar.Renderable;

public class CalibrationView extends CameraView {
    public interface OnCalibrationCompleteListener {
        public void onCalibrationComplete(float[] matrix);
    }

    private OnCalibrationCompleteListener mListener;
    private final CalibratedCameraProjection mProjection = new CalibratedCameraProjection();

    private float mPreviousX;
    private float mPreviousY;
    private float mPreviousScale;
    private int mPreviousPointerCount;
    private boolean mMoved;

    public CalibrationView(Context context) {
        super(context);
        setProjection(mProjection);
        setFocusable(true);
    }

    public CalibrationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setProjection(mProjection);
        setFocusable(true);
    }

    @Override
    public Renderable createBillboard() {
        return new FragmentShaderBillboard(getResources(), getTexture(), R.string.edgeFragmentShader);
    }

    public void setOnCalibrationCompleteListener(OnCalibrationCompleteListener listener) {
        mListener = listener;
    }

    public void setMatrix(float[] matrix) {
        mProjection.setTransformationMatrix(matrix);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        MotionRange motionRangeX = event.getDevice().getMotionRange(MotionEvent.AXIS_X);
        MotionRange motionRangeY = event.getDevice().getMotionRange(MotionEvent.AXIS_Y);
        float rangeX = (motionRangeX.getMax() - motionRangeX.getMin());
        float rangeY = (motionRangeY.getMax() - motionRangeY.getMin());
        float minX = Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE;
        float maxY = Float.MIN_VALUE;
        for (int ptrIdx = 0; ptrIdx < event.getPointerCount(); ptrIdx++) {
            minX = Math.min(minX, event.getX(ptrIdx));
            minY = Math.min(minY, event.getY(ptrIdx));
            maxX = Math.max(maxX, event.getX(ptrIdx));
            maxY = Math.max(maxY, event.getY(ptrIdx));
        }
        float newX = (minX + maxX) / 2;
        float newY = (minY + maxY) / 2;
        float newScale = (float)Math.sqrt((maxX - minX) * (maxX - minX) + (maxY - minY) * (maxY - minY));
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mMoved = false;
        } else if ((event.getAction() == MotionEvent.ACTION_MOVE) &&
            (event.getPointerCount() == mPreviousPointerCount)) {
            if ((event.getPointerCount() > 1) ||
                ((newX - mPreviousX) * (newX - mPreviousX) + (newY - mPreviousY) * (newY - mPreviousY) > 25.0f)) {
                mMoved = true;
            }
            if (event.getPointerCount() > 1) {
                mProjection.scale(newScale / mPreviousScale, newScale / mPreviousScale);
            }
            mProjection.translate((newX - mPreviousX) / rangeX, (newY - mPreviousY) / rangeY);
        } else if ((event.getAction() == MotionEvent.ACTION_UP) &&
                   (!mMoved) &&
                   (event.getEventTime() - event.getDownTime() < 400)) {
            mListener.onCalibrationComplete(mProjection.getTransformationMatrix());
        }
        mPreviousX = newX;
        mPreviousY = newY;
        mPreviousScale = newScale;
        mPreviousPointerCount = event.getPointerCount();
        return true;
    }

    @Override
    public boolean onKeyDown(int key, KeyEvent event) {
        return true;
    }
}
