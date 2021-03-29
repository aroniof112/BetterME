package com.example.betterme.main;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.betterme.R;
import com.example.betterme.TrialFragment;
import com.example.betterme.main.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Navigation Bar
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,new HomeFragment()).commit();

    }

    //Navigation Bar
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
           new BottomNavigationView.OnNavigationItemSelectedListener() {
               @Override
               public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                   Fragment selectedFragment = null;

                   switch (item.getItemId()){
                       case R.id.navigation_home:
                           selectedFragment = new HomeFragment();
                           break;
                       case R.id.navigation_dashboard:
                           selectedFragment = new TrialFragment();
                           break;
                   }
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,selectedFragment).commit();
                return true;
               }
           };
}