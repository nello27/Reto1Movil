package com.example.reto1.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.reto1.Adapters.SneakerAdapter;
import com.example.reto1.Model.Sneaker;
import com.example.reto1.R;
import com.example.reto1.SneakerDetailActivity;

public class ProductosFragment extends Fragment {

    private GridView grilla;
    private SneakerAdapter sneakerAdapter;


    public ProductosFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);\

        return inflater.inflate(R.layout.productos_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        grilla = getView().findViewById(R.id.grilla);
        sneakerAdapter = new SneakerAdapter(getContext());
        grilla.setAdapter(sneakerAdapter);

        grilla.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sneaker item = (Sneaker) parent.getItemAtPosition(position);

                // pasar datos entre actividades INTENT
                Intent intent = new Intent(getContext(), SneakerDetailActivity.class);
                intent.putExtra("sneaker_id", item.getId());
                intent.putExtra("sneaker_img", item.getIdDrawable());
                intent.putExtra("sneaker_name", item.getName());
                intent.putExtra("sneaker_description", item.getDescription());
                startActivity(intent);

                //Toast.makeText(getContext(), "Se selecciono "+item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
