package com.example.paintmeandroidprogram;

import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Parcelable;

import java.io.Serializable;

public interface Figure extends Parcelable { //implementujemy Parcelable aby można było przekazywać dane pomiędzy aktywnościami oraz zapisywać je

    Paint getPaint();
    void setmCurrent(PointF mCurrent);
    PointF getmCurrent();
    PointF getmOrigin();
}
