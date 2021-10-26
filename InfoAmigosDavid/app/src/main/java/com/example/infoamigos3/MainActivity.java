package com.example.infoamigos3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.infoamigos3.db.Database;
import com.example.infoamigos3.util.Amigo;
import com.example.infoamigos3.util.Util;

import java.util.ArrayList;



/*
La MainActivity será la activity que se ejecute cuando lancemos nuestra
aplicación. Esto es así gracias a estas líneas que podemos encontrar en
el Manifest:
<activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
*/

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener,
AdapterView.OnItemClickListener{
    /*Si queremos poder interactuar con los elementos de la interfaz
    * tenemos que implementar los listeners correspondientes. La mejor
    * opción es hacer que la propia Activity implemente las interfaces
    * de dichos listeners*/


    private EditText txt; //Referencia a la caja de texto para saber qué amigo está seleccionado
    private ArrayList<Amigo> listaAmigos; //Contiene los objetos Amigo que hemos leído de la BBDD
    private AmigoAdapter adapter; //Adaptador que le dice a la ListView cómo ha de "pintar" cada elemento que tiene que mostrar.
    private Database db; //Referencia a la base de datos de la aplicación
    private Amigo amigoSeleccionado; //El amigo que se encuentra seleccionado en la ListView
    private SharedPreferences prefs; //Permite acceder e interactuar con las preferencias que hemos programado
    private ListView lvLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      /*El método onCreate se ejecuta siempre que se lanza una Activity, es lo primero que se ejecuta
      * lo utilizaremos para inicializar todo_ lo que necesitemos utilizar*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); /*Vinculo la activity con el layout que le he diseñado, esto se genera solo si creamos la activity mediante el IDE*/

        prefs= PreferenceManager.getDefaultSharedPreferences(this); //Consigo la referencia a las preferencias para poder leerlas y escribirlas

        /*Creo referencias a los elementos de la interfaz con los que voy a tener que
        * interactuar en el código y les añado el listener a los que sirvan para
        * interactuar con el usuario*/
        Button bDeuda=(Button)findViewById(R.id.bDeudaMain);
        bDeuda.setOnClickListener(this); //El listener es this porque la propia activity implementa las interfaces de todos los Listener que necesitamos

        Button bDetalle=(Button)findViewById(R.id.bDetalleMain);
        bDetalle.setOnClickListener(this);

        Button bAnyadir=(Button)findViewById(R.id.bAnyadirMain);
        bAnyadir.setOnClickListener(this);

         txt=(EditText)findViewById(R.id.tInfoMain);

         db=new Database(this); //Consigo la referencia a la base de datos, si no se ha crado aún el constructor la creará
         listaAmigos=new ArrayList<Amigo>();//Creo una referencia para la lista de amigos


        //El código siguiente se creó para añadir amigos a la lista
        //antes de tener la base de datos, por eso ya no se usa
        /*Amigo amigo1=new Amigo("Julio José");
        amigo1.setTlf("666666666");
        amigo1.setTlfMovil("999999999");
        listaAmigos.add(amigo1);
        amigo1=new Amigo();
        amigo1.setNombreApellidos("Ana Francisca");
        amigo1.setTlf("555555555");
        amigo1.setTlfMovil("444444444");
        listaAmigos.add(amigo1);
        for (int i=0; i<10; i++){
            amigo1=new Amigo();
            amigo1.setNombreApellidos(""+i);
            amigo1.setTlf(""+i+""+i+""+i+""+i+""+i+""+i+""+i);
            amigo1.setTlfMovil(""+i+""+i+""+i+""+i+""+i+""+i+""+i+""+i);
            listaAmigos.add(amigo1);
        }
        //
        */
        lvLista=findViewById(R.id.lvListaMain); //Referencia al elemento lista de la interfaz

        lvLista.setOnItemLongClickListener(this);//asigno el listener que reaccionará ante al pulsación larga de un elemento a la lista
        lvLista.setOnItemClickListener(this);
        registerForContextMenu(lvLista); //Con esta instrucción digo que el listview va a tener un menú contextual
            }

    @Override
    protected void onResume() {
        /*Este método se ejecutará cuando el SO vuelva a activar nuestra Activity,
        * lo utilizamos para refrescar la información que puede haberse modificado
        * mientras la activity estaba en segundo plano. En nuestro caso recargaremos
        * la lista por si ha sido modificada. */
        /*Dependiendo del valor de la preferencia 'preferencia_morosos' obtendré
         * la lista de amigos de la base de datos con un método u otro */
        super.onResume();
        if(prefs.getBoolean("preferencia_morosos",false)==false){
            listaAmigos=db.getAmigos();} //Asigno a lista de amigos la lista devuelta por el método de la clase de la base de datos
        else{
            listaAmigos=db.getAmigosD();
        }
       adapter=new AmigoAdapter(this, listaAmigos);//Creo el adaptador para la lista, que trabajará con el arraylist listaAmigos
        lvLista.setAdapter(adapter); //asigno el adaptador propio al listview

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*Método que se ejecuta cuando se va a crear la barra de menú
        * de la activity (la barra superior)
        */
        getMenuInflater().inflate(R.menu.main_menu,menu);//le digo al inflater correspondiente cual es el menú que debe 'inflar'
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*Método que se ejecuta cuando el usuario selecciona un elemento
        * de la barra de menú de la aplicación*/
        switch(item.getItemId()){/*Según la opción seleccionada ejecutaré un código u otro*/
            case R.id.menu_preferencias:
                startActivity(new Intent(this, PreferencesActivity.class)); //lanzo la Activity de preferencias
                return true;

            case R.id.menu_acercade:
                Toast.makeText(this,"Aplicación desarrollada por 2DAM"
                        , Toast.LENGTH_LONG).show();//Creo un 'Toast', un texto que aparecerá en pantalla durante un instante y lo muestro
                break;

        }
        return super.onOptionsItemSelected(item);
    }






    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
       /*Método que se ejecuta cuando el usuario selecciona
       * un item de un menú contextual*/
        switch(item.getItemId()){/*discriminamos qué item se ha seleccionado*/
            case R.id.menu_contextual_detalles: //Llamará a otra Activity que me mostrará la información completa del amigo seleccionado
                Intent i=new Intent(this, DetailActivity.class);//Creo el intent que me servirá para llamar a la DetailActivity,
                //Además lo utilizaré para mandar un mensaje a la activity con la información del amigo seleccionado
                amigoSeleccionado.putExtraAmigo(i); //Añado el mensaje al intent con este método

                startActivity(i);//Lanzo la nueva Activity con el intent que he creado y completado
                break;
            case R.id.menu_contextual_borrar://Borrará el elemento seleccionado de la lista
                db.eliminarAmigo(amigoSeleccionado);//Llamo al método de la base de datos que borra un amigo

                onRestart();//actualizo la activty para que el elemento borrado ya no aparezca en la lista
                break;

        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        /*Método que se ejecutará si hay que crear un menú contextual, si
         * nuestra Activity tiene varios utilizaremos el View v para discriminar
         * qué menú es el apropiado*/
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(
                R.menu.menu_contextual_lista, menu);//Como solo tenemos un elemento con menú contextual le decimos directamente qué menú 'pintar'

    }

    @Override
    public void onClick(View v) {
        /*Método que se ejecuta cuando el usuario pulsa un elemento
        * de la interfaz que tenga registrado el listener OnClick*/
        switch (v.getId()){//Compruebo qué elemento es el pulsado y actúo en consecuencia
            case R.id.bDeudaMain://Muestro los amigos cuya deuda sea > deuda_amigo
                EditText tdeuda=findViewById(R.id.tDeudaMain);
                float deuda_amigo=Float.parseFloat(tdeuda.getText().toString());//obtengo el valor del campo de texto
                    listaAmigos=db.getDeudores(deuda_amigo);//obtengo la lista de amigos de la bbdd
                /*pinto los elementos en el listView*/
                ListView lvLista=findViewById(R.id.lvListaMain);
                adapter=new AmigoAdapter(this, listaAmigos);
                lvLista.setAdapter(adapter);
                break;
            case R.id.bDetalleMain://Lanzo una nueva activity para ver toda la información del amigo seleccionado
                Intent i=new Intent(this, DetailActivity.class);
                amigoSeleccionado.putExtraAmigo(i); //Añado el mensaje al intent con este método
                startActivity(i);

                break;
            case R.id.bAnyadirMain://Lanzo la activity que me permite añadir nuevos amigos a mi base de datos
                Intent intent=new Intent(this,AddActivity.class);
               startActivity(intent);
                break;

        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
      /*Método que se ejecuta cuando el usuario realiza una pulsación larga
      * en un elemento de la lista*/
       amigoSeleccionado=listaAmigos.get(position); //Actualizo el amigo seleccionado al amigo que ha sido pulsado
        ((EditText)findViewById(R.id.tInfoMain)).setText(amigoSeleccionado.getNombreApellidos());//Muestro el nombre del nuevo amigo seleccionado
       return false;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /*Método que se ejecuta cuando el usuario pulsa
         * en un elemento de la lista*/
onItemLongClick(parent,view,position, id);

    }
}
