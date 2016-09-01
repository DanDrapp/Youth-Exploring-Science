package com.yes.youthexploringscience.contacts;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yes.youthexploringscience.R;

import java.util.ArrayList;

/**
 * Created by Dan on 7/27/2016.
 */

public class ContactAdapter extends BaseAdapter {

    private Context context;
    private int resource;
    private ArrayList<Contact> contacts;
    LayoutInflater inflater;

    public ContactAdapter(Context context, int resource, ArrayList<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
        this.resource = resource;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        ContactHolder holder = null;
        Contact contact = contacts.get(position);

        if (convertView == null) {
            holder = new ContactHolder();
            convertView = inflater.inflate(resource, null);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ContactHolder) convertView.getTag();
        }

        holder.name.setText(contact.getFullName());
        if (contacts.get(position).getImage() != null)
            Picasso.with(context).load(contact.getImage()).into(holder.image);
        else
            Picasso.with(context).load("http://www.clker.com/cliparts/5/9/4/c/12198090531909861341man%20silhouette.svg.med.png").into(holder.image);

        return convertView;
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

    private class ContactHolder {
        private ImageView image;
        private TextView name;
    }
}