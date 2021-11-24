package com.example.utilidades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class TresEnRayaActivity extends AppCompatActivity implements View.OnClickListener {

    Boolean tuTurno;

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9;

    Integer[] t = new Integer[]{
            0, 0, 0,
            0, 0, 0,
            0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tres_en_raya);

        Random r = new Random();
        tuTurno = r.nextBoolean(); //empieza jugando de manera aleatoria

        b1 = findViewById(R.id.bTres1);
        b2 = findViewById(R.id.bTres2);
        b3 = findViewById(R.id.bTres3);
        b4 = findViewById(R.id.bTres4);
        b5 = findViewById(R.id.bTres5);
        b6 = findViewById(R.id.bTres6);
        b7 = findViewById(R.id.bTres7);
        b8 = findViewById(R.id.bTres8);
        b9 = findViewById(R.id.bTres9);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);

        if (!tuTurno) {
            jugarCPU();
        }
    }

    private void jugarCPU() {
        if (lleno()) {
            resultado(0);
        } else {
            int pos = (int) (Math.random() * 9);
            while (t[pos] != 0) {
                pos = (int) (Math.random() * 9);
            }
            t[pos] = -1;
            colocarFicha(pos, tuTurno);
            this.tuTurno = true;
        }
    }

    private boolean lleno() {
        for (int i = 0; i < t.length; i++) {
            if (t[i] == 0)
                return false;
        }
        return true;
    }

    private int comprobarFinal() {
        //comprobar filas
        for (int i = 0; i < t.length; i++) {
            if (t[0] == t[1] && t[0] == t[2]) {
                if (t[0] != 0) {
                    return t[0];
                }
            } else if (t[3] == t[4] && t[3] == t[5]) {
                if (t[3] != 0) {
                    return t[3];
                }
            } else if (t[6] == t[7] && t[6] == t[8]) {
                if (t[6] != 0) {
                    return t[6];
                }
            }
        }

        //comprobar columnas
        for (int i = 0; i < t.length; i++) {
            if (t[0] == t[3] && t[0] == t[6]) {
                if (t[0] != 0) {
                    return t[0];
                }
            } else if (t[1] == t[4] && t[1] == t[7]) {
                if (t[1] != 0) {
                    return t[1];
                }
            } else if (t[2] == t[5] && t[2] == t[8]) {
                if (t[2] != 0) {
                    return t[2];
                }
            }
        }

        //comprobar diagonales
        for (int i = 0; i < t.length; i++) {
            if (t[0] == t[4] && t[0] == t[8]) {
                if (t[0] != 0) {
                    return t[0];
                }
            } else if (t[2] == t[4] && t[2] == t[6]) {
                if (t[2] != 0) {
                    return t[2];
                }
            }
        }
        if (lleno()) {
            return 0;
        }
        return 2;
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

    private void colocarFicha(int pos, Boolean tuTurno) {

        if (tuTurno) {
            t[pos] = 1;
            switch (pos) {
                case 0:
                    b1.setText("X");
                    break;
                case 1:
                    b2.setText("X");
                    break;
                case 2:
                    b3.setText("X");
                    break;
                case 3:
                    b4.setText("X");
                    break;
                case 4:
                    b5.setText("X");
                    break;
                case 5:
                    b6.setText("X");
                    break;
                case 6:
                    b7.setText("X");
                    break;
                case 7:
                    b8.setText("X");
                    break;
                case 8:
                    b9.setText("X");
                    break;
            }
        } else {
            t[pos] = -1;
            switch (pos) {
                case 0:
                    b1.setText("O");
                    break;
                case 1:
                    b2.setText("O");
                    break;
                case 2:
                    b3.setText("O");
                    break;
                case 3:
                    b4.setText("O");
                    break;
                case 4:
                    b5.setText("O");
                    break;
                case 5:
                    b6.setText("O");
                    break;
                case 6:
                    b7.setText("O");
                    break;
                case 7:
                    b8.setText("O");
                    break;
                case 8:
                    b9.setText("O");
                    break;
            }
        }
    }


    @Override
    public void onClick(View v) {
        /* Resultado de la partida:
            x = -1 : has perdido
            x =  0 : empate
            x =  1 : has ganado
            x =  2 : partida no finalizada
        */

        int x = 2; // por defecto, no finalizada
        switch (v.getId()) {
            case R.id.bTres1:
                if (t[0] == 0) {
                    colocarFicha(0, tuTurno); //jugar
                    this.tuTurno = false; //turno del rival
                    if ((x = comprobarFinal()) != 2) { //si la partida ha finalizado...
                        resultado(x); //llamar al método que muestra por pantalla al ganador
                    } else { // si no ha finalizado
                        jugarCPU(); //juega la CPU
                    }
                }
                break;
            case R.id.bTres2:
                if (t[1] == 0) {
                    colocarFicha(1, tuTurno);
                    this.tuTurno = false;
                    if ((x = comprobarFinal()) != 2) {
                        resultado(x);
                    } else {
                        jugarCPU();
                    }
                }
                break;
            case R.id.bTres3:
                if (t[2] == 0) {
                    colocarFicha(2, tuTurno);
                    this.tuTurno = false;
                    if ((x = comprobarFinal()) != 2) {
                        resultado(x);
                    } else {
                        jugarCPU();
                    }
                }
                break;
            case R.id.bTres4:
                if (t[3] == 0) {
                    colocarFicha(3, tuTurno);
                    this.tuTurno = false;
                    if ((x = comprobarFinal()) != 2) {
                        resultado(x);
                    } else {
                        jugarCPU();
                    }
                }
                break;
            case R.id.bTres5:
                if (t[4] == 0) {
                    colocarFicha(4, tuTurno);
                    this.tuTurno = false;
                    if ((x = comprobarFinal()) != 2) {
                        resultado(x);
                    } else {
                        jugarCPU();
                    }
                }
                break;
            case R.id.bTres6:
                if (t[5] == 0) {
                    colocarFicha(5, tuTurno);
                    this.tuTurno = false;
                    if ((x = comprobarFinal()) != 2) {
                        resultado(x);
                    } else {
                        jugarCPU();
                    }
                }
                break;
            case R.id.bTres7:
                if (t[6] == 0) {
                    colocarFicha(6, tuTurno);
                    this.tuTurno = false;
                    if ((x = comprobarFinal()) != 2) {
                        resultado(x);
                    } else {
                        jugarCPU();
                    }
                }
                break;
            case R.id.bTres8:
                if (t[7] == 0) {
                    colocarFicha(7, tuTurno);
                    this.tuTurno = false;
                    if ((x = comprobarFinal()) != 2) {
                        resultado(x);
                    } else {
                        jugarCPU();
                    }
                }
                break;
            case R.id.bTres9:
                if (t[8] == 0) {
                    colocarFicha(8, tuTurno);
                    this.tuTurno = false;
                    if ((x = comprobarFinal()) != 2) {
                        resultado(x);
                    } else {
                        jugarCPU();
                    }
                }
                break;
        }
        if (x == 2) { //si la partida no ha finalizado antes de jugar la CPU
            if ((x = comprobarFinal()) != 2) { //y ha finalizado después de jugar la CPU
                resultado(x); // se notifica el resultado
            }
        }
    }
}