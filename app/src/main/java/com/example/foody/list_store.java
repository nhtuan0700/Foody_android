package com.example.foody;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foody.adapter.RecyclerViewAdapter;
import com.example.foody.model.Store;

import java.util.ArrayList;
import java.util.List;

public class list_store extends Fragment {

    List<Store> lstStore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.list_store, container, false);

        lstStore = new ArrayList<>();
        lstStore.add(new Store("MixFood",R.drawable.image1));
        lstStore.add(new Store("MixFood",R.drawable.image1));
        lstStore.add(new Store("MixFood",R.drawable.image1));
        lstStore.add(new Store("MixFood",R.drawable.image1));
        lstStore.add(new Store("MixFood",R.drawable.image1));
        lstStore.add(new Store("MixFood",R.drawable.image1));

        RecyclerView rcv = (RecyclerView) v.findViewById(R.id.recyclerview);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getActivity(),lstStore);
        rcv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rcv.setAdapter(myAdapter);
        rcv.setNestedScrollingEnabled(false);
        return v;
    }
}