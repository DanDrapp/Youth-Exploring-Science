package com.yes.youthexploringscience.contacts;

import java.io.Serializable;

/**
 * Created by Dan on 7/26/2016.
 */
public class Contact implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String icon;

    public Contact() {

    }

    public Contact(String firstName, String lastName, String email, String phone, String icon) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.icon = icon;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIcon() {
        return icon;
    }

    public String getPhone() {
        return phone;
    }

    public String getFormattedPhone() {
        if (phone.length() == 10)
            return String.format("(%s) %s-%s", phone.substring(0, 3), phone.substring(3, 6), phone.substring(6,phone.length()));
        else if (phone.length() == 7)
            return String.format("%s-%s", phone.substring(0, 3), phone.substring(3, phone.length()));
        else
            return phone;
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName + " Email: " + email + " Phone: " + phone + " Image: " + icon + "\n";
    }

}
