package com.example.foody;

import android.content.Intent;
import android.os.Bundle;

import com.example.foody.adapter.PageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


public class activity_home extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public PageAdapter pageAdapter;

    private int[] mImages = new int[]{
            R.drawable.banner1,R.drawable.banner2,R.drawable.banner3
    };
    private String[] mImageTitle = new String[]{
            "banner1","banner2","banner3"
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_home,container,false);
        LinearLayout search_bar = (LinearLayout) v.findViewById(R.id.search_bar);
        search_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),activity_search.class);
                startActivity(intent);
            }
        });
        CarouselView carouselView = v.findViewById(R.id.carousel);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });
        carouselView.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        pageAdapter = new PageAdapter(getChildFragmentManager(),tabLayout.getTabCount());
        pageAdapter.addFragment(new list_store());
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