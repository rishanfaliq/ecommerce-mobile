package com.example.rishan.rishan.Models;

import com.orm.SugarRecord;

/**
 * Created by Rishan on 5/19/2018.
 */

public class Tags extends SugarRecord<Tags> {

    private String Tag;

    @Override
    public String toString() {
        return  Tag ;
    }

    public Tags() {
    }

    public String getTags() {
        return Tag;
    }

    public void setTags(String Tag) {
        this.Tag = Tag;
    }
}
