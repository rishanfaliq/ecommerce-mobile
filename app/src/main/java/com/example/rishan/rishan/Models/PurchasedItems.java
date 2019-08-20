package com.example.rishan.rishan.Models;

import com.orm.SugarRecord;

/**
 * Created by Rishan on 5/23/2018.
 */

public class PurchasedItems extends SugarRecord<PurchasedItems> {

    Cart c;
    String date;
    User u;
    int numberOfItems;
    double total;
    String status="Purchased";
    int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }


    public PurchasedItems() {
    }

    public Cart getC() {
        return c;
    }

    public void setC(Cart c) {
        this.c = c;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
