package com.example.utilidades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView iv1, iv2, iv3;//contenedores para las imágenes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        iv1=(ImageView)findViewById(R.id.ivFoto1Image);
        iv2=(ImageView)findViewById(R.id.ivFoto2Image);
        iv3=(ImageView)findViewById(R.id.ivFoto3Image);

        //creo BitMaps con las imágenes de Drawable y las coloco en los contenedores
        Bitmap miBitMap= BitmapFactory.decodeResource(getResources(), R.drawable.comida);
        iv1.setImageBitmap(miBitMap);
        //iv1.setImageDrawable(getResources().getDrawable(R.drawable.av1));
        miBitMap= BitmapFactory.decodeResource(getResources(), R.drawable.bebida);
        iv2.setImageBitmap(miBitMap);
        //iv2.setImageDrawable(getResources().getDrawable(R.drawable.av3));
        miBitMap= BitmapFactory.decodeResource(getResources(), R.drawable.otros);
        iv3.setImageBitmap(miBitMap);
        //iv3.setImageDrawable(getResources().getDrawable(R.drawable.av4));

        //pongo listener a todas las fotos para poder interactuar con ellas
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent i=getIntent();//preparo el intent, ya que independientemente de la foto que se haya pulsado voy a tener que ejecutar una nueva Activity
        switch(v.getId()){//Según la imagien pulsada, añado el número correspondiente al intent
            case R.id.ivFoto1Image:
                i.putExtra("SELECCION",1);//Añado un par clave/valor = "SELECCION"/1
                this.setResult(RESULT_OK,i);//Esta Activity ha sido ejecutada con un ForResult, por lo tanto se espera de ella que devuelva un código de resultado. Diremos que ha ido OK
                finish();//finalizamos la Activity
                break;
            case R.id.ivFoto2Image:
                i.putExtra("SELECCION",2);
                this.setResult(RESULT_OK,i);
                finish();
                break;
            case R.id.ivFoto3Image:
                i.putExtra("SELECCION",3);
                this.setResult(RESULT_OK,i);
                finish();
                break;
        }
    }
}