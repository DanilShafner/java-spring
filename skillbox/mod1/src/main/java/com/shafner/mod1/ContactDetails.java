package com.shafner.mod1;

public class ContactDetails {
    public String fullName;
    public String phoneNumber;
    public String email;

    public ContactDetails(String fullName, String phoneNumber, String email) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public ContactDetails() {
    }

    public ContactDetails parseFromString(String data, String separator) {
        var split = data.split(separator);

        fullName = split[0];
        phoneNumber = split[1];
        email = split[2];

        return this;
    }

    public ContactDetails parseFromString(String data) {
        return parseFromString(data, "; ");
    }

    public String toString(String separator) {
        return String.join(separator, fullName, phoneNumber, email);
    }

    public String toString() {
        return toString(" | ");
    }
}
