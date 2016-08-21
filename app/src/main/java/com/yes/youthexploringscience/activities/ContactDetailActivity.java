package com.yes.youthexploringscience.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yes.youthexploringscience.R;
import com.yes.youthexploringscience.contacts.Contact;

public class ContactDetailActivity extends AppCompatActivity {

    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        contact = (Contact) getIntent().getSerializableExtra("CONTACT");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Toast.makeText(this, contact.toString(),
                Toast.LENGTH_LONG).show();

        setupContactView();
    }

    private void setupContactView() {
        TextView tvName = (TextView) findViewById(R.id.tvDetailName);
        TextView tvPhone = (TextView) findViewById(R.id.tvDetailPhone);
        TextView tvEmail = (TextView) findViewById(R.id.tvDetailEmail);
        ImageView ivIcon = (ImageView) findViewById(R.id.ivDetailIcon);
        ImageView ivPhone = (ImageView) findViewById(R.id.ivDetailPhone);
        ImageView ivEmail = (ImageView) findViewById(R.id.ivDetailEmail);

        tvName.setText(contact.getFullName());
        tvPhone.setText(contact.getFormattedPhone());
        tvEmail.setText(contact.getEmail());
        Picasso.with(this).load("http://www.clker.com/cliparts/5/9/4/c/12198090531909861341man%20silhouette.svg.med.png").into(ivIcon);

        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + contact.getPhone()));
//                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(callIntent);

            }
        });

        ivEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent emailIntent = new Intent(Intent.ACTION_VIEW);
                emailIntent.setData(Uri.parse("mailto:" + contact.getEmail()));
                startActivity(emailIntent);

            }
        });

    }

}
