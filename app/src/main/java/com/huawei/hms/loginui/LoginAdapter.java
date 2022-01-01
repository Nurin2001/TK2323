package com.huawei.hms.loginui;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class LoginAdapter extends FragmentPagerAdapter {

    private final List<Fragment> listFragment = new ArrayList<>();
    private final List<String> listTitleTab = new ArrayList<>();

    private Context context;
    int totalTabs;

    public LoginAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitleTab.get(position);
    }

    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    public void addFrag(Fragment f, String title) {

        listFragment.add(f);
        listTitleTab.add(title);
    }
}
