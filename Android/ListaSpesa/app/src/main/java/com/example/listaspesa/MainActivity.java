package com.example.listaspesa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etNumber;
    private Button btAdd;
    private ListView lv;

    private ListaSpesaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        btAdd = findViewById(R.id.btAdd);
        lv = findViewById(R.id.lv);

        adapter = new ListaSpesaAdapter(this);
        lv.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String name = etName.getText().toString();
                int number = Integer.parseInt(etNumber.getText().toString());
                adapter.addElement(name, number);
                etName.setText("");
            }
        });
        /*
        ArrayList<String> dati = new ArrayList<String>();
        dati.add("Olio");
        dati.add("Birra");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dati); //R.id diversi

        lv.setAdapter(adapter);

        */
    }
}