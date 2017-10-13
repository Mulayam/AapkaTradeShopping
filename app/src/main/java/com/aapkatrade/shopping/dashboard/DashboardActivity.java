package com.aapkatrade.shopping.dashboard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.aapkatrade.shopping.Account.Mywishlist.MyWishlist_activity;
import com.aapkatrade.shopping.Add_to_cart.Add_to_card_activity;
import com.aapkatrade.shopping.R;

import com.aapkatrade.shopping.dashboard.dashboardcontentnew.DashboardFragment;
import com.aapkatrade.shopping.dashboard.navigation.NavigationFragment;
import com.aapkatrade.shopping.general.AppSharedPreference;
import com.aapkatrade.shopping.general.SharedPreferenceConstants;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.mikepenz.actionitembadge.library.ActionItemBadge;


public class DashboardActivity extends AppCompatActivity
{

    private NavigationFragment drawer;
    private Toolbar toolbar;
    private DashboardFragment homeFragment;
    public static String username,mobno,email,Lastname,dob;
    private AHBottomNavigation bottomNavigation;
    CoordinatorLayout coordinatorLayout;
    Context context;
    public static TextView menu_badge;
    AppSharedPreference appSharedPreference;
    Menu new_menu;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        menu_badge = (TextView) findViewById(R.id.menu_badge);

        context = this;

        appSharedPreference = new AppSharedPreference(context);


        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setupToolBar();
        //setupNavigation();
        setupNavigationCustom();
        setupDashFragment();
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            username =(String) b.get("username");
            mobno =(String) b.get("mobno");
            email =(String) b.get("email");
            Lastname=(String) b.get("lname");
            dob=(String) b.get("dob");

            drawer.setdata(username,mobno,email,Lastname,dob);

        }


        setup_bottomNavigation();

    }

    private void setupNavigationCustom()
    {
        drawer = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        drawer.setup(R.id.fragment, (DrawerLayout) findViewById(R.id.drawer), toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        new_menu = menu;

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard, menu);

        invalidateOptionsMenu();

        if (appSharedPreference.getSharedPrefInt(SharedPreferenceConstants.CART_COUNT.toString(), 0)==0)
        {
            ActionItemBadge.update(((AppCompatActivity) context), menu.findItem(R.id.add_to_cart), ContextCompat.getDrawable(context, R.drawable.ic_cart_black)
                    , ActionItemBadge.BadgeStyles.GREY, 0);

        }
        else
        {
            ActionItemBadge.update(((AppCompatActivity) context), menu.findItem(R.id.add_to_cart), ContextCompat.getDrawable(context, R.drawable.ic_cart_black)
                    , ActionItemBadge.BadgeStyles.GREY,  appSharedPreference.getSharedPrefInt(SharedPreferenceConstants.CART_COUNT.toString(), 0));
        }


        return true;
    }


    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "Home";
        getSupportActionBar().setTitle(teams);

    }

    private void replaceFragment(Fragment newFragment, String tag) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.drawer_layout, newFragment, tag);
        transaction.commit();
    }

    private void setupDashFragment() {
        if (homeFragment == null) {
            homeFragment = new DashboardFragment();
        }
        String tagName = homeFragment.getClass().getName();
        replaceFragment(homeFragment, tagName);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_to_cart:
                //finish();
                Intent i =new Intent(DashboardActivity.this, Add_to_card_activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed()
    {
        finish();
    }


    private void setup_bottomNavigation()
    {
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordination_home_activity);

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        // scrollView = (NestedScrollView) findViewById(R.id.scroll_main);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_mobile, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_mobile, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_mobile, R.color.colorPrimary);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_4, R.drawable.ic_mobile, R.color.colorPrimary);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.tab_5, R.drawable.ic_mobile, R.color.colorPrimary);

        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {
                Log.d("DemoActivity", "BottomNavigation Position: " + y);
            }
        });
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);
        bottomNavigation.setDefaultBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        bottomNavigation.setBehaviorTranslationEnabled(true);
        bottomNavigation.setSelectedBackgroundVisible(true);
        bottomNavigation.setAccentColor(ContextCompat.getColor(context, R.color.white));
        bottomNavigation.setInactiveColor(Color.parseColor("#000000"));

        bottomNavigation.setForceTint(true);
        bottomNavigation.setTranslucentNavigationEnabled(true);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setColored(false);
        bottomNavigation.setCurrentItem(0);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener()
        {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                switch (position) {

                    case 0:
                        if (homeFragment == null) {
                            homeFragment = new DashboardFragment();
                        }
                        String tagName = homeFragment.getClass().getName();
                        replaceFragment(homeFragment, tagName);
                        break;

                    case 1:



                        break;


                    case 2:


                        break;
                    case 3:

                        break;

                    case 4:



                        break;


                }
                // Do something cool here...
                return true;
            }
        });


    }

    @Override
    protected void onResume()
    {
        super.onResume();
       /* ActionItemBadge.update(((AppCompatActivity) context), new_menu.findItem(R.id.add_to_cart), ContextCompat.getDrawable(context, R.drawable.ic_cart_black)
                , ActionItemBadge.BadgeStyles.GREY,  appSharedPreference.getSharedPrefInt(SharedPreferenceConstants.CART_COUNT.toString(), 0));
        */
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {

        ActionItemBadge.update(((AppCompatActivity) context), menu.findItem(R.id.add_to_cart), ContextCompat.getDrawable(context, R.drawable.ic_cart_black)
                , ActionItemBadge.BadgeStyles.GREY,  appSharedPreference.getSharedPrefInt(SharedPreferenceConstants.CART_COUNT.toString(), 0));

        return super.onPrepareOptionsMenu(menu);
    }


}
