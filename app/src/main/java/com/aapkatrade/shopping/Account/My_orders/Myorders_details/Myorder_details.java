package com.aapkatrade.shopping.Account.My_orders.Myorders_details;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.aapkatrade.shopping.R;

public class Myorder_details extends AppCompatActivity {
    LinearLayoutManager myorders_linearlayout_manager;
    RecyclerView recyclerView_my_orders_detail;
    Myorder_detail_adapter orders_detail_adapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myorder_details_activity);
        myorders_linearlayout_manager =  new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        orders_detail_adapter=new Myorder_detail_adapter(this);
        setuprecyclers();
        setupToolBar();



    }
    private void setuprecyclers() {
        setup_my_orders();

//        setupAllSale();
    }
    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "My orders Detail";
        getSupportActionBar().setTitle(teams);

    }
    private void setup_my_orders() {
        recyclerView_my_orders_detail = (RecyclerView)findViewById(R.id.recycler_myorder_detail);
        recyclerView_my_orders_detail.setLayoutManager(myorders_linearlayout_manager);
        recyclerView_my_orders_detail.setAdapter(orders_detail_adapter);

    }
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
