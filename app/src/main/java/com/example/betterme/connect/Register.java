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
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.betterme.R;
import com.example.betterme.TrialFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;


public class Register extends Fragment {

    View objectRegisterFragment;
    private EditText email, password;
    private FirebaseAuth auth;
    private Button register;
    Button backButton;

    public Register(){}

    public void createUser(){
        try{
            if(!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty())
            {
                auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(getContext(),"User created..", Toast.LENGTH_LONG).show();

                        FragmentTransaction fragStart = getFragmentManager().beginTransaction();
                        fragStart.replace(R.id.nav_host_fragment, new ConnectFragment());
                        fragStart.commit();

                        if(auth.getCurrentUser()!=null)
                        {
                            auth.signOut();
                        }
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
            else
            {
                Toast.makeText(getContext(),"Please fill the fields.!",Toast.LENGTH_LONG).show();
            }
        }catch (Exception e)
        {
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    private void checkUserExists()
    {
        try{
            if(auth!=null && !email.getText().toString().isEmpty())
            {

                auth.fetchSignInMethodsForEmail(email.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                boolean checkResult =! task.getResult().getSignInMethods().isEmpty();

                                if(!checkResult)
                                {
                                    createUser();
                                }
                                else
                                {
                                    Toast.makeText(getContext(), "User already been created", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(getContext(), e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
            }
        }catch (Exception e)
        {
            Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void attachTo()
    {
        try {
            email = objectRegisterFragment.findViewById(R.id.ed_email);
            password = objectRegisterFragment.findViewById(R.id.ed_password);

            auth = FirebaseAuth.getInstance();
            register = objectRegisterFragment.findViewById(R.id.registerButton);

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkUserExists();
                }
            });
        }catch (Exception e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        objectRegisterFragment = inflater.inflate(R.layout.fragment_register,container, false);
        attachTo();
        return  objectRegisterFragment;
    }

}
