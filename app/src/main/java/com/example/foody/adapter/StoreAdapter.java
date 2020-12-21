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

import com.example.foody.R;
import com.example.foody.Detail.activity_detail_store;
import com.example.foody.model.Store;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {
    private Context mContext;
    private List<Store> mData;
    private int resource;
    public StoreAdapter(Context mContext, int resource, List<Store> mData) {
        this.resource = resource;
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(resource, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        switch (resource) {
            case R.layout.item_store_1:
                holder.tvName.setText(mData.get(position).getName());
                holder.tvAddress.setText(mData.get(position).getAddress());
                Picasso.get().load(mData.get(position).getImage()).into(holder.imageView);
                break;
            case R.layout.item_store_2:
                holder.tvName.setText(mData.get(position).getName());
                holder.tvAddress.setText(mData.get(position).getAddress());
                Picasso.get().load(mData.get(position).getImage()).into(holder.imageView);
                setCategoryStore(holder, mData.get(position).getIdCateStore());
                holder.tvPoint.setText(mData.get(position).getPointEval()+"");
                break;
            case R.layout.item_store_3:
                holder.tvName.setText(mData.get(position).getName());
                holder.tvAddress.setText(mData.get(position).getAddress());
                Picasso.get().load(mData.get(position).getImage()).into(holder.imageView);
                setCategoryStore(holder, mData.get(position).getIdCateStore());
                break;
            case R.layout.item_slider:
                holder.tvName.setText(mData.get(position).getName());
                holder.tvAddress.setText(mData.get(position).getAddress());
                Picasso.get().load(mData.get(position).getImage()).placeholder(R.drawable.store1).into(holder.imageView);
                break;
            case R.layout.item_post:
                holder.tvName.setText(mData.get(position).getName());
                holder.tvAddress.setText(mData.get(position).getAddress());
                Picasso.get().load(mData.get(position).getImage()).into(holder.imageView);
                holder.tvPoint.setText(mData.get(position).getPointEval()+"");
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvAddress,tvPoint,tvCategory;
        ImageView imageView;
        public MyViewHolder(final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_store_name);
            imageView = itemView.findViewById(R.id.image);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvPoint = itemView.findViewById(R.id.tv_point);
            tvCategory = itemView.findViewById(R.id.tv_category);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, activity_detail_store.class);
                    intent.putExtra("ID",mData.get(getAdapterPosition()).getId());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public void setCategoryStore(final MyViewHolder holder, String id) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        Query query = database.child("CategoryStore").orderByChild("id").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot category : dataSnapshot.getChildren()) {
                        holder.tvCategory.setText(category.child("name").getValue().toString());
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
