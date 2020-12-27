package com.example.foody.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody.Detail.activity_detail_food;
import com.example.foody.R;
import com.example.foody.model.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DeliveryFoodAdapter extends RecyclerView.Adapter<DeliveryFoodAdapter.MyViewHolder> {
    private Context mContext;
    private List<Food> mData;
    private int resource;
    public DeliveryFoodAdapter(Context mContext, List<Food> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_food3,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryFoodAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(mData.get(position).getName());
        setAddressStore(holder, mData.get(position).getIdStore());
        Picasso.get().load(mData.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvAddress,tvRangePrice;
        ImageView imageView;
        public MyViewHolder(final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            imageView = itemView.findViewById(R.id.image);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvRangePrice = itemView.findViewById(R.id.tv_rangePrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, activity_detail_food.class);
                    intent.putExtra("ID",mData.get(getAdapterPosition()).getIdStore());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public void setAddressStore(final MyViewHolder holder, String id) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        Query query = database.child("Store").orderByChild("id").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot store : dataSnapshot.getChildren()) {
                        holder.tvAddress.setText(store.child("address").getValue().toString());
                        holder.tvRangePrice.setText(store.child("rangePrice").getValue().toString());
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
