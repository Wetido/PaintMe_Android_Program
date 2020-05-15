package com.example.paintmeandroidprogram;


import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Parcel;

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    protected Box(Parcel in) {
    }

    public static final Creator<Box> CREATOR = new Creator<Box>() {
        @Override
        public Box createFromParcel(Parcel source) {
            return new Box(source);
        }

        @Override
        public Box[] newArray(int size) {
            return new Box[size];
        }
    };
}
