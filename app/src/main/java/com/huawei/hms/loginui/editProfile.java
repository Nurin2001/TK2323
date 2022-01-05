package com.huawei.hms.loginui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class editProfile extends AppCompatActivity {

    EditText nameET, contactET, addrET;
    Button saveBtn;

    FirebaseAuth firebaseAuth;
    DatabaseReference ref;

    String name="", contact="", addr="", email="";

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(editProfile.this);
        alert.setTitle("Back to View Profile").setMessage("Are you sure you want to go back without saving?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onStop();
                        Intent intent = new Intent(editProfile.this, viewProfile.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.create().show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        MaterialToolbar toolbar = findViewById(R.id.editprofiletoolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(editProfile.this, viewProfile.class));
                AlertDialog.Builder alert = new AlertDialog.Builder(editProfile.this);
                alert.setTitle("Back to View Profile").setMessage("Are you sure you want to go back without saving?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                onStop();
                                Intent intent = new Intent(editProfile.this, viewProfile.class);
                                startActivity(intent);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.create().show();
            }
        });



        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        contact = intent.getStringExtra("contact");
        addr = intent.getStringExtra("addr");

        nameET = findViewById(R.id.profilenameet);
        contactET = findViewById(R.id.profilecontactet);
        addrET = findViewById(R.id.profileaddrET);

        saveBtn = findViewById(R.id.savebtn);

        nameET.setText(name);
        contactET.setText(contact);
        addrET.setText(addr);

        firebaseAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance("https://orderup-trio-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users");
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserDetail user = new UserDetail(nameET.getText().toString(), addrET.getText().toString(), contactET.getText().toString());

                ref.child(firebaseAuth.getCurrentUser().getUid()).setValue(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(editProfile.this, "Data is updated.", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(editProfile.this, viewProfile.class));
                                }
                                else
                                    Toast.makeText(editProfile.this, "There was a problem", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}