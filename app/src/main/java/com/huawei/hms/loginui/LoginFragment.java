package com.huawei.hms.loginui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {

    EditText emailET;
    EditText passET;
    TextView forgotpasstv;

    Button loginbtn;

    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    float v = 0;
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_fragment, container, false);

        emailET = root.findViewById(R.id.emailEt);
        passET = root.findViewById(R.id.passEt);
        loginbtn = root.findViewById(R.id.loginBtn);
        forgotpasstv = root.findViewById(R.id.forgotpasstv);

        progressBar = root.findViewById(R.id.progressbar);

        firebaseAuth = FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString().trim();
                String pass = passET.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    emailET.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(pass)) {
                    passET.setError("Email is required");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //AUTHENTICATE USE
                firebaseAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    startActivity(new Intent(getActivity(), MainActivity.class));
                                    onDestroy();
                                }
                                else {
                                    Toast.makeText(getActivity(), "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    forgotpasstv.setVisibility(View.VISIBLE);
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                            }
                        });
                forgotpasstv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), "Test", Toast.LENGTH_SHORT).show();
                        final EditText resetmail = new EditText(view.getContext());
                        final AlertDialog.Builder passwordresetdialog = new AlertDialog.Builder(view.getContext());
                        passwordresetdialog.setTitle("Reset Password");
                        passwordresetdialog.setMessage("Enter Your Email to Receive Reset Link");
                        passwordresetdialog.setView(resetmail);

                        passwordresetdialog.setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //extract email and send reset link
                                        String email = resetmail.getText().toString().trim();
                                        firebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(getActivity(), "Reset link is sent to your email", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(getActivity(), "Error! "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    }
                                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        passwordresetdialog.create().show();
                    }

                });
            }
        });

        emailET.setTranslationX(800);
        passET.setTranslationX(800);
        loginbtn.setTranslationX(800);

        emailET.setAlpha(v);
        passET.setAlpha(v);
        loginbtn.setAlpha(v);

        emailET.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        passET.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        loginbtn.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(500).start();

        return root;
    }
}
