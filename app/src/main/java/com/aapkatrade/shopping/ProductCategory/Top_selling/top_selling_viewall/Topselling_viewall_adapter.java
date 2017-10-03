package com.aapkatrade.shopping.ProductCategory.Top_selling.top_selling_viewall;

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
 * Created by abcd on 10/14/2016.
 */
public class Topselling_viewall_adapter   extends RecyclerView.Adapter<CommomHolder_viewall_topselling> {
    Context context;
    ArrayList<CommomData> commomDatas;

    public Topselling_viewall_adapter(Context context) {
        this.context = context;

    }

    @Override
    public CommomHolder_viewall_topselling onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_selling_viewall_row, parent, false);
        CommomHolder_viewall_topselling viewHolder = new CommomHolder_viewall_topselling(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommomHolder_viewall_topselling holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
