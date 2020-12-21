package com.example.foody.Profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.foody.R;
import com.example.foody.Search.activity_search;
import com.example.foody.Login.activity_login;
import com.example.foody.adapter.OptionAdapter;
import com.example.foody.model.Option;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class fragment_profile extends Fragment {
    RecyclerView rcv;
    ArrayList<Option> arrOption;
    ImageView btn_search,imageView;
    TextView tvDetail,tvUserName;
    FirebaseUser user;
    FirebaseAuth mAuth;
    LinearLayout viewLogin,viewNotLogin;
    OptionAdapter myAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile,container,false);
        mAuth = FirebaseAuth.getInstance();
        addControls(v);
        loadOptionProfile();
        checkLogin();
        return v;
    }

    public void checkLogin() {
        user = mAuth.getCurrentUser();
        if(user != null){
            viewLogin.setVisibility(View.VISIBLE);
            viewNotLogin.setVisibility(View.GONE);
            tvUserName.setText(user.getDisplayName());
            arrOption.add(new Option("Đăng xuất",R.drawable.ic_turn_off));
            myAdapter.notifyDataSetChanged();
        }else{
            viewLogin.setVisibility(View.GONE);
            viewNotLogin.setVisibility(View.VISIBLE);
            if (arrOption.size() == 10){
                arrOption.remove(9);
            }
            myAdapter.notifyDataSetChanged();
        }
    }
    public void addControls(View v) {
        viewLogin = v.findViewById(R.id.viewLogin);
        viewNotLogin = v.findViewById(R.id.viewNotLogin);
        tvUserName = v.findViewById(R.id.tv_userName);
        rcv = v.findViewById(R.id.rcv);
        imageView = v.findViewById(R.id.avatar);
        tvDetail = v.findViewById(R.id.tv_detail);
        btn_search = v.findViewById(R.id.btn_search);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_search.class);
                startActivity(intent);
            }
        });

        viewNotLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_login.class);
                startActivity(intent);
            }
        });
    }

    public void loadOptionProfile() {
        arrOption = new ArrayList<Option>();
        arrOption.add(new Option("Thanh toán",R.drawable.ic_coupon));
        arrOption.add(new Option("Lịch sử",R.drawable.ic_history));
        arrOption.add(new Option("Hóa đơn",R.drawable.ic_credit_card));
        arrOption.add(new Option("Tiền Thường",R.drawable.reward));

        arrOption.add(new Option("Ứng dụng cho chủ quán",R.drawable.ic_coupon));
        arrOption.add(new Option("Mời bạn bè",R.drawable.ic_add_user));
        arrOption.add(new Option("Góp ý",R.drawable.ic_mail));

        arrOption.add(new Option("Chính sách quy định",R.drawable.ic_help));
        arrOption.add(new Option("Cài đặt ứng dụng",R.drawable.ic_setting_circle));

        myAdapter = new OptionAdapter(getActivity(), arrOption, fragment_profile.this);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setAdapter(myAdapter);
        rcv.setNestedScrollingEnabled(false);
    }
}