package com.aapkatrade.shopping.discover;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aapkatrade.shopping.R;
/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class CategoryHolder extends RecyclerView.ViewHolder {

CardView cardview;
    public CategoryHolder(View itemView) {
        super(itemView);
        cardview=(CardView)itemView.findViewById(R.id.cardview);

        //implementing onClickListener

    }

}