package com.example.reto1.Adapters;

import android.content.Context;
import android.content.Intent;
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
import com.example.reto1.Fragments.FavoritosFragment;
import com.example.reto1.MainActivity;
import com.example.reto1.Model.SneakerDatabaseHelper;
import com.example.reto1.R;
import com.example.reto1.SneakerDetailActivity;
import com.example.reto1.SneakersLista;

import org.w3c.dom.Text;

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
        // Configurar la acción del botón
        btnAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Crear administrador y la instancia de la base de datos
                AdminBD admin = new AdminBD(context, "sneaker_database", null, 1);
                SQLiteDatabase bd = admin.getReadableDatabase();

                // Obtener el ID del sneaker
                Integer dato = sneaker.getID();

                // Utilizar las constantes definidas en SneakerDatabaseHelper para las tablas y columnas
                bd.delete(SneakerDatabaseHelper.TABLE_SNEAKERS, SneakerDatabaseHelper.COLUMN_ID + "=" + dato, null);
                Toast.makeText(context, "Se ha quitado de favoritos " + dato, Toast.LENGTH_SHORT).show();

                bd.close();

                // Reiniciar la actividad para cargar la lista actualizada
                //Intent intent = new Intent(context, MainActivity.class); // Cambiar "TuActividad" al nombre de tu actividad
                //context.startActivity(intent);

                // Actualizar la lista de favoritos
                listaSneakers.remove(position); // Remover el elemento de la lista en la posición actual
                notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado y debe refrescar la vista

            }
        });


        return convertView;
    }

}
