package com.example.foody.Delivery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foody.R;
import com.example.foody.adapter.DeliveryFoodAdapter;
import com.example.foody.adapter.MenuFoodAdapter;
import com.example.foody.adapter.StoreAdapter;
import com.example.foody.model.Food;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class fragment_food_delivery extends Fragment {
    DatabaseReference database;
    RecyclerView rcv;
    ArrayList<Food> arrFood;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_food_delivery, container, false);
        database = FirebaseDatabase.getInstance().getReference();
        addControls(v);
        loadData();
        return v;
    }
    public void addControls(View v) {
        rcv = v.findViewById(R.id.recyclerview);
    }

    public void loadData(){
        arrFood = new ArrayList<>();
        Query query = database.child("Food").orderByChild("random").equalTo(1);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Food food = snapshot.getValue(Food.class);
                arrFood.add(food);
                loadListFood();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void loadListFood() {
        DeliveryFoodAdapter myAdapter = new DeliveryFoodAdapter(getActivity(), arrFood);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcv.setAdapter(myAdapter);
        rcv.setNestedScrollingEnabled(false);
    }
}