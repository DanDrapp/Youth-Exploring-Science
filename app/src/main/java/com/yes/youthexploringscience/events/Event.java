package com.yes.youthexploringscience.events;

import android.os.Parcel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Dan on 8/1/2016.
 */
public class Event implements Comparable<Event>, Serializable {

    private String htmlLink;
    private String created;
    private String updated;
    private String summary;
    private String description;
    private String location;
    private Start start;
    private End end;
    private ArrayList<Attachment> attachments;

    public Event() {
        htmlLink = "";
        created = "";
        updated = "";
        summary = "";
        description = "";
        location = "";
        start = new Start("");
        end = new End("");
        attachments = new ArrayList<>();
    }

    protected Event(Parcel in) {
        htmlLink = in.readString();
        created = in.readString();
        updated = in.readString();
        summary = in.readString();
        description = in.readString();
        location = in.readString();
    }

    public ArrayList<String> getAttachmentLinks() {
        ArrayList<String> links = new ArrayList<>();
        for (Attachment att : attachments) {
            links.add(att.getFileUrl());
        }
        return links;
    }

    public String getHtmlLink() {
        return htmlLink;
    }

    public void setHtmlLink(String htmlLink) {
        this.htmlLink = htmlLink;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Start getStart() {
        return start;
    }

    public void setStart(Start start) {
        this.start = start;
    }

    public End getEnd() {
        return end;
    }

    public void setEnd(End end) {
        this.end = end;
    }

    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<Attachment> attachments) {
        this.attachments = attachments;
    }

    private String getAttachmentsString() {
        String str = "";
        for (int i = 0; i < attachments.size(); i++) {
            str += "\t\t\t{\n\t\t\t\t";
            str += attachments.get(i).toString();
            str += "\n\t\t\t},\n";
        }
        return str;
    }

    @Override
    public String toString() {

        return "\n\t{\n\t\t\"htmlLink\": \"" + htmlLink + "\",\n\t\t\"created\": \"" + created +
                "\",\n\t\t\"updated\": \"" + updated + "\",\n\t\t\"summary\": \"" + summary +
                "\",\n\t\t\"description\": \"" + description + "\",\n\t\t\"location\": \"" + location +
                "\"\n\t\t\"start\': {\n" + start.toString() + "\n\t\t},\n\t\t\"end\": {\n" + end.toString() +
                "\n\t\t},\n\t\t\"attachments\": [\n" + getAttachmentsString() +"\n\t\t]\n\t}\n";

    }

    @Override
    public int compareTo(Event event) {
        int compareInt = this.start.getCompare().compareTo(event.getStart().getCompare());
        if (compareInt < 0)
            return -1;
        else if (compareInt > 0)
            return 1;
        else
            return 0;
    }
}
