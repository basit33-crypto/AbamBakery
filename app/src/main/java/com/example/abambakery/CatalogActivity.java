package com.example.abambakery;

import static com.example.abambakery.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.abambakery.adapter.CakeRecyclerViewAdapter;
import com.example.abambakery.adapter.PromoRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity {

    LinearLayoutManager linearLayoutManager;
    LinearLayoutManager linearLayoutManager2;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_catalog);
        //untuk cake
        RecyclerView recyclerView=findViewById(id.cake_rec);
        gridLayoutManager = new GridLayoutManager(CatalogActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        List<cake> allCakeInfo = getAllCakeInfo();
        CakeRecyclerViewAdapter cakeRecyclerViewAdapter = new CakeRecyclerViewAdapter(CatalogActivity.this, allCakeInfo);
        recyclerView.setAdapter(cakeRecyclerViewAdapter);

        ImageButton btnCoklat = findViewById(R.id.btn_coklat);
        ImageButton btnPeanut = findViewById(R.id.btn_peanut);
        ImageButton btnOffer = findViewById(R.id.btn_offer);
        ImageButton btnCart;

        btnCoklat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cakeRecyclerViewAdapter.filterByCategory("coklat");
            }
        });

        btnPeanut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cakeRecyclerViewAdapter.filterByCategory("peanut");
            }
        });

        btnOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cakeRecyclerViewAdapter.filterByCategory("offer");
            }
        });

        btnCart = findViewById(id.btn_cart_catalog); // Assuming you have a Button in your layout

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle button click, for example, navigate to PaymentDetailActivity
                Intent intent = new Intent(CatalogActivity.this, cart_detail.class);
                startActivity(intent);
            }
        });






        //untuk promosi
        RecyclerView rvPromo = findViewById(id.rv_promo);
        linearLayoutManager2 = new LinearLayoutManager(CatalogActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rvPromo.setLayoutManager(linearLayoutManager2);
        List<promo> allPromoInfo = getAllPromoInfo();
        PromoRecyclerViewAdapter promoRecyclerViewAdapter = new PromoRecyclerViewAdapter(CatalogActivity.this, allPromoInfo);
        rvPromo.setAdapter(promoRecyclerViewAdapter);

        ImageButton leftArrow =findViewById(id.leftarr);
        ImageButton rightArrow = findViewById(id.rightarr);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = linearLayoutManager2.findFirstVisibleItemPosition();
                if (currentPosition > 0) {
                    rvPromo.smoothScrollToPosition(currentPosition - 1);
                }
            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = linearLayoutManager2.findFirstVisibleItemPosition();
                if (currentPosition < linearLayoutManager2.getItemCount() - 1) {
                    rvPromo.smoothScrollToPosition(currentPosition + 1);
                }
            }
        });

    }
    private List<cake> getAllCakeInfo(){
        List<cake> allCake=new ArrayList<cake>();
        //Kacang
        allCake.add(new cake("Peanut Almond", "peanut","12",drawable.k1));
        allCake.add(new cake("Mousse Almond","peanut", "10",drawable.k2));
        allCake.add(new cake("Choco Almond", "peanut","30",drawable.k3));
        allCake.add(new cake("Raise Almond", "peanut","67",drawable.k4));
        allCake.add(new cake("Small Belgian", "peanut","82",drawable.k17));
        allCake.add(new cake("SmallBelgian", "peanut","60",drawable.k18));
        allCake.add(new cake("BigCheese", "peanut","40",drawable.k19));
        allCake.add(new cake("Burnt Belgian", "peanut","18",drawable.k20));
        //Promo
        allCake.add(new cake("Tapak Kuda","offer","19", drawable.k5));
        allCake.add(new cake("Tapak Kuda Ustaz", "offer","20",drawable.k6));
        allCake.add(new cake("Bukan Tapak Kuda","offer","32", drawable.k7));
        allCake.add(new cake("Rainbow Queen", "offer","13",drawable.k14));
        allCake.add(new cake("Vannila Cherry","offer","11", drawable.k15));
        allCake.add(new cake("Rainbow Cake", "offer","92",drawable.k16));

        //Coklat
        allCake.add(new cake("Black Forest", "coklat","12",drawable.k8));
        allCake.add(new cake("Choco Royale","coklat","28", drawable.k9));
        allCake.add(new cake("Choco Mousse","coklat", "62",drawable.k10));
        allCake.add(new cake("Choco Paradise", "coklat","42",drawable.k11));
        allCake.add(new cake("Belgian Choco", "coklat","66",drawable.k12));
        allCake.add(new cake("White Choc", "coklat","22",drawable.k13));



        return allCake;
    }

    private List<promo> getAllPromoInfo(){
        List<promo> allPromo=new ArrayList<promo>();
        allPromo.add(new promo(drawable.p1));
        allPromo.add(new promo(drawable.p2));
        allPromo.add(new promo(drawable.p3));
        allPromo.add(new promo(drawable.p4));
        allPromo.add(new promo(drawable.p5));
        allPromo.add(new promo(drawable.p6));
        allPromo.add(new promo(drawable.p7));
        return allPromo;
    }

}