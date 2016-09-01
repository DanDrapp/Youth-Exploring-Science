package com.yes.youthexploringscience.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.yes.youthexploringscience.R;
import com.yes.youthexploringscience.activities.EventDetailActivity;
import com.yes.youthexploringscience.events.Event;
import com.yes.youthexploringscience.events.ServerConnectionEvents;

import java.util.ArrayList;
import java.util.List;

public class CalendarFragment extends Fragment {

    private int url = R.string.server_fetch_events_localhost;
    private ListView lvEvents;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tvNoEvents;
    public static List<Event> events = new ArrayList<>();

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        tvNoEvents = (TextView) view.findViewById(R.id.tvNoEvents);
        lvEvents = (ListView) view.findViewById(R.id.lvEvents);
        setListItemClickListener(lvEvents);

        fetchEvents(getString(url));

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        setSwipeListener(swipeRefreshLayout, getString(url));

        return view;
    }

    private void fetchEvents(String url) {
        new ServerConnectionEvents(getContext(), lvEvents, events, tvNoEvents).execute(url);
    }

    private void setListItemClickListener(ListView lvEvents) {
        lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Toast.makeText(getContext(), events.get(position).toString() ,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), EventDetailActivity.class);
                intent.putExtra("EVENT", events.get(position));
                startActivity(intent);
            }
        });
    }

    private void setSwipeListener(final SwipeRefreshLayout swipeRefreshLayout, final String url) {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                events.clear();
                new ServerConnectionEvents(getContext(), lvEvents, events, tvNoEvents).execute(url);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
