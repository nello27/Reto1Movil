package com.example.reto1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reto1.Model.Sneaker;

import org.w3c.dom.Text;

public class SneakerDetailActivity extends AppCompatActivity {

    //private Sneaker detailSneaker;
    private ImageView sneakerimg;
    private TextView sneakertitle;
    private TextView sneakerDescription;

    private Button botonComprar;

    private Button botonVolver;

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
        sneakerimg = (ImageView) findViewById(R.id.sneakerdetailimage);

        sneakertitle = (TextView) findViewById(R.id.sneakerTitle);

        sneakerDescription = (TextView) findViewById(R.id.sneakerDescription);

        // se van asignar valores

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

    }
}
