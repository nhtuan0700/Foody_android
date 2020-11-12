package com.example.foody;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foody.adapter.PageAdapter;
import com.google.android.material.tabs.TabLayout;

public class activity_nofi extends Fragment {
    ImageView btn_search;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public PageAdapter pageAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_nofi,container,false);
        btn_search = v.findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),activity_search.class);
                startActivity(intent);
            }
        });
        tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        pageAdapter = new PageAdapter(getChildFragmentManager(),tabLayout.getTabCount());
        pageAdapter.addFragment(new Fragment_requestLogin());
        pageAdapter.addFragment(new Fragment_requestLogin());
        pageAdapter.addFragment(new tab_nodata());
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
        return v;
    }
}