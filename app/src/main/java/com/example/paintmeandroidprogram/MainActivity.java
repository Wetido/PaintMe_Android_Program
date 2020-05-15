package com.example.paintmeandroidprogram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.michaldrabik.tapbarmenulib.TapBarMenu;

import java.util.ArrayList;

import yuku.ambilwarna.AmbilWarnaDialog;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private TapBarMenu tapBarMenu;
    private ImageView linearDraw;
    private ImageView squareDraw;
    private ImageView circleDraw;
    private ImageView colorPicker;
    private DrawableSurface drawable_surface; ///Nasz powierzchnia do rysowania
    private ArrayList<Figure> objects;  ///Lista narysowany obiektów

    public static final int LINE = 0;
    public static final int BOX = 1;
    public static final int CIRCLE = 2;


    /*
    Korzystam z zewnętrznych bibliotek aby stworzyć animowane menu oraz color picker
    https://github.com/yukuku/ambilwarna - color picker
    https://github.com/michaldrabik/TapBarMenu - menu
    */

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

        setListeners();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {  //Zapis danych przed wywołaniem onDestroy()
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("key", drawable_surface.getArray());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {  //Wczytanie danych po wywołaniu onCreate()
        super.onRestoreInstanceState(savedInstanceState);
        drawable_surface.setArray( savedInstanceState.getParcelableArrayList("key") );
    }

    void setListeners(){

        tapBarMenu.setOnClickListener(v -> tapBarMenu.toggle()); ///Domyslny listner

        linearDraw.setOnClickListener(v -> { ///Listeren rysowania linii
            tapBarMenu.close();
            drawable_surface.setMode(LINE);
        });

        squareDraw.setOnClickListener(v -> { ///Listeren rysowania kwadratu

            tapBarMenu.close();
            drawable_surface.setMode(BOX);
        });

        circleDraw.setOnClickListener(v -> {  ///Listener rysowania koła

            tapBarMenu.close();
            drawable_surface.setMode(CIRCLE);
        });


        colorPicker.setOnClickListener(v -> { ///Listeren color pickera

            openDialog(true);
            tapBarMenu.close();

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {     ///Dodanie menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {   ///Start nowego activity do którego wysyłana jest lista utworzonych obiektów

        if (item.getItemId() == R.id.item) {

            ArrayList<Figure> objects = drawable_surface.getArray();///Pobieramy liste obiektów z naszego drugiego activity

            Intent intent = new Intent(this, ElementsInspectorAcitivity.class);
            intent.putExtra("array", objects);
            startActivityForResult(intent, 0);

        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { ///Reakcja na powrot z actviity
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) { ///SKAN QR

            if (resultCode == RESULT_OK) {

                ArrayList<Integer> toDelete = (ArrayList<Integer>) data.getSerializableExtra("arrayResult");    ///Pobieramy liste elem do usuniecia
                ArrayList<Figure> objects = drawable_surface.getArray(); ///Pobieramy liste obiektów z naszego drugiego activity

                for( int i = 0; i < toDelete.size(); i++){  ///Usuwamy elementy z listy

                    objects.remove( (int)toDelete.get(i) ); ///Musimy rzutować na int ponieważ chcemy usunać element listy składającej się z Integerów a nie obiekt
                }
                drawable_surface.setArray(objects); ///Zastępujemy listę nową listą pozbawioną elementów

                Toast.makeText(getApplicationContext(), "Elementy zostaly usuniete", Toast.LENGTH_LONG).show();

            }
        }
    }

    void openDialog(boolean supportsAlpha){ ///Otworzenie color pickera

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
