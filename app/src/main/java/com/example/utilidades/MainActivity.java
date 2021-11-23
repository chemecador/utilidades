package com.example.utilidades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button calculadora;
    Button conversor;
    Button listaCompra;
    Button farmacias;
    Button conecta;
    Button conectaAmigos;
    Button tresEnRaya;
    Button tresEnRayaAmigos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculadora = findViewById(R.id.bMainCalculadora);
        calculadora.setOnClickListener(this);

        conversor = findViewById(R.id.bMainConversor);
        conversor.setOnClickListener(this);

        listaCompra = findViewById(R.id.bMainListaCompra);
        listaCompra.setOnClickListener(this);

        farmacias = findViewById(R.id.bMainFarmacias);
        farmacias.setOnClickListener(this);

        conecta = findViewById(R.id.bMainConecta);
        conecta.setOnClickListener(this);

        conectaAmigos = findViewById(R.id.bMainConectaAmigo);
        conectaAmigos.setOnClickListener(this);

        tresEnRaya = findViewById(R.id.bMainTres);
        tresEnRaya.setOnClickListener(this);

        tresEnRayaAmigos = findViewById(R.id.bMainTresAmigo);
        tresEnRayaAmigos.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bMainCalculadora:
                Intent iCalc = new Intent(this, CalculadoraActivity.class);
                startActivity(iCalc);
                break;
            case R.id.bMainConversor:
                Intent iConv = new Intent(this, ConversorActivity.class);
                startActivity(iConv);
                break;
            case R.id.bMainListaCompra:
                Intent iList = new Intent(this, ListaCompraActivity.class);
                startActivity(iList);
                break;
            case R.id.bMainFarmacias:
                Intent iFarm = new Intent(this, FarmaciaActivity.class);
                startActivity(iFarm);
                break;
            case R.id.bMainConecta:
                Intent iCone = new Intent(this, ConectaActivity.class);
                startActivity(iCone);
                break;
            case R.id.bMainConectaAmigo:
                Intent iConeAmig = new Intent(this, ConectaAmigosActivity.class);
                startActivity(iConeAmig);
                break;
            case R.id.bMainTres:
                Intent iTres = new Intent(this, TresEnRayaActivity.class);
                startActivity(iTres);
                break;
            case R.id.bMainTresAmigo:
                Intent iTresAmig = new Intent(this, TresEnRayaAmigosActivity.class);
                startActivity(iTresAmig);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*Método que se ejecuta cuando se va a crear la barra de menú
         * de la activity (la barra superior)
         */
        getMenuInflater().inflate(R.menu.main_menu, menu);//le digo al inflater correspondiente cual es el menú que debe 'inflar'
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*Método que se ejecuta cuando el usuario selecciona un elemento
         * de la barra de menú de la aplicación*/
        switch (item.getItemId()) {/*Según la opción seleccionada ejecutaré un código u otro*/
            case R.id.menu_preferencias:
                startActivity(new Intent(this, PreferencesActivity.class)); //lanzo la Activity de preferencias
                return true;

            case R.id.menu_acercade:
                Toast.makeText(this, "Aplicación desarrollada por Alejandro Gata"
                        , Toast.LENGTH_LONG).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}