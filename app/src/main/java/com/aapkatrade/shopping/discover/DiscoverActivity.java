package com.aapkatrade.shopping.discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import com.aapkatrade.shopping.Add_to_cart.Add_to_card_activity;
import com.aapkatrade.shopping.R;

import com.aapkatrade.shopping.dashboard.navigation.Category_data;
import com.aapkatrade.shopping.dashboard.navigation.NavigationFragment;

import java.util.ArrayList;


public class DiscoverActivity extends AppCompatActivity
{

      private Toolbar toolbar;
     public static   ArrayList<Category_data> categoryData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_discover);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        categoryData =  NavigationFragment.category_data;

        setupToolBar();

        setupTab();
    }

    private void setupToolBar()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "Discovery";
        getSupportActionBar().setTitle(teams);

    }

    private void setupTab()
    {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        for (int i=0; i<categoryData.size(); i++){

            tabLayout.addTab(tabLayout.newTab().setText(categoryData.get(i).getName()));
        }


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                return true;
            case R.id.add_to_cart:
                //finish();
                Intent i =new Intent(DiscoverActivity.this, Add_to_card_activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
