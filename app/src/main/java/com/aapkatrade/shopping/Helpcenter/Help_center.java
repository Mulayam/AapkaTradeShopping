package com.aapkatrade.shopping.Helpcenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aapkatrade.shopping.Add_to_cart.Add_to_card_activity;
import com.aapkatrade.shopping.general.Connetivity_check;
import com.aapkatrade.shopping.general.Validation;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.aapkatrade.shopping.R;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Help_center extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    Context context;
    EditText name,email,subject,message;
    RelativeLayout rl_submit;
    private Intent intent;
    ProgressDialog _progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        setupToolBar();
        initview();
        context=this;

    }

    private void initview() {

        name=(EditText)findViewById(R.id.editText20);
        email=(EditText)findViewById(R.id.editText21);
        subject=(EditText)findViewById(R.id.editText22);
        message=(EditText)findViewById(R.id.editText23);
        rl_submit=(RelativeLayout)findViewById(R.id.rl_submit);
        rl_submit.setOnClickListener(this);

    }

    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_help_center);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "Help Center";
        getSupportActionBar().setTitle(teams);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection


        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);


                break;

            case  R.id.add_to_cart:
                //finish();
                Intent i =new Intent(Help_center.this, Add_to_card_activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

                break;


            default:
                return super.onOptionsItemSelected(item);
        }
        return true;






    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard, menu);
        ActionItemBadge.update(((AppCompatActivity) context), menu.findItem(R.id.add_to_cart), ContextCompat.getDrawable(context, R.drawable.ic_cart_black)
                , ActionItemBadge.BadgeStyles.GREY, 3);
        return true;
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.rl_submit:
                if (Connetivity_check.isNetworkAvailable(this) == true) {
                    if (name.getText().length() != 0 || email.getText().length() != 0 || subject.getText().length() != 0 || message.getText().length() != 0) {

                        if (Validation.isEmailAddress(email, false))

                        {

                            String contactus_url = "https://netforcesales.com/eclipseexpress/web_api.php?type=contactus&name=" + name.getText().toString() + "&email=" + email.getText().toString() + "&subject=" + subject.getText().toString() + "&comment=" + message.getText().toString();

                            setupSelfSSLCert();
                            Ion.with(this)
                                    .load(contactus_url)
                                    .progressDialog(_progressDialog)
                                    .asJsonObject()

                                    .setCallback(new FutureCallback<JsonObject>() {
                                        @Override
                                        public void onCompleted(Exception e, JsonObject result) {
                                            if (result != null)

                                            {


                                                String status = result.get("status").toString();
                                                String message = result.get("message").getAsString();
                                                ShowMessage(message);


                                            } else {
                                                Log.e("error", e.toString());
                                            }

//
                                        }
                                    });


                        } else {
                            ShowMessage("Invalid Email Id");
                        }

                    } else {
                        ShowMessage("can't blank any field");
                    }
                }
                else{
                    ShowMessage("Their is no Internet");
                }
        }



    }
    private void ShowMessage(String s) {

        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();

    }




    private static class Trust implements X509TrustManager {

        /**
         * {@inheritDoc}
         */
        @Override
        public void checkClientTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void checkServerTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

    }

    public  void setupSelfSSLCert() {
        final Trust trust = new Trust();
        final TrustManager[] trustmanagers = new TrustManager[]{trust};
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustmanagers, new SecureRandom());
            Ion.getInstance(getApplicationContext(), "rest").getHttpClient().getSSLSocketMiddleware().setTrustManagers(trustmanagers);
            Ion.getInstance(getApplicationContext(), "rest").getHttpClient().getSSLSocketMiddleware().setSSLContext(sslContext);
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (final KeyManagementException e) {
            e.printStackTrace();
        }
    }
}
