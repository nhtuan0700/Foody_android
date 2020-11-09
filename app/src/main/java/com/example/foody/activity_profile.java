package com.example.foody;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.foody.adapter.OptionAdapter;
import com.example.foody.model.Option;

import java.util.ArrayList;

public class activity_profile extends Fragment {
    ListView lv1,lv2,lv3;
    OptionAdapter adapter;
    ArrayList<Option> options1,options2,options3;
    LinearLayout nav_login;
    ImageView btn_search;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_profile,container,false);
        lv1 = (ListView) v.findViewById(R.id.list1);
        lv2 = (ListView) v.findViewById(R.id.list2);
        lv3 = (ListView) v.findViewById(R.id.list3);
        btn_search = v.findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),activity_search.class);
                startActivity(intent);
            }
        });
        nav_login = v.findViewById(R.id.nav_login);
        nav_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),activity_login.class);
                startActivity(intent);
            }
        });

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
        return v;
    }
}