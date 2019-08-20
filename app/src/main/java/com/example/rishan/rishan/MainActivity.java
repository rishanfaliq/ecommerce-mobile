package com.example.rishan.rishan;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rishan.rishan.Models.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
EditText usernameTaken;
EditText passwordTaken;
Button button;
Button signup;
String uid;
boolean x=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);
           setTitle("Login");
    button=(Button)findViewById(R.id.button);
    usernameTaken=(EditText)findViewById(R.id.editText);
    passwordTaken=(EditText)findViewById(R.id.editText3);
    signup=(Button)findViewById(R.id.button3);

button.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {
sendMessage(usernameTaken.getText().toString(), passwordTaken.getText().toString());
    }
});

signup.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {
        signup();
    }
});

    } public void sendMessage(String username ,String password) {

        usernameTaken = (EditText) findViewById(R.id.editText);
        String user = usernameTaken.getText().toString();  // saving the given username to a string
        passwordTaken = (EditText) findViewById(R.id.editText3);
        String pass = passwordTaken.getText().toString();  // saving the given password to a string
        List<User> userList = User.listAll(User.class);

        for (User u : userList) {
            if (u.getUsernameSave().equals(user) && u.getPasswordSave().equals(pass)) {
                uid=u.getId().toString();
                SharedPreferences sharedPreferences=getSharedPreferences("loginDetails",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("uid",uid);
                editor.apply();

                Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                startActivity(intent);
                x=true;
            }
            }
        if(!x) {
            Toast.makeText(MainActivity.this, "Username or Password Incorrect!",
                    Toast.LENGTH_LONG).show();
                }
        }

    public void signup() {

        Intent intent=new Intent(MainActivity.this, Registration.class);
        startActivity(intent);

    }
}
