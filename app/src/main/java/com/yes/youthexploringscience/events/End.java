package com.yes.youthexploringscience.events;

import android.annotation.TargetApi;
import android.os.Build;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    public String getTimeInMilliseconds() {
//        try {
//            Calendar calendar = Calendar.getInstance();
//            if (dateTime.equals("")) {
//                SimpleDateFormat format = new SimpleDateFormat();
//                Date currentDate =  format.parse(date);
//                calendar.setTime(currentDate);
//            } else {
//                SimpleDateFormat format = new SimpleDateFormat();
//                Date currentDate = format.parse(dateTime);
//                calendar.setTime(currentDate);
//            }
//
//            return calendar.getTimeInMillis() + "";
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return "";
    }

    public String getFormattedDate() {

        Calendar today = Calendar.getInstance();
        Calendar event = Calendar.getInstance();

        String[] eventComponents = date.equals("") ? dateTime.split("-|T|:") : date.split("-|T|:");

        // Only include time if time is provided.
        if (eventComponents.length == 3) {
            event.set(Integer.parseInt(eventComponents[0]),
                    Integer.parseInt(eventComponents[1]) - 1,
                    Integer.parseInt(eventComponents[2]));
        } else if (eventComponents.length == 8) {
            event.set(Integer.parseInt(eventComponents[0]),
                    Integer.parseInt(eventComponents[1]) - 1,
                    Integer.parseInt(eventComponents[2]),
                    Integer.parseInt(eventComponents[3]),
                    Integer.parseInt(eventComponents[4]));
        }

        String formattedDate = "";
        if (today.get(Calendar.DAY_OF_YEAR) == event.get(Calendar.DAY_OF_YEAR)) {
            formattedDate += "Today, ";
        } else if (today.get(Calendar.DAY_OF_YEAR) == event.get(Calendar.DAY_OF_YEAR) - 1 && dateTime.equals("")) {
            formattedDate += "Today, ";
        } else if (today.get(Calendar.DAY_OF_YEAR) == event.get(Calendar.DAY_OF_YEAR) - 1) {
            formattedDate += "Tomorrow, ";
        }

        // If no time is provided, Must be an all day event otherwise include time.
        if (eventComponents.length == 3) {
            Calendar temp = event;
            temp.add(Calendar.DATE, -1);
            formattedDate += getMonthName(event) + " "
                    + temp.get(Calendar.DAY_OF_MONTH) + ", 11:59 PM";
        } else {
            int numMinutes = event.get(Calendar.MINUTE);
            formattedDate += getMonthName(event) + " "
                    + event.get(Calendar.DAY_OF_MONTH) + ", "
                    + event.get(Calendar.HOUR) % 12 + ":"
                    + ("00" + numMinutes).substring(("" + numMinutes).length())
                    + (event.get(Calendar.AM_PM) == 0 ? " AM" : " PM");
        }

        return formattedDate;
    }

    private String getMonthName(Calendar event) {
        switch (event.get(Calendar.MONTH)) {
            case 0:
                return "January";
            case 1:
                return "February";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "October";
            case 10:
                return "November";
            case 11:
                return "December";
            default:
                return "No Month";
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
