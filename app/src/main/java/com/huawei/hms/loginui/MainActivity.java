package com.huawei.hms.loginui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton Buttonbtn;
    private FirebaseAuth firebaseAuth;
   // LinearLayoutManager linearLayoutManager;
    ViewPager viewPager;
    TabLayout tabLayout;
    long backPressedTime;
    Toast backtoast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpagermain);

        //Menu and Order history
        tabLayout=findViewById(R.id.tabLayoutmain);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addTab(tabLayout.newTab().setText("Menu"));
        tabLayout.addTab(tabLayout.newTab().setText("Order History"));
        setUpTablayout();
        setUpViewpager(viewPager);

        //get instance of FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        Buttonbtn=findViewById(R.id.orderBtn);
        viewPager=findViewById(R.id.viewpagermain);

        //toolbar for main activity
        MaterialToolbar toolbar = findViewById(R.id.topbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        Buttonbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                setupviewpager(viewPager);
                OrderBottomSheetFragment bottomSheet = new OrderBottomSheetFragment();
                bottomSheet.show(getSupportFragmentManager(), "TAG");

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id) {
                    case  R.id.nav_profile:
                        startActivity(new Intent(MainActivity.this, viewProfile.class));
                        Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        break;
                    case  R.id.nav_about:
                        startActivity(new Intent(MainActivity.this, AboutUs.class));
                        Toast.makeText(MainActivity.this, "About", Toast.LENGTH_SHORT).show();
                        break;
                    case  R.id.nav_contact:
                        Toast.makeText(MainActivity.this, "Contact", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, ContactUs.class));
                        break;
                    case  R.id.nav_logout:

                        AlertDialog.Builder logoutdialog = new AlertDialog.Builder(navigationView.getContext());
                        logoutdialog.setTitle("Logout").setMessage("Are you sure you want to logout?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        FirebaseAuth.getInstance().signOut();
                                        startActivity(new Intent(MainActivity.this, login.class));
                                        finish();
                                    }
                                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        logoutdialog.create().show();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {

        if(backPressedTime+2000 > System.currentTimeMillis()) {
            backtoast.cancel();
            super.onBackPressed();
            return;
        }
        else {
            backtoast = Toast.makeText(MainActivity.this, "Press back again to exit", Toast.LENGTH_SHORT);
            backtoast.show();
        }
        backPressedTime = System.currentTimeMillis();

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null) {
            //ada user dah login

        }
        else {
            startActivity(new Intent(MainActivity.this, login.class));
            finish();
        }

    }
    public void setUpViewpager(ViewPager viewpager) {
        LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), MainActivity.this, 1);
        adapter.addFrag(new MenuFragment(), "Menu");
        //adapter.addFrag(new SignupFragment(), "Signup");

        viewpager.setAdapter(adapter);
    }

    public void setUpTablayout() {

        tabLayout.setupWithViewPager(viewPager);
    }

}