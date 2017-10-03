package com.aapkatrade.shopping.dashboard.dashboardcontent;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.aapkatrade.shopping.R;

import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class DashboardAdapter extends RecyclerView.Adapter<DashboardHolder> {

    public static int position = 0;
    private List<DashboardData> itemList;
    private Context context;
    private String imagePath;


    public DashboardAdapter(Context context, List<DashboardData> itemList) {
        this.itemList = itemList;
        this.context = context;
        this.imagePath = imagePath;

    }

    @Override
    public DashboardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_dashboard, parent, false);
        DashboardHolder viewHolder = new DashboardHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DashboardHolder holder, final int position) {

    }


    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {

        return 15;
        //  return itemList.size();
    }


}
