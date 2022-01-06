package com.huawei.hms.loginui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class viewProfile extends AppCompatActivity {

    Button editbtn;
    TextView nametv, contacttv, addrtv;

    DatabaseReference ref;

    String name="", contact="", addr="", email="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        createThis();
    }

    private void createThis() {
        MaterialToolbar toolbar = findViewById(R.id.aboutustoolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStop();
                startActivity(new Intent(viewProfile.this, MainActivity.class));
                finish();
            }
        });

        editbtn = findViewById(R.id.editBtn);
        nametv = findViewById(R.id.profilenameET);
        contacttv = findViewById(R.id.profilecontactET);
        addrtv = findViewById(R.id.profileaddrTV);

        ref = FirebaseDatabase.getInstance("https://orderup-trio-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("Users");

        ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserDetail user = snapshot.getValue(UserDetail.class);

                        if(user != null) {
                            name = user.name;
                            contact = user.contact;
                            addr = user.addr;
                            email = user.email;

                            nametv.setText(name);
                            contacttv.setText(contact);
                            addrtv.setText(addr);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(viewProfile.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPause();
                Intent intent = new Intent(viewProfile.this, editProfile.class);
                intent.putExtra("name", name);
                intent.putExtra("contact", contact);
                intent.putExtra("addr", addr);
                startActivity(intent);

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        createThis();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        onStop();
        startActivity(new Intent(this, MainActivity.class));
    }
}