package com.example.paintmeandroidprogram;

import android.graphics.Paint;
import android.graphics.PointF;

public interface Figure {

    Paint getPaint();
    void setmCurrent(PointF mCurrent);
    PointF getmCurrent();
    PointF getmOrigin();

}
