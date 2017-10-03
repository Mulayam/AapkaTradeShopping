package com.aapkatrade.shopping.ProductCategory.Flat_offers.viewall_flatoffers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aapkatrade.shopping.R;

/**
 * Created by abcd on 10/13/2016.
 */
public class View_all_flat_offer_adapter  extends RecyclerView.Adapter<CommomHolder_subcategory_viewall_flatoffer>

{
    Context context;
    public View_all_flat_offer_adapter(Context context) {
        this.context = context;

    }

    @Override
    public CommomHolder_subcategory_viewall_flatoffer onCreateViewHolder(ViewGroup parent, int viewType) {




        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_cat_viewall_flat_offer_single, parent, false);
        CommomHolder_subcategory_viewall_flatoffer viewHolder = new CommomHolder_subcategory_viewall_flatoffer(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(CommomHolder_subcategory_viewall_flatoffer holder, int position) {







    }

    @Override
    public int getItemCount() {
        return 8;
    }
}
