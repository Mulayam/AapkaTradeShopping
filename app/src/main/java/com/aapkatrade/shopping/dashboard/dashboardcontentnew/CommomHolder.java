package com.aapkatrade.shopping.dashboard.dashboardcontentnew;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aapkatrade.shopping.R;


/**
 * Created by Netforce on 7/25/2016.
 */
public class CommomHolder extends RecyclerView.ViewHolder {
    CardView cardview;
    public CommomHolder(View itemView) {
        super(itemView);
        cardview= (CardView) itemView.findViewById(R.id.cardview);
    }
}
