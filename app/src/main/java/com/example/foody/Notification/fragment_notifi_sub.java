package com.example.foody.Notification;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foody.R;
import com.example.foody.adapter.NotificationAdapter;
import com.example.foody.model.Notification;

import java.util.ArrayList;

public class fragment_notifi_sub extends Fragment {
    RecyclerView rcv;
    ArrayList<Notification> arrNotifi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notifi_sub, container, false);
        addControls(v);
        loadData();
        loadListNotification();
        return v;
    }

    public void addControls(View v){
        rcv = v.findViewById(R.id.recyclerview);
    }

    public void loadData(){
        arrNotifi = new ArrayList<>();
        arrNotifi.add(new Notification("[DN] Đà Nẵng ơi, Now đã trở lại!",
                "Sau cơn bão Vamco, tình hình đã dần ổn định, " +
                        "Now chính thức mở lại tất cả dịch vụ tại Đà Nẵng từ 9h - 15/11/2020!",
                R.drawable.notifi,"5h"));
        arrNotifi.add(new Notification("[DN] Quán ngon độc quyền chỉ có ở NowFood",
                "Ăn ngon với TOP quán độc quyền chỉ có ở NowFood. Tặng bạn mã xịn giảm tới 60k\n" +
                        "- Mã CHINOWCO giảm 20k (Cho đơn từ 0đ)\n" +
                        "- Mã CHINOWCO40 giảm 40k (Cho đơn từ 200k)",
                R.drawable.notifi,"10h"));
        arrNotifi.add(new Notification("[DN] [NowTable] Top 10 Quán Ngon ưu đãi 40%",
                "Cuối tuần Deal chảy về tim, nhận ngay ưu đãi các quán ngon Đà thành như Mama Hotpot, C.Tao,...khi đặt chỗ nước qua NowTable nhé",
                R.drawable.notifi_deal,"1d"));
        arrNotifi.add(new Notification("[DN] Siêu sale 50% thứ Sáu",
                "Món ngon nay lại còn có ưu đãi. Nhập mã TGIF50 giảm ngay 50% tối đa 25k cho đơn từ 25k khi thanh toán qua ví AirPay. Số lượng mã có hạn, Now ngay!",
                R.drawable.notifi_sale,"1d"));
        arrNotifi.add(new Notification("[DN] Trà sữa XingFu Cha MUA 1 TẶNG 1",
                "Hậu siêu sale 11.11 vẫn còn ưu đãi MUA 1 TẶNG 1 từ thương hiệu trà sữa XingFu Cha cực đã",
                R.drawable.notifi_11,"2d"));
        arrNotifi.add(new Notification("[DN] Săn mã GIẢM 50% Siêu Thương Hiệu",
                "Mở tiệc với SIÊU DEAL THƯƠNG HIỆU bao la ưu đãi cùng loại mã xịn từ những thương hiệu được yêu thích nhất.",
                R.drawable.notifi,"2d"));
    }

    public void loadListNotification(){
        NotificationAdapter myAdapter = new NotificationAdapter(getActivity(),R.layout.item_store_1,arrNotifi);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcv.setAdapter(myAdapter);
        rcv.setNestedScrollingEnabled(false);
    }
}