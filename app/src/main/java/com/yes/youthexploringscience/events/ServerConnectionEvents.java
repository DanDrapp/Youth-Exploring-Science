package com.yes.youthexploringscience.events;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.yes.youthexploringscience.R;
import com.yes.youthexploringscience.fragments.CalendarFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Dan on 8/1/2016.
 */
public class ServerConnectionEvents extends AsyncTask<String, Void, List<Event>> {

    private Context context;
    private ListView lvEvents;

    public ServerConnectionEvents(Context context, ListView lvEvents, List<Event> events) {
        this.context = context;
        this.lvEvents = lvEvents;
    }

    @Override
    protected List<Event> doInBackground(String... args) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(args[0]).build();
        List<Event> events = new ArrayList<>();

        try {
            Response response = client.newCall(request).execute();
            events = parseEvents(response);
            return events;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    protected void onPostExecute(List<Event> events) {
        super.onPostExecute(events);

        Collections.sort(events);

        CalendarFragment.events = events;
        EventAdapter eventAdapter = new EventAdapter(context, R.layout.row, events);
        lvEvents.setAdapter(eventAdapter);
    }

    // Send Google Calendar JSON string to be parsed into Events
    private List<Event> parseEvents(Response response) throws Exception {

        List<Event> events = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(response.body().string());
        JSONArray jsonArray = jsonObject.getJSONArray("items");

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObj = jsonArray.getJSONObject(i);
            Event event = new Event();

            if (jsonObj.has("htmlLink")) {
                event.setHtmlLink(jsonObj.getString("htmlLink"));
            }

            if (jsonObj.has("created")) {
                event.setCreated(jsonObj.getString("created"));
            }

            if (jsonObj.has("updated")) {
                event.setUpdated(jsonObj.getString("updated"));
            }

            if (jsonObj.has("summary")) {
                event.setSummary(jsonObj.getString("summary"));
            }

            if (jsonObj.has("description")) {
                event.setDescription(jsonObj.getString("description"));
            }

            if (jsonObj.has("location")) {
                event.setLocation(jsonObj.getString("location"));
            }

            if (jsonObj.has("start")) {
                JSONObject dateTime = jsonObj.getJSONObject("start");
                Start start;
                if (dateTime.has("dateTime")) {
                    start = new Start(dateTime.getString("dateTime"));
                } else {
                    start = new Start(dateTime.getString("date"), false);
                }
                event.setStart(start);
            }

            if (jsonObj.has("end")) {
                JSONObject dateTime = jsonObj.getJSONObject("end");
                End end;
                if (dateTime.has("dateTime")) {
                    end = new End(dateTime.getString("dateTime"));
                } else {
                    end = new End(dateTime.getString("date"), false);
                }
                event.setEnd(end);
            }

            if (jsonObj.has("attachments")) {
                ArrayList<Attachment> attachments = new ArrayList<>();
                JSONArray attachmentArray = jsonObj.getJSONArray("attachments");
                for (int j = 0; j < attachmentArray.length(); j++) {
                    JSONObject attachment = attachmentArray.getJSONObject(j);
                    String fileUrl = attachment.getString("fileUrl");
                    String title = attachment.getString("title");
                    String iconLink = attachment.getString("iconLink");

                    Attachment att = new Attachment(fileUrl, title, iconLink);
                    attachments.add(att);
                }
                event.setAttachments(attachments);
            }

            events.add(event);
        }

        return events;
    }
}
