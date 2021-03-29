package com.example.betterme.plansWorkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.betterme.R;
import com.example.betterme.main.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class WorkoutActivityBeginner extends AppCompatActivity {

    FirebaseAuth auth;
    TextView signOut, userNameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_beginner);

        //firebase
        auth = FirebaseAuth.getInstance();
        signOut = findViewById(R.id.signOutBeginner);
        userNameTv = findViewById(R.id.userBeginner);

        if(auth != null){
            String currentUser = auth.getCurrentUser().getEmail();
            userNameTv.setText(currentUser);
        }

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auth!= null){
                    auth.signOut();
                    startActivity(new Intent(WorkoutActivityBeginner.this, MainActivity.class));

                    finish();
                }
            }
        });

        //Video1
        VideoView videoView1 = findViewById(R.id.videoView1);
        String videoPath = "android.resource://" +getPackageName() + "/" + R.raw.burpee;
        Uri uri = Uri.parse(videoPath);
        videoView1.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView1.setMediaController(mediaController);
        mediaController.setAnchorView(videoView1);

        //Video2
        VideoView videoView2 = findViewById(R.id.videoView2);
        String videoPath2 = "android.resource://" +getPackageName() + "/" + R.raw.mountainclimbers;
        Uri uri1 = Uri.parse(videoPath2);
        videoView2.setVideoURI(uri1);

        MediaController mediaController1 = new MediaController(this);
        videoView2.setMediaController(mediaController1);
        mediaController1.setAnchorView(videoView2);

        //Video3
        VideoView videoView3 = findViewById(R.id.videoView3);
        String videoPath3 = "android.resource://" +getPackageName() + "/" + R.raw.russiantwist;
        Uri uri2 = Uri.parse(videoPath3);
        videoView3.setVideoURI(uri2);

        MediaController mediaController2 = new MediaController(this);
        videoView3.setMediaController(mediaController2);
        mediaController2.setAnchorView(videoView3);
    }
}