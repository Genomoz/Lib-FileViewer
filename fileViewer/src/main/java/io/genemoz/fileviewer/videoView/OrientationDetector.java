package io.genemoz.fileviewer.videoView;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.OrientationEventListener;

import io.genemoz.fileviewer.BuildConfig;


public class OrientationDetector {

    private static final String TAG = "OrientationDetector";
    private static final int HOLDING_THRESHOLD = 1500;
    private final Context context;
    private OrientationEventListener orientationEventListener;

    private int rotationThreshold = 20;
    private long holdingTime = 0;
    private long lastCalcTime = 0;
    private Direction lastDirection = Direction.PORTRAIT;

    private int currentOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;//初始为竖屏

    private OrientationChangeListener listener;

    public OrientationDetector(Context context) {
        this.context = context;
    }


    public void setOrientationChangeListener(OrientationChangeListener listener) {
        this.listener = listener;
    }

    public void enable() {
        if (orientationEventListener == null) {
            orientationEventListener = new OrientationEventListener(context, SensorManager.SENSOR_DELAY_UI) {
                @Override
                public void onOrientationChanged(int orientation) {
                    Direction currDirection = calcDirection(orientation);
                    if (currDirection == null) {
                        return;
                    }

                    if (currDirection != lastDirection) {
                        resetTime();
                        lastDirection = currDirection;
                        if (BuildConfig.DEBUG) {

                        }
                    } else {
                        calcHoldingTime();
                        if (holdingTime > HOLDING_THRESHOLD) {
                            if (currDirection == Direction.LANDSCAPE) {
                                if (currentOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                                    Log.d(TAG, "switch to SCREEN_ORIENTATION_LANDSCAPE");
                                    currentOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                                    if (listener != null) {
                                        listener.onOrientationChanged(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE, currDirection);
                                    }
                                }

                            } else if (currDirection == Direction.PORTRAIT) {
                                if (currentOrientation != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                                    Log.d(TAG, "switch to SCREEN_ORIENTATION_PORTRAIT");
                                    currentOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                                    if (listener != null) {
                                        listener.onOrientationChanged(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT, currDirection);
                                    }
                                }

                            } else if (currDirection == Direction.REVERSE_PORTRAIT) {
                                if (currentOrientation != ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT) {
                                    Log.d(TAG, "switch to SCREEN_ORIENTATION_REVERSE_PORTRAIT");
                                    currentOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                                    if (listener != null) {
                                        listener.onOrientationChanged(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT, currDirection);
                                    }
                                }

                            } else if (currDirection == Direction.REVERSE_LANDSCAPE) {
                                if (currentOrientation != ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
                                    Log.d(TAG, "switch to SCREEN_ORIENTATION_REVERSE_LANDSCAPE");
                                    currentOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                                    if (listener != null) {
                                        listener.onOrientationChanged(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE, currDirection);
                                    }
                                }

                            }

                        }
                    }

                }
            };
        }

        orientationEventListener.enable();
    }

    private void calcHoldingTime() {
        long current = System.currentTimeMillis();
        if (lastCalcTime == 0) {
            lastCalcTime = current;
        }
        holdingTime += current - lastCalcTime;
        lastCalcTime = current;
    }

    private void resetTime() {
        holdingTime = lastCalcTime = 0;
    }

    private Direction calcDirection(int orientation) {
        if (orientation <= rotationThreshold
                || orientation >= 360 - rotationThreshold) {
            return Direction.PORTRAIT;
        } else if (Math.abs(orientation - 180) <= rotationThreshold) {
            return Direction.REVERSE_PORTRAIT;
        } else if (Math.abs(orientation - 90) <= rotationThreshold) {
            return Direction.REVERSE_LANDSCAPE;
        } else if (Math.abs(orientation - 270) <= rotationThreshold) {
            return Direction.LANDSCAPE;
        }
        return null;
    }


    public void setInitialDirection(Direction direction) {
        lastDirection = direction;
    }

    public void disable() {
        if (orientationEventListener != null) {
            orientationEventListener.disable();
        }
    }

    public void setThresholdDegree(int degree) {
        rotationThreshold = degree;
    }

    public enum Direction {
        PORTRAIT, REVERSE_PORTRAIT, LANDSCAPE, REVERSE_LANDSCAPE
    }


    public interface OrientationChangeListener {
        void onOrientationChanged(int screenOrientation, Direction direction);
    }

}
