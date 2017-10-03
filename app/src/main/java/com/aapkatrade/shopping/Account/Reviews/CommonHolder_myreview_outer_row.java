package com.aapkatrade.shopping.Account.Reviews;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.aapkatrade.shopping.R;

/**
 * Created by abcd on 8/26/2016.
 */
public class CommonHolder_myreview_outer_row extends RecyclerView.ViewHolder {
    CardView cardview;

    public CommonHolder_myreview_outer_row(View itemView) {
        super(itemView);
        cardview = (CardView) itemView.findViewById(R.id.cardview_myreviews_outer);

    }
}