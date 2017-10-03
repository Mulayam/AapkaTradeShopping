package com.aapkatrade.shopping.Account.My_orders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aapkatrade.shopping.Account.My_orders.Myorders_details.Myorder_details;
import com.aapkatrade.shopping.R;
import com.aapkatrade.shopping.dashboard.dashboardcontentnew.CommomData;

import java.util.ArrayList;

/**
 * Created by abcd on 8/26/2016.
 */
public class My_orders_adapter  extends RecyclerView.Adapter<CommonHolder_my_orders> {
    Context context;
    ArrayList<CommomData> commomDatas;

    public My_orders_adapter(Context context) {
        this.context = context;

    }

    @Override
    public CommonHolder_my_orders onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myorders_single_row, parent, false);
        CommonHolder_my_orders viewHolder = new CommonHolder_my_orders(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonHolder_my_orders holder, int position) {
        holder.rl_view_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, Myorder_details.class);
                context.startActivity(i);

            }
        });



    }

    @Override
    public int getItemCount() {
        return 6;
    }
}