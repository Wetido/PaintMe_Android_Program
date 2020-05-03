package com.example.paintmeandroidprogram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.michaldrabik.tapbarmenulib.TapBarMenu;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private TapBarMenu tapBarMenu;
    private ImageView button1;
    private ImageView button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final DrawableSurface drawable_surface = (DrawableSurface) findViewById(R.id.drawable_surface);

        tapBarMenu = findViewById(R.id.tapBarMenu);
        button1 = findViewById(R.id.item1);
        button2 = findViewById(R.id.item2);



        tapBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                tapBarMenu.toggle();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                tapBarMenu.close();
                drawable_surface.setPaintColor();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                tapBarMenu.close();
            }
        });





    }



}
