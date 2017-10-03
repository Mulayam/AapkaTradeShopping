package com.aapkatrade.shopping.checkout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.aapkatrade.shopping.R;

import java.util.ArrayList;

/**
 * Created by Netforce on 7/27/2016.
 */
public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int BILL = 0;
    private static final int ITEM = 1;
    ArrayList<Data> datas;
    Context context;

    Adapter(Context context, ArrayList<Data> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 9) {
            return BILL;
        } else {
            return ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_order, parent, false);
            Holder viewHolder = new Holder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bill, parent, false);
            HolderBill viewHolder = new HolderBill(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
