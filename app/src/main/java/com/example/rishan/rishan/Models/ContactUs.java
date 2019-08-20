package com.example.rishan.rishan.Models;

import com.orm.SugarRecord;

/**
 * Created by Rishan on 5/16/2018.
 */

public class ContactUs extends SugarRecord<ContactUs> {

    String sender;
    String recipient;
    String date;

    public ContactUs() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
