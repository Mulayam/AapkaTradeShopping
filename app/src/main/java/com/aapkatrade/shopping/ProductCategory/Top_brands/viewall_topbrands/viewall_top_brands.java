package com.aapkatrade.shopping.ProductCategory.Top_brands.viewall_topbrands;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.aapkatrade.shopping.R;
import com.aapkatrade.shopping.general.SpacesItemDecoration;
import com.aapkatrade.shopping.general.Tabletsize;

public class viewall_top_brands extends AppCompatActivity {
    RecyclerView recyclerview_viewall_subcategory;

    GridLayoutManager gridLayoutManager;
    viewall_topbrands_adapter subcat_recycle_adpater;
    SpacesItemDecoration itemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewall_top_brands_activity);
        if (Tabletsize.isTablet(this)) {
            gridLayoutManager=new GridLayoutManager(this,3);
            itemDecoration = new SpacesItemDecoration(5,3);
            // do something
        } else {
            gridLayoutManager = new GridLayoutManager(this, 2);
            itemDecoration = new SpacesItemDecoration(5,2);
            // do something else
        }


        subcat_recycle_adpater=new viewall_topbrands_adapter(this);
        setuptoolbar();
        setuprecycleview();




    }

    private void setuprecycleview() {


        recyclerview_viewall_subcategory= (RecyclerView)findViewById(R.id.recycler_viewall_subcat_topbrands);
        recyclerview_viewall_subcategory.setLayoutManager(gridLayoutManager);

        recyclerview_viewall_subcategory.addItemDecoration(itemDecoration);
        recyclerview_viewall_subcategory.setAdapter(subcat_recycle_adpater);






    }

    // toolbar set
    private void setuptoolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_subcategory_list);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "Top Brands";
        getSupportActionBar().setTitle(teams);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }





}
