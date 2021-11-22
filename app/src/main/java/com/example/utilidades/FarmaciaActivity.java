package com.example.utilidades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.utilidades.db.ConstantesDB;
import com.example.utilidades.util.Farmacia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FarmaciaActivity extends AppCompatActivity {

    ArrayList<Farmacia> listaFarmacias;
    ListView lv;
    FarmaciaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmacia);
        listaFarmacias = new ArrayList<>();
        Tarea tarea = new Tarea();
        tarea.execute(ConstantesDB.URL);

        lv = findViewById(R.id.lvFarmacias);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("***********************************", "Hay un total de " + listaFarmacias.size());
        adapter = new FarmaciaAdapter(this,listaFarmacias);
        lv.setAdapter(adapter);

        for (Farmacia farmacia : listaFarmacias){
            Log.i("-----------------",farmacia.getNombre());
        }
    }

    public class Tarea extends AsyncTask<String, Void, Void> {
        private boolean error = false;

        @Override
        protected Void doInBackground(String... strings) {
            String cadena;
            JSONObject json;
            JSONArray jsonArray;

            try {
                URL url = new URL(ConstantesDB.URL);
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String linea = null;
                while ((linea = br.readLine()) != null) {
                    sb.append(linea + "\n");
                }
                conexion.disconnect();
                br.close();
                cadena = sb.toString();

                json = new JSONObject(cadena);
                jsonArray = json.getJSONArray("result");

                for (int i = 0; i < jsonArray.length(); i++) {
                    Farmacia farmacia = new Farmacia();
                    farmacia.setNombre(jsonArray.getJSONObject(i).getString("title"));
                    farmacia.setTlf(jsonArray.getJSONObject(i).getString("telefonos"));
                    farmacia.setDireccion(jsonArray.getJSONObject(i).getString("calle"));
                    listaFarmacias.add(farmacia);
                }

            } catch (IOException ioe) {
                ioe.printStackTrace();
                error = true;
            } catch (JSONException jse) {
                jse.printStackTrace();
                error = true;
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (error) {
                Toast.makeText(getApplicationContext(), "Error!!", Toast.LENGTH_LONG).show();
                return;
            }

        }
    }

}