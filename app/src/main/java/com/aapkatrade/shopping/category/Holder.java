package com.aapkatrade.shopping.category;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aapkatrade.shopping.R;

/**
 * Created by Netforce on 7/25/2016.
 */
public class Holder extends RecyclerView.ViewHolder
{

    LinearLayout linearlayout;
    ImageView imgProduct;
    TextView txtProductName,txtPrice,txtShortdescription,txtSpecialPrice;


    public Holder(View itemView)
    {
        super(itemView);

        linearlayout= (LinearLayout) itemView.findViewById(R.id.linearlayout);

        txtProductName = (TextView) itemView.findViewById(R.id.txtProductName);

        txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);

        txtShortdescription = (TextView) itemView.findViewById(R.id.txtShortdescription);

        imgProduct = (ImageView) itemView.findViewById(R.id.imgProduct);

        txtSpecialPrice = (TextView) itemView.findViewById(R.id.txtSpecialPrice);

        txtSpecialPrice.setPaintFlags(txtSpecialPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


    }
}
