package com.example.foody.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody.R;
import com.example.foody.model.CollectionItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.MyViewHolder>{
    private Context mContext;
    private List<CollectionItem> mData;
    private int resource;
    public CollectionAdapter(Context mContext, int resource, List<CollectionItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.resource = resource;
    }

    @NonNull
    @Override
    public CollectionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(resource,parent,false);
        return new CollectionAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionAdapter.MyViewHolder holder, int position) {
        switch (resource) {
            case R.layout.item_collection:
                Picasso.get().load(mData.get(position).getImage()).into(holder.imageView);
                break;
            case R.layout.item_special:
                holder.tvTitle.setText(mData.get(position).getTitle());
                Picasso.get().load(mData.get(position).getImage()).into(holder.imageView);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        ImageView imageView;
        public MyViewHolder(final View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            imageView = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }
}
