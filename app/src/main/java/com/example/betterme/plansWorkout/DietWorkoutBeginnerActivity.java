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

public class DietWorkoutBeginnerActivity extends AppCompatActivity {

    Button buttonDiet,buttonWorkout;
    FirebaseAuth auth;
    TextView signOut, userNameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_workout);

        //firebase
        auth = FirebaseAuth.getInstance();
        signOut = findViewById(R.id.buttonSignOutDietWorkout);
        userNameTv = findViewById(R.id.userDietWorkout);

        if(auth != null){
            String currentUser = auth.getCurrentUser().getEmail();
            userNameTv.setText(currentUser);
        }

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auth!= null){
                    auth.signOut();
                    startActivity(new Intent(DietWorkoutBeginnerActivity.this, MainActivity.class));

                    finish();
                }
            }
        });

        buttonDiet = findViewById(R.id.buttonDiet);
        buttonWorkout = findViewById(R.id.buttonExercises);

        buttonDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DietWorkoutBeginnerActivity.this, DietActivity.class);
                startActivity(intent);
            }
        });

        buttonWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( DietWorkoutBeginnerActivity.this, WorkoutActivityBeginner.class);
                startActivity(intent);
            }
        });

    }
}