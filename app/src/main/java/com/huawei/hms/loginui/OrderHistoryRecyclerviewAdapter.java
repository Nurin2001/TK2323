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

public class OrderHistoryRecyclerviewAdapter extends RecyclerView.Adapter<OrderHistoryRecyclerviewAdapter.OrderHistoryViewHolder> {

    public List<Menu> MenuList;
    private Context context;

    public OrderHistoryRecyclerviewAdapter(List<Menu> menuList, Context context) {
        MenuList = menuList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View orderhistory_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderhistory, null);

        OrderHistoryViewHolder orderVH = new OrderHistoryViewHolder(orderhistory_row);

        return orderVH;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryViewHolder holder, int position) {

        String flavour = MenuList.get(position).getFlavour();

        holder.orderdate.setText(MenuList.get(position).getDate());
        holder.flavour.setText(flavour);
        holder.fillings.setText(MenuList.get(position).getFillings());
        holder.toppings.setText(MenuList.get(position).getTopping());
        holder.size.setText(MenuList.get(position).getSize());
        holder.quantity.setText("" + MenuList.get(position).getQuantity());

        if (flavour.equals("Chocolate"))
            holder.imageview.setImageResource(R.drawable.chocolate_80);
        if (flavour.equals("Strawberry"))
            holder.imageview.setImageResource(R.drawable.strawberry_80);
        if (flavour.equals("Butterscotch"))
            holder.imageview.setImageResource(R.drawable.butterscotch);


    }

    @Override
    public int getItemCount() {
        return MenuList.size();
    }

    public class OrderHistoryViewHolder extends RecyclerView.ViewHolder {

        TextView orderdate, flavour, fillings, toppings, size, quantity;
        ImageView imageview;

        public OrderHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            orderdate = itemView.findViewById(R.id.tv_orderdate);
            flavour = itemView.findViewById(R.id.tv_flavour);
            fillings = itemView.findViewById(R.id.tv_fillings);
            toppings = itemView.findViewById(R.id.tv_toppings);
            size = itemView.findViewById(R.id.tv_size);
            quantity = itemView.findViewById(R.id.tv_quantity);

            imageview = itemView.findViewById(R.id.imageView);

        }
    }
}
