package com.aapkatrade.shopping.add_cart;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.aapkatrade.shopping.AndroidUtils;
import com.aapkatrade.shopping.R;
import com.aapkatrade.shopping.general.AppSharedPreference;
import com.aapkatrade.shopping.general.SharedPreferenceConstants;
import com.aapkatrade.shopping.general.progressbar.ProgressBarHandler;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import java.util.ArrayList;


public class MyCartActivity extends AppCompatActivity
{

    ArrayList<CartData> cartDataArrayList = new ArrayList<>();
    private Context context;
    private ImageView locationImageView;
    public static TextView tvContinue, tvPriceItemsHeading, tvPriceItems, tvLastPayableAmount, tvAmountPayable;
    RecyclerView mycartRecyclerView;
    CartAdapter cartAdapter;
    private ProgressBarHandler progressBarHandler;
    public static CardView cardviewProductDeatails, cardBottom;
    AppSharedPreference app_sharedpreference;
    private int page = 1;
    LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_cart);

        context = MyCartActivity.this;

        progressBarHandler = new ProgressBarHandler(context);

        app_sharedpreference = new AppSharedPreference(MyCartActivity.this);

        initView();

        setup_layout();

        mycartRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItemCount = linearLayoutManager.findLastVisibleItemPosition();
                if (totalItemCount > 0) {
                    if ((totalItemCount - 1) == lastVisibleItemCount) {
                        page = page + 1;
                        cartList(String.valueOf(page));
                    }
                }
            }
        });


    }

   /* private void setuptoolbar()
    {
        AppCompatImageView homeIcon = (AppCompatImageView) findViewById(R.id.logoWord);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyCartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

        });

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("null");
            getSupportActionBar().setElevation(0);
        }


    }*/


    private void initView()
    {
        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        cardviewProductDeatails = (CardView) findViewById(R.id.cardviewProductDeatails);

        cardBottom = (CardView) findViewById(R.id.cardBottom);

        locationImageView = (ImageView) findViewById(R.id.locationImageView);

        tvLastPayableAmount = (TextView) findViewById(R.id.tvLastPayableAmount);

        tvPriceItemsHeading = (TextView) findViewById(R.id.tvPriceItemsHeading);

        tvAmountPayable = (TextView) findViewById(R.id.tvAmountPayable);

        tvPriceItems = (TextView) findViewById(R.id.tvPriceItems);


        tvContinue = (TextView) findViewById(R.id.tvSaveButton);

        tvLastPayableAmount = (TextView) findViewById(R.id.tvLastPayableAmount);


        tvContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });

        AndroidUtils.setImageColor(locationImageView, context, R.color.green);

        /* checkDeliveryButton = (TextView) findViewById(R.id.checkDeliveryButton);

        buttonContainer = (RelativeLayout) findViewById(R.id.buttonContainer);
       AndroidUtils.setBackgroundSolid(buttonContainer, context, R.color.orange, 25, GradientDrawable.RECTANGLE);
      */


    }

    private void setup_layout()
    {
        mycartRecyclerView = (RecyclerView) findViewById(R.id.order_list);
        cartList("0");
    }



    private void cartList(String pageNumber)
    {
        //cartDataArrayList.clear();
        progressBarHandler.show();

        Ion.with(MyCartActivity.this)
                .load(getResources().getString(R.string.webservice_base_url))
                .setHeader("authorization", "xvfdbgfdhbfdhtrh54654h54ygdgerwer3")
                .setBodyParameter("cart_id", app_sharedpreference.getSharedPref(SharedPreferenceConstants.CART_ID.toString(), ""))
                .setBodyParameter("page", pageNumber)
                .setBodyParameter("type", "cart_list")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        AndroidUtils.showErrorLog(context, "my_cart--------", result);

                        if (result != null)
                        {

                            JsonObject jsonObject = result.getAsJsonObject("result");

                            String cart_count = jsonObject.get("total_qty").getAsString();
                            String total_amount = jsonObject.get("total_amount").getAsString();

                            tvPriceItemsHeading.setText(new StringBuilder("Price ( ").append(cart_count).append(" Items )"));
                            tvPriceItems.setText(new StringBuilder(context.getResources().getText(R.string.rupay_text)).append(" ").append(total_amount));
                            tvAmountPayable.setText(new StringBuilder(context.getString(R.string.rupay_text)).append(" ").append(total_amount));
                            tvLastPayableAmount.setText(new StringBuilder(context.getString(R.string.rupay_text)).append(" ").append(total_amount));

                            JsonArray jsonProductList = jsonObject.getAsJsonArray("items");

                            if (jsonProductList != null && jsonProductList.size() > 0) {

                                for (int i = 0; i < jsonProductList.size(); i++) {
                                    JsonObject jsonproduct = (JsonObject) jsonProductList.get(i);
                                    String Id = jsonproduct.get("id").getAsString();
                                    String productName = jsonproduct.get("name").getAsString();
                                    String productqty = jsonproduct.get("quantity").getAsString();
                                    String price = jsonproduct.get("price").getAsString();
                                    String subtotal_price = jsonproduct.get("sub_total").getAsString();
                                     String shop_name = jsonproduct.get("shop_name").getAsString();
                                    System.out.println("price--------------------" + price);
                                    String productImage = jsonproduct.get("image_url").getAsString();
                                    String product_id = jsonproduct.get("product_id").getAsString();
                                    cartDataArrayList.add(new CartData(Id, productName, productqty, price, productImage, product_id, subtotal_price, "", "",shop_name));


                                }
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                mycartRecyclerView.setLayoutManager(mLayoutManager);
                                cartAdapter = new CartAdapter(context, cartDataArrayList);
                                mycartRecyclerView.setAdapter(cartAdapter);
                                cardviewProductDeatails.setVisibility(View.VISIBLE);
                                cardBottom.setVisibility(View.VISIBLE);
                                progressBarHandler.hide();

                            } else {

                                progressBarHandler.hide();

                                AndroidUtils.showErrorLog(context, "-jsonObject------------NULL RESULT FOUND");
                            }

                        }
                    }
                });
    }
}




