package com.example.rishan.rishan.Models;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rishan on 4/2/2018.
 */

public class User extends SugarRecord{
    private String username;
    private String password;
    private String email;
    private String profilepicture="https://pm1.narvii.com/6552/c289310fa2ee0ff58d8f8cc2118f63c7f7b32257_hq.jpg";
    ArrayList<Cart> cartList;

    public ArrayList<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(ArrayList<Cart> cartList) {
        this.cartList = cartList;
    }

    public String getUsernameSave() {
        return username;
    }

    public void setUsernameSave(String usernameSave) {
        this.username = usernameSave;
    }

    public String getPasswordSave() {
        return password;
    }

    public void setPasswordSave(String passwordSave) {
        this.password = passwordSave;
    }

    public String getEmailSave() {
        return email;
    }

    public void setEmailSave(String emailSave) {
        this.email = emailSave;
    }

    public String getProfilePicture() {
        return profilepicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilepicture = profilePicture;
    }

    public User(){

        }
        public User(String name, String password, String email){
            this.username = name;
            this.password = password;
            this.email = email;
        }

}
