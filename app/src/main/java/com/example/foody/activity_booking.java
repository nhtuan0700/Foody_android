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

import com.example.foody.adapter.PageAdapter;
import com.example.foody.adapter.RecyclerViewAdapter;
import com.example.foody.adapter.Special;
import com.example.foody.adapter.SpecialAdapter;
import com.example.foody.model.ListStore;
import com.example.foody.model.Store;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class activity_booking extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public PageAdapter pageAdapter;
    RecyclerView rcv1,rcv2;
    ArrayList<Special> arrSpecial;
    ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        addControls();
        loadData();
        loadListSpecial();
        createTabFragment();
    }
    public void addControls() {
        rcv1 = findViewById(R.id.list_special);
//        rcv2 = findViewById(R.id.list_post);
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
        arrSpecial.add(new Special(R.drawable.special1,"Giảm từ 20% - 40% Buffet"));
        arrSpecial.add(new Special(R.drawable.special2,"Giảm 20% hóa đơn thức ăn khi đặt chỗ qua NowTable"));
        arrSpecial.add(new Special(R.drawable.special3,"Giảm 11% hóa đơn thức ăn khi đặt chỗ qua NowTable"));
        arrSpecial.add(new Special(R.drawable.special4,"Giảm 10% tổng hóa đơn thức ăn khi đặt chỗ qua NowTable"));
        arrSpecial.add(new Special(R.drawable.special5,"Giảm 10% tổng hóa đơn thức ăn khi đặt chỗ qua TableNow"));
        arrSpecial.add(new Special(R.drawable.special6,"Giảm 10% thức ăn Alarte khi đặt chỗ qua TableNow"));
        arrSpecial.add(new Special(R.drawable.special7,"Giảm 10% hóa đơn thức ăn khi đặt chỗ qua NowTable"));
        arrSpecial.add(new Special(R.drawable.special8,"Giảm 10% hóa đơn thức ăn khi đặt chỗ qua NowTable"));
    }

    public void loadListSpecial() {
//        Toast.makeText(this,Integer.toString(arrSpecial.size()),Toast.LENGTH_SHORT).show();
        SpecialAdapter adapterSlider = new SpecialAdapter(this,arrSpecial);
        rcv1.setLayoutManager(new GridLayoutManager(this,2,RecyclerView.HORIZONTAL,false));
        rcv1.setAdapter(adapterSlider);
        rcv1.setNestedScrollingEnabled(false);
    }

    public void createTabFragment() {
        pageAdapter = new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        pageAdapter.addFragment(new fragment_booking_sub1());
        pageAdapter.addFragment(new fragment_booking_sub1());
        pageAdapter.addFragment(new fragment_booking_sub1());
        pageAdapter.addFragment(new fragment_booking_sub1());
        viewPager.setAdapter(pageAdapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                pageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}