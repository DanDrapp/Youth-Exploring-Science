package com.yes.youthexploringscience.events;

import java.io.Serializable;

/**
 * Created by Dan on 8/1/2016.
 */
public class Attachment implements Serializable {
    private String fileUrl;
    private String title;
    private String iconLink;

    public Attachment(String fileUrl, String title, String iconLink) {
        this.fileUrl = fileUrl;
        this.title = title;
        this.iconLink = iconLink;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "\"fileUrl\": \"" + fileUrl + "\"\n\t\t\t\t\"title\": \"" + title +
                "\"\n\t\t\t\t\"iconLink\": \"" + iconLink + "\"\n";
    }
}
