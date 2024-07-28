package com.example.abambakery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abambakery.adapter.OrderRecyclerViewAdapter;
import com.example.abambakery.order;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class cart_detail extends AppCompatActivity {
    LinearLayoutManager linearLayoutManager;
    Button btnPay;
    private DatabaseReference databaseReference;
    TextView tvTotalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_detail);

        RecyclerView recyclerView = findViewById(R.id.rv_order);
        linearLayoutManager = new LinearLayoutManager(cart_detail.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // Initialize Firebase
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("orders")
                .child(mAuth.getCurrentUser().getUid()).child("cakes");
        tvTotalPrice = findViewById(R.id.tv_total_price);
        // Fetch data from Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<order> allOrderInfo = new ArrayList<>();
                double totalPrice = 0.0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    order order = snapshot.getValue(order.class);
                    allOrderInfo.add(order);
                    // Calculate total price
                    totalPrice += Double.parseDouble(order.getPrice()) * order.getQuantity();
                }
                // Display total price
                tvTotalPrice.setText(""+totalPrice);

                // Set up RecyclerView adapter
                OrderRecyclerViewAdapter orderRecyclerViewAdapter =
                        new OrderRecyclerViewAdapter(cart_detail.this, allOrderInfo);
                recyclerView.setAdapter(orderRecyclerViewAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });

        btnPay = findViewById(R.id.btn_pay); // Assuming you have a Button in your layout

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle button click, for example, navigate to PaymentDetailActivity
                Intent intent = new Intent(cart_detail.this, payment_detail.class);

                if(handlePayment()) {
                    // Clear the cart in Firebase
                    clearCart();
                    startActivity(intent);

                } else {
                    Toast.makeText(cart_detail.this, "Payment failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clearCart() {

        // Clear the user's cart by removing all items under the "orders/{userId}/cakes" node
        databaseReference.removeValue();
    }

    private boolean handlePayment() {
        return true;
}
}