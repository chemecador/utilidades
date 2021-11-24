package com.example.utilidades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class ConectaActivity extends AppCompatActivity implements View.OnClickListener {
    View c0, c1, c2, c3, c4, c5, c6;
    Boolean tuTurno;

    ArrayList<ImageView> fichas;

    Integer[] cantidadFichas; //cantidad de fichas en cada columna

    private static final int CANTIDAD_FILAS = 6;
    private static final int CANTIDAD_COLUMNAS = 7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conecta);
        cantidadFichas = new Integer[]{0, 0, 0, 0, 0, 0, 0}; //por defecto, 0

        Log.i("****", String.valueOf(cantidadFichas.length));
        c0 = findViewById(R.id.col0);
        c1 = findViewById(R.id.col1);
        c2 = findViewById(R.id.col2);
        c3 = findViewById(R.id.col3);
        c4 = findViewById(R.id.col4);
        c5 = findViewById(R.id.col5);
        c6 = findViewById(R.id.col6);


        c0.setOnClickListener(this);
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);
        c6.setOnClickListener(this);

        Random r = new Random();
        tuTurno = r.nextBoolean(); //empieza jugando de manera aleatoria

        if (!tuTurno) {
            jugarCPU();
        }

        fichas = new ArrayList<>();
        for (int i = 0; i < CANTIDAD_COLUMNAS; i++) {
            for (int j = 0; j < CANTIDAD_FILAS; j++) {
                String buttonID = "f" + i + "" + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                ImageView ficha = findViewById(resID);
                fichas.add(ficha);
            }
        }
    }

    private void jugarCPU() {
        if (lleno()) {

        } else {
            int pos = (int) (Math.random() * 7);
            while (cantidadFichas[pos] == CANTIDAD_FILAS) {
                pos = (int) (Math.random() * 7);
            }
            colocarFicha(pos, tuTurno);
            this.tuTurno = true;
        }
    }

    private void colocarFicha(int pos, Boolean tuTurno) {
        switch (pos) {
            case 0:
                if (cantidadFichas[0] < CANTIDAD_FILAS) {
                    if (tuTurno)
                        fichas.get(0 * CANTIDAD_FILAS + cantidadFichas[0]).setImageResource(R.drawable.amarillo);
                    else
                        fichas.get(0 * CANTIDAD_FILAS + cantidadFichas[0]).setImageResource(R.drawable.rojo);
                }
                break;
            case 1:
                if (cantidadFichas[1] < CANTIDAD_FILAS) {
                    if (tuTurno)
                        fichas.get(1 * CANTIDAD_FILAS + cantidadFichas[1]).setImageResource(R.drawable.amarillo);
                    else
                        fichas.get(0 * CANTIDAD_FILAS + cantidadFichas[0]).setImageResource(R.drawable.rojo);
                }
                break;
            case 2:
                if (cantidadFichas[2] < CANTIDAD_FILAS) {
                    if (tuTurno)
                        fichas.get(2 * CANTIDAD_FILAS + cantidadFichas[2]).setImageResource(R.drawable.amarillo);
                    else
                        fichas.get(0 * CANTIDAD_FILAS + cantidadFichas[0]).setImageResource(R.drawable.rojo);
                }
                break;
            case 3:
                if (cantidadFichas[3] < CANTIDAD_FILAS) {
                    if (tuTurno)
                        fichas.get(3 * CANTIDAD_FILAS + cantidadFichas[3]).setImageResource(R.drawable.amarillo);
                    else
                        fichas.get(0 * CANTIDAD_FILAS + cantidadFichas[0]).setImageResource(R.drawable.rojo);
                }
                break;
            case 4:
                if (cantidadFichas[4] < CANTIDAD_FILAS) {
                    if (tuTurno)
                        fichas.get(4 * CANTIDAD_FILAS + cantidadFichas[4]).setImageResource(R.drawable.amarillo);
                    else
                        fichas.get(0 * CANTIDAD_FILAS + cantidadFichas[0]).setImageResource(R.drawable.rojo);
                }
                break;
            case 5:
                if (cantidadFichas[5] < CANTIDAD_FILAS) {
                    if (tuTurno)
                        fichas.get(5 * CANTIDAD_FILAS + cantidadFichas[5]).setImageResource(R.drawable.amarillo);
                    else
                        fichas.get(0 * CANTIDAD_FILAS + cantidadFichas[0]).setImageResource(R.drawable.rojo);
                }
                break;
            case 6:
                if (cantidadFichas[6] < CANTIDAD_FILAS) {
                    if (tuTurno)
                        fichas.get(6 * CANTIDAD_FILAS + cantidadFichas[6]).setImageResource(R.drawable.amarillo);
                    else
                        fichas.get(0 * CANTIDAD_FILAS + cantidadFichas[0]).setImageResource(R.drawable.rojo);
                }
                break;
        }
        cantidadFichas[pos]++;
    }

    private boolean lleno() {
        for (int i = 0; i < cantidadFichas.length; i++) {
            if (cantidadFichas[i] < CANTIDAD_FILAS) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        /* Resultado de la partida:
            x = -1 : has perdido
            x =  0 : empate
            x =  1 : has ganado
            x =  2 : partida no finalizada
        */

        int x = 2; // por defecto, no finalizada
        switch (view.getId()) {
            case R.id.col0:
                colocarFicha(0, tuTurno);
                this.tuTurno = false; //turno del rival
                if ((x = comprobarFinal()) != 2) { //si la partida ha finalizado...
                    resultado(x); //llamar al método que muestra por pantalla al ganador
                } else { // si no ha finalizado
                    jugarCPU(); //juega la CPU
                }
                break;
            case R.id.col1:
                colocarFicha(1, tuTurno);
                this.tuTurno = false;
                if ((x = comprobarFinal()) != 2) {
                    resultado(x);
                } else {
                    jugarCPU();
                }
                break;
            case R.id.col2:
                colocarFicha(2, tuTurno);
                this.tuTurno = false;
                if ((x = comprobarFinal()) != 2) {
                    resultado(x);
                } else {
                    jugarCPU();
                }
                break;
            case R.id.col3:
                colocarFicha(3, tuTurno);
                this.tuTurno = false;
                if ((x = comprobarFinal()) != 2) {
                    resultado(x);
                } else {
                    jugarCPU();
                }
                break;
            case R.id.col4:
                colocarFicha(4, tuTurno);
                this.tuTurno = false;
                if ((x = comprobarFinal()) != 2) {
                    resultado(x);
                } else {
                    jugarCPU();
                }
                break;
            case R.id.col5:
                colocarFicha(5, tuTurno);
                this.tuTurno = false;
                if ((x = comprobarFinal()) != 2) {
                    resultado(x);
                } else {
                    jugarCPU();
                }
                break;
            case R.id.col6:
                colocarFicha(6, tuTurno);
                this.tuTurno = false;
                if ((x = comprobarFinal()) != 2) {
                    resultado(x);
                } else {
                    jugarCPU();
                }
                break;
        }
    }

    private void resultado(int r) {
        if (r == 1) {
            Toast.makeText(getApplicationContext(),
                    "¡Has ganado!",
                    Toast.LENGTH_SHORT).show();
        } else if (r == -1) {
            Toast.makeText(getApplicationContext(),
                    "¡Has perdido!",
                    Toast.LENGTH_SHORT).show();
        } else if (r == 0) {
            Toast.makeText(getApplicationContext(),
                    "¡Empate!",
                    Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    private int comprobarFinal() {
        return 2;
    }
}