package com.aapkatrade.shopping.Account.Reviews;

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
 * Created by abcd on 8/26/2016.
 */
public class My_review_adapter extends RecyclerView.Adapter<CommonHolder_myreview_outer_row> {
    Context context;
    ArrayList<CommomData> commomDatas;

    public My_review_adapter(Context context) {
        this.context = context;

    }

    @Override
    public CommonHolder_myreview_outer_row onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myreview_outer_row, parent, false);
        CommonHolder_myreview_outer_row viewHolder = new CommonHolder_myreview_outer_row(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonHolder_myreview_outer_row holder, int position)
    {
        holder.linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Review_data.class);
                context.startActivity(intent);
                ((AppCompatActivity)context).overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });


    }

    @Override
    public int getItemCount() {
        return 6;
    }
}