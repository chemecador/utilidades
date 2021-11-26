package com.example.utilidades;

import static com.example.utilidades.util.Util.solicitarPermiso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import java.util.List;

public class FarmaciaActivity extends AppCompatActivity implements View.OnClickListener, LocationListener {

    private static final int SOLICITUD_PERMISO_LOCALIZACION = 3;
    private ArrayList<Farmacia> listaFarmacias;
    private ListView lv;
    private FarmaciaAdapter adapter;
    private Button comprobar;
    private LocationManager gestor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmacia);
        listaFarmacias = new ArrayList<>();
        Tarea tarea = new Tarea();
        tarea.execute(ConstantesDB.URL);
        lv = findViewById(R.id.lvFarmacias);

        comprobar = findViewById(R.id.bFarmacia);
        comprobar.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new FarmaciaAdapter(this, listaFarmacias);
        lv.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bFarmacia:
                gestor = (LocationManager) getSystemService(LOCATION_SERVICE);
                obtenerLocalizacion();
                break;
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SOLICITUD_PERMISO_LOCALIZACION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                obtenerLocalizacion();
            } else {
                Toast.makeText(this, "No puedo mostrarte las farmacias en tu zona sin este permiso",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    private void obtenerLocalizacion() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(),
                    "Mostrando las farmacias en tu zona",
                    Toast.LENGTH_SHORT).show();

            onResume();
        } else {
            solicitarPermiso(Manifest.permission.ACCESS_FINE_LOCATION,
                    "Necesito acceder a la ubicación",
                    SOLICITUD_PERMISO_LOCALIZACION, this);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {

    }

    @Override
    public void onFlushComplete(int requestCode) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

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
                Toast.makeText(getApplicationContext(),
                        "Ha ocurrido un error al leer el JSON de las Farmacias",
                        Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

}