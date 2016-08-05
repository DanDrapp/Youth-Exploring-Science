package com.yes.youthexploringscience.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.yes.youthexploringscience.R;
import com.yes.youthexploringscience.activities.ContactDetailActivity;
import com.yes.youthexploringscience.contacts.Contact;
import com.yes.youthexploringscience.contacts.ServerConnectionContacts;


import java.util.ArrayList;


public class ContactsFragment extends Fragment {

    private int url = R.string.server_fetch_contact_localhost;
    public static ArrayList<Contact> contacts = new ArrayList<>();
    private GridView gridView;

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        gridView = (GridView) view.findViewById(R.id.gridView);
        setGridItemClickListener(gridView);

        fetchContacts(getString(url));

        return view;
    }

    private void fetchContacts(String url) {
        new ServerConnectionContacts(getContext(), gridView, contacts).execute(url);
    }

    private void setGridItemClickListener(GridView gridView) {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getContext(), contacts.get(position).toString() ,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), ContactDetailActivity.class);
                intent.putExtra("CONTACT", contacts.get(position));
                startActivity(intent);
            }
        });
    }
}
