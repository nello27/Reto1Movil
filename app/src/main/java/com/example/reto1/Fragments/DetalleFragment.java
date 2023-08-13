package com.example.reto1.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.reto1.MainActivity;
import com.example.reto1.Model.SneakerDatabaseHelper;
import com.example.reto1.R;

import java.io.ByteArrayOutputStream;

public class DetalleFragment extends Fragment {

    private Context context;
    private static final String ARG_SNEAKER_ID = "sneaker_id";
    private static final String ARG_SNEAKER_IMG = "sneaker_img";
    private static final String ARG_SNEAKER_NAME = "sneaker_name";
    private static final String ARG_SNEAKER_DESCRIPTION = "sneaker_description";


    public static DetalleFragment newInstance(int sneakerId, int sneakerImg, String sneakerName, String sneakerDescription) {

        DetalleFragment fragment = new DetalleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SNEAKER_ID, sneakerId);
        args.putInt(ARG_SNEAKER_IMG, sneakerImg);
        args.putString(ARG_SNEAKER_NAME, sneakerName);
        args.putString(ARG_SNEAKER_DESCRIPTION, sneakerDescription);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.sneaker_detail, container, false);\
        View rootView = inflater.inflate(R.layout.sneaker_detail, container, false);
        TextView sneakerTextId = rootView.findViewById(R.id.sneakerid);
        ImageView sneakerImgView = rootView.findViewById(R.id.sneakerdetailimage);
        TextView sneakerNameView = rootView.findViewById(R.id.sneakerTitle);
        TextView sneakerDescriptionView = rootView.findViewById(R.id.sneakerDescription);
        Button saveButton = rootView.findViewById(R.id.btnFavoritos); // Agregar el botón
        Button saveButtonVolver = rootView.findViewById(R.id.btnVolver); // Agregar el botón
        Button saveButtonCarrito = rootView.findViewById(R.id.btnCompra); // Agregar el botón


        Bundle args = getArguments();
        if (args != null) {
            int sneakerImg = args.getInt(ARG_SNEAKER_IMG);
            String sneakerName = args.getString(ARG_SNEAKER_NAME);
            String sneakerDescription = args.getString(ARG_SNEAKER_DESCRIPTION);
            String sneakerId = args.getString(ARG_SNEAKER_ID);

            sneakerImgView.setImageResource(sneakerImg);
            sneakerNameView.setText(sneakerName);
            sneakerDescriptionView.setText(sneakerDescription);
            sneakerTextId.setText(sneakerId);
        }

// Agregar el manejo de clics del botón
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SneakerDatabaseHelper databaseHelper = new SneakerDatabaseHelper(getContext());

                if (args != null) {
                    int sneakerImgResource = args.getInt(ARG_SNEAKER_IMG); // Obtener el recurso de imagen (int)
                    String sneakerName = args.getString(ARG_SNEAKER_NAME);
                    String sneakerDescription = args.getString(ARG_SNEAKER_DESCRIPTION);

                    // Convertir el recurso de imagen a un arreglo de bytes
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), sneakerImgResource);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] sneakerImgBytes = stream.toByteArray();

                    // Insertar en la base de datos
                    long rowId = databaseHelper.insertSneaker(sneakerImgBytes, sneakerName, sneakerDescription);

                    Toast.makeText(getContext(), "hecho guardado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        saveButtonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Inicio", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(context, MainActivity.class); // Actividad a cargar
                //context.startActivity(intent);

                Toast.makeText(getActivity(), "Inicio", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        saveButtonCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Carrito de compras proximamente", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;

    }
}
