package com.example.paintmeandroidprogram;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import java.util.ArrayList;


public class DrawableSurface extends View implements SurfaceHolder.Callback, Runnable {

    private int mode = 0;   ///tryb rysowania domyslnie linia
    private Integer color = 0xffe7dedf; ///Domyślny kolor

    private Box mCurrentBox;    ///Obiekty służące do rysowania
    private nPath mPath;
    private Circle mCircle;

    public static final int LINE = 0; ///Tryby rysowania zdefinowane w celu czytelności kodu
    public static final int BOX = 1;
    public static final int CIRCLE = 2;

    private ArrayList<Figure> objects = new ArrayList<>(); ///Tablica obiektów
    private Paint mBackgroundPaint;


    public DrawableSurface(Context context){
        this(context, null);
    }

    public DrawableSurface(Context context, AttributeSet attrs) { ///Inicjalizacja oraz ustawienei koloru tła
        super(context, attrs);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);

    }

    public void setColor(int color){
        this.color = color;
    }

    public ArrayList<Figure> getArray(){
        return objects;
    }

    public void setArray( ArrayList<Figure> obj){   ///podmiana tablicy
        objects = obj;
        invalidate();
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){

        PointF current = new PointF(event.getX(), event.getY()); ///Pobieramy współrzędne kliknięcia
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN: { ///W momecie rozpoczecia akcji tworzymy nowe obiekty i operujemy na referencjach do nich aktualizujemy położenie wraz z
                //przesuwaniem obiektu po ekranie aby było widać to na bierzaco podczas rysowania

                mPath = new nPath(color);
                mCurrentBox = new Box(current, color);
                mCircle = new Circle(current, color);

                if (mode == LINE) {
                    mPath.moveTo(current.x, current.y);
                    objects.add(mPath);

                } else if (mode == BOX) {
                    objects.add(mCurrentBox);

                } else if (mode == CIRCLE) {
                    objects.add(mCircle);

                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {     ///z każdym ruchem zmieniamy współżedne

                if (mode == LINE && (mCurrentBox != null)) {

                    mPath.lineTo(current.x, current.y);
                    invalidate(); //przerysowanie widoku

                } else if (mode == BOX && (mPath != null)) {

                    mCurrentBox.setmCurrent(current);
                    invalidate();

                } else if (mode == CIRCLE && (mCircle != null)){

                    mCircle.setmCurrent(current);
                    invalidate();
                }

                break;
            }
            case MotionEvent.ACTION_UP:

            case MotionEvent.ACTION_CANCEL: ///Jeżeli anulujemy akcje obiekty są kasowane

                mPath = null;
                mCurrentBox = null;
                mCircle = null;
                break;

        }

        return  true;
    }

    @Override
    protected void onDraw(final Canvas canvas){ ///funkcja rysująca

        canvas.drawPaint(mBackgroundPaint);

        for(Figure obj : objects){

            if ( obj instanceof nPath ){ ///Jeżeli obiekt jest ściężką rysujemy ścieżkę

                canvas.drawPath((Path) obj, obj.getPaint());
            } else { ///Jeżeli nie to ustalamy współżędne które są potrzebne zarówno do rysowania kwadratu jak i koła

                float left = Math.min( obj.getmOrigin().x, obj.getmCurrent().x );
                float right = Math.max( obj.getmOrigin().x, obj.getmCurrent().x );
                float top = Math.min( obj.getmOrigin().y, obj.getmCurrent().y );
                float bottom = Math.max( obj.getmOrigin().y, obj.getmCurrent().y );

                if(obj instanceof Box){ ///Jeżeli jest to box to rysujemy czworokąt

                    canvas.drawRect(left, top, right, bottom, obj.getPaint() );
                } else if (obj instanceof Circle){ ///Jeżeli nie to rysujemy okrąg

                    canvas.drawCircle( (right + left)/2, (top + bottom)/2 , (right - left) /2 , obj.getPaint() );
                }
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) { }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) { }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) { }


    @Override
    public void run() { }
}