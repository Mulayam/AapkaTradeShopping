package com.aapkatrade.shopping.ProductCategory.Top_brands.viewall_topbrands;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aapkatrade.shopping.R;
/**
 * Created by abcd on 10/12/2016.
 */
public class viewall_topbrands_adapter  extends RecyclerView.Adapter<CommomHolder_subcategory_all_topbrands>

{
    Context context;
    public viewall_topbrands_adapter(Context context) {
        this.context = context;

    }

    @Override
    public CommomHolder_subcategory_all_topbrands onCreateViewHolder(ViewGroup parent, int viewType) {




        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewall_subcategory_top_brands, parent, false);
        CommomHolder_subcategory_all_topbrands viewHolder = new CommomHolder_subcategory_all_topbrands(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(CommomHolder_subcategory_all_topbrands holder, int position) {







    }

    @Override
    public int getItemCount() {
        return 8;
    }
}
