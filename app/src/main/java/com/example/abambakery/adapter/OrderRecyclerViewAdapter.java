package com.example.abambakery.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abambakery.R;
import com.example.abambakery.cake;
import com.example.abambakery.cake_detail;
import com.example.abambakery.order;

import java.util.ArrayList;
import java.util.List;

public class OrderRecyclerViewAdapter extends RecyclerView.Adapter<OrderRecyclerViewAdapter.OrderViewHolder>{

    public List<order> orderList ;
    private Context context;

    public OrderRecyclerViewAdapter(Context context,List<order> orderList ) {
        this.orderList = orderList;
        this.context = context;
    }
    @NonNull
    @Override
    public OrderRecyclerViewAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cart_order= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_order,null);
        OrderRecyclerViewAdapter.OrderViewHolder orderVH=new OrderRecyclerViewAdapter.OrderViewHolder(cart_order);
        return orderVH;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderRecyclerViewAdapter.OrderViewHolder holder, int position) {
        holder.tvCakeName.setText(orderList.get(position).getName());
        holder.tvCakePrice.setText(orderList.get(position).getPrice());
        holder.tvCakeTotal.setText(String.valueOf(orderList.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
          return orderList.size();

    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCakeName;
        public TextView tvCakePrice;
        public TextView tvCakeTotal;


        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCakeName = itemView.findViewById(R.id.tv_name_order);
            tvCakePrice=itemView.findViewById(R.id.tv_price_order);
            tvCakeTotal=itemView.findViewById(R.id.tv_quantity);

        }


    }
}
