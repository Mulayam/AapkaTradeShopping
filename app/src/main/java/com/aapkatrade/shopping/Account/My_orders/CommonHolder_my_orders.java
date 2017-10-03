package com.aapkatrade.shopping.Account.My_orders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.aapkatrade.shopping.R;

/**
 * Created by abcd on 8/26/2016.
 */
public class CommonHolder_my_orders extends RecyclerView.ViewHolder {
    CardView cardview;
    RelativeLayout rl_view_order;
    public CommonHolder_my_orders(View itemView) {
        super(itemView);
        cardview= (CardView) itemView.findViewById(R.id.cardview_myreviews);
        rl_view_order=(RelativeLayout)itemView.findViewById(R.id.rl_order_container);
    }
}