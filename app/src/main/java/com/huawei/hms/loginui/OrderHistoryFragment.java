package com.huawei.hms.loginui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderHistoryFragment extends Fragment {

    DatabaseReference reference;

    TextView tv_fillings,tv_flavour,tv_toppings,tv_size,tv_quantity, tv_orderdate;
    ImageView imageView;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.recyclerview_order_history, container,false);

        imageView = root.findViewById(R.id.imageView);
        recyclerView = root.findViewById(R.id.recyclerOrderHistory);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        List<Menu> menuList = new ArrayList<>();
        OrderHistoryRecyclerviewAdapter adapter = new OrderHistoryRecyclerviewAdapter(menuList, getActivity());

        recyclerView.setAdapter(adapter);

//        tv_fillings =  root.findViewById(R.id.tv_fillings);
//        tv_flavour = root.findViewById(R.id.tv_flavour);
//        tv_toppings = root.findViewById(R.id.tv_toppings);
//        tv_size = root.findViewById(R.id.tv_size);
//        tv_quantity = root.findViewById(R.id.tv_quantity);
//        imageView = root.findViewById(R.id.imageView);
//        tv_orderdate = root.findViewById(R.id.tv_orderdate);

        reference = FirebaseDatabase.getInstance("https://orderup-trio-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users");
        reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("OrderHistory")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot snap: snapshot.getChildren()) {

                            String flavour = "";

                            Menu menu = snap.getValue(Menu.class);

                            menuList.add(menu);

                        }
                        adapter.notifyDataSetChanged();

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        return root;

    }

}
