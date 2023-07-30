package com.example.reto1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.reto1.Adapters.SneakerAdapter;
import com.example.reto1.Model.Sneaker;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;

    private SneakerAdapter sneakerAdapter;

    private static final long SPLASH_DELAY = 2000; // 2 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        super.onCreate(savedInstanceState);

        //Establecer en el action bar en la parte superior derecha el logo
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.zapatillas);


        // Mostrar el icono "zapatilla" en el lado izquierdo del action bar
        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.grilla);
        sneakerAdapter = new SneakerAdapter(this);
        gridView.setAdapter(sneakerAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sneaker item = (Sneaker) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Se selecciono "+item.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        //Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.options_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.menuOptionProductos:
                Toast.makeText(this, "Seleccionaste Productos", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuOptionServicios:
                Toast.makeText(this, "Seleccionaste Servicios", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuOptionSucursales:
                Toast.makeText(this, "Seleccionaste Sucursales", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        //return super.onOptionsItemSelected(item);
    }
}