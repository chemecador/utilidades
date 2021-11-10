package com.example.utilidades;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.utilidades.db.Database;
import com.example.utilidades.util.Producto;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView foto;
    private final int RESULTADO_CARGA_IMAGEN = 42;
    private Database db;
    private Button anadir;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        foto=(ImageView)findViewById(R.id.ivFotoAdd);
        //foto.setImageDrawable(getResources().getDrawable(R.drawable.comida));
        Bitmap miBitMap= BitmapFactory.decodeResource(getResources(), R.drawable.comida);
        foto.setImageBitmap(miBitMap);
        foto.setOnClickListener(this);

        db=new Database(this);

        anadir=findViewById(R.id.bAddAnadir);
        anadir.setOnClickListener(this);
        spinner = findViewById(R.id.spinerAdd);

        String[] opciones = {"comida", "bebida", "otros"};
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opciones);
        spinner.setAdapter(adapterSpinner);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULTADO_CARGA_IMAGEN && resultCode == RESULT_OK && data != null) {//Si venimos de la activity de cargar imagen, si nos ha devuelto un ok y si hemos recibido datos
            int resultado = data.getExtras().getInt("SELECCION");//Extraigo del 'mensaje' el entero referente a la foto seleccionada que sé que he etiquetado como "SELECCION"

            switch (resultado) {//Muestro la foto seleccionada según su número
                case 1:
                    foto.setImageDrawable(getResources().getDrawable(R.drawable.comida));//pongo la imgaen en el imageView
                    break;
                case 2:
                    foto.setImageDrawable(getResources().getDrawable(R.drawable.bebida));
                    break;
                case 3:
                    foto.setImageDrawable(getResources().getDrawable(R.drawable.otros));
                    break;
            }

        }
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.ivFotoAdd:
                Intent intent=new Intent(this,ImageActivity.class);//preparo el intent para ejecutar la nueva activity
                startActivityForResult(intent,RESULTADO_CARGA_IMAGEN);//Como quiero que la activity me devuelva un resultado (la foto que se ha seleccionado) la ejecuto con ForResult y envío un código para cuando me devuelva, saber quién me ha devuelto
                break;
            case R.id.bAddAnadir:
                Producto producto = new Producto();

                producto.setTipo("comida");

                EditText txt = findViewById(R.id.tAddNombre);
                producto.setNombre(txt.getText().toString());
                txt = findViewById(R.id.tAddPrecio);
                producto.setPrecio(Double.parseDouble(txt.getText().toString()));

                txt = findViewById(R.id.tAddCantidad);
                producto.setCantidad(Integer.parseInt(txt.getText().toString()));

                producto.setFoto(((BitmapDrawable)foto.getDrawable()).getBitmap());
                db.nuevoProducto(producto);
                break;
        }
    }
}