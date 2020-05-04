package com.example.paintmeandroidprogram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;


public class DrawableSurface extends View implements SurfaceHolder.Callback, Runnable {

    private int mode = 0;
    private Integer color = 0xffe7dedf;

    private Box mCurrentBox;
    private nPath mPath;
    private Circle mCircle;

    //private ArrayList<Circle> mCircles = new ArrayList<>();
    //private ArrayList<Box> mBoxes = new ArrayList<>(); //lista boxow
    //private HashMap<Path,Paint> mPaths = new HashMap<>();


    private ArrayList<Object> objects = new ArrayList<>();
    
    private Paint mBackgroundPaint;


    public DrawableSurface(Context context){

        this(context, null);
    }

    public DrawableSurface(Context context, AttributeSet attrs) {
        super(context, attrs);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);

    }

    public void setColor(int color){

        this.color = color;
    }


    public void setMode(int mode) {
        this.mode = mode;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        PointF current = new PointF(event.getX(), event.getY());
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN: {
                mPath = new nPath(color);
                mCurrentBox = new Box(current, color);
                mCircle = new Circle(current, color);

                if (mode == 0) {
                    mPath.moveTo(current.x, current.y);
                    objects.add(mPath);

                } else if (mode == 1) {
                    objects.add(mCurrentBox);

                } else if (mode == 2) {
                    objects.add(mCircle);

                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {

                if (mode == 0 && (mCurrentBox != null)) {

                    mPath.lineTo(current.x, current.y);
                    invalidate();

                } else if (mode == 1 && (mPath != null)) {

                    mCurrentBox.setmCurrent(current);
                    invalidate(); //przerysowanie widoku

                } else if (mode == 2 && (mCircle != null)){

                    mCircle.setmCurrent(current);
                    invalidate();
                }

                break;
            }
            case MotionEvent.ACTION_UP:

            case MotionEvent.ACTION_CANCEL:

                mPath = null;
                mCurrentBox = null;
                mCircle = null;
                break;

        }

        return  true;
    }

    public boolean performClick(){
        return super.performClick();
    }

    @Override
    protected void onDraw(final Canvas canvas){

        canvas.drawPaint(mBackgroundPaint);

        for(Object obj : objects){

            if(obj instanceof Box){

                float left = Math.min( ((Box) obj).getmOrigin().x, ((Box) obj).getmCurrent().x );
                float right = Math.max( ((Box) obj).getmOrigin().x, ((Box) obj).getmCurrent().x );
                float top = Math.min( ((Box) obj).getmOrigin().y, ((Box) obj).getmCurrent().y );
                float bottom = Math.max( ((Box) obj).getmOrigin().y, ((Box) obj).getmCurrent().y );

                canvas.drawRect(left, top, right, bottom, ((Box) obj).getPaint() );
            } else if (obj instanceof Circle){

                float left = Math.min( ((Circle) obj).getmOrigin().x, ((Circle) obj).getmCurrent().x );
                float right = Math.max( ((Circle) obj).getmOrigin().x, ((Circle) obj).getmCurrent().x );
                float top = Math.min( ((Circle) obj).getmOrigin().y, ((Circle) obj).getmCurrent().y );
                float bottom = Math.max( ((Circle) obj).getmOrigin().y, ((Circle) obj).getmCurrent().y );

                canvas.drawCircle( (right + left)/2, (top + bottom)/2 , (right - left) /2 , ((Circle) obj).getPaint() );
            } else if (obj instanceof nPath){

                canvas.drawPath((Path) obj, ((nPath) obj).getPaint());
            }
        }

    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }


    @Override
    public void run() {

    }
}