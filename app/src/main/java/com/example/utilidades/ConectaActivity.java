package com.example.utilidades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ConectaActivity extends AppCompatActivity implements View.OnClickListener {
    View c0,c1,c2,c3,c4,c5;
    ImageView iv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conecta);
        c0 = findViewById(R.id.col0);
        c1 = findViewById(R.id.col1);
        c2 = findViewById(R.id.col2);
        c3 = findViewById(R.id.col3);
        c4 = findViewById(R.id.col4);

        c0.setOnClickListener(this);
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);

        iv1 = findViewById(R.id.f00);
        iv1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.f00:
                //iv1.setImageDrawable(getDrawable(R.id.));
                break;
        }
    }
}