package com.huawei.hms.loginui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    public List<Menu> MenuList;
    private Context context;


    public MenuAdapter(List<Menu> menuList, Context context) {
        MenuList = menuList;
        this.context = context;
    }

    @NonNull
    @Override
    public MenuAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Menu_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.flavor_row,null);

        MenuViewHolder menuVH = new MenuViewHolder(Menu_row);

        return menuVH;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MenuViewHolder holder, int position) {
       holder.flavortv.setText(MenuList.get(position).getFlavour());
       holder.imgflavor.setImageResource(MenuList.get(position).getImageflavor());

    }

    @Override
    public int getItemCount() {
        return MenuList.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        public TextView flavortv;
        public ImageView imgflavor;

        public MenuViewHolder(@NonNull View itemView) {

            super(itemView);
            flavortv=itemView.findViewById(R.id.flavortv);
            imgflavor=itemView.findViewById(R.id.img_item_main);

        }
    }
}
