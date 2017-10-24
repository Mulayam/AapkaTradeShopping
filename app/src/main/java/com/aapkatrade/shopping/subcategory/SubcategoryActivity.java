package com.aapkatrade.shopping.subcategory;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.aapkatrade.shopping.AndroidUtils;
import com.aapkatrade.shopping.R;
import com.aapkatrade.shopping.dashboard.navigation.Category_data;
import com.aapkatrade.shopping.dashboard.navigation.ExpandableListAdapter;
import com.aapkatrade.shopping.general.progressbar.ProgressBarHandler;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubcategoryActivity extends AppCompatActivity
{

    ExpandableListView expListView;

    Context context;

    public static ArrayList<Category_data> category_data = new ArrayList<>();

    private HashMap<String, List<String>> listDataChild = null;

    public static ArrayList<Category_data> sub_category_data = new ArrayList<>();

    public static ArrayList<String> sub_categoryName = new ArrayList<>();

    public static ArrayList<String> categoryName = new ArrayList<>();
    private ExpandableListAdapter listAdapter;
    ProgressBarHandler progressBarHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);

        context = SubcategoryActivity.this;

        progressBarHandler = new ProgressBarHandler(context);

        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        callwebservice__category();



    }


    private void callwebservice__category()
    {

         progressBarHandler.show();

        String login_url = context.getResources().getString(R.string.webservice_base_url);

        Ion.with(context)
                .load(login_url)
                .setHeader("authorization", "xvfdbgfdhbfdhtrh54654h54ygdgerwer3")
                .setBodyParameter("authorization", "xvfdbgfdhbfdhtrh54654h54ygdgerwer3")
                .setBodyParameter("type", "get_category")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>()
                {
                    @Override
                    public void onCompleted(Exception e, JsonObject result)
                    {

                        progressBarHandler.hide();
                        if (result!=null)
                        {
                            listDataChild = new HashMap<>();
                            System.out.println("result--------------" + result);
                            JsonObject jsonObject = result.getAsJsonObject("category");

                            JsonArray jsonProductList = jsonObject.getAsJsonArray("children");

                            JsonObject jsonproduct = (JsonObject) jsonProductList.get(0);

                            JsonArray category_menu = jsonproduct.getAsJsonArray("children");

                            System.out.println("category_menu--------------" + category_menu.size());

                            // Log.d("category_menu",category_menu.toString());

                            for (int i = 0; i < 2; i++)
                            {
                                JsonObject menu = (JsonObject) category_menu.get(i);
                                String main_menu_name = menu.get("name").getAsString();
                                String main_menu_id = menu.get("category_id").getAsString();

                                category_data.add(new Category_data(main_menu_id,main_menu_name));
                                categoryName.add(main_menu_name);

                               JsonArray jsonArraysubmenu = menu.getAsJsonArray("children");

                               if (jsonArraysubmenu.size()==0)
                               {

                               }
                               else
                               {

                                   sub_category_data.clear();
                                   sub_categoryName.clear();
                                  // listDataChild.clear();

                                   System.out.println("i-------------"+i+"size--"+jsonArraysubmenu.size());
                                   for (int j = 0; j < jsonArraysubmenu.size(); j++)
                                   {
                                       JsonObject sub_menu = (JsonObject) jsonArraysubmenu.get(j);
                                       String sub_menu_name = sub_menu.get("name").getAsString();
                                       String sub_menu_id = sub_menu.get("category_id").getAsString();

                                       //ArrayList<String> sub_categoryName+i = new ArrayList<>();


                                       sub_category_data.add(new Category_data(sub_menu_name,sub_menu_id));
                                       sub_categoryName.add(sub_menu_name);
                                   }



                               }
                                listDataChild.put(categoryName.get(0), sub_categoryName);
                                listDataChild.put(categoryName.get(1), categoryName);

                            }

                            //initView();
                            listAdapter = new ExpandableListAdapter(context, categoryName, listDataChild);
                            // setting list adapter
                            expListView.setAdapter(listAdapter);

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
