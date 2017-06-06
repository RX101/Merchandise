package com.example.a15041867.merchandise;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 15041867 on 6/6/2017.
 */

public class ItemAdapter extends ArrayAdapter<Item> {

    Context context;
    int layoutResourceId;
    ArrayList<Item> itemList = null;


    public ItemAdapter(Context context, int resource, ArrayList<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.itemList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PersonHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new PersonHolder();
            holder.ItemName = (TextView)row.findViewById(R.id.txtItemName);
            holder.Price = (TextView)row.findViewById(R.id.txtPrice);

            row.setTag(holder);
        }
        else
        {
            holder = (PersonHolder)row.getTag();
        }

        Item item = itemList.get(position);
        holder.ItemName.setText(item.getName());
        holder.Price.setText(item.getPrice());
        return row;
    }

    static class PersonHolder
    {
        TextView ItemName;
        TextView Price;
    }



}
