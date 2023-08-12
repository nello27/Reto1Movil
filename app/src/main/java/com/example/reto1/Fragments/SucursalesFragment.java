package com.example.reto1.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.reto1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SucursalesFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;


    public SucursalesFragment(){


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sucursales_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapContainer);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.mapContainer, mapFragment)
                    .commit();
            mapFragment.getMapAsync(this);
        }
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Bogotá and set a closer zoom level
        LatLng bogota = new LatLng(4.750340, -74.119895);
        float zoomLevel = 12f; // Ajusta el valor para lograr el nivel de zoom deseado
        mMap.addMarker(new MarkerOptions().position(bogota).title("Sede Suba"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bogota, zoomLevel));

        // Ubicación sede chapinero  4.650493669603073, -74.06133722556912
        LatLng Chapinero = new LatLng(4.650493669603073, -74.06133722556912); // Reemplaza con las coordenadas de la otra ciudad
        mMap.addMarker(new MarkerOptions().position(Chapinero).title("Sede Chapinero"));
        // Ubicación sede Fontibon 4.677054, -74.142900
        LatLng Fontibon = new LatLng(4.677054, -74.142900); // Reemplaza con las coordenadas de la otra ciudad
        mMap.addMarker(new MarkerOptions().position(Fontibon).title("Sede Fontibon"));
    }

}
