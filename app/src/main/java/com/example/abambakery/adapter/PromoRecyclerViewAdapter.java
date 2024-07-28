package com.example.abambakery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abambakery.R;
import com.example.abambakery.promo;

import java.util.List;

public class PromoRecyclerViewAdapter extends RecyclerView.Adapter<PromoRecyclerViewAdapter.PromoViewHolder> {

    public List<promo> promoList;
    private Context context;

    public PromoRecyclerViewAdapter(Context context,List<promo> promoList ) {
        this.promoList = promoList;
        this.context = context;
    }

    @NonNull
    @Override
    public PromoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View promo_row= LayoutInflater.from(parent.getContext()).inflate(R.layout.promo_row,null);
        PromoViewHolder promoVH = new PromoViewHolder(promo_row);
        return promoVH;
    }

    @Override
    public void onBindViewHolder(@NonNull PromoRecyclerViewAdapter.PromoViewHolder holder, int position) {
        holder.imageViewPromoImage.setImageResource(promoList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return promoList.size();
    }

    public class PromoViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewPromoImage;
        public PromoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPromoImage=itemView.findViewById(R.id.img_promo1);
        }
    }



}
