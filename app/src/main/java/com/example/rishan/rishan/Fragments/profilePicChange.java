package com.example.rishan.rishan.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rishan.rishan.Models.Cart;
import com.example.rishan.rishan.Models.Products;
import com.example.rishan.rishan.Models.PurchasedItems;
import com.example.rishan.rishan.Models.User;
import com.example.rishan.rishan.R;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Rishan on 6/6/2018.
 */

public class profilePicChange extends AppCompatDialogFragment {
    String text;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.profilepic_dialog, null);

        final TextView urlPic= view.findViewById(R.id.urlPic);

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        String id=sharedPreferences.getString("uid","");

        final User userData = User.findById(User.class, Long.parseLong(id));



        builder.setView( view)
                .setTitle("Enter details")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        text=urlPic.getText().toString();
                        userData.setProfilePicture(text);
                        userData.save();
                        Fragment fragment= new ManageAccountFragment();
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_container, fragment);
                        ft.commit();

                    }
                });

        return builder.create();

    }

}
