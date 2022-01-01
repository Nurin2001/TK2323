package com.huawei.hms.loginui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class login extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    float v = 0;

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

        //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


//        tabLayout.setTranslationY(300);
//        tabLayout.setAlpha(v);
//        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();

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

}