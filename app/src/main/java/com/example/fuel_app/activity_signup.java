package com.example.fuel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuel_app.data.FuelDbHelper;

public class activity_signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //getting TextViews and Buttons
        TextView fullname = findViewById(R.id.fullname);
        TextView email = findViewById(R.id.email);
        TextView username = findViewById(R.id.username);
        TextView password = findViewById(R.id.password);
        TextView go_to_login_activity = findViewById(R.id.login_activity);
        Button signup_btn = findViewById(R.id.btn_register);

        //DB
        FuelDbHelper DB = new FuelDbHelper(this);

        // action when the user clicks the signup button
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = fullname.getText().toString();
                String user_email = email.getText().toString();
                String user_username = username.getText().toString();
                String user_password = password.getText().toString();

                if(user_name.equals("")|| user_email.equals("") || user_username.equals("") || user_password.equals(""))
                    Toast.makeText(activity_signup.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                else {
                    //Check to see if the user already exists
                    Boolean checkuser = DB.checkUserName(user_username);

                    if(!checkuser) {
                        //Insert data into the database
                        Boolean insert_data = DB.insertData(user_name, user_email, user_username, user_password);
                        if(insert_data){
                            Toast.makeText(activity_signup.this, "Signup successful", Toast.LENGTH_SHORT).show();
                            Intent main_activity = new Intent(activity_signup.this, MainActivity.class);
                            startActivity(main_activity);
                        } else {
                            //when data insertion fails
                            Toast.makeText(activity_signup.this, "Signup failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //when the username is not unique and already exists
                        Toast.makeText(activity_signup.this, "Username already exists!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //for switching over to login activity from sign up activity
        go_to_login_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_activity = new Intent(activity_signup.this, activity_login.class);
                startActivity(login_activity);
            }
        });
    }
}