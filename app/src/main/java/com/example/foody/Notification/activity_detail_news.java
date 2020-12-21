package com.example.foody.Notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foody.R;
import com.example.foody.model.Notification;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class activity_detail_news extends AppCompatActivity {
    DatabaseReference database;
    ImageView btnBack, imageView;
    TextView tvTitle, tvDescription, tvTime;
    int id = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_detail_news);
        addControls();
        getData();
    }

    public void addControls() {
        btnBack = findViewById(R.id.btn_back);
        tvTitle = findViewById(R.id.tv_title);
        tvTime = findViewById(R.id.tv_time);
        tvDescription = findViewById(R.id.tv_description);
        imageView = findViewById(R.id.image);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("ID", -1);
        Query query = database.child("News").orderByChild("id").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot category : dataSnapshot.getChildren()) {
                        Notification mNoti = category.getValue(Notification.class);
                        loadDataNews(mNoti);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void loadDataNews(Notification notification) {
        tvTitle.setText(notification.getTitle());
        tvDescription.setText(notification.getDescription());
        tvTime.setText(notification.getTime());
        Picasso.get().load(notification.getImage()).into(imageView);
    }
}