package com.example.utilidades.util;

import android.content.Intent;
import android.graphics.Bitmap;

public class Producto {
    private long id;

    private String tipo;
    private String nombre;
    private Double precio;
    private int cantidad;
    private Bitmap foto;

    public Producto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public Intent putExtraProducto (Intent i){
        i.putExtra("TIPO",this.getTipo());
        i.putExtra("NOMBRE",this.getNombre());
        i.putExtra("PRECIO",this.getPrecio());
        i.putExtra("CANTIDAD",this.getCantidad());
        return i;
    }
}
