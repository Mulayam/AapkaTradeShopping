package com.aapkatrade.shopping.productdetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.aapkatrade.shopping.Add_to_cart.Add_to_card_activity;
import com.aapkatrade.shopping.AndroidUtils;
import com.aapkatrade.shopping.checkout.CheckoutActivity1;
import com.aapkatrade.shopping.general.AppSharedPreference;
import com.aapkatrade.shopping.general.SharedPreferenceConstants;
import com.aapkatrade.shopping.general.progressbar.ProgressBarHandler;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.aapkatrade.shopping.R;
import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;

import java.util.ArrayList;

import at.blogc.android.views.ExpandableTextView;
import me.relex.circleindicator.CircleIndicator;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewPager;
    private ViewPagerAdapter adapter;
    ArrayList<String> arrayList = new ArrayList<>();
    LinearLayout linearLayoutQuantity;
    DroppyMenuPopup droppyMenu;
    public EditText editText;
    TextView textViewQuantity;
    ExpandableTextView expandableTextView;
    CardView cardviewDescription, cardViewTag, cardViewReview;
    ImageView imageViewPlus;
    Context context;
    RadioGroup radioGroupColor, radioGroupSize;
    private Toolbar toolbar;
    LinearLayout linearLayoutAdd_to_cart;
    AppSharedPreference appSharedPreference;
    ProgressBarHandler progressBarHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        context = ProductDetailActivity.this;

        appSharedPreference = new AppSharedPreference(context);
        progressBarHandler = new ProgressBarHandler(context);
        context = this;
        setupToolBar("Product Detail");
        initview();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard, menu);
        ActionItemBadge.update(((AppCompatActivity) context), menu.findItem(R.id.add_to_cart), ContextCompat.getDrawable(context, R.drawable.ic_cart_black)
                , ActionItemBadge.BadgeStyles.GREY, 3);
        return true;
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

        linearLayoutAdd_to_cart = (LinearLayout) findViewById(R.id.linearLayoutAdd_to_cart);


        linearLayoutAdd_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        radioGroupColor = (RadioGroup) findViewById(R.id.radioGroupColor);
        radioGroupSize = (RadioGroup) findViewById(R.id.radioGroupSize);
        linearLayoutQuantity = (LinearLayout) findViewById(R.id.linearlayoutQuantity);
        expandableTextView = (ExpandableTextView) findViewById(R.id.expandableTextView);
        expandableTextView.setText(Html.fromHtml("<b>" + "Description \n" + "</b>" + "<br />") + "this is just random");
        textViewQuantity = (TextView) findViewById(R.id.textViewQuantity);
        cardviewDescription = (CardView) findViewById(R.id.cardviewDescription);
        imageViewPlus = (ImageView) findViewById(R.id.imageViewPlus);
        cardViewReview = (CardView) findViewById(R.id.cardviewReview);
        cardViewTag = (CardView) findViewById(R.id.cardviewTag);
        cardViewReview.setOnClickListener(this);
        cardViewTag.setOnClickListener(this);
        cardviewDescription.setOnClickListener(this);
        findViewById(R.id.buttonBuyNow).setOnClickListener(this);
        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(this, linearLayoutQuantity);
        droppyBuilder.addMenuItem(new DroppyMenuItem("1"))
                .addMenuItem(new DroppyMenuItem("2"))
                .addMenuItem(new DroppyMenuItem("3"))
                .addMenuItem(new DroppyMenuItem("4"))
                .addMenuItem(new DroppyMenuItem("5"))
                .addSeparator()
                .addMenuItem(new DroppyMenuItem("More"));
        droppyBuilder.setOnClick(new DroppyClickCallbackInterface() {
            @Override
            public void call(View v, int id) {
                switch (id) {
                    case 0:
                        textViewQuantity.setText("1");
                        break;
                    case 1:
                        textViewQuantity.setText("2");
                        break;
                    case 2:
                        textViewQuantity.setText("3");
                        break;
                    case 3:
                        textViewQuantity.setText("4");
                        break;
                    case 4:
                        textViewQuantity.setText("5");
                        break;
                    case 5:
                        showPopup("Quantity");
                        break;


                }
            }
        });
        linearLayoutQuantity.setOnClickListener(this);
        droppyMenu = droppyBuilder.build();
        viewPager = (ViewPager) findViewById(R.id.viewpager_custom);
        arrayList.add("http://loremflickr.com/320/380");
        arrayList.add("http://loremflickr.com/320/380/dog");
        arrayList.add("http://loremflickr.com/g/320/380/paris");
        arrayList.add("http://loremflickr.com/320/380/rio");
        adapter = new ViewPagerAdapter(this, arrayList);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator_custom);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        String color[] = {"#000000", "#ffffff", "#eeeeee", "#0f0fee", "#456789"};
        initcolor(color);
        int[] sizes = {12, 14, 16, 18, 20, 22, 24};
        initSize(sizes);


    }

    private void initSize(final int[] sizes) {
        final ArrayList<Integer> sizeIds = new ArrayList<>();
        for (int row = 0; row < 1; row++) {
            for (int i = 0; i < sizes.length; i++) {
                final RadioButton rdbtn = new RadioButton(context);
                rdbtn.setId(i);
                sizeIds.add(i);
                rdbtn.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.customradiosize, null));
                int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
                RadioGroup.LayoutParams param = new RadioGroup.LayoutParams(height, height);
                int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
                param.setMargins(margin, margin, margin, margin);
                rdbtn.setLayoutParams(param);
                rdbtn.setButtonDrawable(R.color.transparent);

                // android:button="@android:color/transparent"
                rdbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            int position = rdbtn.getId();
                            showMessage("size" + sizes[position]);
                        } else {

                        }
                    }
                });
                radioGroupSize.addView(rdbtn);
            }
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlayoutTextViewSize);
        for (int row = 0; row < 1; row++) {

            for (int i = 0; i < sizes.length; i++) {
                final TextView rdbtn = new TextView(context);
                rdbtn.setId((row * 2) + i + 1);
                int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(height, height);
                int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
                param.setMargins(margin, margin, margin, margin);
                rdbtn.setLayoutParams(param);
                rdbtn.setText("" + sizes[i]);
                rdbtn.setGravity(Gravity.CENTER);
                rdbtn.setBackground(ResourcesCompat.getDrawable(getResources(), R.color.transparent, null));
                linearLayout.addView(rdbtn);
            }
        }

    }

    private void initcolor(final String[] color) {
        ArrayList<Integer> colorids = new ArrayList<>();
        for (int row = 0; row < 1; row++) {

            for (int i = 0; i < color.length; i++) {
                final RadioButton rdbtn = new RadioButton(context);
                rdbtn.setId(i);
                colorids.add((row * 2) + i + 1);
                rdbtn.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.customradio, null));
                int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
                RadioGroup.LayoutParams param = new RadioGroup.LayoutParams(height, height);
                int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
                param.setMargins(margin, margin, margin, margin);
                rdbtn.setLayoutParams(param);
                rdbtn.setButtonDrawable(R.color.transparent);

                // android:button="@android:color/transparent"
                rdbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            int position = rdbtn.getId();
                            showMessage(color[position]);
                        } else {

                        }
                    }
                });
                radioGroupColor.addView(rdbtn);
            }
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlayoutViewColor);
        for (int row = 0; row < 1; row++) {

            for (int i = 0; i < color.length; i++) {
                final View rdbtn = new View(context);
                rdbtn.setId((row * 2) + i + 1);
                int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(height, height);
                int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
                param.setMargins(margin, margin, margin, margin);
                rdbtn.setLayoutParams(param);
                rdbtn.setBackgroundColor(Color.parseColor(color[i]));
                linearLayout.addView(rdbtn);
            }
        }

    }


    private void showPopup(String description) {
        boolean wrapInScrollView = true;
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("Quantity")
                .customView(R.layout.more, wrapInScrollView)
                .positiveText(R.string.ok)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (editText.getText().length() <= 0) {
                            showMessage("Nothing done");
                        } else {
                            showMessage(editText.getText().toString());
                            textViewQuantity.setText(editText.getText().toString());
                        }
                        dialog.dismiss();
                    }
                })
                .show();
        editText = (EditText) dialog.findViewById(R.id.editText);

    }

    private void showMessage(String s) {
        Toast.makeText(ProductDetailActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cardviewDescription:
                showMessage("description page will open");
                break;
            case R.id.cardviewTag:
                showMessage("Tag page will open");
                break;
            case R.id.cardviewReview:
                showMessage("Review page will open");
                break;
            case R.id.buttonBuyNow:
                Intent intent = new Intent(context, CheckoutActivity1.class);
                startActivity(intent);
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

            case R.id.add_to_cart:
                //finish();
                Intent i =new Intent(ProductDetailActivity.this, Add_to_card_activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                return true;



            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void callwebservice__add_tocart(String product_id, String qty)
    {
        progressBarHandler.show();

        String login_url = context.getResources().getString(R.string.webservice_base_url);

        String user_id = appSharedPreference.getSharedPref(SharedPreferenceConstants.USER_ID.toString(), "notlogin");

        if (user_id.equals("notlogin"))
        {
            user_id="";
        }

        Ion.with(context)
                .load(login_url)
                .setHeader("authorization", "xvfdbgfdhbfdhtrh54654h54ygdgerwer3")
                .setBodyParameter("authorization", "xvfdbgfdhbfdhtrh54654h54ygdgerwer3")
                .setBodyParameter("type", "cart_add")
                .setBodyParameter("pid", product_id)
                .setBodyParameter("qty",qty)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>()
                {
                    @Override
                    public void onCompleted(Exception e, JsonObject result)
                    {

                        if (result!=null)
                        {
                            System.out.println("result--------------" + result);
                            String message = result.get("message").getAsString();
                            JsonObject jsonObject = result.getAsJsonObject("result");

                            if (message.equals("Product added to cart"))
                            {
                                AndroidUtils.showToast(context, "Product Successfully Added on Cart.");
                                String cart_count = jsonObject.get("total_qty").getAsString();
                                appSharedPreference.setSharedPrefInt(SharedPreferenceConstants.CART_COUNT.toString(), Integer.valueOf(cart_count));

                                //int j = appSharedPreference.getSharedPrefInt("cart_count",0);
                                //ShopAllProductActivity.tvCartCount.setText(String.valueOf(appSharedPreference.getSharedPrefInt(SharedPreferenceConstants.CART_COUNT.toString(), 0)));
                                progressBarHandler.hide();

                            }
                            else
                            {

                                progressBarHandler.hide();
                                AndroidUtils.showToast(context, message);
                            }

                        }
                        else
                        {
                            progressBarHandler.hide();
                            AndroidUtils.showToast(context, "Server is not responding. Please try again.");
                        }

                    }
                });

    }

}
