package com.aapkatrade.shopping.Account.Mywishlist;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.aapkatrade.shopping.R;


/**
 * Created by abcd on 8/26/2016.
 */
public class CommonHolder_wishlist extends RecyclerView.ViewHolder
{
    CardView cardview;
    ImageView imgDeleteProduct;
    RelativeLayout relative_decreament_qty ,relative_increament_qty;


    public CommonHolder_wishlist(View itemView)
    {
        super(itemView);
        cardview= (CardView) itemView.findViewById(R.id.cardview_myreviews);

        imgDeleteProduct = (itemView).findViewById(R.id.imgDeleteProduct);

        relative_decreament_qty = (RelativeLayout) itemView.findViewById(R.id.relative_decreament_qty);

        relative_increament_qty = (RelativeLayout) itemView.findViewById(R.id.relative_increament_qty);

    }
}