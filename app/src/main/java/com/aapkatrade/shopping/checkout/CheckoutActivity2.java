package com.aapkatrade.shopping.checkout;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.andexert.library.RippleView;
import com.aapkatrade.shopping.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CheckoutActivity2 extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    RecyclerView recyclerView;
    private Toolbar toolbar;
    Context context;
    ArrayList<Data> datas = new ArrayList<>();
    RadioButton radioButtonPayPal, radioButtonCheck, radioButtonBankTransfer;
    CircleImageView circleImageViewpaypal, circleImageViewcheck, circleImageViewbanktransfer;
    private LinearLayout linearLayoutTransfer, linearLayoutCheck, linearLayoutPaypal;
    RippleView rippleTransfer,rippleCheck,ripplePaypal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout2);
        context = this;
        setupToolBar("Checkout");
        initview();
    }

    private void setupToolBar(String s) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = s;
        getSupportActionBar().setTitle(teams);

    }

    private void initview() {
        rippleCheck= (RippleView) findViewById(R.id.rippleCheck);
        ripplePaypal= (RippleView) findViewById(R.id.ripplePaypal);
        rippleTransfer= (RippleView) findViewById(R.id.rippleTransfer);
        linearLayoutCheck = (LinearLayout) findViewById(R.id.linearlayoutCheck);
        linearLayoutPaypal = (LinearLayout) findViewById(R.id.linearlayoutPayPal);
        linearLayoutTransfer = (LinearLayout) findViewById(R.id.linearlayoutTransfer);
        linearLayoutCheck.setOnClickListener(this);
        linearLayoutPaypal.setOnClickListener(this);
        linearLayoutTransfer.setOnClickListener(this);
        radioButtonBankTransfer = (RadioButton) findViewById(R.id.radioDirectBankTransfer);
        radioButtonCheck = (RadioButton) findViewById(R.id.radioCheckPayment);
        radioButtonPayPal = (RadioButton) findViewById(R.id.radioPaypal);
        radioButtonPayPal.setChecked(true);
        circleImageViewpaypal = (CircleImageView) findViewById(R.id.imageviewPaypal);
        circleImageViewcheck = (CircleImageView) findViewById(R.id.imageViewCheck);
        circleImageViewbanktransfer = (CircleImageView) findViewById(R.id.imageViewBankTransfer);
        radioButtonBankTransfer.setOnCheckedChangeListener(this);
        radioButtonPayPal.setOnCheckedChangeListener(this);
        radioButtonCheck.setOnCheckedChangeListener(this);
        circleImageViewbanktransfer.setOnClickListener(this);
        circleImageViewcheck.setOnClickListener(this);
        circleImageViewpaypal.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        Adapter adapter = new Adapter(context, datas);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        switch (compoundButton.getId()) {
            case R.id.radioCheckPayment:
                Log.i("kunsangcheck", "radioCheckPayment  " + b);

                if (b) {
                    rippleCheck.setBackgroundColor(ContextCompat.getColor(context, R.color.grey));
                    circleImageViewcheck.setImageResource(R.drawable.ic_radio_custom);
                } else {
                    rippleCheck.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                    circleImageViewcheck.setImageResource(R.drawable.ic_circle);
                }
                break;
            case R.id.radioDirectBankTransfer:
                Log.i("kunsangcheck", "radioDirectBankTransfer  " + b);

                if (b) {
                    rippleTransfer.setBackgroundColor(ContextCompat.getColor(context, R.color.grey));
                    circleImageViewbanktransfer.setImageResource(R.drawable.ic_radio_custom);
                } else {
                    rippleTransfer.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                    circleImageViewbanktransfer.setImageResource(R.drawable.ic_circle);
                }

                break;
            case R.id.radioPaypal:
                Log.i("kunsangcheck", "radioPaypal  " + b);

                if (b) {
                    ripplePaypal.setBackgroundColor(ContextCompat.getColor(context, R.color.grey));
                    circleImageViewpaypal.setImageResource(R.drawable.ic_radio_custom);
                } else {
                    ripplePaypal.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                    circleImageViewpaypal.setImageResource(R.drawable.ic_circle);
                }

                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageviewPaypal:
            case R.id.linearlayoutPayPal:
                radioButtonPayPal.setChecked(true);
                break;
            case R.id.linearlayoutCheck:
            case R.id.imageViewCheck:
                radioButtonCheck.setChecked(true);
                break;
            case R.id.linearlayoutTransfer:
            case R.id.imageViewBankTransfer:
                radioButtonBankTransfer.setChecked(true);
                break;
        }
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
