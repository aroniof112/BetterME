package com.example.betterme.connect;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.betterme.PlansFragment;
import com.example.betterme.R;
import com.example.betterme.main.HomeFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ConnectFragment extends Fragment {

    private View view;
    private FirebaseAuth auth;

    private EditText email,password;
    private Button login;

    public ConnectFragment(){}

    private void initializeVariables()
    {
        try
        {
            auth = FirebaseAuth.getInstance();
            email= view.findViewById(R.id.email_login);
            password = view.findViewById(R.id.pass_login);
            login=view.findViewById(R.id.LoginButton);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginUser();
                }
            });

        }catch (Exception e)
        {
            Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loginUser()
    {
        try
        {
            if(!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty())
            {
                if(auth!=null)
                {
                    auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    startActivity(new Intent(getActivity().getApplicationContext(), PlansFragment.class));
                                    getActivity().finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
            else
            {
                Toast.makeText(getContext(), "Please fill the fields first", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_connect,container,false);
        //RegisterButton
        Button btnRegister = (Button)view.findViewById(R.id.RegisterButton);
        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction fragTrial = getFragmentManager().beginTransaction();
                fragTrial.replace(R.id.nav_host_fragment, new Register());
                fragTrial.commit();
            }
        });

        initializeVariables();

        return view;
    }
}
