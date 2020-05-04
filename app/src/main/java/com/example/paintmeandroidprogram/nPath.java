package com.example.paintmeandroidprogram;

import android.graphics.Paint;
import android.graphics.Path;

import androidx.annotation.Nullable;

public class nPath extends Path {

    private Paint paint;

    public nPath(int color) {

        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
    }

    public nPath(@Nullable Path src, int color) {
        super(src);
        this.paint = paint;
    }

    public Paint getPaint() {
        return paint;
    }
}
