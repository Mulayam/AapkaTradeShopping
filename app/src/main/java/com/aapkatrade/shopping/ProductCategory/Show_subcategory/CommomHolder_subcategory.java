package com.aapkatrade.shopping.ProductCategory.Show_subcategory;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.aapkatrade.shopping.R;

/**
 * Created by Ritesh on 22-Aug-16.
 */
public class CommomHolder_subcategory extends RecyclerView.ViewHolder {
    CardView cardview;
    public CommomHolder_subcategory(View itemView) {
        super(itemView);
        cardview= (CardView) itemView.findViewById(R.id.cardview_subcategory);
    }
}