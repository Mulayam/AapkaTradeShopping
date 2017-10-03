package com.aapkatrade.shopping.discover;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.aapkatrade.shopping.R;
import com.aapkatrade.shopping.productdetail.ProductDetailActivity;

import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {

    public static int position = 0;
    private List<CategoryData> itemList;
    private Context context;
    private String imagePath;


    public CategoryAdapter(Context context, List<CategoryData> itemList) {
        this.itemList = itemList;
        this.context = context;
        this.imagePath = imagePath;

    }

    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_dashboard, parent, false);
        CategoryHolder viewHolder = new CategoryHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryHolder holder, final int position) {
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ProductDetailActivity.class);
                context.startActivity(intent);



            }
        });

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
