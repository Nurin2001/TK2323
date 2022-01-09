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

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.MenuViewHolder> {

    public List<Menu> MenuList;
    private Context context;

    View.OnClickListener onClickListener;

    public MenuRecyclerViewAdapter(List<Menu> menuList, Context context) {
        MenuList = menuList;
        this.context = context;
    }

    @NonNull
    @Override
    public MenuRecyclerViewAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Menu_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.flavor_row,null);

        MenuViewHolder menuVH = new MenuViewHolder(Menu_row);
        return menuVH;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuRecyclerViewAdapter.MenuViewHolder holder, int position) {
       holder.flavortv.setText(MenuList.get(position).getFlavour());
       holder.imgview.setImageResource(MenuList.get(position).getImageflavor());

    }

    @Override
    public int getItemCount() {
        return MenuList.size();
    }

    public boolean onClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return true;
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder  {

        public TextView flavortv;
        public ImageView imgview;
       // private Object mContext;

        public MenuViewHolder(@NonNull View itemView) {

            super(itemView);
            flavortv=itemView.findViewById(R.id.testtv);
            imgview = itemView.findViewById(R.id.imgview);
            //nextbtn = itemView.findViewById(R.id.nextbtn);

        }
    }
}
