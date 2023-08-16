package com.example.reto1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.reto1.Adapters.AdaptadorLista;
import com.example.reto1.Model.Sneakers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BlankFragment extends BaseVolleyFragment{

    //Conecta con el layouy fragment_blank
    private Button conector,enlazagit,enlazaoracle,pidearray,cargaprods;
    private TextView etiqueta,etiqueta2;
    private ListView listaV;
    //Para optimizar código
    private VolleyError err;
    private int cant_items,id,talla; //Cantidad de elementos, id y talla
    private String descripcion,marca,color,name,description,image,material; //elementos que se consultan en la BD
    private Double precio;
    private ArrayList productos;
    //Obtener elementos de la lista
    private String[] elementos;

    public BlankFragment() {
        // Constructor - se deja quieto
    }
    //Se sobre escribe este método
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);// la herencia
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_blank,container,false);
        etiqueta = (TextView) vista.findViewById(R.id.label);
        etiqueta2 = (TextView) vista.findViewById(R.id.label2);
        conector = (Button) vista.findViewById(R.id.button5);
        enlazagit = (Button) vista.findViewById(R.id.button5);
        enlazaoracle = (Button) vista.findViewById(R.id.button5);
        pidearray = (Button) vista.findViewById(R.id.button5);
        cargaprods = (Button) vista.findViewById(R.id.button5);
        listaV = (ListView) vista.findViewById(R.id.lista1);
        return vista;
    }
    //Este método es el que hace el request tipo JSon Array
    private void armaPeticion(){
        String url="https://mdn.github.io/learning-area/javascript/oojs/json/superheroes.json";
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray array) {
                etiqueta.setText(array.toString());
                onConnectionFinished();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onConnectionFailed(error.toString());
            }
        });
        addToQueue(request);
    }

    //Método para request tipo Object
    private void armaPeticionJson(){
        String url="https://mdn.github.io/learning-area/javascript/oojs/json/superheroes.json";
        JsonObjectRequest solicitud = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                etiqueta.setText(response.toString());
                onConnectionFinished();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onConnectionFailed(error.toString());
            }
        });
        addToQueue(solicitud);
    }
    //Método para request tipo Object Oracle

    //Método para el Json String Array
    //Este método es el que hace el request tipo JSon Array
    private void armaPeticionArrayOracle(){
        String url="https://gda974b9aace0ce-r42dmc0rm5n1wmbz.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/prueba/calzado/";
        JsonObjectRequest request = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject array) {
                //Toma los items y los ubica en un array Json
                JSONArray arregloJson;
                String cadena = null;
                String cadena2 = null;
                int cantidad = 0;
                //etiqueta.setText(array.toString());
                cadena = array.toString();
                try {
                    cadena = array.getString("items");
                    arregloJson = new JSONArray(cadena);
                    cantidad = arregloJson.length();
                } catch (JSONException e) {
                    //e.printStackTrace();
                    onConnectionFailed(e.toString());
                    cadena = "Error";
                }
                etiqueta.setText(cadena);
                cadena2 = "Total items: "+String.valueOf(cantidad);
                etiqueta2.setText(cadena2);
                onConnectionFinished();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onConnectionFailed(error.toString());
            }
        });
        addToQueue(request);
    }
    //Este método lee el Json de respuesta y obtiene un array de objetos tipo producto
    /*private void cargaListaProd(){
        String url="https://g127742c93d7e5c-r09bclxntcqiu4s0.adb.sa-saopaulo-1.oraclecloudapps.com/ords/admin/shoestore/sneakers";
        //String url="https://gda974b9aace0ce-r42dmc0rm5n1wmbz.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/prueba/calzado/";
        productos = new ArrayList();
        JsonObjectRequest request = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject array) {
                //Toma los items y los ubica en un array Json
                JSONArray arregloJson;
                String cadena = null;
                cadena = array.toString();
                String cadena2 = null;
                cant_items = 0;
                try {
                    cadena = array.getString("items");
                    arregloJson = new JSONArray(cadena); //convertir string de respuesta a Json
                    cant_items = arregloJson.length();
                    elementos = new String[cant_items];
                    //Iterar sobre el arrayJson para obtener los objetos
                    for(int i=0;i<cant_items;i++){
                        JSONObject objeto = arregloJson.getJSONObject(i); //obtener el elemento Json del índice i
                        id = objeto.getInt("id");
                        descripcion = objeto.getString("descripcion");
                        marca = objeto.getString("marca");
                        talla = objeto.getInt("talla");
                        color = objeto.getString("color");
                        material = objeto.getString("material");
                        precio = objeto.getDouble("precio");
                        Producto producto = new Producto(id,descripcion,marca,talla,color,material,precio);
                        cadena = producto.getDescripcion();
                        cadena2 = producto.getMarca();
                        productos.add(producto); //arma la lista de productos
                        //elementos que van a ir al listview
                        elementos[i] = producto.getDescripcion()+"-"+producto.getMarca();
                    }
                } catch (JSONException e) {
                    //e.printStackTrace();
                    onConnectionFailed(e.toString());
                    cadena = "Error";
                }
                AdaptadorLista adaptador = new AdaptadorLista(elementos);
                listaV.setAdapter(adaptador);
                etiqueta.setText(cadena);
                cadena2 = "Ultimo ítem: "+cadena;
                etiqueta2.setText(cadena2);
                onConnectionFinished();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onConnectionFailed(error.toString());
            }
        });
        addToQueue(request);
    }*/

    private void cargaListaProd(){
        String url="https://g127742c93d7e5c-r09bclxntcqiu4s0.adb.sa-saopaulo-1.oraclecloudapps.com/ords/admin/shoestore/sneakers";
        //String url="https://gda974b9aace0ce-r42dmc0rm5n1wmbz.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/prueba/calzado/";
        productos = new ArrayList();
        JsonObjectRequest request = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject array) {
                //Toma los items y los ubica en un array Json
                JSONArray arregloJson;
                String cadena = null;
                cadena = array.toString();
                String cadena2 = null;
                cant_items = 0;
                try {
                    cadena = array.getString("items");
                    arregloJson = new JSONArray(cadena); //convertir string de respuesta a Json
                    cant_items = arregloJson.length();
                    elementos = new String[cant_items];
                    //Iterar sobre el arrayJson para obtener los objetos
                    for(int i=0;i<cant_items;i++){
                        JSONObject objeto = arregloJson.getJSONObject(i); //obtener el elemento Json del índice i
                        id = objeto.getInt("id");
                        description = objeto.getString("description");
                        name = objeto.getString("name");
                        //talla = objeto.getInt("talla");
                        image = objeto.getString("image");
                        //material = objeto.getString("material");
                        //precio = objeto.getDouble("precio");
                        Sneakers sneakers = new Sneakers(id,name,description,image);
                        cadena = sneakers.getDescription();
                        cadena2 = sneakers.getName();
                        productos.add(sneakers); //arma la lista de productos
                        //elementos que van a ir al listview
                        elementos[i] = sneakers.getDescription()+"-"+sneakers.getName();
                    }
                } catch (JSONException e) {
                    //e.printStackTrace();
                    onConnectionFailed(e.toString());
                    cadena = "Error";
                }
                AdaptadorLista adaptador = new AdaptadorLista(elementos);
                listaV.setAdapter(adaptador);
                etiqueta.setText(cadena);
                cadena2 = "Ultimo ítem: "+cadena;
                etiqueta2.setText(cadena2);
                onConnectionFinished();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onConnectionFailed(error.toString());
            }
        });
        addToQueue(request);
    }

    //Asignar métodos  a los botones
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        conector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                armaPeticion();
            }
        });
        enlazagit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                armaPeticionJson();
            }
        });

        pidearray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                armaPeticionArrayOracle();
            }
        });
        cargaprods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargaListaProd();
            }
        });
    }
}
