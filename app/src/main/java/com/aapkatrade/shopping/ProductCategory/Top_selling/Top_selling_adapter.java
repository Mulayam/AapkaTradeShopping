package com.aapkatrade.shopping.ProductCategory.Top_selling;

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
public class Top_selling_adapter extends RecyclerView.Adapter<CommomHolder_Top_selling> {
    Context context;
    ArrayList<CommomData> commomDatas;

    public Top_selling_adapter(Context context) {
        this.context = context;

    }

    @Override
    public CommomHolder_Top_selling onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_selling_row, parent, false);
        CommomHolder_Top_selling viewHolder = new CommomHolder_Top_selling(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommomHolder_Top_selling holder, int position) {
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
