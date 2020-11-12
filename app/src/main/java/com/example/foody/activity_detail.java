package com.example.foody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.foody.adapter.MenuAdapter;
import com.example.foody.adapter.RecyclerViewAdapter;
import com.example.foody.model.Food;
import com.example.foody.model.Store;

import java.util.ArrayList;
import java.util.List;

public class activity_detail extends AppCompatActivity {
    List<Store> lstStore;
    List<Food> lstFood;
    ImageView btn_back,option_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        RecyclerView rcv1 = (RecyclerView) findViewById(R.id.list_menu);
        RecyclerView rcv2 = (RecyclerView) findViewById(R.id.listview_detail);
        btn_back = findViewById(R.id.btn_back);
        option_menu = findViewById(R.id.option_menu);
        option_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lstFood=new ArrayList<>();
        lstFood.add(new Food("Trà đào",20000, R.drawable.food1));
        lstFood.add(new Food("Trà đào",20000, R.drawable.food1));
        lstFood.add(new Food("Trà đào",20000, R.drawable.food1));
        lstFood.add(new Food("Trà đào",20000, R.drawable.food1));
        lstFood.add(new Food("Trà đào",20000, R.drawable.food1));
        MenuAdapter myAdapter1 = new MenuAdapter(activity_detail.this, lstFood);
        rcv1.setLayoutManager(new LinearLayoutManager(activity_detail.this));
        rcv1.setAdapter(myAdapter1);
        rcv1.setNestedScrollingEnabled(false);

        lstStore=new ArrayList<>();
        lstStore.add(new Store("Trà Sữa Bobapop - Nguyễn Văn Linh",R.drawable.store1,"28 Nguyễn Văn Linh, Nam Dương"));
        lstStore.add(new Store("Trà Sữa Bobapop - Nguyễn Văn Linh",R.drawable.store1,"28 Nguyễn Văn Linh, Nam Dương"));
        lstStore.add(new Store("Trà Sữa Bobapop - Nguyễn Văn Linh",R.drawable.store1,"28 Nguyễn Văn Linh, Nam Dương"));
        lstStore.add(new Store("Trà Sữa Bobapop - Nguyễn Văn Linh",R.drawable.store1,"28 Nguyễn Văn Linh, Nam Dương"));
        lstStore.add(new Store("Trà Sữa Bobapop - Nguyễn Văn Linh",R.drawable.store1,"28 Nguyễn Văn Linh, Nam Dương"));
        lstStore.add(new Store("Trà Sữa Bobapop - Nguyễn Văn Linh",R.drawable.store1,"28 Nguyễn Văn Linh, Nam Dương"));
        RecyclerViewAdapter myAdapter2 = new RecyclerViewAdapter(activity_detail.this,lstStore);
        rcv2.setLayoutManager(new GridLayoutManager(activity_detail.this,2));
        rcv2.setAdapter(myAdapter2);
        rcv2.setNestedScrollingEnabled(false);
    }
}