package com.example.foody.Discovery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.foody.R;
import com.example.foody.Search.activity_search;
import com.example.foody.adapter.StoreAdapter;
import com.example.foody.model.Store;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class activity_discovery extends AppCompatActivity {
    DatabaseReference database;
    RecyclerView rcv1,rcv2;
    ArrayList<Store> arrPost, arrStore;
    ImageView btnBack,btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);
        database = FirebaseDatabase.getInstance().getReference();
        addControls();
        getData();
    }

    public void addControls() {
        rcv1 = findViewById(R.id.slider);
        rcv2 = findViewById(R.id.list_post);
        btnBack = findViewById(R.id.btn_back);
        btnSearch = findViewById(R.id.btn_search);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_discovery.this, activity_search.class);
                startActivity(intent);
            }
        });
    }

    public void getData(){
        arrPost = new ArrayList<>();
        Query query = database.child("Store").orderByChild("pointEval").limitToLast(6);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Store store = snapshot.getValue(Store.class);
                arrPost.add(store);
                loadListPost();
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

        arrStore = new ArrayList<>();
        query = database.child("Store").orderByChild("id").limitToLast(6);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Store store = snapshot.getValue(Store.class);
                arrStore.add(store);
                loadSlider();
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

    public void loadSlider() {
        StoreAdapter adapterSlider = new StoreAdapter(this,R.layout.item_slider, arrStore);
        rcv1.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        rcv1.setAdapter(adapterSlider);
        rcv1.setNestedScrollingEnabled(false);
    }

    public void loadListPost() {
        StoreAdapter adapter2 = new StoreAdapter(this,R.layout.item_post, arrPost);
        rcv2.setLayoutManager(new LinearLayoutManager(this));
        rcv2.setAdapter(adapter2);
        rcv1.setNestedScrollingEnabled(false);
    }
}