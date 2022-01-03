package com.huawei.hms.loginui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Toolbar toolbar = findViewById(R.id.aboutustoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); //keluar back button kat toolbar
    }
    @Override
    public void onBackPressed() {
        onStop();
        Intent intent = new Intent(AboutUs.this, MainActivity.class);
        startActivity(intent);
    }
}