package com.example.foody;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody.adapter.RecyclerViewAdapter;
import com.example.foody.model.ListStore;
import com.example.foody.model.Store;

import java.util.ArrayList;


public class fragment_search_sub1 extends Fragment {
    RecyclerView rcv;
    ArrayList<Store> arrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search_sub1, container, false);
        addControls(v);
        loadData();
        loadListStore();
        return v;
    }

    public void addControls(View v){
        rcv = v.findViewById(R.id.recyclerview);
    }

    public void loadData(){
        ListStore listStore = new ListStore();
        arrayList = listStore.getListStore();
    }

    public void loadListStore() {
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getActivity(), R.layout.item_store_2, arrayList);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcv.setAdapter(myAdapter);
        rcv.setNestedScrollingEnabled(false);
    }
}