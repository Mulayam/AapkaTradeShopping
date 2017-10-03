package com.aapkatrade.shopping.ProductCategory.Top_brands;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aapkatrade.shopping.R;
import com.aapkatrade.shopping.dashboard.dashboardcontentnew.CommomData;

import java.util.ArrayList;

/**
 * Created by Ritesh on 23-Aug-16.
 */
public class Topbrands_adapter extends RecyclerView.Adapter<CommomHolder_Topbrands> {
    Context context;
    ArrayList<CommomData> commomDatas;

    public Topbrands_adapter(Context context) {
        this.context = context;

    }

    @Override
    public CommomHolder_Topbrands onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_brands_categories_row, parent, false);
        CommomHolder_Topbrands viewHolder = new CommomHolder_Topbrands(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommomHolder_Topbrands holder, int position) {
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, CategoryActivity.class);
//                context.startActivity(intent);
                ((AppCompatActivity)context).overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });


    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
