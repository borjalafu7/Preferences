package com.borjalapa.preferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences.OnSharedPreferenceChangeListener listener;

    public static final String TEXTO = "Texto";
    EditText editText;

    /*implementar estas dos lineas en el gradle

        def preference_version = "1.1.1"
        implementation "androidx.preference:preference:$preference_version"

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.etTexto);

        //para obtener la referencia de las preferencias compartidas
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this /* Activity context */);
        String name = sharedPreferences.getString("list_preference_1", "");

        Log.i("PREFERENCIA", name);

        //listener para que cambie el valor de la preferencia cuando el usuario la cambie y no cuando reinicie la app
        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                //mapa con todas las preferencias
                Map<String,?> preferencias = prefs.getAll();
                //guarda en la variable la preferencia que cambia
                String s = preferencias.get(key).toString();

            }
        };

        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);

        recuperar(null);
    }

    //hacer menu en el resource manager

    //meter el menu en la app
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.mi_menu, menu);
        return true;
    }

    //realiza lo que le pongamos cuando clickamos esa opcion del menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
            if (id == R.id.action_settings) {
                //Toast.makeText(this,"Pulsado Settings", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            }
        return super.onOptionsItemSelected(item);
    }


    //creamos un XML con el resource manager
    //lo inflamos en la actividad MySettingsFragment
    // y por ultimo en la clase SettingsActivity lo reemplazas para que se vea


    //metodo para guardar una variable de un editText en preferencias
    public void guardar(View view) {
        /*Preferencias guardadas en en archivo común*/
        /*
            SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(TEXTO, editText.getText().toString());
            editor.commit(); //devuelve true o false segun si ha ido bien o no
         */

        /*Preferencias guardadas en archivo específico*/
        SharedPreferences miInfo = getApplicationContext().getSharedPreferences("INFO", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = miInfo.edit();
        editor1.putString(TEXTO, editText.getText().toString());
        editor1.apply(); //Es asíncrono no devuelve true o false. También podemos usar commit()
    }

    //metodo para recuperar una variable de preferencias
    public void recuperar(View view) {
    /*
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        String texto = sharedPreferences.getString(TEXTO, "default");

     */

        SharedPreferences sharedPreferences = this.getSharedPreferences("INFO",Context.MODE_PRIVATE);
        String texto = sharedPreferences.getString(TEXTO, "default");

        editText.setText(texto);
    }

    public void goNext(View view) {
        Intent i = new Intent(this,MainActivity2.class);

        startActivity(i);
    }
}