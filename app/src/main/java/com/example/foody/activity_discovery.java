package com.example.foody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foody.adapter.RecyclerViewAdapter;
import com.example.foody.model.ListStore;
import com.example.foody.model.Store;

import java.util.ArrayList;
import java.util.List;

public class activity_discovery extends AppCompatActivity {
    RecyclerView rcv1,rcv2;
    ArrayList<Store> arrayList;
    ImageView btnBack,btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);
        addControls();
        loadData();
        loadSlider();
        loadPost();
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
                Intent intent = new Intent(activity_discovery.this,activity_search.class);
                startActivity(intent);
            }
        });
    }

    public void loadData(){
        ListStore listStore = new ListStore();
        arrayList = listStore.getListStore();
    }

    public void loadSlider() {
        ListStore listStore = new ListStore();
        RecyclerViewAdapter adapterSlider = new RecyclerViewAdapter(this,R.layout.item_slider,listStore.getListStore());
        rcv1.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        rcv1.setAdapter(adapterSlider);
        rcv1.setNestedScrollingEnabled(false);
    }

    public void loadPost() {
        RecyclerViewAdapter adapter2 = new RecyclerViewAdapter(this,R.layout.item_post,arrayList);
        rcv2.setLayoutManager(new LinearLayoutManager(this));
        rcv2.setAdapter(adapter2);
        rcv1.setNestedScrollingEnabled(false);
    }
}