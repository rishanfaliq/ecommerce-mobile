package com.example.rishan.rishan.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rishan.rishan.Models.User;
import com.example.rishan.rishan.R;
import com.orm.SugarRecord;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rishan on 5/11/2018.
 */

public class ManageAccountFragment extends Fragment {
    boolean showingFirst = true;
    String password;
    String username;
    String email;
    String id;
    Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflater = LayoutInflater.from(getContext());
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);

        id=sharedPreferences.getString("uid","");
        final User u=User.findById(User.class, Long.parseLong(id));
        getActivity().setTitle("Profile");

        final View view = inflater.inflate(R.layout.manage_account, container, false);
        final EditText usernameText=view.findViewById(R.id.usernameText);
        final EditText passwordText=view.findViewById(R.id.passwordText);
        final EditText emailText=view.findViewById(R.id.emailText);
        Button profilePicButton=view.findViewById(R.id.profilePicButton);
        ImageView profilePic=view.findViewById(R.id.profilePic);
        final Button editProfile=view.findViewById(R.id.editProfile);
        final Button saveChanges=view.findViewById(R.id.saveChanges);
        usernameText.setHint(u.getUsernameSave());
        passwordText.setHint(u.getPasswordSave());
        emailText.setHint(u.getEmailSave());
        saveChanges.setEnabled(false);


        Picasso.get()
                .load(u.getProfilePicture())
                .placeholder(R.drawable.dress)
                .into(profilePic);

        profilePicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilePicChange ppc=new profilePicChange();
                ppc.show(getFragmentManager(), "ppc");
                Toast.makeText(getActivity(),"Choose you new Profile picture!", Toast.LENGTH_LONG).show();
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showingFirst == true) {
                    usernameText.setEnabled(true);
                    passwordText.setEnabled(true);
                    emailText.setEnabled(true);
                    saveChanges.setEnabled(true);
                    editProfile.setEnabled(false);
                }
            }
            });

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile.setText("EDIT PROFILE");
                username = usernameText.getText().toString();
                password = passwordText.getText().toString();
                email = emailText.getText().toString();
                List<User> usernameList = User.find(User.class, " username = ? ",username );
                List<User> emailList = User.find(User.class, " email= ? ",email);

                if(usernameList.size()== 0 && emailList.size()==0)
                {
                    User userData = SugarRecord.findById(User.class, Long.parseLong(id));
                    userData.setUsernameSave(username);
                    userData.setPasswordSave(password);
                    userData.setEmailSave(email);
                    userData.save();
                    saveChanges.setEnabled(false);
                    usernameText.setEnabled(false);
                    passwordText.setEnabled(false);
                    emailText.setEnabled(false);
                    editProfile.setEnabled(true);
                    Toast.makeText(getActivity(), "Change Successfull!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getActivity(),"Username/Email already in use!",Toast.LENGTH_LONG).show();
                }



            }

        });




return view;


    }
    public void setChange(String username,String password, String email){
        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("userDetails", Context.MODE_PRIVATE);

        String id=sharedPreferences.getString("uid","");

        User userData= SugarRecord.findById(User.class,Long.parseLong(id));
            userData.setUsernameSave(username);
            userData.setPasswordSave(password);
            userData.setEmailSave(email);
            userData.save();

    }


}
