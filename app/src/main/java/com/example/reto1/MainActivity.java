package com.example.reto1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.reto1.Adapters.SneakerAdapter;
import com.example.reto1.Fragments.ProductosFragment;
import com.example.reto1.Fragments.ServiciosFragment;
import com.example.reto1.Fragments.SucursalesFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    private GridView gridView;

    private SneakerAdapter sneakerAdapter;

    private static final long SPLASH_DELAY = 1000; // 2 segundos

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

        //FRAGMENT PRODUCTOS LAYOUT

        // Obtén el FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Comienza una nueva transacción de fragmento
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Crea una instancia de tu fragmento ProductosFragment
        ProductosFragment productosFragment = new ProductosFragment();

        // Reemplaza el contenido del FrameLayout con el fragmento ProductosFragment
        fragmentTransaction.replace(R.id.fragment, productosFragment);

        // Realiza la transacción
        fragmentTransaction.commit();

        /*gridView = (GridView) findViewById(R.id.grilla);
        sneakerAdapter = new SneakerAdapter(this);
        gridView.setAdapter(sneakerAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sneaker item = (Sneaker) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Se selecciono "+item.getName(), Toast.LENGTH_SHORT).show();
            }
        });*/
        //Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();

        //DRAWER LAYOUT

        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Maneja los eventos de los elementos del menú aquí
                switch (item.getItemId()) {
                    case R.id.productosid:
                        // Acción para la opción 1
                        Fragment productosFagment = new ProductosFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, productosFagment).addToBackStack(null).commit();

                        break;
                    case R.id.sucursalesid:
                        // Acción para la opción 2
                        Fragment sucursalesFragment = new SucursalesFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, sucursalesFragment).addToBackStack(null).commit();

                        break;
                    case R.id.serviciosid:
                        // Acción para la opción 2
                        Fragment serviciosFragment = new SucursalesFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, serviciosFragment).addToBackStack(null).commit();

                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

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
                //Toast.makeText(this, "Seleccionaste Productos", Toast.LENGTH_SHORT).show();
                Fragment productosFagment = new ProductosFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, productosFagment).addToBackStack(null).commit();
                return true;
            case R.id.menuOptionServicios:
                //Toast.makeText(this, "Seleccionaste Servicios", Toast.LENGTH_SHORT).show();
                Fragment serviciosFragment = new ServiciosFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, serviciosFragment).addToBackStack(null).commit();
                return true;
            case R.id.menuOptionSucursales:
                //Toast.makeText(this, "Seleccionaste Sucursales", Toast.LENGTH_SHORT).show();
                Fragment sucursalesFragment = new SucursalesFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, sucursalesFragment).addToBackStack(null).commit();
                return true;
            case android.R.id.home:
                if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
                    return true;
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


        //return super.onOptionsItemSelected(item);
    }
}