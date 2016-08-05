package com.yes.youthexploringscience.events;

import java.io.Serializable;

/**
 * Created by Dan on 8/1/2016.
 */
public class End implements Serializable {
    private String dateTime;
    private String date;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public End(String dateTime) {
        this.dateTime = dateTime;
        date = "";
    }

    public End(String date, boolean bool) {
        this.date = date;
        dateTime = "";
    }

    public String getFormattedDate() {
        if (dateTime.equals("")) {
            return date;
        } else {
            return dateTime;
        }
    }

    @Override
    public String toString() {
        if (dateTime.equals("")) {
            return "\t\t\t\"date\": \"" + date + "\"";
        }
        else {
            return "\t\t\t\"dateTime\": \"" + dateTime + "\"";
        }
    }
}
