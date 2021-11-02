package com.example.utilidades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

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

        cuadrado = findViewById(R.id.bElevar);
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
        DecimalFormat formateador = new DecimalFormat("####.###");
        double n1 = 0, n2 = 0;
        if (numero1.getText().toString().length() > 0 && numero2.getText().toString().length() > 0) {
            n1 = Double.parseDouble(numero1.getText().toString());
            n2 = Double.parseDouble(numero2.getText().toString());
        }
        switch (view.getId()) {
            case R.id.bSuma:
                if (numero1.getText().toString().length() < 1 || numero2.getText().toString().length() < 1) {
                    Toast.makeText(this, "Introduce los dos sumandos arriba"
                            , Toast.LENGTH_LONG).show();
                } else{
                    resultado.setText(String.valueOf((int) (n1 + n2)));
                }
                break;
            case R.id.bResta:                if (numero1.getText().toString().length() < 1 || numero2.getText().toString().length() < 1) {
                Toast.makeText(this, "Introduce el minuendo y el sustraendo arriba"
                        , Toast.LENGTH_LONG).show();
            } else{
                resultado.setText(String.valueOf((int) (n1 - n2)));
            }
                break;
            case R.id.bMultiplicacion:
                if (numero1.getText().toString().length() < 1 || numero2.getText().toString().length() < 1) {
                    Toast.makeText(this, "Introduce los dos productos arriba"
                            , Toast.LENGTH_LONG).show();
                } else{
                    resultado.setText(String.valueOf((int) (n1 * n2)));
                }
                break;
            case R.id.bDivision:
                if (numero1.getText().toString().length() < 1 || numero2.getText().toString().length() < 1) {
                    Toast.makeText(this, "Introduce el dividendo y el divisor arriba"
                            , Toast.LENGTH_LONG).show();
                } else{
                    if (n2 == 0) {
                        Toast.makeText(this, "No se puede dividir entre 0"
                                , Toast.LENGTH_LONG).show();
                    } else {
                        resultado.setText(String.valueOf(formateador.format(n1 / n2)));
                    }
                }

                break;
            case R.id.bElevar:
                if (numero1.getText().toString().length() < 1) {
                    Toast.makeText(this, "Introduce la base y el exponente"
                            , Toast.LENGTH_LONG).show();
                } else{
                    n1 = Double.parseDouble(numero1.getText().toString());
                    resultado.setText(String.valueOf((int)Math.pow(n1,n2)));
                }
                break;
            case R.id.bRaiz:
                numero2.setText(String.valueOf(""));
                if (numero1.getText().toString().length() < 1) {
                    Toast.makeText(this, "Introduce en el primer hueco el número del que quieres calcular su raíz"
                            , Toast.LENGTH_LONG).show();
                } else{
                    n1 = Double.parseDouble(numero1.getText().toString());
                    resultado.setText(String.valueOf(formateador.format(Math.sqrt(n1))));
                }
                break;
        }
    }
}