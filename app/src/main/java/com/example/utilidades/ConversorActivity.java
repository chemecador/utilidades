package com.example.utilidades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class ConversorActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText cantidad;
    private Spinner spinner1;
    private Spinner spinner2;
    private Button convertir;
    private TextView resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversor);

        cantidad = findViewById(R.id.iCantidad);
        cantidad.setOnClickListener(this);

        spinner1 = findViewById(R.id.conversorSpinner1);
        spinner2 = findViewById(R.id.conversorSpinner2);

        String[] opciones = {"euros", "dolares", "libras"};
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opciones);
        spinner1.setAdapter(adapterSpinner);
        spinner2.setAdapter(adapterSpinner);

        convertir = findViewById(R.id.bConvertir);
        convertir.setOnClickListener(this);

        resultado = findViewById(R.id.tCantidadFinal);
        resultado.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        DecimalFormat formateador = new DecimalFormat("####.###");
        Double antes = 0.0;
        if (cantidad.getText().toString().length() > 0){
            antes = Double.parseDouble(cantidad.getText().toString());
        }
        Double despues = antes;
        switch(v.getId()){
            case R.id.bConvertir:
                if (cantidad.getText().toString().length() < 1) {
                    Toast.makeText(this, "Introduce los dos sumandos arriba"
                        , Toast.LENGTH_LONG).show();
                }
                String divisa1 = spinner1.getSelectedItem().toString();
                String divisa2 = spinner2.getSelectedItem().toString();
                if (divisa1.equals("euros")){
                    if (divisa2.equals("dolares")){
                        despues = antes*1.16;
                    }
                    else if (divisa2.equals("libras")){
                        despues = antes*0.84;

                    }
                }
                else if (divisa1.equals("dolares")){
                    if (divisa2.equals("euros")){
                        despues = antes*0.86;
                    }
                    else if (divisa2.equals("libras")){
                        despues = antes*0.73;
                    }
                }
                else if (divisa1.equals("libras")){
                    if (divisa2.equals("euros")){
                        despues = antes*1.18;
                    }
                    else if (divisa2.equals("dolares")){
                        despues = antes*1.37;
                    }
                }
                String resultadoMostrar = formateador.format(antes) + " " + divisa1 + " son " +
                        String.valueOf(formateador.format(despues) + " " + divisa2);
                resultado.setText(resultadoMostrar);
                break;
        }
    }
}