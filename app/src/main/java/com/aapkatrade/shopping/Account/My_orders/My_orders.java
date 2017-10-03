package com.aapkatrade.shopping.Account.My_orders;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.aapkatrade.shopping.R;

public class My_orders extends AppCompatActivity {
    LinearLayoutManager myorders_linearlayout_manager;
    RecyclerView  recyclerView_my_orders;
    My_orders_adapter orders_adapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        myorders_linearlayout_manager =  new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
       orders_adapter=new My_orders_adapter(this);
        setuprecyclers();
        setupToolBar();

    }





    private void initilaizeview() {



    }
    private void setuprecyclers() {
        setup_my_orders();

//        setupAllSale();
    }



    private void setup_my_orders() {
        recyclerView_my_orders = (RecyclerView)findViewById(R.id.recycler_my_orders);
        recyclerView_my_orders.setLayoutManager(myorders_linearlayout_manager);
        recyclerView_my_orders.setAdapter(orders_adapter);

    }
   @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editprofile_menu, menu);
//        ActionItemBadge.update(((AppCompatActivity) this), menu.findItem(R.id.add_to_cart), ContextCompat.getDrawable(this, R.drawable.ic_cart_black)
//                , ActionItemBadge.BadgeStyles.GREY, 3);
        return true;
    }



    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "My orders";
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
