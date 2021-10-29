package com.example.utilidades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        Intent i = new Intent(this,CalculadoraActivity.class);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*Método que se ejecuta cuando se va a crear la barra de menú
         * de la activity (la barra superior)
         */
        getMenuInflater().inflate(R.menu.main_menu,menu);//le digo al inflater correspondiente cual es el menú que debe 'inflar'
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*Método que se ejecuta cuando el usuario selecciona un elemento
         * de la barra de menú de la aplicación*/
        switch(item.getItemId()){/*Según la opción seleccionada ejecutaré un código u otro*/
            case R.id.menu_preferencias:
                return true;

            case R.id.menu_acercade:
                Toast.makeText(this,"Aplicación desarrollada por Alejandro Gata"
                        , Toast.LENGTH_LONG).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}