package com.aapkatrade.shopping.category;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aapkatrade.shopping.R;
import com.aapkatrade.shopping.productdetail.ProductDetailActivity;
import com.squareup.picasso.Picasso;

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
    public void onBindViewHolder(Holder holder, final int position)
    {
        holder.linearlayout.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("product_id",datas.get(position).id);
                context.startActivity(intent);
            }


        });

        Picasso.with(context).load(datas.get(position).imageurl)
                .placeholder(R.drawable.david)
                .error(R.drawable.david)
                .into(holder.imgProduct);

       holder.txtProductName.setText(datas.get(position).name);

        holder.txtPrice.setText(context.getResources().getText(R.string.rupay_text)+datas.get(position).price);

        holder.txtShortdescription.setText(datas.get(position).short_description);

        holder.txtSpecialPrice.setText(context.getResources().getText(R.string.rupay_text)+datas.get(position).special_price);


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
