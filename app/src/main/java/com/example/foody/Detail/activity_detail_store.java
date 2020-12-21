package com.example.foody.Detail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.foody.R;
import com.example.foody.adapter.MenuFoodAdapter;
import com.example.foody.adapter.PageAdapter;
import com.example.foody.adapter.StoreAdapter;
import com.example.foody.model.Food;
import com.example.foody.model.Store;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class activity_detail_store extends AppCompatActivity {
    DatabaseReference database;
    private ViewPager viewPager;
    public PageAdapter pageAdapter;
    ImageView btn_back, option_menu;
    ImageView imageStore;
    TextView tvTitle, tvStoreName, tvPoint, tvTimeWork, tvAddress, tvRangePrice, tvCategory;
    RecyclerView rcv;
    ArrayList<Store> arrStore;
    ArrayList<Food> arrFood;
    String id = "";
    LinearLayout btnOrder;
    Store mStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_store);
        database = FirebaseDatabase.getInstance().getReference();
        addControls();
        initUI();
    }

    public void addControls() {
        viewPager = findViewById(R.id.viewpager);
        rcv = (RecyclerView) findViewById(R.id.list_store);
        btn_back = findViewById(R.id.btn_back);
        option_menu = findViewById(R.id.option_menu);
        imageStore = findViewById(R.id.image);
        tvTitle = findViewById(R.id.tv_title);
        tvStoreName = findViewById(R.id.tv_store_name);
        tvPoint = findViewById(R.id.tv_point);
        tvTimeWork = findViewById(R.id.tv_timeWork);
        tvAddress = findViewById(R.id.tv_address);
        tvRangePrice = findViewById(R.id.tv_rangePrice);
        tvCategory = findViewById(R.id.tv_category);
        btnOrder = findViewById(R.id.btn_order);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), activity_detail_food.class);
                intent.putExtra("ID",mStore.getId());
                startActivity(intent);
            }
        });
    }

    public void initUI() {
        Intent intent = getIntent();
        id  = intent.getStringExtra("ID");
        Query query = database.child("Store").orderByChild("id").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot store : dataSnapshot.getChildren()) {
                        mStore = store.getValue(Store.class);
                        loadDataStore(mStore);
                        getListMenu(mStore.getId());
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        getListStore();
    }

    public void loadDataStore(Store store) {
        Picasso.get().load(store.getImage()).into(imageStore);
        tvTitle.setText(store.getName());
        tvStoreName.setText(store.getName());
        tvPoint.setText(store.getPointEval() + "");
        tvTimeWork.setText(store.getTimeWork());
        tvAddress.setText(store.getAddress());
        tvRangePrice.setText(store.getRangePrice());
        setCategoryStore(store.getIdCateStore());
    }

    public void setCategoryStore(String id) {
        Query query = database.child("CategoryStore").orderByChild("id").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot category : dataSnapshot.getChildren()) {
                        tvCategory.setText(category.child("name").getValue().toString());
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getListMenu(String idStore) {
        pageAdapter = new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new fragment_list_food(idStore), "MENU");
        viewPager.setAdapter(pageAdapter);
    }

    public void getListStore() {
        arrStore = new ArrayList<>();
        Query query = database.child("Store").orderByChild("id").limitToLast(4);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Store store = snapshot.getValue(Store.class);
                arrStore.add(store);
                loadListStore();
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

    public void loadListStore() {
        int i = 0;
        StoreAdapter myAdapter2 = new StoreAdapter(this,R.layout.item_store_1,arrStore);
        rcv.setLayoutManager(new GridLayoutManager(this,2));
        rcv.setAdapter(myAdapter2);
        rcv.setNestedScrollingEnabled(false);
    }

}