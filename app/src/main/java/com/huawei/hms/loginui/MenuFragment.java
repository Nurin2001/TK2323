package com.huawei.hms.loginui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recyclerviewlayout_flavor, container, false);
        recyclerView = root.findViewById(R.id.recyclerview2);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        List<Menu> allMenuInfor = getallMenuInfor();
        MenuRecyclerViewAdapter Menuadapter = new MenuRecyclerViewAdapter(allMenuInfor,getActivity());

        recyclerView.setAdapter(Menuadapter);
        return root;
    }
    private List<Menu> getallMenuInfor(){
        List<Menu> allMenu = new ArrayList<>();
        allMenu.add(new Menu("Chocolate", R.drawable.chocolate_80));
        allMenu.add(new Menu("Strawberry",R.drawable.strawberry_80));
        allMenu.add(new Menu("Butterscotch", R.drawable.butterscotch));
        return allMenu;

    }
}
