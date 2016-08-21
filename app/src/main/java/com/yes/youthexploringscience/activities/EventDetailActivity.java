package com.yes.youthexploringscience.activities;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.yes.youthexploringscience.R;
import com.yes.youthexploringscience.events.Attachment;
import com.yes.youthexploringscience.events.Event;

public class EventDetailActivity extends AppCompatActivity {

    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBar actionBar;
        if ((actionBar = getSupportActionBar()) != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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
        ListView lvDetailAttachments = (ListView) findViewById(R.id.lvDetailAttachments);
        Button htmlLink = (Button) findViewById(R.id.htmlLink);

        // Title
        tvTitle.setText(event.getSummary().equals("") ? "No Title" : event.getSummary());
        tvTitle.setPaintFlags(tvTitle.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);

        // Start and End Labels
        tvStartLabel.setPaintFlags(tvStartLabel.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
        tvEndLabel.setPaintFlags(tvEndLabel.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);

        // Start and End
        tvStart.setText(event.getStart().getFormattedDate());
        tvEnd.setText(event.getEnd().getFormattedDate());

        // Location
        if (event.getLocation().equals("")) {
            tvLocationLabel.setVisibility(View.GONE);
            tvLocation.setVisibility(View.GONE);
        } else {
            tvLocationLabel.setPaintFlags(tvLocationLabel.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
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

        // Description
        if (event.getDescription().equals("")) {
            tvDescriptionLabel.setVisibility(View.GONE);
            tvDescription.setVisibility(View.GONE);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tvDescription.setText(Html.fromHtml(event.getDescription(), Html.FROM_HTML_MODE_LEGACY));
            } else {
                tvDescription.setText(Html.fromHtml(event.getDescription()));
            }
            tvDescriptionLabel.setPaintFlags(tvDescriptionLabel.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG );
        }

        // Attachments
        if (event.getAttachments().size() == 0) {
            tvAttachmentsLabel.setVisibility(View.GONE);
        } else {
            tvAttachmentsLabel.setPaintFlags(tvAttachmentsLabel.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
            lvDetailAttachments.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, event.getAttachmentLinks()));

            lvDetailAttachments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                    startActivity(browserIntent);
                }
            });
        }

        // Add Event Button
        htmlLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eventIntent = new Intent(Intent.ACTION_INSERT);
                eventIntent.setType("vnd.android.cursor.item/event")
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, event.getStart().getTimeInMilliseconds())
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, event.getEnd().getTimeInMilliseconds())
                        .putExtra(CalendarContract.Events.TITLE, event.getSummary())
                        .putExtra(CalendarContract.Events.DESCRIPTION, event.getDescription())
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, event.getLocation());
                startActivity(eventIntent);
            }
        });
    }

}
