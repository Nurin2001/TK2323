package com.huawei.hms.loginui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

public class login extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    String TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Signup"));
        setUpTablayout();
        setUpViewpager(viewPager);

    }

    public void setUpViewpager(ViewPager viewpager) {
        LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), login.this, tabLayout.getTabCount());
        adapter.addFrag(new LoginFragment(), "Login");
        adapter.addFrag(new SignupFragment(), "Signup");

        viewpager.setAdapter(adapter);
    }

    public void setUpTablayout() {

        tabLayout.setupWithViewPager(viewPager);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "oResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }
}