package com.example.infoamigos3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.infoamigos3.util.Amigo;

import java.util.ArrayList;

public class AmigoAdapter extends BaseAdapter {
//Adaptador para mostrar Amigo en una ListView
//Para cada elemento mostrará una imagen y varios campos de texto

    private Context context; //El contexto será la activity en la que se esté usando
    private ArrayList<Amigo> listaAmigos;//La lista de elementos a mostrar
    private LayoutInflater inflater;//El encargado de distribuir/pintar los elementos en la interfaz

    public AmigoAdapter(Activity context, ArrayList<Amigo> listaAmigos){
        this.context=context;
        this.listaAmigos=listaAmigos;
        this.inflater=LayoutInflater.from(context);//El inflater será el mismo que se esté encargando de la interfaz general de la activity
    }

    static class ViewHolder{
        //Patrón utilizado para ganar eficiencia en la carga y visualización de listas
        //Básicamente nos ahorra tener que hacer 4 findViewById cada vez que pinta un elemento de la lista (solo los hará para el primero)
        ImageView foto;
        TextView nombre;
        TextView fijo;
        TextView movil;
    }


    @Override
    public int getCount() {
        //El total de elementos en el listview coincidirá con el total de la lista que representa
        return listaAmigos.size();
    }

    @Override
    public Object getItem(int position) {
        //Cuando nos tenga que devolver un elemento, querremos que nos devuelva el que coincide con la posición seleccionada
        return listaAmigos.get(position);
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
            convertView=inflater.inflate(R.layout.fila, null);
            holder=new ViewHolder();
            holder.foto=(ImageView)convertView.findViewById(R.id.ivFotoFila);
            holder.nombre=(TextView)convertView.findViewById(R.id.txtNombreFila);
            holder.fijo=convertView.findViewById(R.id.txtFijoFila);
            holder.movil=convertView.findViewById(R.id.txtMovilFila);
            convertView.setTag(holder);//añado el holder a la convertView, para tenerlos siempre disponible
        }
        else{
            holder=(ViewHolder)convertView.getTag();//Si no es la primera vez, cogeré el holder que ya había creado
        }
        Amigo amigo=listaAmigos.get(position);//cogo el elemento que toca pintar

        //Pongo cada dato en su elemento de interfaz en el holder
        holder.foto.setImageBitmap(amigo.getFoto());
        //holder.foto.setImageDrawable(context.getResources().getDrawable(R.drawable.av4));

        holder.nombre.setText(amigo.getNombreApellidos());
        holder.fijo.setText(amigo.getTlf());
        holder.movil.setText(amigo.getTlfMovil());

        return convertView;//devuelvo la vista del elemento
    }
}
