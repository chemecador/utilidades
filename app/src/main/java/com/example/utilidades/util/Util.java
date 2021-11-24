package com.example.utilidades.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;

public class Util {

    public static byte[] getBytes(Bitmap bitmap) {
        //Conversión de una imagen a un vector de byte
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        return bos.toByteArray();

    }

    public static Bitmap getBitmap(byte[] bytes) {
        //conversión de un vector de byte a imagen
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static void solicitarPermiso(final String permiso, String justificacion, final int resquestCode, final Activity actividad) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(actividad, permiso)) {
            new AlertDialog.Builder(actividad).
                    setTitle("Solicitud de permiso").
                    setMessage(justificacion).
                    setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(actividad,
                                    new String[]{permiso}, resquestCode);

                        }
                    }).show();

        } else {
            ActivityCompat.requestPermissions(actividad, new String[]{permiso}, resquestCode);
        }

    }
}
