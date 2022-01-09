package com.huawei.hms.loginui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class ThankYou extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        Intent intent = getIntent();
        String orderDate = intent.getStringExtra("date");

        TextView date = findViewById(R.id.tqorderdatetv);
        date.setText(orderDate);

        Button backbtn = findViewById(R.id.itembtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThankYou.this, MainActivity.class).putExtra("date_order", orderDate));
                finish();
            }
        });
    }
}