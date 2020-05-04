package com.example.paintmeandroidprogram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.michaldrabik.tapbarmenulib.TapBarMenu;

import yuku.ambilwarna.AmbilWarnaDialog;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private TapBarMenu tapBarMenu;
    private ImageView linearDraw;
    private ImageView squareDraw;
    private ImageView circleDraw;
    private ImageView colorPicker;
    DrawableSurface drawable_surface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawable_surface = findViewById(R.id.drawable_surface);

        tapBarMenu = findViewById(R.id.tapBarMenu);
        linearDraw = findViewById(R.id.item1);
        squareDraw = findViewById(R.id.item2);
        circleDraw = findViewById(R.id.item3);
        colorPicker = findViewById(R.id.item4);


        tapBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                tapBarMenu.toggle();
            }
        });

        linearDraw.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                tapBarMenu.close();
                drawable_surface.setMode(0);
            }
        });

        squareDraw.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                tapBarMenu.close();
                drawable_surface.setMode(1);
            }
        });

        circleDraw.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                tapBarMenu.close();
                drawable_surface.setMode(2);
            }
        });


        colorPicker.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                openDialog(true);
                tapBarMenu.close();

            }
        });

    }

    void openDialog(boolean supportsAlpha){

        AmbilWarnaDialog dialog = new AmbilWarnaDialog(MainActivity.this,  0x0000ff  , supportsAlpha, new AmbilWarnaDialog.OnAmbilWarnaListener() {

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                Toast.makeText(getApplicationContext(), "Udalo sie wybrac kolor", Toast.LENGTH_LONG).show();
                drawable_surface.setColor( color );
            }

            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                Toast.makeText(getApplicationContext(), "Operacja przerwana", Toast.LENGTH_LONG).show();
            }
        });
        dialog.show();
    }


}
