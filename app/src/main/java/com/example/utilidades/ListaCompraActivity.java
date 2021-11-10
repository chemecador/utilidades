package com.example.utilidades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListaCompraActivity extends AppCompatActivity implements View.OnClickListener {

    Button anadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compra);

        anadir = findViewById(R.id.bListaAnadir);
        anadir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bListaAnadir:
                Intent i = new Intent (this, AddActivity.class);
                startActivity(i);
        }
    }
}