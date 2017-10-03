package com.aapkatrade.shopping.ProductCategory.Show_subcategory.viewall_subcategory;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import com.aapkatrade.shopping.R;
import com.aapkatrade.shopping.general.SpacesItemDecoration;
import com.aapkatrade.shopping.general.Tabletsize;

public class Viewall_subcategory extends AppCompatActivity {
    RecyclerView recyclerview_viewall_subcategory;

    GridLayoutManager gridLayoutManager;

    LinearLayoutManager subcat_all__linearlayout_manager;
    Viewall_subcat_recycle_adpater subcat_recycle_adpater;
    SpacesItemDecoration itemDecoration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewall_subcategory_activity);
        subcat_all__linearlayout_manager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        if (Tabletsize.isTablet(this)) {
            gridLayoutManager=new GridLayoutManager(this,3);
     itemDecoration = new SpacesItemDecoration(5,3);
            // do something
        } else {
            gridLayoutManager = new GridLayoutManager(this, 2);
           itemDecoration = new SpacesItemDecoration(5,2);
            // do something else
        }


        subcat_recycle_adpater=new Viewall_subcat_recycle_adpater(this);
        setuptoolbar();
        setuprecycleview();




    }

    private void setuprecycleview() {


        recyclerview_viewall_subcategory= (RecyclerView)findViewById(R.id.recycler_viewall_subcat);
        recyclerview_viewall_subcategory.setLayoutManager(subcat_all__linearlayout_manager);

       // recyclerview_viewall_subcategory.addItemDecoration(itemDecoration);
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
