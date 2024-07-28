package com.example.abambakery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class cake_detail extends AppCompatActivity {
    ImageButton btnAdd, btnMinus;
    Button cart;
    TextView tvQuantity;
    int quantity;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_detail);
        Intent intent = getIntent();
        TextView tvName = findViewById(R.id.tv_name);
        ImageView imgCake = findViewById(R.id.img_cake);
        TextView tvPrice = findViewById(R.id.tv_price);

        tvName.setText(intent.getStringExtra("cakeName"));
        tvPrice.setText(intent.getStringExtra("cakePrice"));

        int defaultImageResourceId = R.drawable.k2;
        int imageResourceId = intent.getIntExtra("cakeImg", defaultImageResourceId);
        imgCake.setImageResource(imageResourceId);

        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);
        cart = findViewById(R.id.btn_cart);
        tvQuantity = findViewById(R.id.tv_total);
        quantity = 1;

        mAuth = FirebaseAuth.getInstance();
        // Get reference to your Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference("orders").child(mAuth.getCurrentUser().getUid()).child("cakes");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                tvQuantity.setText("" + quantity);
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity <= 0)
                    tvQuantity.setText("" + 0);
                else {
                    quantity--;
                    tvQuantity.setText("" + quantity);
                }
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get data
                String nameCake = tvName.getText().toString();
                String priceCake = tvPrice.getText().toString();

                // Create a data map
                Map<String, Object> cakeData = new HashMap<>();
                cakeData.put("name", nameCake);
                cakeData.put("price", priceCake);
                cakeData.put("quantity", quantity);

                // Add data to Firebase Realtime Database
                databaseReference.push().setValue(cakeData)
                        .addOnSuccessListener(aVoid -> {
                            // Data added successfully
                            Toast.makeText(cake_detail.this, "Item added to cart", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(cake_detail.this, cart_detail.class);
                            startActivity(intent);
                        })
                        .addOnFailureListener(e -> {
                            // Handle errors
                            Toast.makeText(cake_detail.this, "Error adding item to cart", Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }
}
