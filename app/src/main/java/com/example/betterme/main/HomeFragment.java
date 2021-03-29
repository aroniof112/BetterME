package com.example.betterme.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.betterme.connect.ConnectFragment;
import com.example.betterme.R;
import com.example.betterme.TrialFragment;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container, false);

        //TrialButton
        Button btnTrial = (Button)view.findViewById(R.id.trial);
        btnTrial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragTrial = getFragmentManager().beginTransaction();
                fragTrial.replace(R.id.nav_host_fragment, new TrialFragment());
                fragTrial.commit();
            }
        });

        //StartButton
        Button btnStart = (Button) view.findViewById(R.id.button1);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View ve) {
                FragmentTransaction fragStart = getFragmentManager().beginTransaction();
                fragStart.replace(R.id.nav_host_fragment, new ConnectFragment());
                fragStart.commit();
            }
        });

        return view;

    }
}
