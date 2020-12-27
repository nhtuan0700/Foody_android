package com.example.foody.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody.Detail.activity_detail_food;
import com.example.foody.Detail.activity_detail_store;
import com.example.foody.Login.activity_login;
import com.example.foody.R;
import com.example.foody.model.Cart;
import com.example.foody.model.Food;
import com.example.foody.model.Option;
import com.example.foody.model.Store;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuFoodAdapter extends RecyclerView.Adapter<MenuFoodAdapter.MyViewHolder>{
    FirebaseUser user;
    FirebaseAuth mAuth;
    DatabaseReference database;
    private Context mContext;
    private List<Food> mData;
    int resource;
    public MenuFoodAdapter(Context mContext, int resource, List<Food> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.resource = resource;
        database = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_price.setText(mData.get(position).getStringPrice(mData.get(position).getPrice()));
        Picasso.get().load(mData.get(position).getImage()).into(holder.imageView);
        // Kiem tra context co duoc khoi tao tu activity_detail_store hay khong
        if (mContext instanceof activity_detail_store) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, activity_detail_food.class);
                    intent.putExtra("ID",mData.get(position).getIdStore());
                    mContext.startActivity(intent);
                }
            });
        } else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createBottomSheetLayout(position);
                }
            });
        }
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
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            btn_add = itemView.findViewById(R.id.btn_add);

        }
    }

    public void createBottomSheetLayout(final int position){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mContext, R.style.BottomSheetDialogTheme);
        final View bottomSheetView = LayoutInflater.from(mContext).inflate(R.layout.layout_bottom_sheet, null);
        ImageView imageView = bottomSheetView.findViewById(R.id.image);
        TextView tvName = bottomSheetView.findViewById(R.id.tv_name);
        TextView tvPrice = bottomSheetView.findViewById(R.id.tv_price);
        final TextView tvAmmount = bottomSheetView.findViewById(R.id.tv_ammount);
        LinearLayout btnAddCart = bottomSheetView.findViewById(R.id.btn_addCart);
        ImageView btnIncrease = bottomSheetView.findViewById(R.id.increase);
        ImageView btnDecrease = bottomSheetView.findViewById(R.id.decrease);
        final TextView tvQty = bottomSheetView.findViewById(R.id.tv_qty);

        tvName.setText(mData.get(position).getName());
        tvPrice.setText(mData.get(position).getStringPrice(mData.get(position).getPrice()));
        tvAmmount.setText(mData.get(position).getStringPrice(mData.get(position).getPrice()));
        Picasso.get().load(mData.get(position).getImage()).into(imageView);

        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(tvQty.getText().toString());
                qty++;
                tvQty.setText(qty + "");
                int ammount = mData.get(position).getPrice() * qty;
                tvAmmount.setText(mData.get(position).getStringPrice(ammount));
            }
        });

        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(tvQty.getText().toString());
                if (qty > 1) {
                    qty--;
                }
                tvQty.setText(qty + "");
                int ammount = mData.get(position).getPrice() * qty;
                tvAmmount.setText(mData.get(position).getStringPrice(ammount));

            }
        });

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user != null){
                    int qty = Integer.parseInt(tvQty.getText().toString());
                    int ammount = mData.get(position).getPrice() * qty;
                    checkCart(position, qty, ammount);
                } else {
                    Intent intent = new Intent(mContext, activity_login.class);
                    mContext.startActivity(intent);
                }
            }
        });

        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }


    public void checkCart(final int position,final int qty,final int ammount) {
        Query query = database.child("Cart").orderByChild("idUser").equalTo(user.getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = (int) snapshot.getChildrenCount();
                int i = 1;
                if (snapshot.exists()) {
                    for (DataSnapshot cart : snapshot.getChildren()) {
                        Cart mCart = cart.getValue(Cart.class);
                        if (mCart.getFood().getId().equals(mData.get(position).getId())) {
                            Toast.makeText(mContext, "Giỏ hàng đã thay đổi", Toast.LENGTH_SHORT).show();
                            updateCart(position, qty, ammount);
                            return;
                        }
                        if (i == count) {
                            Toast.makeText(mContext, "Món ăn đã được thêm vào giỏ!", Toast.LENGTH_SHORT).show();
                            addCart(position, qty, ammount);
                        }
                    i++;
                    }
                } else {
                    Toast.makeText(mContext, "Món ăn đã được thêm vào giỏ!", Toast.LENGTH_SHORT).show();
                    addCart(position, qty, ammount);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
            public void addCart(final int position, int qty, int ammount) {
                Cart cart = new Cart(user.getUid(), mData.get(position), qty, ammount);
                database.child("Cart").push().setValue(cart);
            }

            public void updateCart(final int position, final int qty, final int ammount) {
                Query query = database.child("Cart").orderByChild("idUser").equalTo(user.getUid());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            Cart mCart = childSnapshot.getValue(Cart.class);
                            if (mCart.getFood().getId().equals(mData.get(position).getId())) {
                                String idFood = mData.get(position).getId();
                                String idStore = mData.get(position).getIdStore();

                                Cart cart = new Cart(user.getUid(),mData.get(position), qty ,ammount);
                                database.child("Cart").child(childSnapshot.getKey()).setValue(cart);
                                return;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }


}
