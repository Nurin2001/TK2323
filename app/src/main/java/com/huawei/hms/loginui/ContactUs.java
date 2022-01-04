package com.huawei.hms.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

public class ContactUs extends AppCompatActivity {

    LinearLayout ll_whatsapp, ll_telegram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        MaterialToolbar toolbar = findViewById(R.id.contactustoolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDestroy();
                startActivity(new Intent(ContactUs.this, MainActivity.class));
            }
        });

        ll_whatsapp = findViewById(R.id.ll_whatsapp);
        ll_telegram = findViewById(R.id.ll_telegram);

        ll_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStop();
                openChat("https://wa.link/ov1gfs");
            }
        });
        ll_telegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStop();
                openChat("https://msng.link/o/?Nurinrin_rah=tg");
            }
        });
    }
    private void openChat(String link) {
        onStop();
        Uri webpage = Uri.parse(link);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

        if(webIntent.resolveActivity(getPackageManager())!=null)
            startActivity(webIntent);

        else
            Toast.makeText(ContactUs.this, "No app to support this action", Toast.LENGTH_SHORT).show();
    }
}