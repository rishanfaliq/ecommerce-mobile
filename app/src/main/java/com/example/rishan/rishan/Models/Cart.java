package com.example.rishan.rishan.Models;

import com.orm.SugarRecord;

/**
 * Created by Rishan on 5/9/2018.
 */

public class Cart extends SugarRecord<Cart> {

    private Products p;
    private User u;
    private String status="Pending";
    private int numberOfItems=0;
    private double total;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Products getP() {
        return p;
    }

    public void setP(Products p) {
        this.p = p;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public Cart() {
    }




}