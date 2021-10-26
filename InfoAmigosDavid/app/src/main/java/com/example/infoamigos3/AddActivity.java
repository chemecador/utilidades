package com.example.infoamigos3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.infoamigos3.db.Database;
import com.example.infoamigos3.util.Amigo;

/*Activity que nos permitirá rellenar un formulario para insertar un amigo
* en la base de datos con los datos introducidos*/
public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView foto;//Referencia al elemento de interfaz que nos mostrará la foto
    private final int RESULTADO_CARGA_IMAGEN=42;//constante que definimos para comprobar si volvemos del selector de imágenes
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        foto=(ImageView)findViewById(R.id.ivFotoAdd);
        //foto.setImageDrawable(getResources().getDrawable(R.drawable.av1));
       Bitmap miBitMap= BitmapFactory.decodeResource(getResources(), R.drawable.av1);
        foto.setImageBitmap(miBitMap);
        foto.setOnClickListener(this);

        db=new Database(this);

        Button anyadir=findViewById(R.id.bAnyadirAdd);
        anyadir.setOnClickListener(this);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        /*Método que se ejecuta cuando volvemos de una Activity que habíamos lanzado
        * con startActivityForResult. En nuestro caso cuando volvemos de la Activity
        * del selector de imágenes*/
        if(requestCode==RESULTADO_CARGA_IMAGEN && resultCode==RESULT_OK && data!=null){//Si venimos de la activity de cargar imagen, si nos ha devuelto un ok y si hemos recibido datos
            int resultado=data.getExtras().getInt("SELECCION");//Extraigo del 'mensaje' el entero referente a la foto seleccionada que sé que he etiquetado como "SELECCION"

            switch (resultado){//Muestro la foto seleccionada según su número
                case 1:
                    foto.setImageDrawable(getResources().getDrawable(R.drawable.av1));//pongo la imgaen en el imageView
                    break;
                case 2:
                    foto.setImageDrawable(getResources().getDrawable(R.drawable.av3));
                    break;
                case 3:
                    foto.setImageDrawable(getResources().getDrawable(R.drawable.av4));
                    break;
            }

        }
    }

    @Override
    public void onClick(View v) {
        /*Método que se ejecuta cuando el usuario pulsa un elemento
         * de la interfaz que tenga registrado el listener OnClick*/

        switch(v.getId()){
            case R.id.ivFotoAdd://El usuario ha pulsado la foto para ir al selector de imágenes
                Intent intent=new Intent(this,ImageActivity.class);//preparo el intent para ejecutar la nueva activity
                startActivityForResult(intent,RESULTADO_CARGA_IMAGEN);//Como quiero que la activity me devuelva un resultado (la foto que se ha seleccionado) la ejecuto con ForResult y envío un código para cuando me devuelva, saber quién me ha devuelto
                break;
            case R.id.bAnyadirAdd://El usuario ha pulsado el botón que añade un amigo con los datos introducidos
                Amigo amigo= new Amigo();
                //Para cada elemento de interfaz que el usuario tiene que haber rellenado:
                    //1Referencio el elemento
                    //2Asigno su contenido al atributo correspondiente de amigo
                EditText txt=findViewById(R.id.tNombreAdd);
                amigo.setNombreApellidos(txt.getText().toString());
                txt=findViewById(R.id.tMailAdd);
                amigo.setEmail(txt.getText().toString());
                txt=findViewById(R.id.tTlfnAdd);
                amigo.setTlf(txt.getText().toString());
                txt=findViewById(R.id.tMovilAdd);
                amigo.setTlfMovil(txt.getText().toString());
                txt=findViewById(R.id.tDeudaAdd);
                amigo.setDeudas(Float.valueOf(txt.getText().toString()));
                amigo.setFoto(((BitmapDrawable)foto.getDrawable()).getBitmap());
                db.nuevoAmigo(amigo);//Inserto el amigo en la base de datos
                break;

        }

    }
}
