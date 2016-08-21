package com.yes.youthexploringscience.events;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yes.youthexploringscience.R;

import java.util.List;

/**
 * Created by Dan on 8/1/2016.
 */
public class EventAdapter extends ArrayAdapter {

    private List<Event> eventsList;
    private int resource;
    private LayoutInflater inflater;

    public EventAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);

        this.resource = resource;
        eventsList = objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EventHolder holder = null;

        if (convertView == null) {

            holder = new EventHolder();
            convertView = inflater.inflate(resource, null);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.subtitle = (TextView) convertView.findViewById(R.id.subtitle);
            holder.start = (TextView) convertView.findViewById(R.id.start);
            holder.end = (TextView) convertView.findViewById(R.id.end);
            convertView.setTag(holder);
        } else {
            holder = (EventHolder) convertView.getTag();
        }



        holder.title.setText(eventsList.get(position).getSummary());
        holder.title.setTextColor(Color.BLACK);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.subtitle.setText(Html.fromHtml(eventsList.get(position).getDescription(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.subtitle.setText(Html.fromHtml(eventsList.get(position).getDescription()));
        }
        holder.subtitle.setTextColor(Color.BLACK);
        holder.start.setText("Start: " + eventsList.get(position).getStart().getFormattedDate());
        holder.start.setTextColor(Color.BLACK);
        holder.end.setText("End: " + eventsList.get(position).getEnd().getFormattedDate());
        holder.end.setTextColor(Color.BLACK);

        return convertView;
    }

    private class EventHolder {
        private ImageView icon;
        private TextView title;
        private TextView subtitle;
        private TextView start;
        private TextView end;
    }
}
