package com.example.reto1.Adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reto1.AdminBD;
import com.example.reto1.R;
import com.example.reto1.SneakerDetailActivity;
import com.example.reto1.SneakersLista;

import java.util.List;

public class SneakerAdapterFav extends BaseAdapter {

    private Context context;
    private List<SneakersLista> listaSneakers;

    private View rootView; // Agrega un miembro para guardar la referencia a la vista


    public SneakerAdapterFav(Context context, List<SneakersLista> listaSneakers) {
        this.context = context;
        this.listaSneakers = listaSneakers;
        this.rootView = rootView; // Asigna la referencia a la vista

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

        TextView textView = convertView.findViewById(R.id.titleTextView);
        TextView textView2 = convertView.findViewById(R.id.descriptionTextView);// Cambia "R.id.text1" al ID correcto de tu TextView
        ImageView imageView = convertView.findViewById(R.id.imageView); // Asegúrate de usar el ID correcto para la ImageView

        SneakersLista sneaker = (SneakersLista) getItem(position);

        textView.setText(sneaker.getTitulo());
        textView2.setText(sneaker.getDescripcion());

        Button btnAccion = convertView.findViewById(R.id.btnAccion);

        if (sneaker.getImagen() != null) {
            // Configurar la imagen directamente desde el byte[]
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(sneaker.getImagen(), 0, sneaker.getImagen().length));
        }

        // Configurar la acción del botón
        btnAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrar();
                 }
        });

        return convertView;
    }

    public void borrar(){


        // Obtener una referencia al TextView dentro de la vista rootView
        TextView textView = rootView.findViewById(R.id.titleTextView);

        // crear administrador y la instancia de la bd
        /*AdminBD admin = new AdminBD(context, "administracion", null,1);
        SQLiteDatabase bd = admin.getReadableDatabase();//bd es para leer la tabla
        bd.delete("favoritos", "sneakertitle=" + textView, null);
        Toast.makeText(context, "Ha sido borrado de favoritos ", Toast.LENGTH_SHORT).show();

        bd.close();*/
        //restablecer las cajas de texto

    }

}
