package com.example.bookfinder.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bookfinder.MainActivity;
import com.example.bookfinder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


    public class Login extends Fragment {
        EditText mEmail, mPassword;
        Button mLoginBtn;
        FirebaseAuth fAuth;


        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_login, container, false);

            mEmail = view.findViewById(R.id.email);
            mPassword = view.findViewById(R.id.password);
            fAuth = FirebaseAuth.getInstance();
            mLoginBtn = view.findViewById(R.id.loginBtn);


            mLoginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String email = mEmail.getText().toString().trim();
                    String password = mPassword.getText().toString().trim();

                    if (TextUtils.isEmpty(email)) {
                        mEmail.setError("Email is Required.");
                        return;
                    }

                    if (TextUtils.isEmpty(password)) {
                        mPassword.setError("Password is Required.");
                        return;
                    }

                    if (password.length() < 6) {
                        mPassword.setError("Password Must be >= 6 Characters");
                        return;
                    }

                    // authenticate the user

                    fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getContext(), MainActivity.class));
                            } else {
                                Toast.makeText(getContext(), "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }

                        }
                    });


                }
            });


            return view;
        }
    }
