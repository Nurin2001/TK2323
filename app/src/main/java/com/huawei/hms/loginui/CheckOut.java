package com.huawei.hms.loginui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class CheckOut extends AppCompatActivity {

    TextView pricetv, filligstv, flavortv, toppingtv, sizetv, addrtv, qtytv;
    Button confirmbtn;
    RadioGroup radgroupDelivery, radgroupPayment;
    ImageView imageView;

    boolean clicked;
    float price=0;
    int qty=0;
    long maxid=0;
    String filling="", flavor="", topping="", size="", TAG = "CheckOut";

    DatabaseReference dbref;

    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        clicked = false;
        MaterialToolbar toolbar = findViewById(R.id.checkouttoolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        radgroupDelivery = findViewById(R.id.radgrpdelivery);
        radgroupPayment = findViewById(R.id.radgrppay);

        addrtv = findViewById(R.id.addrtv);
        filligstv = findViewById(R.id.fillingstv);
        toppingtv = findViewById(R.id.toptv);
        flavortv = findViewById(R.id.flavortv);
        sizetv = findViewById(R.id.sizetv);
        qtytv = findViewById(R.id.qtytv);
        imageView = findViewById(R.id.imgorder);

        confirmbtn = findViewById(R.id.confirmbtn);

        dbref = FirebaseDatabase.getInstance("https://orderup-trio-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("Users");

        //to get user address
        dbref.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        UserDetail user = snapshot.getValue(UserDetail.class);

                        String name = "", contact = "", addr = "", email = "";
                        if (user != null) {
                            name = user.name;
                            contact = user.contact;
                            addr = user.addr;
                            email = user.email;

                            addrtv.setText(addr);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        dbref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("OrderHistory")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            maxid = (snapshot.getChildrenCount());
                            Log.i("no of children", String.valueOf(maxid));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        Intent intent = getIntent();
        price = intent.getFloatExtra("total_price", 0);
        filling = intent.getStringExtra("fillings");
        flavor = intent.getStringExtra("flavor");
        topping = intent.getStringExtra("topping");
        size = intent.getStringExtra("size");
        qty = intent.getIntExtra("qty", 1);

        pricetv = findViewById(R.id.totalpricetv);

        if (flavor.equals("Chocolate"))
            imageView.setImageResource(R.drawable.chocolate_80);
        if (flavor.equals("Strawberry"))
            imageView.setImageResource(R.drawable.strawberry_80);
        if (flavor.equals("Butterscotch"))
            imageView.setImageResource(R.drawable.butterscotch);
        filligstv.setText(filling);
        flavortv.setText(flavor);
        toppingtv.setText(topping);
        sizetv.setText(size);
        qtytv.setText(""+qty);
        pricetv.setText("RM" + price*qty + "0");



        radgroupDelivery.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioGroup1(i);
            }
        });

        radgroupPayment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioGroup2(i);
            }
        });

        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radgroupDelivery.getCheckedRadioButtonId() == -1 || radgroupPayment.getCheckedRadioButtonId() == -1)
                {
                    // no radio buttons are checked
                    Toast.makeText(CheckOut.this, "Please make one choice for every group" ,Toast.LENGTH_SHORT).show();

                }
                else {
                    Calendar calendar = Calendar.getInstance();
                    String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                    String shortDate = DateFormat.getDateInstance().format(calendar.getTime());

                    Menu menu = new Menu(filling, flavor, topping, size, shortDate, qty);
                    dbref.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child("OrderHistory").child(String.valueOf(maxid+1)).setValue(menu)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Intent intent = new Intent(CheckOut.this, ThankYou.class);
                                        intent.putExtra("date", currentDate);
                                        Toast.makeText(CheckOut.this, "Order is saved.", Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                        finish();
                                    }
                                    else
                                        Toast.makeText(CheckOut.this, "There was a problem", Toast.LENGTH_SHORT).show();

                                }
                            });
                }

            }
        });
    }

    private void radioGroup1(int radBtnId) {
        switch (radBtnId) {
            case R.id.jntradbtn:
                pricetv.setText("RM" + ((price*qty)+5) + "0");
                break;
            case  R.id.poslajuradbtn:
                pricetv.setText("RM" + ((price*qty)+6) + "0");
                break;
        }
    }
    private void radioGroup2(int radBtnId) {
        switch (radBtnId) {
            case R.id.codradbtn:
                break;
            case  R.id.onlineradbtn:
                break;
        }
    }
    private void alertMessage() {
        AlertDialog.Builder alert = new AlertDialog.Builder(CheckOut.this);
        alert.setTitle("Back to Main Menu").setMessage("Are you sure you want to go back? Your order will be lost.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.create().show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
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
}