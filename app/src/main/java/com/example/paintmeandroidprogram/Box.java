package com.example.paintmeandroidprogram;

import android.graphics.Paint;
import android.graphics.PointF;

public class Box {

    private PointF mOrigin;
    private PointF mCurrent;
    private Paint paint;

    public Box(PointF mOrigin, int color) {
        this.mOrigin = mOrigin;
        this.mCurrent = mOrigin;

        paint = new Paint();
        paint.setColor(color);
    }

    public PointF getmOrigin() {
        return mOrigin;
    }

    public PointF getmCurrent() {
        return mCurrent;
    }

    public void setmOrigin(PointF mOrigin) {
        this.mOrigin = mOrigin;
    }

    public void setmCurrent(PointF mCurrent) {
        this.mCurrent = mCurrent;
    }

    public Paint getPaint() {
        return paint;
    }

}
