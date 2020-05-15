package com.example.paintmeandroidprogram;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;


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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    protected nPath(Parcel in) {
    }

    public static final Creator<nPath> CREATOR = new Creator<nPath>() {
        @Override
        public nPath createFromParcel(Parcel source) {
            return new nPath(source);
        }

        @Override
        public nPath[] newArray(int size) {
            return new nPath[size];
        }
    };
}
