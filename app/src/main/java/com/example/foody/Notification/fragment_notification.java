package com.example.foody.Notification;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.foody.Home.tab_nodata;
import com.example.foody.R;
import com.example.foody.Search.activity_search;
import com.example.foody.adapter.PageAdapter;
import com.example.foody.Login.fragment_request;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class fragment_notification extends Fragment {
    ImageView btnSearch;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public PageAdapter pageAdapter;
    FirebaseUser user;
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notification,container,false);
        mAuth = FirebaseAuth.getInstance();
        addControls(v);
        createTabFragment();
        return v;
    }

    public void addControls(View v) {
        btnSearch = v.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_search.class);
                startActivity(intent);
            }
        });
        tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
    }

    public void createTabFragment() {
        pageAdapter = new PageAdapter(getChildFragmentManager());
        if (checkLogin() == true) {
            pageAdapter.addFragment(new tab_nodata(), "Dịch vụ");
            pageAdapter.addFragment(new tab_nodata(), "Của tôi");
        } else {
            pageAdapter.addFragment(new fragment_request(), "Dịch vụ");
            pageAdapter.addFragment(new fragment_request(), "Của tôi");
        }
        pageAdapter.addFragment(new fragment_notifi_sub(), "Tin mới");
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
        tabLayout.postDelayed(
                new Runnable(){
                    @Override
                    public void run() {
                        tabLayout.getTabAt(2).select();
                    }
                }, 1);
    }

    public boolean checkLogin() {
        user = mAuth.getCurrentUser();
        if(user != null){
            return true;
        }
        return false;
    }
}