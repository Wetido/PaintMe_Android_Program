package com.example.paintmeandroidprogram;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class nPath extends Path implements Figure {

    private transient Paint paint;

    public nPath(int color) {

        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
    }

    @Override
    public Paint getPaint() {
        return paint;
    }

    @Override
    public void setmCurrent(PointF mCurrent) { }

    @Override
    public PointF getmCurrent() {
        return null;
    }

    @Override
    public PointF getmOrigin() {
        return null;
    }
}
