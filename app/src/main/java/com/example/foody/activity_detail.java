package com.example.foody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foody.adapter.MenuAdapter;
import com.example.foody.adapter.RecyclerViewAdapter;
import com.example.foody.model.Food;
import com.example.foody.model.ListFood;
import com.example.foody.model.ListStore;
import com.example.foody.model.Store;

import java.util.ArrayList;
import java.util.List;

public class activity_detail extends AppCompatActivity {
    ImageView btn_back,option_menu;
    ImageView imageStore;
    TextView tvStoreName,tvAddress,tvTitle;
    RecyclerView rcv1,rcv2;
    ArrayList<Store> arrStore;
    ArrayList<Food> arrFood;
    private int idStore = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        addControls();
        initUI();
        loadData();
        loadListMenu();
        loadListStore();
    }

    public void addControls() {
        rcv1 = (RecyclerView) findViewById(R.id.list_menu);
        rcv2 = (RecyclerView) findViewById(R.id.listview_detail);
        btn_back = findViewById(R.id.btn_back);
        option_menu = findViewById(R.id.option_menu);
        tvTitle = findViewById(R.id.tv_title);
        tvStoreName = findViewById(R.id.tv_store_name);
        tvAddress = findViewById(R.id.tv_address);
        imageStore = findViewById(R.id.image);

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
    }

    public void initUI() {
        Intent intent = getIntent();
        Store store  = intent.getParcelableExtra("Object");
        tvTitle.setText(store.getName());
        tvStoreName.setText(store.getName());
        tvAddress.setText(store.getAddress());
        imageStore.setImageResource(store.getImage());
        idStore = store.getId();
    }

    public void loadListMenu() {
        MenuAdapter myAdapter1 = new MenuAdapter(activity_detail.this, arrFood);
        rcv1.setLayoutManager(new LinearLayoutManager(activity_detail.this));
        rcv1.setAdapter(myAdapter1);
        rcv1.setNestedScrollingEnabled(false);
    }

    public void loadListStore() {
        ListStore listStore = new ListStore();
        RecyclerViewAdapter myAdapter2 = new RecyclerViewAdapter(activity_detail.this,R.layout.item_store,arrStore);
        rcv2.setLayoutManager(new GridLayoutManager(activity_detail.this,2));
        rcv2.setAdapter(myAdapter2);
        rcv2.setNestedScrollingEnabled(false);
    }

    public void loadData(){
        ListStore listStore = new ListStore();
        arrStore = listStore.getListStore();
        ListFood listFood = new ListFood();
        arrFood = listFood.loadStoreFoods(listFood.getListFood(),idStore);
        Toast.makeText(this,Integer.toString(arrFood.size()),Toast.LENGTH_SHORT).show();
    }
}