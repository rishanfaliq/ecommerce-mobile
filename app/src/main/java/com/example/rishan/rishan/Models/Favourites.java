package com.example.rishan.rishan.Models;

import com.orm.SugarRecord;

/**
 * Created by Rishan on 5/30/2018.
 */

public class Favourites extends SugarRecord<Favourites> {

    private Products p;
    private User u;

    public Favourites() {
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
}
