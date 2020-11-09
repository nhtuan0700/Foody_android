package com.example.foody.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.foody.R;
import com.example.foody.model.Option;

import java.util.List;

public class OptionAdapter extends ArrayAdapter<Option>{
    private Context context;
    private int resource;
    private List<Option> options;

    public OptionAdapter(@NonNull Context context, int resource, @NonNull List<Option> options) {
        super(context, resource, options);
        this.context = context;
        this.resource = resource;
        this.options = options;
    }

    @Override
    public int getCount() {
        return options.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Option getItem(int position) {
        return options.get(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.option_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.mItemPic = (ImageView) view.findViewById(R.id.image);
            viewHolder.mName= (TextView) view.findViewById(R.id.name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        try{
            Option music = options.get(position);
            int image = music.getImage();
            viewHolder.mItemPic.setImageResource(image);
            viewHolder.mName.setText(music.getName());
        } catch (Exception e) {
        }
        return view;
    }

    public class ViewHolder{
        ImageView mItemPic;
        TextView mName;
    }
}
