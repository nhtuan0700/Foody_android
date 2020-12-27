package com.example.foody.Detail;

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
import com.example.foody.adapter.MenuFoodAdapter;
import com.example.foody.model.Food;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class fragment_list_food extends Fragment {
    DatabaseReference database;
    ArrayList<Food> arrFood;
    RecyclerView rcv;
    String idStore;
    MenuFoodAdapter myAdapter;
    public fragment_list_food(String idStore) {
        this.idStore = idStore;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_food, container, false);
        database = FirebaseDatabase.getInstance().getReference();
        addControls(v);
        getListMenu(idStore);
        return v;
    }


    public void addControls(View v) {
        rcv = v.findViewById(R.id.list_menu);
    }

    public void getListMenu(String id) {
        arrFood = new ArrayList<>();
        Query query = database.child("Food").orderByChild("idStore").equalTo(id);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Food food = snapshot.getValue(Food.class);
                arrFood.add(food);
                loadListMenu();
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

    public void loadListMenu() {

        if (getContext() instanceof activity_detail_store) {
            myAdapter = new MenuFoodAdapter(getContext(), R.layout.item_food1, arrFood);
        } else{
            myAdapter = new MenuFoodAdapter(getContext(), R.layout.item_food2, arrFood);
        }
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setAdapter(myAdapter);
        rcv.setNestedScrollingEnabled(false);
    }
}