package com.aapkatrade.shopping.Account.Mywishlist;

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
 * Created by abcd on 8/26/2016.
 */
public class My_wishlist_adapter extends RecyclerView.Adapter<CommonHolder_wishlist> {
    Context context;
    ArrayList<CommomData> commomDatas;

    public My_wishlist_adapter(Context context) {
        this.context = context;

    }

    @Override
    public CommonHolder_wishlist onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mywishlist_single_row, parent, false);
        CommonHolder_wishlist viewHolder = new CommonHolder_wishlist(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonHolder_wishlist holder, int position)
    {
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, CategoryActivity.class);
//                context.startActivity(intent);
                ((AppCompatActivity)context).overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });


    }

    @Override
    public int getItemCount() {
        return 6;
    }
}