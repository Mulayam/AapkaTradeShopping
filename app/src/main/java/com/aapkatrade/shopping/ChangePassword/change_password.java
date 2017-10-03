package com.aapkatrade.shopping.ChangePassword;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.aapkatrade.shopping.Account.Mywishlist.MyWishlist_activity;
import com.aapkatrade.shopping.general.Connetivity_check;
import com.aapkatrade.shopping.login.LoginActivity;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.aapkatrade.shopping.R;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class change_password extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    EditText oldpassword,newpassword,confirmpassword;
    Button Change_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        setupToolBar();
        initview();
    }

    private void initview() {

        oldpassword=(EditText)findViewById(R.id.editText16);
        newpassword=(EditText)findViewById(R.id.editText17);
        confirmpassword=(EditText)findViewById(R.id.editText18);
        Change_password=(Button)findViewById(R.id.btn_changepassword);
        Change_password.setOnClickListener(this);
    }

    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_change_password);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "Change Password";
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
                Intent i =new Intent(change_password.this, MyWishlist_activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

                break;




            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void ShowMessage(String s) {

        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {

            case R.id.btn_changepassword:
                if (Connetivity_check.isNetworkAvailable(this) == true) {
                    if (newpassword.getText().toString().contains(confirmpassword.getText().toString())) {
                        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                        String emailid = sharedpreferences.getString("email", null);
                        if (emailid != null) {
                            setupSelfSSLCert();
                            String Change_password_url = "https://netforcesales.com/eclipseexpress/web_api.php?type=changepassword&email=" + emailid + "&old_password=" + oldpassword.getText().toString() + "&new_password=" + newpassword.getText().toString();
                            Log.e("Change_password_url", Change_password_url);
                            Ion.with(this)
                                    .load(Change_password_url)
                                    .asJsonObject()
                                    .setCallback(new FutureCallback<JsonObject>() {
                                        @Override
                                        public void onCompleted(Exception e, JsonObject result) {
                                            String status = result.get("status").getAsString();


                                            if (status.contains("sussess")) {
                                                ShowMessage("Successfully password change ");
                                                finish();

                                            } else if (status.contains("error")) {
                                                String msg = result.get("message").getAsString();
                                                ShowMessage(msg);
                                            }


                                        }
                                    });


                        }
                    } else {
                        ShowMessage("confirm password and new password don't match");
                    }

                }else{
                    ShowMessage("Their is no internet connection");
                }
                break;


        }

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
