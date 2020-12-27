package com.example.foody.Detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.foody.Home.tab_nodata;
import com.example.foody.R;
import com.example.foody.adapter.PageAdapter;
import com.example.foody.model.Store;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class activity_detail_food extends AppCompatActivity {
    FirebaseUser user;
    FirebaseAuth mAuth;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public PageAdapter pageAdapter;
    DatabaseReference database;
    String idStore = null;
    TextView tvTitle, tvName, tvAddress;
    ImageView imageView;
    ImageView btnBack;
    LinearLayout btnCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);
        database = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        addControls();
        initUI();
        createTabFragment();
    }
    public void addControls() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);
        tvTitle = findViewById(R.id.tv_title);
        tvName = findViewById(R.id.tv_store_name);
        tvAddress = findViewById(R.id.tv_address);
        imageView =  findViewById(R.id.image);
        btnCart = findViewById(R.id.btn_cart);

        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), activity_cart.class);
                intent.putExtra("ID",idStore);
                startActivity(intent);
            }
        });

        if (user != null) {
            btnCart.setVisibility(View.VISIBLE);
        } else {
            btnCart.setVisibility(View.GONE);
        }
    }

    public void initUI() {
        Intent intent = getIntent();
        idStore  = intent.getStringExtra("ID");
        Query query = database.child("Store").orderByChild("id").equalTo(idStore);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot store : dataSnapshot.getChildren()) {
                        Store mStore = store.getValue(Store.class);
                        loadDataStore(mStore);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

            public void loadDataStore(Store store) {
                Picasso.get().load(store.getImage()).into(imageView);
                tvTitle.setText(store.getName());
                tvName.setText(store.getName());
                tvAddress.setText(store.getAddress());
            }
        });
    }

    public void createTabFragment() {
        pageAdapter = new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new fragment_list_food(idStore), "Giao hàng");
        pageAdapter.addFragment(new tab_nodata(), "Thông tin");
        pageAdapter.addFragment(new tab_nodata(), "Đặt nhiều");
        pageAdapter.addFragment(new tab_nodata(), "Hình ảnh");
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