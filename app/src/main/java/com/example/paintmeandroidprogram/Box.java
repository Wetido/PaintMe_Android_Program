package com.example.paintmeandroidprogram;

import android.graphics.Paint;
import android.graphics.PointF;

import java.io.Serializable;

public class Box implements Figure {

    private transient PointF mOrigin;
    private transient PointF mCurrent;
    private transient Paint paint;

    public Box(PointF mOrigin, int color) {
        this.mOrigin = mOrigin;
        this.mCurrent = mOrigin;

        paint = new Paint();
        paint.setColor(color);
    }

    @Override
    public PointF getmOrigin() {
        return mOrigin;
    }

    @Override
    public PointF getmCurrent() {
        return mCurrent;
    }

    @Override
    public void setmCurrent(PointF mCurrent) {
        this.mCurrent = mCurrent;
    }

    @Override
    public Paint getPaint() { return paint; }

}
