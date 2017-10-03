package com.aapkatrade.shopping.ProductCategory.sub_category_brands_offers;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aapkatrade.shopping.R;

/**
 * Created by Ritesh on 23-Aug-16.
 */
public class CommomHolder_offers_brands  extends RecyclerView.ViewHolder {
    CardView cardview;
    public CommomHolder_offers_brands(View itemView) {
        super(itemView);
        cardview= (CardView) itemView.findViewById(R.id.cardview_brands_offers);
    }
}