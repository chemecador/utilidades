package com.example.utilidades.util;

import android.content.Intent;

public class Farmacia {

    private String nombre;
    private String tlf;
    private String direccion;

    public Farmacia(String nombre, String tlf, String direccion) {
        this.nombre = nombre;
        this.tlf = tlf;
        this.direccion = direccion;
    }

    public Farmacia() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Intent putExtraFarmacia (Intent i){
        i.putExtra("NOMBRE",this.getNombre());
        i.putExtra("TELEFONO",this.getTlf());
        i.putExtra("DIRECCION",this.getDireccion());
        return i;
    }

}
