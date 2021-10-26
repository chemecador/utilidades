package com.example.infoamigos3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.Switch;

public class PreferencesActivity extends PreferenceActivity  {
//Clase para poder mostrar las preferencias
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }
}
