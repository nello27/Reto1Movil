package com.example.reto1.Adapters;

import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reto1.Model.Sneaker;
import com.example.reto1.R;

public class SneakerAdapter extends BaseAdapter {

    private Context context;

    public SneakerAdapter(Context context){

        this.context = context;
    }


    @Override
    public int getCount() {
        //return 0;
        return Sneaker.ITEMS.length;
    }

    @Override
    public Sneaker getItem(int position) {
        //return null;
        return Sneaker.ITEMS[position];
    }

    @Override
    public long getItemId(int position) {
        //return 0;
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {


        if (view == null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_grilla, parent, false);
        }

        ImageView fotoSneaker = (ImageView) view.findViewById(R.id.sneakerimg);
        TextView nombreSneaker = (TextView) view.findViewById(R.id.sneakerName);

        Sneaker item = getItem(position);
        fotoSneaker.setImageResource(item.getIdDrawable());
        nombreSneaker.setText(item.getName());

        return view;

    }

}
