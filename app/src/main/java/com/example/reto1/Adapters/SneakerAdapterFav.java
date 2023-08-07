package com.example.reto1.Adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reto1.R;
import com.example.reto1.SneakerDetailActivity;
import com.example.reto1.SneakersLista;

import java.util.List;

public class SneakerAdapterFav extends BaseAdapter {

    private Context context;
    private List<SneakersLista> listaSneakers;



    public SneakerAdapterFav(Context context, List<SneakersLista> listaSneakers) {
        this.context = context;
        this.listaSneakers = listaSneakers;
    }

    @Override
    public int getCount() {
        return listaSneakers.size();
    }

    @Override
    public Object getItem(int position) {
        return listaSneakers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.titleTextView); // Cambia "R.id.text1" al ID correcto de tu TextView
        ImageView imageView = convertView.findViewById(R.id.imageView); // Asegúrate de usar el ID correcto para la ImageView

        SneakersLista sneaker = (SneakersLista) getItem(position);

        textView.setText(sneaker.getTitulo());

        if (sneaker.getImagen() != null) {
            // Configurar la imagen directamente desde el byte[]
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(sneaker.getImagen(), 0, sneaker.getImagen().length));
        }

        return convertView;
    }

}
