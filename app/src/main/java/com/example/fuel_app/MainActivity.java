package com.example.fuel_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public ActionBarDrawerToggle actionBarDrawerToggle;
    public DrawerLayout drawerLayout;
    int tank = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button maps = findViewById(R.id.Maps);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Uri gmmIntentUri = Uri.parse("geo:0,0?q=");
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);
                    }
                }, 1000);
            }
        });


        CheckBox petrol = findViewById(R.id.petrolflag);
        CheckBox deisel = findViewById(R.id.deiselflag);
        for (CheckBox checkBox : Arrays.asList(petrol, deisel))
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(petrol.isChecked()) {
                    }
                        if(deisel.isChecked()){

                        };
                }
            });
        TextView fueltanks = findViewById(R.id.tanks);
        Button btnadd = findViewById(R.id.add);
        Button btnminus = findViewById(R.id.minus);

        for (Button tanks : Arrays.asList(btnminus, btnadd))
            tanks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(v.equals(btnadd))
                    {
                        tank++;
                        fueltanks.setText(String.valueOf(tank));
                    }
                    else if(v.equals(btnminus))
                    {
                        if(tank>0)
                            tank--;
                        fueltanks.setText(String.valueOf(tank));
                    }
                }
            });

        Button place_order = findViewById(R.id.btn_place_order);
        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText addr_details = findViewById(R.id.addrline1);
                EditText addr2_details = findViewById(R.id.addrline2);
                String address_details = addr_details.getText().toString();
                address_details += addr2_details.getText().toString();
                if(address_details.equals(""))
                    Toast.makeText(MainActivity.this, "Enter address details", Toast.LENGTH_SHORT).show();
                else {
                    Intent place_order_screen = new Intent(MainActivity.this, activity_order_screen.class);
                    startActivity(place_order_screen);
                }
            }
        });
        // Setting up the navigation drawer
        drawerLayout = findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        NavigationView navigationView;
        navigationView = findViewById(R.id.navView);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Navigation listener for fetching the menu item pressed in the navigation drawer
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.item_account){
                    //Handle "account" event
                } else if (id == R.id.item_myOrders) {
                    //Handle "my orders" event
                } else if (id == R.id.item_help) {
                    //Handle "help" event
                } else if (id == R.id.item_logout) {
                    //Handle "logout" event
                    Intent logout_intent = new Intent(MainActivity.this, activity_login.class);
                    startActivity(logout_intent);
                    finish();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }


//    For checking if the navigation toggle button has been pressed
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}