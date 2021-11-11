package com.example.utilidades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();

        TextView tnombre=(TextView)findViewById(R.id.tDetailNombre);
        tnombre.setText(intent.getStringExtra("NOMBRE"));

        TextView tprecio=(TextView)findViewById(R.id.tDetailPrecio);
        tprecio.setText(((Double)intent.getDoubleExtra("PRECIO",0)).toString());

        TextView tcantidad=(TextView)findViewById(R.id.tDetailCantidad);
        tcantidad.setText(((Integer)intent.getIntExtra("CANTIDAD",0)).toString());
    }
}