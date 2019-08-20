package com.example.rishan.rishan.Models;

import android.nfc.Tag;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rishan on 5/11/2018.
 */

public class Products extends SugarRecord<Products> {
    @SerializedName("Name")
   private String Name;
    @SerializedName("ShortDescription")
    private String ShortDescription;
    @SerializedName("longdescription")
    private String longdescription;
    @SerializedName("Catagory")
     private String Catagory;
    @SerializedName("Price")
  private double Price;
    @SerializedName("Quantity")
   private int Quantity;
    @SerializedName("Active")
     private boolean Active;
    @SerializedName("ScaledImage")
  private String ScaledImage;
    @SerializedName("FullImage")
    private String FullImage;
    @SerializedName("Status")
    private String Status;
    @SerializedName("Tag")


    private ArrayList<Integer> Tag;
    //Tag arraylist has been made to store the Tags of the json file but has not been called in constructor
    // does have getters and setters


    public ArrayList<Integer> getTag() {
        return Tag;
    }

    public void setTag(ArrayList<Integer> tag) {
        Tag = tag;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }


    public Products() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        ShortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longdescription;
    }

    public void setLongDescription(String longDescription) {
        longdescription = longDescription;
    }

    public String getCatagory() {
        return Catagory;
    }

    public void setCatagory(String catagory) {
        Catagory = catagory;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public String getScaledImage() {
        return ScaledImage;
    }

    public void setScaledImage(String scaledImage) {
        ScaledImage = scaledImage;
    }

    public String getFullImage() {
        return FullImage;
    }

    public void setFullImage(String fullImage) {
        FullImage = fullImage;
    }

  //  public List<ProductTags> mTagList(){
    //    return Select.from(ProductTags.class).where(Condition.prop("p").eq(this.getId())).list();
   // }
}
