package com.example.reto1.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.reto1.Adapters.SneakerAdapterFav;
import com.example.reto1.AdminBD;
import com.example.reto1.R;
import com.example.reto1.SneakersLista;

import java.util.ArrayList;
import java.util.List;

public class FavoritosFragment extends Fragment {

    private List listaFav;

    private SneakerAdapterFav sneakerAdapterfav;
    public FavoritosFragment(){


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

        // Crear una instancia de la base de datos
        AdminBD admin = new AdminBD(requireContext(), "administracion", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();

        // Consultar la base de datos y obtener el cursor
        Cursor cursor = bd.rawQuery("SELECT sneakerId,sneakertitle, sneakerDescription, sneakerimg FROM favoritos", null);

        // Obtener los índices de las columnas del cursor
        int indiceColumnaId = cursor.getColumnIndex("sneakerId");
        int indiceColumnaTitulo = cursor.getColumnIndex("sneakertitle");
        int indiceColumnaDescripcion = cursor.getColumnIndex("sneakerDescription");
        int indiceColumnaImg = cursor.getColumnIndex("sneakerimg");

        // Limpia la lista antes de llenarla
        List<SneakersLista> listaSneak = new ArrayList<>();

        // Recorrer el cursor para obtener los datos y llenar la lista
        while (cursor.moveToNext()) {
            String Id = cursor.getString(indiceColumnaId);
            String titulo = cursor.getString(indiceColumnaTitulo);
            String descripcion = cursor.getString(indiceColumnaDescripcion);
            byte[] imgBytes = cursor.getBlob(indiceColumnaImg);

            Bitmap bitmap = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
            int idValue = Integer.parseInt(Id);
            SneakersLista articulo = new SneakersLista(idValue,titulo, descripcion,descripcion,imgBytes);
            listaSneak.add(articulo);
        }

        // Crear el adaptador y pasar la lista de elementos
        SneakerAdapterFav adapter = new SneakerAdapterFav(requireContext(), listaSneak);

        // Asignar el adaptador al ListView
        listView.setAdapter(adapter);

        // Cerrar el cursor y la base de datos
        cursor.close();
        bd.close();
    }


}
