package com.yes.youthexploringscience.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yes.youthexploringscience.R;

import java.util.ArrayList;

/**
 * Created by Dan on 7/27/2016.
 */

public class ContactAdapter extends BaseAdapter {

    private Context context;
    private int resource;
    private ArrayList<Contact> contacts;

    public ContactAdapter(Context context, int resource, ArrayList<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
        this.resource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;

        if (convertView == null) {

            gridView = new View(context);
            gridView = inflater.inflate(resource, null);

            TextView textView = (TextView) gridView.findViewById(R.id.name);
            textView.setText(contacts.get(position).getFullName());

            ImageView imageView = (ImageView) gridView.findViewById(R.id.image);
            imageView.setImageResource(R.drawable.head);

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}