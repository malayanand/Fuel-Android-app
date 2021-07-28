package com.example.fuel_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    public ActionBarDrawerToggle actionBarDrawerToggle;
    public DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button place_order = findViewById(R.id.btn_place_order);

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


        //Place order button action
        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText addr_details = findViewById(R.id.addrline1);
                EditText addr2_details = findViewById(R.id.addrline2);
                TextView fueltanks = findViewById(R.id.tanks);
                Button maps = findViewById(R.id.Maps);
                boolean checked = ((android.widget.CheckBox)v).isChecked();
                CheckBox petrol = findViewById(R.id.petrolflag);
                CheckBox deisel = findViewById(R.id.deiselflag);
                switch(v.getId()) {
                    case R.id.petrolflag:
                        if (checked)
                            petrol.setChecked(true);
                        break;
                    case R.id.deiselflag:
                        if (checked) deisel.setChecked(true);
                        break;
                    default:
                        throw new IllegalStateException("Set Fuel Type");
                }
                String address_details = addr_details.getText().toString();
                address_details += addr2_details.getText().toString();
                Button btnadd = findViewById(R.id.add);
                Button btnminus = findViewById(R.id.minus);
                int tanks = 0;
                if(v.equals(btnadd))
                {
                    tanks++;
                    fueltanks.setText(String.valueOf(tanks));
                }
                else if(v.equals(btnminus))
                {
                    tanks--;
                    fueltanks.setText(String.valueOf(tanks));
                }

                if(address_details.equals(""))
                    Toast.makeText(MainActivity.this, "Enter address details", Toast.LENGTH_SHORT).show();
                else {
                    Intent place_order_screen = new Intent(MainActivity.this, activity_order_screen.class);
                    startActivity(place_order_screen);
                }
//                if(v.equals(maps))
//                {
//                    Intent maps = new Intent(MainActivity.this, )
//                }
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