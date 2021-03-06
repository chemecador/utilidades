package com.example.utilidades.db;

import static android.provider.BaseColumns._ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.utilidades.util.Producto;
import com.example.utilidades.util.Util;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static final int VERSION = 2;

    public Database(Context contexto) {
        super(contexto, ConstantesDB.BASE_DATOS, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ConstantesDB.TABLA_PRODUCTOS + "(" + _ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesDB.TIPO + " TEXT, " +
                ConstantesDB.NOMBRE + " TEXT, " +
                ConstantesDB.PRECIO + " TEXT, " +
                ConstantesDB.CANTIDAD + " TEXT, " +
                ConstantesDB.FOTO + " BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesDB.TABLA_PRODUCTOS);
        onCreate(db);
    }

    public void nuevoProducto(Producto producto) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ConstantesDB.TIPO, producto.getTipo());
        values.put(ConstantesDB.NOMBRE, producto.getNombre());
        values.put(ConstantesDB.PRECIO, producto.getPrecio());
        values.put(ConstantesDB.CANTIDAD, producto.getCantidad());
        values.put(ConstantesDB.FOTO, Util.getBytes(producto.getFoto()));
        db.insertOrThrow(ConstantesDB.TABLA_PRODUCTOS, null, values);
        db.close();
    }

    public void eliminarProducto(Producto producto) {
        SQLiteDatabase db = getWritableDatabase();
        String[] argumentos = new String[]{String.valueOf(producto.getId())};
        db.delete(ConstantesDB.TABLA_PRODUCTOS, "_id= ?", argumentos);
        db.close();
    }

    public ArrayList<Producto> getProductosNombre(Boolean desc) {
        final String[] SELECT = {_ID, ConstantesDB.TIPO, ConstantesDB.NOMBRE,
                ConstantesDB.PRECIO, ConstantesDB.CANTIDAD, ConstantesDB.FOTO};//Vector con los nombres de las columnas para el select de la consulta
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        if (desc) {
            cursor = db.query(ConstantesDB.TABLA_PRODUCTOS, SELECT,
                    null, null, null, null,
                    ConstantesDB.NOMBRE + " DESC");//Ejecutar la consulta
        } else {
            cursor = db.query(ConstantesDB.TABLA_PRODUCTOS, SELECT,
                    null, null, null, null,
                    ConstantesDB.NOMBRE);
        }

        ArrayList<Producto> listaProductos = new ArrayList<>();
        Producto producto = null;
        while (cursor.moveToNext()) {
            producto = new Producto();
            producto.setId(cursor.getLong(0));//El orden de las columnas debe ser el que haya puesto en el vector SELECT
            producto.setTipo(cursor.getString(1));
            producto.setNombre(cursor.getString(2));
            producto.setPrecio(cursor.getDouble(3));
            producto.setCantidad(cursor.getInt(4));
            producto.setFoto(Util.getBitmap(cursor.getBlob(5)));
            listaProductos.add(producto);

        }
        cursor.close();
        db.close();
        return listaProductos;
    }

    public ArrayList<Producto> getProductosCantidad(Boolean desc) {

        final String[] SELECT = {_ID, ConstantesDB.TIPO, ConstantesDB.NOMBRE,
                ConstantesDB.PRECIO, ConstantesDB.CANTIDAD, ConstantesDB.FOTO};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        if (desc) {
            cursor = db.query(ConstantesDB.TABLA_PRODUCTOS, SELECT,
                    null, null, null, null,
                    ConstantesDB.CANTIDAD + " DESC");//Ejecuto la consulta
        } else {
            cursor = db.query(ConstantesDB.TABLA_PRODUCTOS, SELECT,
                    null, null, null, null,
                    ConstantesDB.CANTIDAD);//Ejecuto la consulta
        }
        ArrayList<Producto> listaProductos = new ArrayList<>();
        Producto producto = null;
        while (cursor.moveToNext()) {
            producto = new Producto();
            producto.setId(cursor.getLong(0));
            producto.setTipo(cursor.getString(1));
            producto.setNombre(cursor.getString(2));
            producto.setPrecio(cursor.getDouble(3));
            producto.setCantidad(cursor.getInt(4));
            producto.setFoto(Util.getBitmap(cursor.getBlob(5)));
            listaProductos.add(producto);

        }
        cursor.close();
        db.close();
        return listaProductos;
    }
}
