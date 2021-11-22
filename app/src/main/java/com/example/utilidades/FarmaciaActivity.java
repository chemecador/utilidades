package com.example.utilidades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.utilidades.db.ConstantesDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FarmaciaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmacia);
    }
    /*public class Tarea extends AsyncTask<String, Void, Void> {
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
                jsonArray = json.getJSONArray("features");

                String titulo;
                String link;
                String coordenadas;
                Punto punto;
                for (int i = 0; i < jsonArray.length(); i++) {
                    titulo = jsonArray.getJSONObject(i).getJSONObject("properties").getString("title");
                    link = jsonArray.getJSONObject(i).getJSONObject("properties").getString("link");
                    coordenadas = jsonArray.getJSONObject(i).getJSONObject("geometry").getString("coordinates");
                    coordenadas = coordenadas.substring(1, coordenadas.length() - 1);
                    String latlong[] = coordenadas.split(",");

                    punto = new Punto();
                    punto.setNombre(titulo);
                    punto.setLink(link);
                    punto.setLatitud(DeUMTSaLatLng(Double.parseDouble(latlong[0]),
                            Double.parseDouble(latlong[1])).getLat());
                    punto.setLongitud(DeUMTSaLatLng(Double.parseDouble(latlong[0]),
                            Double.parseDouble(latlong[1])).getLng());
                    puntos.add(punto);

                    System.out.println(punto.getNombre());
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

            pintarPuntos();
        }
    }*/
}