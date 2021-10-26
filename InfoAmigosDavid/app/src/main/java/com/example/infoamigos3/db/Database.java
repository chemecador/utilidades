package com.example.infoamigos3.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.infoamigos3.util.Amigo;
import com.example.infoamigos3.util.Util;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;


/*Base de datos de Amigos para InfoAmigos*/
public class Database extends SQLiteOpenHelper {
    private static final int VERSION=2; //Solo cuando modifique el número de versión la base de datos se borrará y volverá a crear, si no lo modifico
    //no se aplicarán los cambios que haga en la definición de las tablas

    public Database(Context contexto){
        super(contexto, ConstantesDB.BASE_DATOS,null, VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creación de la tabla amigo
       db.execSQL("CREATE TABLE "+ConstantesDB.TABLA_AMIGOS+"("+_ID+
               " INTEGER PRIMARY KEY AUTOINCREMENT, "+
               ConstantesDB.NOMBRE+" TEXT, "+
               ConstantesDB.EMAIL+" TEXT, "+
               ConstantesDB.TLF+ " TEXT, "+
               ConstantesDB.MOVIL+ " TEXT, "+
               ConstantesDB.DEUDAS+ " REAL, "+
               ConstantesDB.FOTO+ " BLOB)"
       );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       //Método que se ejecutará cuando se modifique la versión de la BBDD
        //en nuestro caso borra la tabla y la vuelve a crear
        db.execSQL("DROP TABLE IF EXISTS "+ConstantesDB.TABLA_AMIGOS);
        onCreate(db);
    }

    public void nuevoAmigo(Amigo amigo){
        //Añadir un Amigo a la base de datos
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ConstantesDB.NOMBRE,amigo.getNombreApellidos());
        values.put(ConstantesDB.EMAIL,amigo.getEmail());
        values.put(ConstantesDB.TLF,amigo.getTlf());
        values.put(ConstantesDB.MOVIL,amigo.getTlfMovil());
        values.put(ConstantesDB.DEUDAS,amigo.getDeudas());
        values.put(ConstantesDB.FOTO, Util.getBytes(amigo.getFoto()));
        db.insertOrThrow(ConstantesDB.TABLA_AMIGOS,null,values);
        db.close();
    }

    public void eliminarAmigo(Amigo amigo){
        //Elimina un amigo de la base de datos
        SQLiteDatabase db=getWritableDatabase();
        String[] argumentos=new String[]{String.valueOf(amigo.getId())};
        db.delete(ConstantesDB.TABLA_AMIGOS,"_id= ?",argumentos);//borrará el amigo cuyo id coincida con el que hemos puesto en 'argumentos'
        db.close();
    }

    public ArrayList<Amigo> getAmigos(){
        //devuelve todos los amigos de la base de datos, ordenados por nombre
        final String[] SELECT ={_ID,ConstantesDB.NOMBRE,ConstantesDB.EMAIL,
                ConstantesDB.TLF,ConstantesDB.MOVIL, ConstantesDB.DEUDAS, ConstantesDB.FOTO};//Creo un vector con los nombres de las columnas para el select de la consulta
        SQLiteDatabase db=getReadableDatabase();//Referencia a la base de datos en modo lectura
        Cursor cursor=db.query(ConstantesDB.TABLA_AMIGOS,SELECT,
                null,null,null,null,
                ConstantesDB.NOMBRE);//Ejecuto la consulta
        ArrayList<Amigo> listaAmigos=new ArrayList<>();
        Amigo amigo=null;
        while(cursor.moveToNext()){//Mientras el cursor pueda recorrer el resultado de la consulta (hay más amgios por asignar)
            amigo=new Amigo();
            amigo.setId(cursor.getLong(0));//El orden de las columnas debe ser el que haya puesto en el vector SELECT
            amigo.setNombreApellidos(cursor.getString(1));
            amigo.setEmail(cursor.getString(2));
            amigo.setTlf(cursor.getString(3));
            amigo.setTlfMovil(cursor.getString(4));
            amigo.setDeudas(cursor.getFloat(5));
            amigo.setFoto(Util.getBitmap(cursor.getBlob(6)));

            listaAmigos.add(amigo);

        }
        cursor.close();
        db.close();
        return listaAmigos;
    }

    public ArrayList<Amigo> getAmigosD(){
       //devuelve todos los amigos de la base de datos ordenados por deuda
        final String[] SELECT ={_ID,ConstantesDB.NOMBRE,ConstantesDB.EMAIL,
                ConstantesDB.TLF,ConstantesDB.MOVIL, ConstantesDB.DEUDAS, ConstantesDB.FOTO};
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(ConstantesDB.TABLA_AMIGOS,SELECT,null,null,null,null,ConstantesDB.DEUDAS);
        ArrayList<Amigo> listaAmigos=new ArrayList<>();
        Amigo amigo=null;
        while(cursor.moveToNext()){
            amigo=new Amigo();
            amigo.setId(cursor.getLong(0));//El orden de las columnas debe ser el que haya puesto en el vector SELECT
            amigo.setNombreApellidos(cursor.getString(1));
            amigo.setEmail(cursor.getString(2));
            amigo.setTlf(cursor.getString(3));
            amigo.setTlfMovil(cursor.getString(4));
            amigo.setDeudas(cursor.getFloat(5));
            amigo.setFoto(Util.getBitmap(cursor.getBlob(6)));

            listaAmigos.add(amigo);

        }
        cursor.close();
        db.close();
        return listaAmigos;
    }

    //obetener amigos deuda > x
    public ArrayList<Amigo> getDeudores(float deuda){
       //devuelve todos los amigos que nos deban una cantidad de dinero > dueda
        final String[] SELECT ={_ID,ConstantesDB.NOMBRE,ConstantesDB.EMAIL,
                ConstantesDB.TLF,ConstantesDB.MOVIL, ConstantesDB.DEUDAS, ConstantesDB.FOTO};
        String[] args={""+deuda};//creo el argumento que pasaré a la consulta con el valor de la deuda
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(ConstantesDB.TABLA_AMIGOS,SELECT,"deudas > ?",args,null,null,ConstantesDB.DEUDAS);//ejecuta la consulta con el where: "deudas > ?" y sustituye el '?' por el argumento
        ArrayList<Amigo> listaAmigos=new ArrayList<>();
        Amigo amigo=null;
        while(cursor.moveToNext()){
            amigo=new Amigo();
            amigo.setId(cursor.getLong(0));//El orden de las columnas debe ser el que haya puesto en el vector SELECT
            amigo.setNombreApellidos(cursor.getString(1));
            amigo.setEmail(cursor.getString(2));
            amigo.setTlf(cursor.getString(3));
            amigo.setTlfMovil(cursor.getString(4));
            amigo.setDeudas(cursor.getFloat(5));
            amigo.setFoto(Util.getBitmap(cursor.getBlob(6)));

            listaAmigos.add(amigo);

        }
        cursor.close();
        db.close();
        return listaAmigos;
    }


}
