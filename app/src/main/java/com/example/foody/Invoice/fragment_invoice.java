package com.example.foody.Invoice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foody.Home.tab_nodata;
import com.example.foody.R;
import com.example.foody.adapter.PageAdapter;
import com.example.foody.Login.fragment_request;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class fragment_invoice extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public PageAdapter pageAdapter;
    FirebaseUser user;
    FirebaseAuth mAuth;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_invoice,container,false);
        mAuth = FirebaseAuth.getInstance();
        addControls(v);
        createTabFragment();
        return v;
    }

    public void addControls(View v){
        tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
    }

    public void createTabFragment(){
        pageAdapter = new PageAdapter(getChildFragmentManager());
        if (checkLogin() == true) {
            pageAdapter.addFragment(new tab_nodata(), "Đang đến");
            pageAdapter.addFragment(new tab_nodata(), "Lịch sử");
            pageAdapter.addFragment(new tab_nodata(), "Đơn nháp");
        } else {
            pageAdapter.addFragment(new fragment_request(), "Đang đến");
            pageAdapter.addFragment(new fragment_request(), "Lịch sử");
            pageAdapter.addFragment(new fragment_request(), "Đơn nháp");
        }

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

    public boolean checkLogin() {
        user = mAuth.getCurrentUser();
        if(user != null){
            return true;
        }
        return false;
    }
}