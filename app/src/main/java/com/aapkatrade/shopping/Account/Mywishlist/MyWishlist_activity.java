package com.aapkatrade.shopping.Account.Mywishlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.aapkatrade.shopping.R;

public class MyWishlist_activity extends AppCompatActivity {
    LinearLayoutManager myorders_linearlayout_manager;
    RecyclerView recyclerView_mywishlist;
    My_wishlist_adapter wishadapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wishlist_activity);
        myorders_linearlayout_manager =  new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        wishadapter=new My_wishlist_adapter(this);
        setuprecyclers();
        setupToolBar();
    }
    private void setuprecyclers() {
        setup_my_wishlist();

//        setupAllSale();
    }



    private void setup_my_wishlist() {
        recyclerView_mywishlist = (RecyclerView)findViewById(R.id.recycler_my_wishlist);
        recyclerView_mywishlist.setLayoutManager(myorders_linearlayout_manager);
        recyclerView_mywishlist.setAdapter(wishadapter);

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
        String teams = "My WishList";
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
