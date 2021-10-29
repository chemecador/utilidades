package com.example.utilidades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculadoraActivity extends AppCompatActivity implements View.OnClickListener {

    Button suma;
    Button resta;
    Button multiplicacion;
    Button division;
    Button cuadrado;
    Button raiz;

    EditText numero1;
    EditText numero2;
    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        suma = findViewById(R.id.bSuma);
        suma.setOnClickListener(this);

        resta = findViewById(R.id.bResta);
        resta.setOnClickListener(this);

        multiplicacion = findViewById(R.id.bMultiplicacion);
        multiplicacion.setOnClickListener(this);

        division = findViewById(R.id.bDivision);
        division.setOnClickListener(this);

        cuadrado = findViewById(R.id.bCuadrado);
        cuadrado.setOnClickListener(this);

        raiz = findViewById(R.id.bRaiz);
        raiz.setOnClickListener(this);

        numero1 = findViewById(R.id.tNumero1);
        numero1.setOnClickListener(this);

        numero2 = findViewById(R.id.tNumero2);
        numero2.setOnClickListener(this);

        resultado = findViewById(R.id.resultado);
        resultado.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int n1 = 0, n2 = 0;
        if (numero1.getText()!=null && numero2.getText() != null) {
               n1 = Integer.parseInt(numero1.getText().toString());
               n2 = Integer.parseInt(numero2.getText().toString());
        }

        switch (view.getId()){
            case R.id.bSuma:
                resultado.setText(String.valueOf(n1+n2));
                break;
            case R.id.bResta:
                resultado.setText(String.valueOf(n1-n2));
                break;
            case R.id.bMultiplicacion:
                resultado.setText(String.valueOf(n1*n2));
                break;
            case R.id.bDivision:
                if (n2 == 0){
                    Toast.makeText(this,"No se puede dividir entre 0"
                            , Toast.LENGTH_LONG).show();
                }
                else {
                    resultado.setText(String.valueOf(n1/n2));
                }
                break;
            case R.id.bCuadrado:
                break;
            case R.id.bRaiz:
                break;
        }
    }
}