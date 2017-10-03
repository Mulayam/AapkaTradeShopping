package com.aapkatrade.shopping.ProductCategory.sub_category_brands_offers.sub_category_viewall_offer;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aapkatrade.shopping.R;
import com.aapkatrade.shopping.dashboard.dashboardcontentnew.CommomData;

import java.util.ArrayList;

/**
 * Created by abcd on 10/15/2016.
 */
public class Viewall_topoffers_adapter extends RecyclerView.Adapter<CommomHolder_viewall_offers> {
    Context context;
    ArrayList<CommomData> commomDatas;

    public Viewall_topoffers_adapter(Context context) {
        this.context = context;

    }

    @Override
    public CommomHolder_viewall_offers onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_sub_categories_row, parent, false);
        CommomHolder_viewall_offers viewHolder = new CommomHolder_viewall_offers(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommomHolder_viewall_offers holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
