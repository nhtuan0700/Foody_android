package com.example.foody.Detail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foody.R;
import com.example.foody.adapter.CartAdapter;
import com.example.foody.model.Cart;
import com.example.foody.model.Food;
import com.example.foody.model.Store;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class activity_cart extends AppCompatActivity {
    RecyclerView rcv;
    FirebaseUser user;
    FirebaseAuth mAuth;
    DatabaseReference database;
    ArrayList<Cart> arrCart;
    String idStore;
    TextView tvTotal;
    ImageView btnBack;
    int total = 0;
    CartAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        database = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        Intent intent = getIntent();
        idStore = intent.getStringExtra("ID");
        addControls();
        getData();
    }

    public void addControls(){
        rcv = findViewById(R.id.rcv);
        tvTotal = findViewById(R.id.tv_total);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getData() {
        arrCart = new ArrayList<>();
        Query query = database.child("Cart").orderByChild("idUser").equalTo(user.getUid());
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Cart cart = snapshot.getValue(Cart.class);
                if (cart.getFood().getIdStore().equals(idStore)) {
                    arrCart.add(cart);
                    loadFoodCart(cart);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Cart cart = snapshot.getValue(Cart.class);
                total -= cart.getAmmount();
                tvTotal.setText(cart.getFood().getStringPrice(total));
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void loadFoodCart(Cart cart) {
        adapter = new CartAdapter(this, arrCart);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(adapter);
        total += cart.getAmmount();
        tvTotal.setText(cart.getFood().getStringPrice(total));
    }
}