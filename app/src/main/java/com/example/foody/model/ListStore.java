package com.example.foody.model;

import com.example.foody.R;

import java.util.ArrayList;
import java.util.List;

public class ListStore {
    private ArrayList<Store> listStore = new ArrayList();

    public ListStore() {

    }
    public ArrayList<Store> getListStore(){
        listStore.add(new Store(0,"Đầy N Day - Pizza & Hamburger",R.drawable.store1,"FREESHIP. Có người mới đặt giao tận nơi","03 Hoàng Văn Thụ, Quận Hải Châu"));
        listStore.add(new Store(1,"Trà Sữa Royaltea - Phạm Văn Đồng",R.drawable.store2,"FREESHIP. Có người mới đặt giao tận nơi","168 Phạm Văn Đồng, Quận Sơn Trà, Đà Nẵng"));
        listStore.add(new Store(2,"Cơm Gà Vân - Lê Đình Lý",R.drawable.store3,"FREESHIP. Có người mới đặt giao tận nơi","177 Lê Đình Lý,  Quận Hải Châu, Đà Nẵng"));
        listStore.add(new Store(3,"Cơm Gà Hồng Phát 2",R.drawable.store4,"FREESHIP. Có người mới đặt giao tận nơi","168 Phạm Như Xương,  Quận Liên Chiểu, Đà Nẵng"));
        listStore.add(new Store(4,"Quán Cháo Mai Lộc",R.drawable.store5,"FREESHIP. Có người mới đặt giao tận nơi","168 Huỳnh Ngọc Huệ,  Quận Thanh Khê, Đà Nẵng"));
        listStore.add(new Store(5,"Tokbokki 56 - Hồ Huân Nghiệp",R.drawable.store6,"FREESHIP. Có người mới đặt giao tận nơi","106 Hồ Huân Nghiệp, P. Mỹ An,  Quận Ngũ Hành Sơn, Đà Nẵng"));
        return listStore;
    }
}
