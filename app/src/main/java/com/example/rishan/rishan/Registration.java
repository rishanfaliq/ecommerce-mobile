package com.example.rishan.rishan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rishan.rishan.Models.User;

import java.util.List;

public class Registration extends AppCompatActivity {
    EditText usernameSU;

    EditText passwordSU;

    EditText emailSU;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //getIntent().getStringExtra(MainActivity.username);\
        usernameSU=(EditText)findViewById(R.id.editText2);
        passwordSU=(EditText)findViewById(R.id.editText4);
        emailSU=(EditText)findViewById(R.id.editText5);
        signup=(Button)findViewById(R.id.button2);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((passwordSU.toString()=="") ||(usernameSU.toString()=="") ||(emailSU.toString()=="")) {
                    Toast.makeText(Registration.this, "Enter all required feilds!",
                            Toast.LENGTH_LONG).show();
                }else {

                    sendMessage(usernameSU.getText().toString(), passwordSU.getText().toString(), emailSU.getText().toString());
                    Toast.makeText(Registration.this, "You have been Registered!",
                            Toast.LENGTH_LONG).show();

                }
      }
        });

    }
    public void sendMessage(String username ,String password, String emailAddress) {

        usernameSU=(EditText)findViewById(R.id.editText2);

        passwordSU=(EditText)findViewById(R.id.editText4);

        emailSU=(EditText)findViewById(R.id.editText5);

        String user=usernameSU.getText().toString();  // saving the given username to a string
        String email=emailSU.getText().toString();
        String pass=passwordSU.getText().toString();  // saving the given password to a string
        List<User> asd=User.listAll(User.class);
        List<User> usernameList = User.find(User.class, " username = ? ",username );
        List<User> emailList = User.find(User.class, " email= ? ",email);

    if(usernameList.size()== 0 && emailList.size()==0)        //Validation
    {
        User userData = new User(user, pass, email); //Creating a new object with recieved data
        userData.save();                            // Saving the object
        Intent intent=new Intent(Registration.this, MainActivity.class);
        startActivity(intent);                      //Redirecting to Login Screen
    }
    else
    {
        Toast.makeText(this, "Username/Email already being used.", Toast.LENGTH_LONG).show();
    }



        }
    }



