package com.example.foody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.foody.adapter.PageAdapter;
import com.google.android.material.tabs.TabLayout;

public class activity_search extends AppCompatActivity {
    ImageView btn_back;
    EditText ed_search;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public PageAdapter pageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ed_search = findViewById(R.id.ed_search);

        ed_search.requestFocus();
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
        btn_back = (ImageView) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                InputMethodManager imm = (InputMethodManager) getSystemService(activity_search.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(ed_search.getWindowToken(), 0);
                finish();
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pageAdapter = new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        pageAdapter.addFragment(new tab_nodata());
        pageAdapter.addFragment(new tab_nodata());
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
    }
}