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

import java.util.ArrayList;
import java.util.List;

public class CakeRecyclerViewAdapter extends RecyclerView.Adapter<CakeRecyclerViewAdapter.CakeViewHolder>{

    public List<cake> cakeList ;
    private Context context;
    private List<cake> allCakes;
    private List<cake> filteredCakes;
    public CakeRecyclerViewAdapter(Context context, List<cake> cakeList) {
        this.context = context;
        this.cakeList = cakeList;
        this.allCakes = cakeList;
        this.filteredCakes = new ArrayList<>(cakeList);

    }
    public void filterByCategory(String category) {
        filteredCakes.clear();
        for (cake cake : allCakes) {
            if (cake.getCat().equals(category)) {
                filteredCakes.add(cake);
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cake_row= LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_row,null);
        CakeViewHolder cakeVH=new CakeViewHolder(cake_row);
        return cakeVH;
    }

    @Override
    public void onBindViewHolder(@NonNull CakeViewHolder holder, int position) {
        holder.tvCakeName.setText(filteredCakes.get(position).getName());
        holder.imageViewCakeImage.setImageResource(filteredCakes.get(position).getImg());

    }

    @Override
    public int getItemCount() {
      //  return cakeList.size();
        return filteredCakes.size();
    }

    public class CakeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tvCakeName;
        public ImageView imageViewCakeImage;
        public CakeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCakeName=itemView.findViewById(R.id.tv_cake_name);
            imageViewCakeImage=itemView.findViewById(R.id.img_cat);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Cake Name: " + filteredCakes.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();

            Intent intent =new Intent(view.getContext(), cake_detail.class);
            //intent.putExtras
            intent.putExtra("cakeName",filteredCakes.get(getAdapterPosition()).getName());
            intent.putExtra("cakeImg",filteredCakes.get(getAdapterPosition()).getImg());
            intent.putExtra("cakePrice",filteredCakes.get(getAdapterPosition()).getPrice());
            view.getContext().startActivity(intent);

        }
    }


}
