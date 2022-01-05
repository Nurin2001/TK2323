package com.huawei.hms.loginui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends FragmentPagerAdapter {

    private List<Fragment> listFragment = new ArrayList<>();
    private List<String> listTitle = new ArrayList<>();

    private Context context;

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    public OrderAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }


    @Override
    public int getCount() {
        return 1;
    }
    public void addFrag(Fragment f, String title) {
        listFragment.add(f);
        listTitle.add(title);
    }
}
