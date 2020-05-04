package com.example.paintmeandroidprogram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ElementsInspectorAcitivity extends AppCompatActivity  {

    private ListView lv;
    private ArrayList<Figure> objects;
    private ArrayAdapter arAdapter;
    private Button button;
    private ArrayList<Integer> toDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elements_inspector_acitivity);

        button = findViewById(R.id.save);

        Intent intent = getIntent();
        objects = (ArrayList<Figure>) intent.getSerializableExtra("array");
        toDelete = new ArrayList<>();

        lv = findViewById(R.id.listView);
        startLoader();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                toDelete.add(position);
                objects.remove(position);
                arAdapter.notifyDataSetChanged();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent resultIntent = new Intent();
                resultIntent.putExtra("arrayResult", toDelete);
                setResult(RESULT_OK, resultIntent);
                finish();

            }
        });


    }

    private void startLoader(){

        //String[] animalList = {"Lion","Tiger","Monkey","Elephant","Dog","Cat","Camel"};

        arAdapter = new ArrayAdapter( getApplicationContext(), R.layout.list_table, R.id.name1,  objects  );

        lv.setAdapter(arAdapter);
    }


}
