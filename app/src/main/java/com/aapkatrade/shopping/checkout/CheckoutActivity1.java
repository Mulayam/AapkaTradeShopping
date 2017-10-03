package com.aapkatrade.shopping.checkout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.aapkatrade.shopping.R;
import com.aapkatrade.shopping.general.Validation;
import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class CheckoutActivity1 extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    ImageView imageViewClose;
    RelativeLayout relativeLayoutCoupon;
    LinearLayout linearLayoutCountry;
    EditText editTextFullname, editTextAddress1, editTextAddress2, editTextCity, editTextPostalcode, editTextPhone, editTextEmail;
    ArrayList<String> countries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        setupToolBar("Checkout");
        initview();
    }

    private void initview() {
        imageViewClose = (ImageView) findViewById(R.id.imageViewCloseCoupon);
        relativeLayoutCoupon = (RelativeLayout) findViewById(R.id.relativeLayoutCoupon);
        linearLayoutCountry = (LinearLayout) findViewById(R.id.linearlayoutCountry);
        editTextFullname = (EditText) findViewById(R.id.editTextFullname);
        editTextAddress1 = (EditText) findViewById(R.id.editTextAddress1);
        editTextAddress2 = (EditText) findViewById(R.id.editTextAddress2);
        editTextCity = (EditText) findViewById(R.id.editTextCity);
        editTextPostalcode = (EditText) findViewById(R.id.editTextPostalCode);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        findViewById(R.id.buttonNextOrder).setOnClickListener(this);
        imageViewClose.setOnClickListener(this);
        initCountry();
    }

    private void initCountry() {
        Locale[] locales = Locale.getAvailableLocales();

        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0) {
                countries.add(country);
            }


        }
        Collections.sort(countries);
        Set<String> hs = new HashSet<>();
        hs.addAll(countries);
        countries.clear();
        countries.addAll(hs);

        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(this, linearLayoutCountry);
        for (int i = 0; i < countries.size(); i++) {
            droppyBuilder.addMenuItem(new DroppyMenuItem(countries.get(i)));
        }


        /*for (int i = 0; i < countries.size(); i++) {
            droppyBuilder.addMenuItem(new DroppyMenuItem(countries.get(i)));
        }*/
        droppyBuilder.setOnClick(new DroppyClickCallbackInterface() {
            @Override
            public void call(View v, int id) {
                showMessage(countries.get(id));
            }
        });
        droppyBuilder.build();

    }

    private void showMessage(String s) {
        Toast.makeText(CheckoutActivity1.this, s, Toast.LENGTH_SHORT).show();
    }

    private void setupToolBar(String s) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = s;
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonNextOrder:
                if (Validation.isEmailAddress(editTextEmail, false)) {
                    editTextEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_green, 0);
                } else {
                    editTextEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_red, 0);
                }
                Intent intent = new Intent(CheckoutActivity1.this, CheckoutActivity2.class);
                startActivity(intent);
                break;
            case R.id.imageViewCloseCoupon:
                relativeLayoutCoupon.setVisibility(View.GONE);
                break;
        }
    }




}
