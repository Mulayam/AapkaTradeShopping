package com.aapkatrade.shopping.Add_to_cart;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.aapkatrade.shopping.R;

/**
 * Created by abcd on 9/1/2016.
 */
public class CommonHolder_addtocard extends RecyclerView.ViewHolder
{
    CardView cardview;
    Spinner spinner;
    LinearLayout linearLayoutQuantity;
    TextView tvquantity;

    public CommonHolder_addtocard(View itemView)
    {
        super(itemView);
        cardview= (CardView) itemView.findViewById(R.id.cardview_addtocard);
        spinner=(Spinner)itemView.findViewById(R.id.spinner_addto_card);
        linearLayoutQuantity=(LinearLayout)itemView.findViewById(R.id.dropdown_ll);
        tvquantity=(TextView)itemView.findViewById(R.id.textView64);
    }
}