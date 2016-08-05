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

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName + " Email: " + email + " Phone: " + phone + " Image: " + icon + "\n";
    }

}
