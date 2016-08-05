package com.yes.youthexploringscience.contacts;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;

import com.google.gson.Gson;
import com.yes.youthexploringscience.R;
import com.yes.youthexploringscience.fragments.ContactsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Dan on 8/1/2016.
 */
public class ServerConnectionContacts extends AsyncTask<String, Void, ArrayList<Contact>> {

    private Context context;
    private ArrayList<Contact> contact;
    private GridView gridView;

    public ServerConnectionContacts(Context context, GridView gridView, ArrayList<Contact> contacts) {
        this.context = context;
        this.gridView = gridView;
        this.contact = contacts;
    }

    @Override
    protected ArrayList<Contact> doInBackground(String... args) {
        ArrayList<Contact> contacts = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(args[0]).build();

        try {
            Response response = client.newCall(request).execute();

            Gson gson = new Gson();
            JSONArray jsonArray = new JSONArray(response.body().string());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                Contact contact = gson.fromJson(jsonObj.toString(), Contact.class);
                contacts.add(contact);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    protected void onPostExecute(ArrayList<Contact> contacts) {
        super.onPostExecute(contacts);

        ContactsFragment.contacts = contacts;
        ContactAdapter contactAdapter = new ContactAdapter(context, R.layout.cell, contacts);
        gridView.setAdapter(contactAdapter);

    }
}
