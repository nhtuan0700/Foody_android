package com.example.foody.Booking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.foody.Home.tab_nodata;
import com.example.foody.R;
import com.example.foody.adapter.PageAdapter;
import com.example.foody.adapter.CollectionAdapter;
import com.example.foody.model.CollectionItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class activity_booking extends AppCompatActivity {
    DatabaseReference database;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public PageAdapter pageAdapter;
    RecyclerView rcv1;
    ArrayList<CollectionItem> arrSpecial;
    ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        database = FirebaseDatabase.getInstance().getReference();
        addControls();
        loadData();
        createTabFragment();
    }
    public void addControls() {
        rcv1 = findViewById(R.id.list_special);
        btnBack = findViewById(R.id.btn_back);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void loadData(){
        arrSpecial = new ArrayList<>();
        Query query = database.child("Special").orderByChild("id");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                CollectionItem collectionItem = snapshot.getValue(CollectionItem.class);
                arrSpecial.add(collectionItem);
                loadListSpecial();
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

    public void loadListSpecial() {
//        Toast.makeText(this,Integer.toString(arrSpecial.size()),Toast.LENGTH_SHORT).show();
        CollectionAdapter adapterSlider = new CollectionAdapter(this,R.layout.item_special,arrSpecial);
        rcv1.setLayoutManager(new GridLayoutManager(this,2,RecyclerView.HORIZONTAL,false));
        rcv1.setAdapter(adapterSlider);
        rcv1.setNestedScrollingEnabled(false);
    }

    public void createTabFragment() {
        pageAdapter = new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new fragment_booking_sub1(), "Nổi bật");
        pageAdapter.addFragment(new tab_nodata(), "Đặt nhiều");
        pageAdapter.addFragment(new tab_nodata(), "Mới");
        pageAdapter.addFragment(new tab_nodata(), "Gần tôi");
        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}