package com.aapkatrade.shopping.Account.Mywishlist;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aapkatrade.shopping.R;


/**
 * Created by abcd on 8/26/2016.
 */
public class CommonHolder_wishlist extends RecyclerView.ViewHolder {
    CardView cardview;
    public CommonHolder_wishlist(View itemView) {
        super(itemView);
        cardview= (CardView) itemView.findViewById(R.id.cardview_myreviews);
    }
}