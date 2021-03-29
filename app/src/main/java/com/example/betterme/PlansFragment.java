package com.example.betterme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.betterme.main.MainActivity;
import com.example.betterme.message.MessageSender;
import com.example.betterme.plansWorkout.WorkoutActivity;
import com.google.firebase.auth.FirebaseAuth;

public class PlansFragment extends AppCompatActivity {

    FirebaseAuth auth;
    TextView signOut,userNameTv;
    EditText messageWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans_fragment);

        auth = FirebaseAuth.getInstance();
        signOut = findViewById(R.id.signOut);

        userNameTv = findViewById(R.id.userNameTv);
        if(auth!=null)
        {
            String currentUser = auth.getCurrentUser().getEmail();
            userNameTv.setText(currentUser);
        }

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auth!=null)
                {
                    auth.signOut();
                    startActivity(new Intent(PlansFragment.this, MainActivity.class));

                    finish();
                }
            }
        });

        //MessageServer
        messageWeight = (EditText)findViewById(R.id.weightNumber);
    }

    //MessageServer
    public void sendMessage(View v)
    {
        MessageSender messageSender = new MessageSender();
        messageSender.execute(messageWeight.getText().toString());
        //setContentView(R.layout.fragment_trial);
        Intent intent = new Intent(PlansFragment.this , WorkoutActivity.class);
        startActivity(intent);
    }
}