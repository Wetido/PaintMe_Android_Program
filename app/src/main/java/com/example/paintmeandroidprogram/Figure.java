package com.example.paintmeandroidprogram;

import android.graphics.Paint;
import android.graphics.PointF;

import java.io.Serializable;

public interface Figure extends Serializable {

    Paint getPaint();
    void setmCurrent(PointF mCurrent);
    PointF getmCurrent();
    PointF getmOrigin();


}
