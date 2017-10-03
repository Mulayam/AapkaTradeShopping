package com.aapkatrade.shopping.ProductCategory.Flat_offers;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aapkatrade.shopping.R;

/**
 * Created by Ritesh on 23-Aug-16.
 */
public class CommomHolder_Flat_offers extends RecyclerView.ViewHolder {
    CardView cardview;
    public CommomHolder_Flat_offers(View itemView) {
        super(itemView);
        cardview= (CardView) itemView.findViewById(R.id.cardview_flat_offers);
    }
}