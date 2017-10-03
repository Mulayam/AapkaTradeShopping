package com.aapkatrade.shopping.ProductCategory.Top_selling;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aapkatrade.shopping.R;
/**
 * Created by Ritesh on 23-Aug-16.
 */
public class CommomHolder_Top_selling extends RecyclerView.ViewHolder {
    CardView cardview;
    public CommomHolder_Top_selling(View itemView) {
        super(itemView);
        cardview= (CardView) itemView.findViewById(R.id.cardview_top_selling);
    }
}