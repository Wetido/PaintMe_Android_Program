package com.example.paintmeandroidprogram;

import android.graphics.Paint;
import android.graphics.PointF;

public class Circle implements Figure{

    private PointF mOrigin;
    private PointF mCurrent;
    private Paint paint;

    public Circle(PointF mOrigin, int color) {
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
    public Paint getPaint() {
        return paint;
    }

}
