package com.example.paintmeandroidprogram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ElementsInspectorAcitivity extends AppCompatActivity  {

    private ListView mListView;
    private ArrayList<Figure> objects;
    private ArrayAdapter arrayAdapter;
    private Button button;
    private ArrayList<Integer> toDelete; ///lista elementów do usuniecia

    ///Korzystamy z tablicy elementów która usuwamy później w main aktywności.
    //Nie opierujemy na tej samej tablicy ponieważ obiekty Box nPath oraz Circle zawierają w sobie obiekty innych klas co uniemożliwia poprawną serializację


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elements_inspector_acitivity);

        button = findViewById(R.id.save);
        mListView = findViewById(R.id.listView);

        initIntent(); ///Pobieranie intentu (tablicy do usuwania)
        initListView();//Inicjalizajca listView
        setListeners();//Usstawianie listenera wysylajacego liste usunietych elementów z tablicy

        Toast.makeText(getApplicationContext(), "Kliknij element na liscie aby usunac obiekt", Toast.LENGTH_LONG).show();

    }

    private void initIntent(){

        Intent intent = getIntent();
        objects = intent.getParcelableArrayListExtra("array"); ///pobranie zserializowanego obiektu tablicy
        toDelete = new ArrayList<>();

    }

    private void initListView(){

        mListView.setOnItemClickListener((parent, view, position, id) -> { ///Po klikniecie na element list view

            toDelete.add(position); ///Dodanie do tablicy usunietych elementów nowego elementu
            objects.remove(position); ///Usuniecie tego elementu z tablicy (która do nas przyszła z list view)
            arrayAdapter.notifyDataSetChanged();///"odświerenie" adaptera
        });

        arrayAdapter = new ArrayAdapter( getApplicationContext(), R.layout.list_table, R.id.name1,  objects  ); ///ustawienie adaptera
        mListView.setAdapter(arrayAdapter);
    }

    private void setListeners(){

        button.setOnClickListener(view -> {

            Intent resultIntent = new Intent(); ///Wyslanie listy usunietych elementów
            resultIntent.putExtra("arrayResult", toDelete);
            setResult(RESULT_OK, resultIntent);
            finish();

        });

    }


}
