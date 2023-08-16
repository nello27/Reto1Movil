package com.example.reto1.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.reto1.R;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> itemsList;

    public GridViewAdapter(Context context, ArrayList<String> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
        this.itemsList = itemsList;

    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.grid_item_layout, null);
        }

        TextView textView = convertView.findViewById(R.id.textView);
        String itemName = itemsList.get(position);
        textView.setText(itemName);

        return convertView;
    }
}
