package com.yes.youthexploringscience.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yes.youthexploringscience.R;
import com.yes.youthexploringscience.events.Event;

public class EventDetailActivity extends AppCompatActivity {

    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        event = (Event) getIntent().getSerializableExtra("EVENT");

        setupEventView();

    }

    private void setupEventView() {
        TextView tvTitle = (TextView) findViewById(R.id.tvDetailTitle);
        TextView tvLocationLabel = (TextView) findViewById(R.id.tvDeatilLocationLabel);
        TextView tvLocation = (TextView) findViewById(R.id.tvDetailLocation);
        TextView tvStartLabel = (TextView) findViewById(R.id.tvDetailStartLabel);
        TextView tvStart = (TextView) findViewById(R.id.tvDetailStart);
        TextView tvEndLabel = (TextView) findViewById(R.id.tvDetailEndLabel);
        TextView tvEnd = (TextView) findViewById(R.id.tvDetailEnd);
        TextView tvDescriptionLabel = (TextView) findViewById(R.id.tvDetailDescriptionLabel);
        TextView tvDescription = (TextView) findViewById(R.id.tvDetailDescription);
        TextView tvAttachmentsLabel = (TextView) findViewById(R.id.tvDetailAttachmentsLabel);

        tvTitle.setText(event.getSummary().equals("") ? "No Title" : event.getSummary());
        tvTitle.setPaintFlags(tvTitle.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);

        tvStartLabel.setPaintFlags(tvStartLabel.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
        tvEndLabel.setPaintFlags(tvEndLabel.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);

        tvStart.setText(event.getStart().getFormattedDate());
        tvEnd.setText(event.getEnd().getFormattedDate());

        if (event.getLocation().equals("")) {
            tvLocationLabel.setVisibility(View.GONE);
            tvLocation.setVisibility(View.GONE);
        } else {
            tvLocationLabel.setPaintFlags(tvLocationLabel.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
            tvLocationLabel.setTextColor(Color.RED);
            tvLocation.setText(event.getLocation());
            tvLocation.setPaintFlags(tvLocation.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            tvLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                    mapIntent.setData(Uri.parse("http://maps.google.co.in/maps?q=" + event.getLocation()));
                    startActivity(mapIntent);
                }
            });
        }

        if (event.getDescription().equals("")) {
            tvDescriptionLabel.setVisibility(View.GONE);
            tvDescription.setVisibility(View.GONE);
        } else {
            tvDescription.setText(event.getDescription());
            tvDescriptionLabel.setPaintFlags(tvDescriptionLabel.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG );
            tvDescriptionLabel.setTextColor(Color.RED);
        }

        if (event.getAttachments().size() == 0) {
            tvAttachmentsLabel.setVisibility(View.GONE);
        } else {
            tvAttachmentsLabel.setPaintFlags(tvAttachmentsLabel.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
            tvAttachmentsLabel.setTextColor(Color.RED);
            // TODO
        }
    }

}
