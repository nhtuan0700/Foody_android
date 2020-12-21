package com.example.foody.Search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.foody.R;
import com.example.foody.adapter.StoreAdapter;
import com.example.foody.model.Store;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class activity_search extends AppCompatActivity {
    DatabaseReference database;
    RecyclerView rcv;
    ArrayList<Store> arrStore;
    ImageView btn_back;
    EditText ed_search;
    StoreAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        database = FirebaseDatabase.getInstance().getReference();
        addControls();
        getData();
    }

    public void addControls() {
        ed_search = findViewById(R.id.ed_search);
        btn_back = (ImageView) findViewById(R.id.btn_back);
        ed_search.requestFocus();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rcv = findViewById(R.id.recyclerview);

        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchStore(ed_search.getText().toString());
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void getData(){
        arrStore = new ArrayList<>();
        Query query = database.child("Store").orderByChild("id");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Store store = snapshot.getValue(Store.class);
                arrStore.add(store);
                loadListStore(arrStore);
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

    public void loadListStore(ArrayList<Store> arrStore) {
        myAdapter = new StoreAdapter(this, R.layout.item_store_2, arrStore);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(myAdapter);
        rcv.setNestedScrollingEnabled(false);
    }

    public void searchStore(String value) {
        value = deAccent(value);
        ArrayList<Store> arrStore2 = new ArrayList<>();
        for (Store store : arrStore) {
            if (deAccent(store.getName().toLowerCase()).contains(value.toLowerCase())){
                arrStore2.add(store);
            }
        }
        loadListStore(arrStore2);
    }

//    convert vietnames to normal text
    public  String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}