package com.aapkatrade.shopping.login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.aapkatrade.shopping.R;

public class Forgot_password extends AppCompatActivity {
    Toolbar toolbar;
    private Send_veri_mail verify_mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        setupToolBar();

    }
    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_change_password);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "FORGOT PASSWORD";
        getSupportActionBar().setTitle(teams);
        setupforgotFragment();


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
    private void replaceFragment(Fragment newFragment, String tag) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_forgot, newFragment, tag);
        transaction.commit();
    }

    private void setupforgotFragment() {
        if (verify_mail == null) {
            verify_mail = new Send_veri_mail();
        }
        String tagName = verify_mail.getClass().getName();
        replaceFragment(verify_mail, tagName);
    }
}
