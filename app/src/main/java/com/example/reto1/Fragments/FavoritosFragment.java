package com.example.reto1.Fragments;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.reto1.Adapters.SneakerAdapterFav;
import com.example.reto1.Model.SneakerDatabaseHelper; // Importa la clase SneakerDatabaseHelper
import com.example.reto1.R;
import com.example.reto1.SneakersLista;

import java.util.ArrayList;
import java.util.List;

public class FavoritosFragment extends Fragment {

    private List<SneakersLista> listaFav; // Asegúrate de especificar el tipo de lista

    private SneakerAdapterFav sneakerAdapterfav;

    public FavoritosFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favoritos_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener una referencia al ListView en la vista inflada
        ListView listView = view.findViewById(R.id.ListaFavoritos);

        // Crear una instancia de la base de datos utilizando SneakerDatabaseHelper
        SneakerDatabaseHelper databaseHelper = new SneakerDatabaseHelper(requireContext());

        // Consultar la base de datos y obtener el cursor
        Cursor cursor = databaseHelper.getAllSneakers();

        // Obtener los índices de las columnas del cursor
        int indiceColumnaId = cursor.getColumnIndex("id");
        int indiceColumnaTitulo = cursor.getColumnIndex("name");
        int indiceColumnaDescripcion = cursor.getColumnIndex("description");
        int indiceColumnaImg = cursor.getColumnIndex("image");

// Limpia la lista antes de llenarla
        List<SneakersLista> listaSneak = new ArrayList<>();

// Recorrer el cursor para obtener los datos y llenar la lista
        while (cursor.moveToNext()) {
            int idValue = cursor.getInt(indiceColumnaId);
            String titulo = cursor.getString(indiceColumnaTitulo);
            String descripcion = cursor.getString(indiceColumnaDescripcion);
            byte[] imgBytes = cursor.getBlob(indiceColumnaImg); // Obtener el BLOB de la columna de imagen

            // Convertir el arreglo de bytes a un Bitmap
            Bitmap bitmap = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);

            SneakersLista articulo = new SneakersLista(idValue, titulo, descripcion, descripcion, imgBytes); // Pasar imgBytes como imagen
            listaSneak.add(articulo);
        }
        // Crear el adaptador y pasar la lista de elementos
        //SneakerAdapterFav adapter = new SneakerAdapterFav(requireContext(), listaSneak);

        // Asignar el adaptador al ListView
        //listView.setAdapter(adapter);

        // Cerrar el cursor (la base de datos se cierra automáticamente en getAllSneakers())
        cursor.close();

        // Crear el adaptador y pasar la lista de elementos
        SneakerAdapterFav adapter = new SneakerAdapterFav(requireContext(), listaSneak);

        // Asignar el adaptador al ListView
        listView.setAdapter(adapter);

        // Verificar si la lista está vacía y mostrar un mensaje
        if (listaSneak.isEmpty()) {
            TextView emptyTextView = view.findViewById(R.id.sinfavoritos); // Agrega un TextView en tu diseño con el ID emptyTextView
            emptyTextView.setVisibility(View.VISIBLE); // Mostrar el mensaje de "No hay favoritos"
            listView.setVisibility(View.GONE); // Ocultar el ListView
        } else {
            TextView emptyTextView = view.findViewById(R.id.sinfavoritos);
            emptyTextView.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
    }
}
