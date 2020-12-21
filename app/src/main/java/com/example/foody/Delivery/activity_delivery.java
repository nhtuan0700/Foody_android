package com.example.foody.Delivery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.foody.Home.tab_nodata;
import com.example.foody.R;
import com.example.foody.Search.activity_search;
import com.example.foody.adapter.CollectionAdapter;
import com.example.foody.adapter.PageAdapter;
import com.example.foody.model.CollectionItem;
import com.example.foody.model.Food;
import com.example.foody.adapter.RequestLocation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class activity_delivery extends AppCompatActivity {
    DatabaseReference database;
    FusedLocationProviderClient fusedLocationProviderClient;
    RecyclerView rcv1, rcv2;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public PageAdapter pageAdapter;
    CarouselView carouselView;
    ArrayList<Food> arrFood;
    ArrayList<CollectionItem> arrCollects;
    LinearLayout search_bar;
    ImageView btnBack;
    private String address = "202 Lý Tự Trọng, Thanh Binh, Hải Châu, Đà Nẵng";
    private TextView tvLocation;

    private int[] mImages = new int[]{
            R.drawable.banner2, R.drawable.banner3, R.drawable.banner1, R.drawable.banner4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        database  = FirebaseDatabase.getInstance().getReference();
        getLocation();
        addControls();
        createSlider();
        getData();
        createTabFragment();
    }

    public void addControls() {
        search_bar = findViewById(R.id.search_bar);
        rcv1 = findViewById(R.id.rcv_collection);
        rcv2 = findViewById(R.id.recyclerview);
        carouselView = findViewById(R.id.carousel);
        tabLayout = findViewById(R.id.tabLayout);
        btnBack = findViewById(R.id.btn_back);
        tvLocation = findViewById(R.id.tv_location);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);

        search_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), activity_search.class);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void createSlider() {
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });
        carouselView.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
    }

    public void getData() {
        arrCollects = new ArrayList<>();
        Query query = database.child("Collection").orderByChild("id");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                CollectionItem collectionItem = snapshot.getValue(CollectionItem.class);
                arrCollects.add(collectionItem);
                loadListCollection();
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

    public void loadListCollection() {
        CollectionAdapter myAdapter = new CollectionAdapter(this, R.layout.item_collection, arrCollects);
        rcv1.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rcv1.setAdapter(myAdapter);
        rcv1.setNestedScrollingEnabled(false);
    }

    public void createTabFragment() {
        pageAdapter = new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new fragment_food_delivery(), "Gần tôi");
        pageAdapter.addFragment(new tab_nodata(), "Bán chạy");
        pageAdapter.addFragment(new tab_nodata(), "Đánh giá");
        pageAdapter.addFragment(new tab_nodata(), "Chạy nhanh");
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
    public void getLocation() {
        RequestLocation requestLocation = new RequestLocation();
        requestLocation.displayLocationSettingsRequest(activity_delivery.this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(activity_delivery.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity_delivery.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        Geocoder geocoder = new Geocoder(activity_delivery.this, Locale.getDefault());
                        try {
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            address = addresses.get(0).getAddressLine(0);
                            setAddress(address);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else {
            ActivityCompat.requestPermissions(activity_delivery.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }

    public void setAddress(String address) {
        tvLocation.setText(address);
    }
}