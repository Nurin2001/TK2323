package com.huawei.hms.loginui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class OrderHistoryFragment extends Fragment {

    DatabaseReference reference;

    TextView tv_fillings,tv_flavour,tv_toppings,tv_size,tv_quantity, tv_orderdate;
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.orderhistory,container,false);
        tv_fillings =  root.findViewById(R.id.tv_fillings);
        tv_flavour = root.findViewById(R.id.tv_flavour);
        tv_toppings = root.findViewById(R.id.tv_toppings);
        tv_size = root.findViewById(R.id.tv_size);
        tv_quantity = root.findViewById(R.id.tv_quantity);
        imageView = root.findViewById(R.id.imageView);
        tv_orderdate = root.findViewById(R.id.tv_orderdate);

        reference = FirebaseDatabase.getInstance("https://orderup-trio-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users");
        reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("OrderHistory")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String fillings = "",flavour = "" ,topping = "" ,size = "", date = "";
                        int quantity =0;
                        Menu menu = snapshot.getValue(Menu.class);

                        if (menu != null){
                            fillings = menu.fillings;
                            flavour = menu.flavour;
                            topping = menu.topping;
                            size = menu.size;
                            quantity = menu.quantity;
                            date = menu.date;

                            if (flavour.equals("Chocolate"))
                                imageView.setImageResource(R.drawable.chocolate_80);
                            if (flavour.equals("Strawberry"))
                                imageView.setImageResource(R.drawable.strawberry_80);
                            if (flavour.equals("Butterscotch"))
                                imageView.setImageResource(R.drawable.butterscotch);

                            tv_fillings.setText("Fillings : " + fillings);
                            tv_flavour.setText("Flavour : " +flavour);
                            tv_toppings.setText("Toppings : " + topping);
                            tv_size.setText("Size : " + size);
                            tv_quantity.setText("Quantity : " + quantity);
                            tv_orderdate.setText("Order Date: " + date);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        return root;

    }

}
