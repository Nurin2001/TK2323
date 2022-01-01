package com.huawei.hms.loginui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupFragment extends Fragment {

    EditText emailET, passET, nameET, addrET, phoneET;
    Button signupbtn;
    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;

    float v = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_fragment, container, false);

        emailET = root.findViewById(R.id.emailEt);
        passET = root.findViewById(R.id.passEt);
        nameET = root.findViewById(R.id.nameET);
        addrET = root.findViewById(R.id.addrET);
        phoneET = root.findViewById(R.id.phoneET);
        signupbtn = root.findViewById(R.id.signupBtn);

        progressBar = root.findViewById(R.id.progressBar);

        //get current instance of db from firebase to perform operation on db
        firebaseAuth = FirebaseAuth.getInstance();

        //if someone already logged in
        if(firebaseAuth.getCurrentUser()!=null) {
            startActivity(new Intent(getActivity(), MainActivity.class));
            onDestroyView();
        }

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString().trim();
                String pass = passET.getText().toString().trim();
                String name = nameET.getText().toString().trim();
                String phone = phoneET.getText().toString().trim();
                String addr = addrET.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    emailET.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(pass)) {
                    passET.setError("Password is required");
                    return;
                }
                if(TextUtils.isEmpty(name)) {
                    nameET.setError("Name is required");
                    return;
                }
                if(TextUtils.isEmpty(phone)) {
                    phoneET.setError("Phone is required");
                    return;
                }
                if(TextUtils.isEmpty(addr)) {
                    addrET.setError("Address is required");
                    return;
                }
                if(pass.length() < 6){
                    passET.setError("Password must be longer than 6 characters");
                }

                progressBar.setVisibility(View.VISIBLE);

                //REGISTER USER
                firebaseAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getActivity(), "User is registered.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), MainActivity.class));
                        }
                        else {
                            Toast.makeText(getContext(), "Fail to register." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        });

//        emailET.setTranslationX(800);
//        passET.setTranslationX(800);
//        nameET.setTranslationX(800);
//        addrET.setTranslationX(800);
//        phoneET.setTranslationX(800);
//        signupbtn.setTranslationX(800);
//
//        emailET.setAlpha(v);
//        passET.setAlpha(v);
//        nameET.setAlpha(v);
//        addrET.setAlpha(v);
//        phoneET.setAlpha(v);
//        signupbtn.setAlpha(v);
//
//        emailET.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(100).start();
//        passET.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
//        nameET.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();
//        addrET.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(1200).start();
//        phoneET.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(1600).start();
//        signupbtn.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(1600).start();

        return root;
    }
}
