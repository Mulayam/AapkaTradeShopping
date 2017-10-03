package com.aapkatrade.shopping.Account.My_orders.Myorders_details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aapkatrade.shopping.R;
import com.aapkatrade.shopping.dashboard.dashboardcontentnew.CommomData;

import java.util.ArrayList;

/**
 * Created by abcd on 10/14/2016.
 */
public class Myorder_detail_adapter extends RecyclerView.Adapter<CommonHolder_my_orders_details> {
    Context context;
    ArrayList<CommomData> commomDatas;

    public Myorder_detail_adapter( Context context) {
        this.context=context;
    }

    @Override
    public CommonHolder_my_orders_details onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myorders_datail_single_row, parent, false);
        CommonHolder_my_orders_details viewHolder = new CommonHolder_my_orders_details(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonHolder_my_orders_details holder, int position) {




    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
