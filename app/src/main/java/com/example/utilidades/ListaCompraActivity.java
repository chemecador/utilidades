package com.example.utilidades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.utilidades.db.Database;
import com.example.utilidades.util.Producto;

import java.util.ArrayList;

public class ListaCompraActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemLongClickListener,
AdapterView.OnItemClickListener{

    private Button anadir;
    private TextView precioTotal;
    private ArrayList<Producto> listaProductos;
    private Database db;
    private Producto productoSeleccionado;
    private ProductoAdapter adapter;
    private SharedPreferences prefs;
    private ListView lvLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compra);
        prefs = PreferenceManager.getDefaultSharedPreferences(this); //Consigo la referencia a las preferencias para poder leerlas y escribirlas


        precioTotal = findViewById(R.id.txtListaTotal);

        anadir = findViewById(R.id.bListaAnadir);
        anadir.setOnClickListener(this);

        db = new Database(this);
        listaProductos = new ArrayList<>();
        lvLista = findViewById(R.id.lvListaCompra);

        lvLista.setOnItemLongClickListener(this);//asigno el listener que reaccionará ante al pulsación larga de un elemento a la lista
        lvLista.setOnItemClickListener(this);
        registerForContextMenu(lvLista);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bListaAnadir:
                Intent i = new Intent (this, AddActivity.class);
                startActivity(i);
        }
    }


    @Override
    protected void onResume() {

        super.onResume();
        if (prefs.getBoolean("preferencias_cantidad", false) == false) {
            if (prefs.getBoolean("preferencias_descendente",false) == false){
                listaProductos = db.getProductosNombre(false);
            }
            else {

                listaProductos = db.getProductosNombre(true);
            }
        } else {
            if (prefs.getBoolean("preferencias_descendente",false) == false){

                listaProductos = db.getProductosCantidad(false);
            }
            else {

                listaProductos = db.getProductosCantidad(true);
            }
        }
        adapter=new ProductoAdapter(this, listaProductos);//Creo el adaptador para la lista, que trabajará con el arraylist listaAmigos
        lvLista.setAdapter(adapter); //asigno el adaptador propio al listview
        double total = 0.0;
        for (Producto p : listaProductos){
            double parcial = p.getCantidad() * p.getPrecio();
            total +=parcial;
        }
        precioTotal.setText("Precio total: " + String.valueOf(total));
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        onItemLongClick(parent,view,position, id);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        productoSeleccionado=listaProductos.get(position); //Actualizo el amigo seleccionado al amigo que ha sido pulsado
        ((EditText)findViewById(R.id.tInfoMain)).setText(productoSeleccionado.getNombre());//Muestro el nombre del nuevo amigo seleccionado
        return false;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        /*Método que se ejecuta cuando el usuario selecciona
         * un item de un menú contextual*/
        switch(item.getItemId()){/*discriminamos qué item se ha seleccionado*/
            case R.id.menu_contextual_detalles: //Llamará a otra Activity que me mostrará la información completa del amigo seleccionado
                Intent i=new Intent(this, DetailActivity.class);//Creo el intent que me servirá para llamar a la DetailActivity,
                //Además lo utilizaré para mandar un mensaje a la activity con la información del amigo seleccionado
                productoSeleccionado.putExtraProducto(i); //Añado el mensaje al intent con este método

                startActivity(i);//Lanzo la nueva Activity con el intent que he creado y completado
                break;
            case R.id.menu_contextual_borrar://Borrará el elemento seleccionado de la lista
                db.eliminarProducto(productoSeleccionado);//Llamo al método de la base de datos que borra un amigo

                //onRestart();//actualizo la activty para que el elemento borrado ya no aparezca en la lista
                onResume();
                break;

        }
        return super.onContextItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        /* Método que se ejecutará si hay que crear un menú contextual, si
         * nuestra Activity tiene varios utilizaremos el View v para discriminar
         * qué menú es el apropiado*/
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(
                R.menu.menu_contextual_lista, menu);//Como solo tenemos un elemento con menú contextual le decimos directamente qué menú 'pintar'

    }
}