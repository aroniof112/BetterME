package com.example.betterme.plansWorkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.betterme.R;
import com.example.betterme.main.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class WorkoutActivity extends AppCompatActivity {

    FirebaseAuth auth;
    TextView signOut, userNameTv;
    Button buttonBeginner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_activity);

        //firebase
        auth = FirebaseAuth.getInstance();
        signOut = findViewById(R.id.signOutWorkout);
        userNameTv = findViewById(R.id.userWorkout);

        buttonBeginner = findViewById(R.id.buttonBeginner);

        if(auth != null){
            String currentUser = auth.getCurrentUser().getEmail();
            userNameTv.setText(currentUser);
        }

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auth!= null){
                    auth.signOut();
                    startActivity(new Intent(WorkoutActivity.this, MainActivity.class));

                    finish();
                }
            }
        });

        buttonBeginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutActivity.this, DietWorkoutBeginnerActivity.class);
                startActivity(intent);
            }
        });

    }
}