package com.example.utilidades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class ConectaActivity extends AppCompatActivity implements View.OnClickListener {
    private View c0, c1, c2, c3, c4, c5, c6;
    private View noTocar;
    private Boolean tuTurno;
    private ArrayList<ImageView> fichas;
    private Integer[] cantidadFichas; //cantidad de fichas en cada columna
    private Integer[][] t; //tablero con las fichas

    private static final int CANTIDAD_FILAS = 6;
    private static final int CANTIDAD_COLUMNAS = 7;
    private boolean partidaTerminada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conecta);
        inicializar();

    }

    private void inicializar() {

        iniciarTablero();

        c0 = findViewById(R.id.col0);
        c1 = findViewById(R.id.col1);
        c2 = findViewById(R.id.col2);
        c3 = findViewById(R.id.col3);
        c4 = findViewById(R.id.col4);
        c5 = findViewById(R.id.col5);
        c6 = findViewById(R.id.col6);

        noTocar = findViewById(R.id.c4NoTocar);

        c0.setOnClickListener(this);
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);
        c6.setOnClickListener(this);
        noTocar.setOnClickListener(this);

        this.partidaTerminada = false;
        this.tuTurno = true;
        this.fichas = new ArrayList<>();

        for (int i = 0; i < CANTIDAD_COLUMNAS; i++) {
            for (int j = 0; j < CANTIDAD_FILAS; j++) {
                String buttonID = "f" + i + "" + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                ImageView ficha = findViewById(resID);
                this.fichas.add(ficha);
            }
        }
    }

    private void iniciarTablero() {

        //por defecto, todas las casillas a 0
        this.cantidadFichas = new Integer[]{0, 0, 0, 0, 0, 0, 0};
        this.t = new Integer[CANTIDAD_FILAS][CANTIDAD_COLUMNAS];
        for (int i = 0; i < CANTIDAD_FILAS; i++) {
            for (int j = 0; j < CANTIDAD_COLUMNAS; j++) {
                this.t[i][j] = 0;
            }
        }
    }

    private void jugarCPU() {
        if (lleno()) {
            resultado(2);
        } else {
            //coloca la ficha en una columna aleatoria
            int pos = (int) (Math.random() * 7);
            //comprueba que esa columna no est?? llena
            while (this.cantidadFichas[pos] == CANTIDAD_FILAS) {
                pos = (int) (Math.random() * 7);
            }
            if (colocarFicha(pos, tuTurno)) { //si ha podido colocar bien la ficha...
                this.tuTurno = true; //cambia el turno
            } else {
                //nunca deber??a llegar aqu??
                Toast.makeText(getApplicationContext(),
                        "Error al colocar la ficha de la CPU. Por favor, contacta con el desarrollador.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean colocarFicha(int pos, Boolean tuTurno) {
        for (int i = 0; i < CANTIDAD_COLUMNAS; i++) {
            if (i == pos) {
                if (this.cantidadFichas[i] < CANTIDAD_FILAS) {
                    int indice = i * CANTIDAD_FILAS + this.cantidadFichas[i];
                    if (tuTurno) {
                        this.fichas.get(indice).setImageResource(R.drawable.amarillo);
                        this.t[cantidadFichas[pos]][pos] = 1;
                    } else {
                        this.fichas.get(indice).setImageResource(R.drawable.rojo);
                        this.t[cantidadFichas[pos]][pos] = -1;
                    }
                    this.cantidadFichas[pos]++;
                    return true;
                }
                return false;
            }
        }
        //nunca deber??a llegar aqu??
        Toast.makeText(getApplicationContext(),
                "Error al colocar la ficha. Por favor, contacta con el desarrollador.",
                Toast.LENGTH_SHORT).show();
        return false;
    }

    //devuelve true si el tablero est?? lleno
    private boolean lleno() {
        for (int i = 0; i < this.cantidadFichas.length; i++) {
            if (this.cantidadFichas[i] < CANTIDAD_FILAS) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onClick(View view) {

        if (!partidaTerminada) {


        /* Resultados posibles de la partida:
            x = -1 : has perdido
            x =  0 : empate
            x =  1 : has ganado
            x =  2 : partida no finalizada
        */

            int x = 2; // por defecto, no finalizada
            switch (view.getId()) {
                case R.id.col0:
                    if (colocarFicha(0, tuTurno)) { //si ha podido colocar la ficha
                        this.tuTurno = false; //se cambia el turno //si no, no hace nada
                        if ((x = comprobarFinal()) != 2) { //si la partida ha finalizado...
                            resultado(x); //llamar al m??todo que muestra por pantalla al ganador
                        } else { // si no ha finalizado
                            jugarCPU(); //juega la CPU
                        }
                    }
                    break;
                case R.id.col1:
                    if (colocarFicha(1, tuTurno)) {
                        this.tuTurno = false;
                        if ((x = comprobarFinal()) != 2) {
                            resultado(x);
                        } else {
                            jugarCPU();
                        }
                    }
                    break;
                case R.id.col2:
                    if (colocarFicha(2, tuTurno)) {
                        this.tuTurno = false;
                        if ((x = comprobarFinal()) != 2) {
                            resultado(x);
                        } else {
                            jugarCPU();
                        }
                    }
                    break;
                case R.id.col3:
                    if (colocarFicha(3, tuTurno)) {
                        this.tuTurno = false;

                        if ((x = comprobarFinal()) != 2) {
                            resultado(x);
                        } else {
                            jugarCPU();
                        }
                    }
                    break;
                case R.id.col4:
                    if (colocarFicha(4, tuTurno)) {
                        this.tuTurno = false;

                        if ((x = comprobarFinal()) != 2) {
                            resultado(x);
                        } else {
                            jugarCPU();
                        }
                    }
                    break;
                case R.id.col5:
                    if (colocarFicha(5, tuTurno)) {
                        this.tuTurno = false;

                        if ((x = comprobarFinal()) != 2) {
                            resultado(x);
                        } else {
                            jugarCPU();
                        }
                    }
                    break;
                case R.id.col6:
                    if (colocarFicha(6, tuTurno)) {
                        this.tuTurno = false;

                        if ((x = comprobarFinal()) != 2) {
                            resultado(x);
                        } else {
                            jugarCPU();
                        }
                    }
                    break;
                default:
                    Toast.makeText(getApplicationContext(),
                            "Toca arriba en la columna que deseas colocar la ficha",
                            Toast.LENGTH_SHORT).show();
                    break;
            }
            if (x == 2) { //si la partida no ha finalizado antes de jugar la CPU
                if ((x = comprobarFinal()) != 2) { //y ha finalizado despu??s de jugar la CPU
                    resultado(x); // se notifica el resultado
                }
            }
        }
    }

    private void resultado(int r) {
        if (r == 1) {
            Toast.makeText(getApplicationContext(),
                    "??Has ganado!",
                    Toast.LENGTH_SHORT).show();
            partidaTerminada = true;
        } else if (r == -1) {
            Toast.makeText(getApplicationContext(),
                    "??Has perdido!",
                    Toast.LENGTH_SHORT).show();
            partidaTerminada = true;
        } else if (r == 0) {
            Toast.makeText(getApplicationContext(),
                    "??Empate!",
                    Toast.LENGTH_SHORT).show();
            partidaTerminada = true;
        }
    }

    private int comprobarFinal() {
        // comprobar horizontal
        for (int i = 0; i < CANTIDAD_FILAS; i++) {
            for (int j = 0; j < CANTIDAD_COLUMNAS - 3; j++) {
                if (this.t[i][j] == this.t[i][j + 1] &&
                        this.t[i][j] == this.t[i][j + 2] &&
                        this.t[i][j] == this.t[i][j + 3] &&
                        this.t[i][j] != 0) {
                    return this.t[i][j];
                }
            }
        }
        // comprobar vertical
        for (int i = 0; i < CANTIDAD_FILAS - 3; i++) {
            for (int j = 0; j < CANTIDAD_COLUMNAS; j++) {
                if (this.t[i][j] == this.t[i + 1][j] &&
                        this.t[i][j] == this.t[i + 2][j] &&
                        this.t[i][j] == this.t[i + 3][j] &&
                        this.t[i][j] != 0) {
                    return this.t[i][j];
                }
            }
        }
        // comprobar diagonal ascendente
        for (int i = 0; i < CANTIDAD_FILAS - 3; i++) {
            for (int j = 0; j < CANTIDAD_COLUMNAS - 3; j++) {
                if (this.t[i][j] == this.t[i + 1][j + 1] &&
                        this.t[i][j] == this.t[i + 2][j + 2] &&
                        this.t[i][j] == this.t[i + 3][j + 3] &&
                        this.t[i][j] != 0) {
                    return this.t[i][j];
                }
            }
        }
        // comprobar diagonal descendente
        for (int i = 3; i < CANTIDAD_FILAS; i++) {
            for (int j = 0; j < CANTIDAD_COLUMNAS - 3; j++) {
                if (this.t[i][j] == this.t[i - 1][j + 1] &&
                        this.t[i][j] == this.t[i - 2][j + 2] &&
                        this.t[i][j] == this.t[i - 3][j + 3] &&
                        this.t[i][j] != 0) {
                    return this.t[i][j];
                }
            }
        }
        return 2;
    }
}