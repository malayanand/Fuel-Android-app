package com.example.fuel_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuel_app.data.FuelDbHelper;

import org.w3c.dom.Text;

public class activity_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //geting all TextView and Buttons
        TextView go_to_signup_activity = findViewById(R.id.signup_activity);
        TextView username = findViewById(R.id.login_username);
        TextView password = findViewById(R.id.login_password);
        Button login_btn = findViewById(R.id.btn_login);

        //DB
        FuelDbHelper DB = new FuelDbHelper(this);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_username = username.getText().toString();
                String user_password = password.getText().toString();

                if(user_username.equals("")|| user_password.equals(""))
                    Toast.makeText(activity_login.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                else {
                    Boolean check_user_password = DB.checkUsernameAndPassword(user_username, user_password);
                    if(check_user_password) {
                        Toast.makeText(activity_login.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent main_activity = new Intent(activity_login.this, MainActivity.class);
                        startActivity(main_activity);
                    }
                    else {
                        Toast.makeText(activity_login.this, "Check username and password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //For switching over to the signup activity
        go_to_signup_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup_intent = new Intent(activity_login.this, activity_signup.class);
                startActivity(signup_intent);
            }
        });

    }
}