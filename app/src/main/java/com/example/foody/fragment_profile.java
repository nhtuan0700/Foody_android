package com.example.foody;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.foody.adapter.OptionAdapter;
import com.example.foody.model.Option;

import java.util.ArrayList;

public class fragment_profile extends Fragment {
    ListView lv1,lv2,lv3;
    OptionAdapter adapter;
    ArrayList<Option> options1,options2,options3;
    LinearLayout nav_login;
    ImageView btn_search,imageView;
    TextView tvDetail,tvUserName;
    private boolean isLogin = false;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile,container,false);
        addControl(v);
        addOptionProfile();
        initUI();
        addEvent();
        return v;
    }

    public void initUI() {
        if (isLogin == true) {
            tvDetail.setVisibility(View.VISIBLE);
            options3.add(new Option("Đăng xuất",R.drawable.ic_turn_off));
            adapter.notifyDataSetChanged();
        }
    }

    public void addControl(View v) {
        lv1 = v.findViewById(R.id.list1);
        lv2 = v.findViewById(R.id.list2);
        lv3 = v.findViewById(R.id.list3);
        imageView = v.findViewById(R.id.avatar);
        tvUserName = v.findViewById(R.id.tv_user_name);
        tvDetail = v.findViewById(R.id.tv_detail);
        btn_search = v.findViewById(R.id.btn_search);
        nav_login = v.findViewById(R.id.nav_login);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),activity_search.class);
                startActivity(intent);
            }
        });

        nav_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),activity_login.class);
                startActivity(intent);
            }
        });
    }

    public void addOptionProfile() {
        options1 = new ArrayList<Option>();
        options1.add(new Option("Thanh toán",R.drawable.ic_coupon));
        options1.add(new Option("Lịch sử",R.drawable.ic_history));
        options1.add(new Option("Hóa đơn",R.drawable.ic_credit_card));
        options1.add(new Option("Tiền Thường",R.drawable.reward));
        options1.add(new Option("Ứng dụng cho chủ quán",R.drawable.ic_coupon));
        adapter = new OptionAdapter(getActivity(),R.layout.option_item,options1);
        lv1.setAdapter(adapter);

        options2 = new ArrayList<Option>();
        options2.add(new Option("Mời bạn bè",R.drawable.ic_add_user));
        options2.add(new Option("Góp ý",R.drawable.ic_mail));
        adapter = new OptionAdapter(getActivity(),R.layout.option_item,options2);
        lv2.setAdapter(adapter);

        options3 = new ArrayList<Option>();
        options3.add(new Option("Chính sách quy định",R.drawable.ic_help));
        options3.add(new Option("Cài đặt ứng dụng",R.drawable.ic_setting_circle));
        adapter = new OptionAdapter(getActivity(),R.layout.option_item,options3);
        lv3.setAdapter(adapter);
    }

    public void addEvent() {
        if (isLogin == false) {
            lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    openLogin();
                }
            });
            lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    openLogin();
                }
            });
            lv3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    openLogin();
                }
            });
        } else {
            lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                }
            });
            lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                }
            });
            lv3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                    }
                }
            });
        }
    }

    public void openLogin(){
        Intent intent = new Intent(getActivity(),activity_login.class);
        startActivity(intent);
    }
}