package com.example.reto1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reto1.Adapters.SneakerAdapterFav;
import com.example.reto1.Model.Sneaker;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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





    }

    /*public class Sneakerlist {
        private String descripcion;
        private String titulo;

        private byte[] imagen;  // Agrega este campo para la imagen en formato BLOB


        public Sneakerlist(String descripcion, String titulo, byte[] img) {
            this.descripcion = descripcion;
            this.titulo = titulo;
            this.imagen = img;
        }




        // Métodos getter y setter para la descripción y el precio


        public byte[] getImagen() {

            return imagen;
        }

        public void setImagen(byte[] imagen) {
            this.imagen = imagen;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }


        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        @Override
        public String toString() {
            return  titulo + " " + descripcion;
        }
    }*/
}
