package com.example.foody.Notification;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foody.R;
import com.example.foody.adapter.NotificationAdapter;
import com.example.foody.model.Notification;
import com.example.foody.model.Store;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class fragment_notifi_sub extends Fragment {
    DatabaseReference database;
    RecyclerView rcv;
    ArrayList<Notification> arrNotifi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notifi_sub, container, false);
        database = FirebaseDatabase.getInstance().getReference();
        addControls(v);
//        loadData();
        getDataNews();
        return v;
    }

    public void addControls(View v){
        rcv = v.findViewById(R.id.recyclerview);
    }

    public void getDataNews() {
        arrNotifi = new ArrayList<>();
        Query query = database.child("News").orderByChild("id");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Notification notification = snapshot.getValue(Notification.class);
                arrNotifi.add(notification);
                loadListNews();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    
    public void loadListNews(){
        NotificationAdapter myAdapter = new NotificationAdapter(getActivity(),R.layout.item_store_1,arrNotifi);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcv.setAdapter(myAdapter);
        rcv.setNestedScrollingEnabled(false);
    }
}