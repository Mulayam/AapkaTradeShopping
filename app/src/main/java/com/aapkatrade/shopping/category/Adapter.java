package com.aapkatrade.shopping.category;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aapkatrade.shopping.R;
import com.aapkatrade.shopping.productdetail.ProductDetailActivity;

import java.util.ArrayList;

/**
 * Created by Netforce on 7/25/2016.
 */
public class Adapter extends RecyclerView.Adapter<Holder> {
    Context context;
    ArrayList<Data> datas;

    Adapter(Context context, ArrayList<Data> datas) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category, parent, false);
        Holder viewHolder = new Holder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
