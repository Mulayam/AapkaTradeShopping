package com.aapkatrade.shopping.ProductCategory.Flat_offers.viewall_flatoffers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.aapkatrade.shopping.R;
import com.aapkatrade.shopping.general.SpacesItemDecoration;
import com.aapkatrade.shopping.general.Tabletsize;

public class View_all_flat_offers extends AppCompatActivity {
    RecyclerView recyclerview_viewall_subcategory_flatoffers;

    GridLayoutManager gridLayoutManager;
    View_all_flat_offer_adapter subcat_recycle_adpater;
    SpacesItemDecoration itemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_flat_offers);
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


        subcat_recycle_adpater=new View_all_flat_offer_adapter(this);
        setuptoolbar();
        setuprecycleview();




    }

    private void setuprecycleview() {


        recyclerview_viewall_subcategory_flatoffers= (RecyclerView)findViewById(R.id.recycler_viewall_subcat_flatoffers);
        recyclerview_viewall_subcategory_flatoffers.setLayoutManager(gridLayoutManager);

        recyclerview_viewall_subcategory_flatoffers.addItemDecoration(itemDecoration);
        recyclerview_viewall_subcategory_flatoffers.setAdapter(subcat_recycle_adpater);






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
