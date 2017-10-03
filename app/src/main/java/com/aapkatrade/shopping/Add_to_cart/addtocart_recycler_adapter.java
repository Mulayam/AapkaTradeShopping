package com.aapkatrade.shopping.Add_to_cart;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.aapkatrade.shopping.checkout.CheckoutActivity1;
import com.aapkatrade.shopping.dashboard.dashboardcontentnew.CommomData;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.aapkatrade.shopping.R;
import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abcd on 8/31/2016.
 */
public class addtocart_recycler_adapter extends RecyclerView.Adapter<CommonHolder_addtocard> implements View.OnClickListener {
    Context context;
    ArrayList<CommomData> commomDatas;
    String count_item[]={"1","2","3","4","5","6","7","8","9","More"};
    private EditText editText;
    LinearLayout  linearLayoutQuantity;
    DroppyMenuPopup droppyMenu;
     TextView textViewQuantity;
    public addtocart_recycler_adapter(Context context) {
        this.context = context;

    }

    @Override
    public CommonHolder_addtocard onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addtocard_single_row, parent, false);
        CommonHolder_addtocard viewHolder = new CommonHolder_addtocard(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonHolder_addtocard holder, int position) {


        textViewQuantity=holder.tvquantity;
        linearLayoutQuantity=holder.linearLayoutQuantity;
        holder.linearLayoutQuantity.setOnClickListener(this);
        final DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(context, holder.linearLayoutQuantity);
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





        droppyMenu = droppyBuilder.build();

        List<String> count = new ArrayList<String>();
        count.add("1");
        count.add("2");
        count.add("3");
        count.add("4");
        count.add("5");
        count.add("6");
        count.add("7");
        count.add("8");
        count.add("9");
        count.add("10");
        count.add("More");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,R.layout.spinner_layout,count );

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        holder.spinner.setAdapter(dataAdapter);
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, CategoryActivity.class);
//                context.startActivity(intent);
                ((AppCompatActivity)context).overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });




    }

    @Override
    public int getItemCount() {
        return 6;
    }


    private void showPopup(String description) {
        boolean wrapInScrollView = true;
        MaterialDialog dialog = new MaterialDialog.Builder(context)
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
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
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
               context.startActivity(intent);
                break;
        }
    }



}