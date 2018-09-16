package com.example.hendzee.funplus;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<DataItem>{

    Context context;
    int layoutResourceId;
    List<DataItem> data = null;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<DataItem> objects) {
        super(context, resource, objects);

        this.context = context;
        this.layoutResourceId = resource;
        this.data = objects;
    }

    static class DataHolder{
        ImageView imageIcon;
        TextView textValue;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataHolder dataHolder = null;

        if (convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();

            convertView = inflater.inflate(layoutResourceId, parent, false);

            dataHolder = new DataHolder();
            dataHolder.imageIcon = (ImageView) convertView.findViewById(R.id.imageIcon);
            dataHolder.textValue = (TextView) convertView.findViewById(R.id.textValue);

            convertView.setTag(dataHolder);
        }else{
            dataHolder = (DataHolder)convertView.getTag();
        }

        DataItem dataItem = data.get(position);
        dataHolder.imageIcon.setImageResource(dataItem.resourceThumbnail);
        dataHolder.textValue.setText("Value is " + dataItem.textValue);

        return convertView;
    }
}
