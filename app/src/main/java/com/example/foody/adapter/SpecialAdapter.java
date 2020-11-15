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

import java.util.List;

public class SpecialAdapter extends RecyclerView.Adapter<SpecialAdapter.MyViewHolder>{
    private Context mContext;
    private List<Special> mData;
    private int resource;
    public SpecialAdapter(Context mContext, List<Special> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public SpecialAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_special,parent,false);
        return new SpecialAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialAdapter.MyViewHolder holder, int position) {
        holder.tvDescription.setText(mData.get(position).getDescription());
        holder.imageView.setImageResource(mData.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvDescription;
        ImageView imageView;
        public MyViewHolder(final View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            imageView = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }
}
