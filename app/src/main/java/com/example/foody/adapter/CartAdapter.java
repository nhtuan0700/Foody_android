package com.example.foody.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody.R;
import com.example.foody.model.Cart;
import com.example.foody.model.Food;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{
    FirebaseUser user;
    FirebaseAuth mAuth;
    DatabaseReference database;
    ArrayList<Cart> arrCart;
    Context mContext;
    public CartAdapter(Context mContext, ArrayList<Cart> arrCart) {
        this.arrCart = arrCart;
        this.mContext = mContext;
        database = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_cart, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tvName.setText(arrCart.get(position).getFood().getName());
        holder.tvPrice.setText(arrCart.get(position).getFood().getStringPrice(arrCart.get(position).getFood().getPrice()));
        holder.tvQty.setText(arrCart.get(position).getQty() + "");
        holder.tvAmmount.setText(arrCart.get(position).getFood().getStringPrice(arrCart.get(position).getAmmount()) + "");
        Picasso.get().load(arrCart.get(position).getFood().getImage()).into(holder.imageView);
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCart(arrCart.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrCart.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvName, tvPrice, tvQty, tvAmmount, tvDelete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvQty = itemView.findViewById(R.id.tv_qty);
            tvAmmount = itemView.findViewById(R.id.tv_ammount);
            tvDelete = itemView.findViewById(R.id.tv_delete);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    public void deleteCart(final Cart cart) {
        Query query = database.child("Cart").orderByChild("idUser").equalTo(user.getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot childDataSnapshot : snapshot.getChildren()) {
                        Cart mCart = childDataSnapshot.getValue(Cart.class);
                        if (mCart.getFood().getId().equals(cart.getFood().getId())) {
                            database.child("Cart").child(childDataSnapshot.getKey()).setValue(null);
                            Toast.makeText(mContext, "Đã xóa món ăn khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
                            arrCart.remove(cart);
                            notifyDataSetChanged();
                            return;
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
