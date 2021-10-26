package com.example.infoamigos3.util;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;


/*Clas que utilizaremos para crear métodos que nos puedan resultar de utilidad
* en distintas partes de nuestra aplicación*/
public class Util {


    public static byte[] getBytes(Bitmap bitmap){
       //Conversión de una imagen a un vector de byte
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,bos);
        return bos.toByteArray();

    }

    public static Bitmap getBitmap(byte[] bytes){
        //conversión de un vector de byte a imagen
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }



}
