package com.example.rishan.rishan.Models;

import com.orm.SugarRecord;

/**
 * Created by Rishan on 5/14/2018.
 */

public class ProductTags extends SugarRecord<ProductTags>{

    String t;
    Products p;

    public ProductTags() {
    }

    public ProductTags(String t, Products p) {
        this.t = t;
        this.p = p;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public Products getP() {
        return p;
    }

    public void setP(Products p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return "ProductTags{" +
                "t=" + this.getT() +
                ", p=" + this.getP() +
                '}';
    }
}
