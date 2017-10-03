package com.aapkatrade.shopping.category;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.aapkatrade.shopping.R;

/**
 * Created by Netforce on 7/25/2016.
 */
public class Holder extends RecyclerView.ViewHolder {
    LinearLayout linearlayout;

    public Holder(View itemView) {
        super(itemView);
        linearlayout= (LinearLayout) itemView.findViewById(R.id.linearlayout);
    }
}
