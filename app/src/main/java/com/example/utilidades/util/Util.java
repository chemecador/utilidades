package com.example.utilidades.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

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
