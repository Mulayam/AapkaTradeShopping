package com.aapkatrade.shopping.category;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.aapkatrade.shopping.AndroidUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.aapkatrade.shopping.R;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    Context context;
    private RecyclerView recyclerView;
    ArrayList<Data> datas = new ArrayList<>();
    LinearLayout linearLayoutSortby, linearLayoutFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        context = this;
        setupToolBar(getResources().getString(R.string.app_name));
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        initView();
        setupData("1");

    }

    private void initView() {
        linearLayoutFilter = (LinearLayout) findViewById(R.id.linearlayoutFilter);
        linearLayoutSortby = (LinearLayout) findViewById(R.id.linearlayoutSort);
        linearLayoutSortby.setOnClickListener(this);
        linearLayoutFilter.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard, menu);
        ActionItemBadge.update(((AppCompatActivity) context), menu.findItem(R.id.add_to_cart), ContextCompat.getDrawable(context, R.drawable.ic_cart_black)
                , ActionItemBadge.BadgeStyles.GREY, 3);
        return true;
    }


    private void setupData(String pageNumber)
    {
       // progressBarHandler.show();
        Ion.with(CategoryActivity.this)
                .load("http://shopping.aapkatrade.com/webservice.php")
                .setHeader("authorization", "xvfdbgfdhbfdhtrh54654h54ygdgerwer3")
                .setBodyParameter("authorization", "xvfdbgfdhbfdhtrh54654h54ygdgerwer3")
                .setBodyParameter("page", "2")
                .setBodyParameter("type","productlist")
                .setBodyParameter("category_id","140")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                      //  progressBarHandler.hide();
                        if (result != null)
                        {
                            AndroidUtils.showErrorLog(context, "-jsonObject------------" + result.toString());
                            JsonArray jsonProductList = result.getAsJsonArray("products");
                            if (jsonProductList != null && jsonProductList.size() > 0)
                            {
                                for (int i = 0; i < jsonProductList.size(); i++) {
                                    JsonObject jsonproduct = (JsonObject) jsonProductList.get(i);
                                    String productId = jsonproduct.get("id").getAsString();

                                    String productName = jsonproduct.get("name").getAsString();
                                    String category_name = jsonproduct.get("category_name").getAsString();
                                    String productShortDescription = jsonproduct.get("short_description").getAsString();
                                    String price = jsonproduct.get("price").getAsString();
                                    String special_price = jsonproduct.get("special_price").getAsString();

                                    // String unitsname = jsonproduct.get("unitsname").getAsString();
                                    String saving = jsonproduct.get("saving").getAsString();

                                    String productImage = jsonproduct.get("image").getAsString();

                                    datas.add(new Data(productId, productName,category_name, productShortDescription, price,special_price,saving, productImage));
                                }
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                                Adapter adapter = new Adapter(context, datas);
                                recyclerView.setLayoutManager(linearLayoutManager);
                                recyclerView.setAdapter(adapter);
                            }
                        } else {
                            AndroidUtils.showErrorLog(context, "-jsonObject------------NULL RESULT FOUND");
                        }

                    }
                });
    }




    private void setupToolBar(String app_name) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(app_name);

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
            case R.id.linearlayoutSort:
                showSortbyPopup();
                break;
            case R.id.linearlayoutFilter:
                showMessage("Open filter activity");
                break;
        }
    }

    private void showSortbyPopup() {
//        final String[] sortlist = {"Popularity", "Price - Low to High", "Price - High to low", "Newest First"};
//        new MaterialDialog.Builder(this)
//                .title("Sort by")
//                .items(sortlist)
//                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
//                    @Override
//                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
//                        showMessage("clicked:" + sortlist[which]);
//                        return true;
//                    }
//                })
//                .positiveText(R.string.ok)
//                .show();
    }

    private void showMessage(String s) {
        Toast.makeText(CategoryActivity.this, s, Toast.LENGTH_SHORT).show();
    }
}
