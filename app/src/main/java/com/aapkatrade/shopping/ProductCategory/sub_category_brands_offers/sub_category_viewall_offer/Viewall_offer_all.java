package com.aapkatrade.shopping.ProductCategory.sub_category_brands_offers.sub_category_viewall_offer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.aapkatrade.shopping.R;
import com.aapkatrade.shopping.general.SpacesItemDecoration;
import com.aapkatrade.shopping.general.Tabletsize;

public class Viewall_offer_all extends AppCompatActivity {

    GridLayoutManager gridLayoutManager;
    Viewall_topoffers_adapter subcat_offers_recycle_adpater;
    SpacesItemDecoration itemDecoration;
    RecyclerView recyclerview_viewall_subcategory_offers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall_offer_all);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Tabletsize.isTablet(this)) {
            gridLayoutManager=new GridLayoutManager(this,3);
            itemDecoration = new SpacesItemDecoration(2,3);
            // do something
        } else {
            gridLayoutManager = new GridLayoutManager(this, 2);
            itemDecoration = new SpacesItemDecoration(2,2);
            // do something else
        }


        subcat_offers_recycle_adpater=new Viewall_topoffers_adapter(this);
        setuptoolbar();
        setuprecycleview();




    }

    private void setuprecycleview() {


        recyclerview_viewall_subcategory_offers= (RecyclerView)findViewById(R.id.recycler_viewall_subcat_offers);
        recyclerview_viewall_subcategory_offers.setLayoutManager(gridLayoutManager);

        recyclerview_viewall_subcategory_offers.addItemDecoration(itemDecoration);
        recyclerview_viewall_subcategory_offers.setAdapter(subcat_offers_recycle_adpater);






    }

    // toolbar set
    private void setuptoolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_subcategory_list_offers);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "Top Offers";
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
