package com.example.foody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foody.adapter.RecyclerViewAdapter;
import com.example.foody.adapter.RecyclerViewDelivery;
import com.example.foody.model.Food;
import com.example.foody.model.ListStore;
import com.example.foody.model.Store;
import com.google.android.material.tabs.TabLayout;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class activity_delivery extends AppCompatActivity {
    RecyclerView rcv;
    private TabLayout tabLayout;
    CarouselView carouselView;
    ArrayList<Food> arrFood;
    ImageView btnBack;

    private int[] mImages = new int[]{
            R.drawable.banner2,R.drawable.banner3,R.drawable.banner1,R.drawable.banner4
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        addControls();
        createSlider();
        loadData();
        loadListStore();
    }

    public void addControls(){
        rcv = findViewById(R.id.recyclerview);
        carouselView = findViewById(R.id.carousel);
        tabLayout = findViewById(R.id.tabLayout);
        btnBack = findViewById(R.id.btn_back);

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

    public void loadData(){
        arrFood = new ArrayList<>();
        arrFood.add(new Food("Trà sữa cake cream",25000,R.drawable.food1,1));
        arrFood.add(new Food("Cơm gà chiên mắm",30000,R.drawable.food4,3));
        arrFood.add(new Food("Cháo xương tái",25000,R.drawable.food5,4));
        arrFood.add(new Food("Choco uji",25000,R.drawable.food2,1));
        Toast.makeText(this,Integer.toString(arrFood.size()),Toast.LENGTH_SHORT).show();
    }

    public void loadListStore(){
        RecyclerViewDelivery myAdapter = new RecyclerViewDelivery(this,R.layout.item_food2,arrFood);
        rcv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        rcv.setAdapter(myAdapter);
        rcv.setNestedScrollingEnabled(false);
    }
}