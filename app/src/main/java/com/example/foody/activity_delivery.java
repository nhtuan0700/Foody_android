package com.example.foody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
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
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foody.adapter.RecyclerViewAdapter;
import com.example.foody.adapter.RecyclerViewDelivery;
import com.example.foody.model.Food;
import com.example.foody.model.ListStore;
import com.example.foody.model.Store;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class activity_delivery extends AppCompatActivity {
    FusedLocationProviderClient fusedLocationProviderClient;
    RecyclerView rcv;
    private TabLayout tabLayout;
    CarouselView carouselView;
    ArrayList<Food> arrFood;
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
        getLocation();
        addControls();
        createSlider();
        loadData();
        loadListStore();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setAddress(address);
            }},5000);
    }

    public void initUI() {
        setAddress(address);
    }

    public void addControls() {
        rcv = findViewById(R.id.recyclerview);
        carouselView = findViewById(R.id.carousel);
        tabLayout = findViewById(R.id.tabLayout);
        btnBack = findViewById(R.id.btn_back);
        tvLocation = findViewById(R.id.tv_location);
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

    public void loadData() {
        arrFood = new ArrayList<>();
        arrFood.add(new Food("Trà sữa cake cream", 25000, R.drawable.food1, 1));
        arrFood.add(new Food("Cơm gà chiên mắm", 30000, R.drawable.food4, 3));
        arrFood.add(new Food("Cháo xương tái", 25000, R.drawable.food5, 4));
        arrFood.add(new Food("Choco uji", 25000, R.drawable.food2, 1));
    }

    public void loadListStore() {
        RecyclerViewDelivery myAdapter = new RecyclerViewDelivery(this, R.layout.item_food2, arrFood);
        rcv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rcv.setAdapter(myAdapter);
        rcv.setNestedScrollingEnabled(false);
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