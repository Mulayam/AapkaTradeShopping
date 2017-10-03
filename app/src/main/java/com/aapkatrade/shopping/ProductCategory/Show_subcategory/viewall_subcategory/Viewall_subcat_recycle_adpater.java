package com.aapkatrade.shopping.ProductCategory.Show_subcategory.viewall_subcategory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aapkatrade.shopping.R;

/**
 * Created by abcd on 10/12/2016.
 */
public class Viewall_subcat_recycle_adpater extends RecyclerView.Adapter<CommomHolder_subcategory_all>

{
    Context context;
    public Viewall_subcat_recycle_adpater(Context context) {
        this.context = context;

    }

    @Override
    public CommomHolder_subcategory_all onCreateViewHolder(ViewGroup parent, int viewType) {




        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_cat_viewall_single, parent, false);
        CommomHolder_subcategory_all viewHolder = new CommomHolder_subcategory_all(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(CommomHolder_subcategory_all holder, int position) {







    }

    @Override
    public int getItemCount() {
        return 8;
    }
}
