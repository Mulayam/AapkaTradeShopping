package com.aapkatrade.shopping.ProductCategory.Top_brands;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aapkatrade.shopping.R;
/**
 * Created by Ritesh on 23-Aug-16.
 */
public class CommomHolder_Topbrands extends RecyclerView.ViewHolder {
    CardView cardview;
    public CommomHolder_Topbrands(View itemView) {
        super(itemView);
        cardview= (CardView) itemView.findViewById(R.id.cardview_Top_brands);
    }
}