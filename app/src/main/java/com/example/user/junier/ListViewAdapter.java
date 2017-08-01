package com.example.user.junier;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 2017-08-01.
 */

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<list_Item> listViewItemList = new ArrayList<>();


    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return listViewItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item, viewGroup, false);
        }
        ImageView imageView = v.findViewById(R.id.imageView1);
        TextView textView = v.findViewById(R.id.textView1);

        list_Item list = listViewItemList.get(i);
        imageView.setImageDrawable(list.getImage());
        textView.setText(list.getText());

        return v;
    }

    public void addItem(Drawable icon, String text) {
        list_Item list = new list_Item();
        list.setImage(icon);
        list.setText(text);

        listViewItemList.add(list);
    }
}
