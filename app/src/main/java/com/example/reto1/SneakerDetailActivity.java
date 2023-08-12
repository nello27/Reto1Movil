package com.example.reto1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.reto1.Adapters.SneakerAdapterFav;
import com.example.reto1.Fragments.FavoritosFragment;
import com.example.reto1.Fragments.ProductosFragment;
import com.example.reto1.Fragments.ServiciosFragment;
import com.example.reto1.Fragments.SucursalesFragment;
import com.example.reto1.Model.Sneaker;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SneakerDetailActivity extends AppCompatActivity {

    //private Sneaker detailSneaker;

    private ImageView sneakerimg;
    private TextView sneakertitle;
    private TextView sneakerDescription;

    private TextView sneakerId;

    private Button botonComprar;

    private Button botonVolver;

    private Button botonFavoritos;

    private Button botonTraerFavoritos;

    public List<SneakersLista> listaSneakers = new ArrayList<>();

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sneaker_detail);



        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.sneaker_detail);

        int id = getIntent().getExtras().getInt("sneaker_id");
        int sneaker_drawable = getIntent().getExtras().getInt("sneaker_img");
        String sneaker_name = getIntent().getExtras().getString("sneaker_name");
        String sneaker_description = getIntent().getExtras().getString("sneaker_description");

        //Inicializar las vistas

        //castear

        sneakerId = (TextView) findViewById(R.id.sneakerid);

        sneakerimg = (ImageView) findViewById(R.id.sneakerdetailimage);

        sneakertitle = (TextView) findViewById(R.id.sneakerTitle);

        sneakerDescription = (TextView) findViewById(R.id.sneakerDescription);

        // se van asignar valores
        //para convertir el valor numérico en una cadena. Integer.toString(id)
        sneakerId.setText(Integer.toString(id));
        sneakerimg.setImageResource(sneaker_drawable);
        sneakertitle.setText(sneaker_name);
        sneakerDescription.setText(sneaker_description);

        //castear
        botonComprar = (Button) findViewById(R.id.btnCompra);

        //crear el onclick

        botonComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SneakerDetailActivity.this, "Producto Agregado al carrito", Toast.LENGTH_SHORT).show();
            }
        });

        botonVolver = (Button) findViewById(R.id.btnVolver);

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        botonFavoritos = (Button) findViewById(R.id.btnFavoritos);

        botonFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminBD admin = new AdminBD(getApplicationContext(), "administracion", null, 1);
                SQLiteDatabase bd = admin.getWritableDatabase();

                String id = sneakerId.getText().toString();
                String title = sneakertitle.getText().toString();
                String description = sneakerDescription.getText().toString();

                // Convierte la imagen a un array de bytes (BLOB)
                BitmapDrawable drawable = (BitmapDrawable) sneakerimg.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] imageBytes = stream.toByteArray();

                ContentValues registro = new ContentValues();
                registro.put("sneakerId", id);
                registro.put("sneakertitle", title);
                registro.put("sneakerDescription", description);
                registro.put("sneakerimg", imageBytes); // Aquí almacenamos la imagen como bytes

                bd.insert("favoritos", null, registro);
                bd.close();

                sneakerId.setText("");
                sneakertitle.setText("");
                sneakerDescription.setText("");

                Toast.makeText(getApplicationContext(), "Se guardaron los datos correctamente", Toast.LENGTH_SHORT).show();
            }
        });


        botonTraerFavoritos = (Button) findViewById(R.id.btnCargarFav);

        botonTraerFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AdminBD admin = new AdminBD(SneakerDetailActivity.this, "administracion", null, 1);
                SQLiteDatabase bd = admin.getReadableDatabase();

                // ...

                Cursor cursor = bd.rawQuery("SELECT sneakerId, sneakertitle, sneakerDescription, sneakerimg FROM favoritos", null);

                int indiceColumnaId = cursor.getColumnIndex("sneakerId");
                int indiceColumnaTitulo = cursor.getColumnIndex("sneakertitle");
                int indiceColumnaDescripcion = cursor.getColumnIndex("sneakerDescription");
                int indiceColumnaImg = cursor.getColumnIndex("sneakerimg");

                // Limpia la lista antes de llenarla
                listaSneakers.clear();

                // Recorrer el cursor para obtener los datos
                while (cursor.moveToNext()) {
                    String Id = cursor.getString(indiceColumnaId);
                    String titulo = cursor.getString(indiceColumnaTitulo);
                    String descripcion = cursor.getString(indiceColumnaDescripcion);
                    byte[] imgBytes = cursor.getBlob(indiceColumnaImg);

                    Bitmap bitmap = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
                    int idValue = Integer.parseInt(Id);
                    SneakersLista articulo = new SneakersLista(idValue,titulo,descripcion,descripcion,imgBytes);
                    listaSneakers.add(articulo);
                }

                // Crear el adaptador
                SneakerAdapterFav adapter = new SneakerAdapterFav(SneakerDetailActivity.this, listaSneakers);


                // Obtener la referencia al ListView
                ListView listView = findViewById(R.id.listFav);

                // Asignar el adaptador al ListView
                listView.setAdapter(adapter);

                // ...
            }
        });

        //DRAWER LAYOUT

        /*drawerLayout = findViewById(R.id.s);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);


        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Inicializa el NavigationView
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Maneja los eventos de los elementos del menú aquí
                switch (item.getItemId()) {
                    case R.id.productosid:
                        // Acción para la opción 1
                        Fragment productosFagments = new ProductosFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.s, productosFagments).addToBackStack(null).commit();
                        //setContentView(R.layout.activity_main);
                        break;
                    case R.id.sucursalesid:
                        // Acción para la opción 2
                        Fragment sucursalesFragment = new SucursalesFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.s, sucursalesFragment).addToBackStack(null).commit();
                        break;
                    case R.id.serviciosid:
                        // Acción para la opción 2
                        Fragment serviciosFragment = new ServiciosFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.s, serviciosFragment).addToBackStack(null).commit();
                        break;
                    case R.id.favoritosid:
                        // Acción para la opción 2
                        Fragment favoritosFragment = new FavoritosFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.s, favoritosFragment).addToBackStack(null).commit();
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });*/

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.nav_menu, menu);

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/


}
