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
import com.example.foody.Detail.activity_detail_store;
import com.example.foody.R;
import com.example.foody.model.Food;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuFoodAdapter extends RecyclerView.Adapter<MenuFoodAdapter.MyViewHolder>{
    private Context mContext;
    private List<Food> mData;
    public MenuFoodAdapter(Context mContext, List<Food> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_food,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_price.setText(mData.get(position).getStringPrice() + " đ");
        Picasso.get().load(mData.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        ImageView imageView;
        TextView tv_price;
        ImageView btn_add;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_price = (TextView)  itemView.findViewById(R.id.tv_price);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            btn_add = itemView.findViewById(R.id.btn_add);
            // Kiem tra context co duoc khoi tao tu activity_detail_store hay khong
            if (mContext instanceof activity_detail_store) {
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
    }
}
