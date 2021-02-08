package com.borjalapa.preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setTitle("Settings");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new MySettingsFragment())
                .commit();
    }
}