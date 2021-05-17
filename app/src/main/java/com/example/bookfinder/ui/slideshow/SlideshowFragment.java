package com.example.bookfinder.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bookfinder.MainActivity;
import com.example.bookfinder.R;
import com.example.bookfinder.ui.login.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SlideshowFragment extends Fragment {
        EditText mUsername, mName, mEmail, mPassword;
        Button mRegister;
        TextView mLogin;
        FirebaseAuth fAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slideshow, container, false);

        mUsername =view.findViewById(R.id.username);
        mName =view.findViewById(R.id.name);
        mEmail =view.findViewById(R.id.email);
        mPassword =view.findViewById(R.id.password);
        mRegister=view.findViewById(R.id.register);
        mLogin=view.findViewById(R.id.login_user);


        fAuth=FirebaseAuth.getInstance();



        mRegister.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String Email=mEmail.getText().toString().trim();
                String Password=mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(Email)){
                    mEmail.setError("Email is Required.");
                    return;}

                    if(TextUtils.isEmpty(Password)){
                        mPassword.setError("Password is Required");
                        return;
                    }

                    if(Password.length()<6){
                        mPassword.setError("Password must be >= 6 characters.");
                    }

                    fAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getContext(), "User Created.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getContext(), MainActivity.class));
                            }else{
                                Toast.makeText(getContext(), "Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment Login = new Login();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, Login ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });

        return view;
    }
}