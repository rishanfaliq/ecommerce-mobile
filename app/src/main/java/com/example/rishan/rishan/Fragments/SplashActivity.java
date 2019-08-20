package com.example.rishan.rishan.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.rishan.rishan.MainActivity;
import com.example.rishan.rishan.Models.ProductTags;
import com.example.rishan.rishan.Models.Products;
import com.example.rishan.rishan.Models.Tags;
import com.example.rishan.rishan.NavigationActivity;
import com.example.rishan.rishan.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

import javax.security.auth.login.LoginException;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=1000;
    String sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash2);

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {

                SharedPreferences sharedPreferences=getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
                sp= sharedPreferences.getString("uid","");

                if (Tags.listAll(Tags.class).size() < 1) {

                    List<Tags> tagsList;
                    Gson gson = new Gson();

                    Type typeOfObjectsList1 = new TypeToken<List<Tags>>() {
                    }.getType();
                    tagsList = gson.fromJson(readTags(getApplicationContext()), typeOfObjectsList1);

                    Tags.saveInTx(tagsList);

                }


                if (Products.listAll(Products.class).size() < 1)
                {
                    List<Products> productList;
                    Gson gson = new Gson();
                    Type typeOfObjectsList = new TypeToken<List<Products>>() {
                    }.getType();
                    productList = gson.fromJson(readJSONFromAsset(getApplicationContext()), typeOfObjectsList);
                    // Reading the products from the json
                    String tag;
                    //initializing a string variable to add my tags into
                    for (Products p : productList)
                    //Iterating through the product table obtianed from the json file
                    {
                        p.save();
                        // saving the first element
                        for (int x : p.getTag())
                        //iterating through the ArrayList of tags which is present in the  products table
                        // can be seen in products class
                        {
                            Long a = Long.parseLong(String.valueOf(x));
                            //x denotes the first tag. E.g:- 1 of [1 , 2, 3]
                            Tags t = Tags.findById(Tags.class, a);
                            // As the Id of the tag is known now, the real tag can be found using findbyid from the Tag class
                            tag = t.toString();
                            //to string method called on tag t. toString() method of Tag class has been overriden to return tag;
                            ProductTags pts = new ProductTags(tag, p);
                            // using 2 parameter constructor of ProductTags table to create a row in the ProductsTags table which containg
                            // both the product and the tag
                            pts.save();
                            //Prouct Tags have been saved
                        }

                    }
                }

                if(sp.length()==0){

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(SplashActivity.this, NavigationActivity.class);
                    startActivity(intent);
                }

            }
        },SPLASH_TIME_OUT);




    }

    public String readJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("product.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public String readTags(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("tags.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }



}
