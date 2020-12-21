package com.example.foody.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody.Login.activity_login;
import com.example.foody.Profile.fragment_profile;
import com.example.foody.R;
import com.example.foody.model.Food;
import com.example.foody.model.Option;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.MyViewHolder> {
    FirebaseUser user;
    FirebaseAuth mAuth;
    private Context mContext;
    private int resource;
    private List<Option> mData;
    private fragment_profile fragment_profile;

    public OptionAdapter(Context mContext, List<Option> mData, fragment_profile fragment_profile) {
        this.mContext = mContext;
        this.mData = mData;
        this.fragment_profile = fragment_profile;
        mAuth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.option_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvName.setText(mData.get(position).getName());
        holder.imageView.setImageResource(mData.get(position).getImage());
        if (position == 5 || position == 7) {
            int pixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, mContext.getResources().getDisplayMetrics());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, pixels, 0, 0);
            holder.itemView.setLayoutParams(lp);
        }
        addEvent(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }

    }

    public void addEvent(final View itemView, int position) {
        user = mAuth.getCurrentUser();
        if (user != null) {
            switch (position) {
                case 9:
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            logOut();
                        }
                    });
                    break;
            }
        } else {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, activity_login.class);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public void logOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có chắc chắn muốn đăng xuất");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();
                fragment_profile.checkLogin();
            }
        });
        builder.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
