package com.example.infoamigos3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {
//Cero misterio
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();

        TextView tnombre=(TextView)findViewById(R.id.tNombreDetail);
        tnombre.setText(intent.getStringExtra("NOMBRE"));

        TextView tmail=(TextView)findViewById(R.id.tMailDetail);
        tmail.setText(intent.getStringExtra("MAIL"));

        TextView ttlf=(TextView)findViewById(R.id.tTlfnDetail);
        ttlf.setText(intent.getStringExtra("FIJO"));

        TextView tdeuda=(TextView)findViewById(R.id.tDeudaDetail);
        tdeuda.setText(((Float)intent.getFloatExtra("DEUDA",0)).toString());
    }
}
