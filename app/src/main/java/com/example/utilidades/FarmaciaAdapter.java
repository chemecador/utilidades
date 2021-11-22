package com.example.utilidades;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.utilidades.util.Farmacia;

import java.util.ArrayList;

public class FarmaciaAdapter extends BaseAdapter {
    private Context context; //El contexto será la activity en la que se esté usando
    private ArrayList<Farmacia> listaFarmacias; //La lista de elementos a mostrar
    private LayoutInflater inflater;//El encargado de distribuir/pintar los elementos en la interfaz

    public FarmaciaAdapter(Activity context, ArrayList<Farmacia> listaFarmacias){
        this.context=context;
        this.listaFarmacias=listaFarmacias;
        this.inflater=LayoutInflater.from(context);//El inflater será el mismo que se esté encargando de la interfaz general de la activity
    }

    static class ViewHolder{
        //Patrón utilizado para ganar eficiencia en la carga y visualización de listas
        //Básicamente nos ahorra tener que hacer 4 findViewById cada vez que pinta un elemento de la lista (solo los hará para el primero)
        TextView nombre;
        TextView telefono;
        TextView direccion;
    }

    @Override
    public int getCount() {
        //El total de elementos en el listview coincidirá con el total de la lista que representa
        return listaFarmacias.size();
    }

    @Override
    public Object getItem(int position) {
        //Cuando nos tenga que devolver un elemento, querremos que nos devuelva el que coincide con la posición seleccionada
        return listaFarmacias.get(position);
    }

    @Override
    public long getItemId(int position) {
        //El id de cada item en la lista coincidirá con su posición
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Este es el método que pinta un elemento de la lista, por lo tanto se ejecutará una vez para cada elemento que se pinte

        ViewHolder holder=null;
        if(convertView==null){//Si convertView es nulo, será la primera vez que
            convertView=inflater.inflate(R.layout.fila_farmacia, null);
            holder=new ViewHolder();
            holder.nombre=(TextView)convertView.findViewById(R.id.txtFarmaciaNombre);
            holder.telefono=convertView.findViewById(R.id.txtFarmaciaTlf);
            holder.direccion=convertView.findViewById(R.id.txtFarmaciaDireccion);
            convertView.setTag(holder);//añado el holder a la convertView, para tenerlos siempre disponible
        }
        else{
            holder=(ViewHolder)convertView.getTag();//Si no es la primera vez, cogeré el holder que ya había creado
        }
        Farmacia farmacia = listaFarmacias.get(position);//cogo el elemento que toca pintar

        //Pongo cada dato en su elemento de interfaz en el holder


        holder.nombre.setText(farmacia.getNombre());
        holder.telefono.setText(farmacia.getTlf());
        holder.direccion.setText(farmacia.getDireccion());

        return convertView;//devuelvo la vista del elemento
    }

}
