package com.example.paintmeandroidprogram;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Parcel;

import java.io.Serializable;

@SuppressLint("ParcelCreator")
public class Circle implements Figure {

    private transient PointF mOrigin;
    private transient PointF mCurrent;
    private transient Paint paint;

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    protected Circle(Parcel in) {
    }

    public static final Creator<Circle> CREATOR = new Creator<Circle>() {
        @Override
        public Circle createFromParcel(Parcel source) {
            return new Circle(source);
        }

        @Override
        public Circle[] newArray(int size) {
            return new Circle[size];
        }
    };
}
