package com.aapkatrade.shopping.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aapkatrade.shopping.AndroidUtils;
import com.aapkatrade.shopping.MainActivity;
import com.aapkatrade.shopping.dashboard.DashboardActivity;
import com.aapkatrade.shopping.general.Connetivity_check;
import com.aapkatrade.shopping.general.progressbar.ProgressBarHandler;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.aapkatrade.shopping.R;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener
{
    RelativeLayout registration;
    EditText Fname,Lname,EmailId,password,confirm_password;
    private Pattern pattern;
    private Matcher matcher;
    TextView txtSign;
    SSLEngine engine;
    private ProgressBarHandler progressBarHandler;
    ProgressDialog _progressDialog;
    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_registration);

        progressBarHandler = new ProgressBarHandler(RegistrationActivity.this);


       /*
        Window window = getWindow();


       // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

         // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // only for gingerbread and newer versions
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.grey));
        }*/



        initview();
    }

    private void initview()
    {

        _progressDialog = new ProgressDialog(this);
        registration=(RelativeLayout) findViewById(R.id.buttonGetstarted);
        Fname=(EditText)findViewById(R.id.etfirstname);
        Lname=(EditText)findViewById(R.id.et_lastname);
        EmailId=(EditText)findViewById(R.id.etEmail);
        password=(EditText)findViewById(R.id.etpassword);
        confirm_password=(EditText)findViewById(R.id.etconfirmpassword);

        txtSign = (TextView) findViewById(R.id.txtSign);

        txtSign.setPaintFlags(txtSign.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

       // Mobile_no=(EditText)findViewById(R.id.etmob);
        registration.setOnClickListener(this);

        txtSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent=new Intent(RegistrationActivity.this, NewLoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {

            case R.id.buttonGetstarted:
                Applyvalidation();
                break;




        }
    }

    private void Applyvalidation()
    {
        if(Connetivity_check.isNetworkAvailable(this)==true)

        {
            if (Fname.getText().length() != 0 || password.getText().length() != 0 || confirm_password.getText().length() != 0 || EmailId.getText().length() != 0  || Lname.getText().length() != 0)
            {
                String email = EmailId.getText().toString();
                if (isValidEmail(email))
                {
                    if (password.getText().toString().equals(confirm_password.getText().toString()))
                    {


                        //callWebServiceForBuyerRegistration();

                    }
                    else
                     {


                        //Showmessage("password don't match");
                        AndroidUtils.showToast(RegistrationActivity.this,"password don't match");
                    }


                }
                else
                {
                   // Showmessage("Invalid Email");
                    AndroidUtils.showToast(RegistrationActivity.this,"Please Enter Valid Email");
                }

            } else {
                AndroidUtils.showToast(RegistrationActivity.this,"Can't Leave blank Field");
               // Showmessage("Can't Leave blank");
            }
        }
        else{
           // Showmessage("Their is no Internet connectivity");
            AndroidUtils.showToast(RegistrationActivity.this,"Please Connect Internet");
        }
    }

   /* private void callWebServiceForBuyerRegistration()
    {
        progressBarHandler.show();

        Ion.with(RegistrationActivity.this)
                .load(getResources().getString(R.string.webservice_base_url) + "/buyerregister")
                .setHeader("authorization", "xvfdbgfdhbfdhtrh54654h54ygdgerwer3")
                .setBodyParameter("authorization", "xvfdbgfdhbfdhtrh54654h54ygdgerwer3")
                .setBodyParameter("type", "registration")
                .setBodyParameter("email", "android")
                .setBodyParameter("firstname", AppConfig.getCurrentDeviceId(context))
                .setBodyParameter("lastname", formBuyerData.getFirstName())
                .setBodyParameter("password", formBuyerData.getLastName())
                .setBodyParameter("website_id", formBuyerData.getCountryId())
                .setBodyParameter("store_id", formBuyerData.getStateId())
                .setBodyParameter("group_id", formBuyerData.getCityId())
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (result != null) {

                            if (result.get("error").getAsString().equals("false")) {


                                Log.e("registration_buyer", result.toString());

                                progressBarHandler.hide();


                            } else {
                                progressBarHandler.hide();
                                AndroidUtils.showSnackBar(registrationLayout, result.get("message").getAsString());
                            }
                        } else {

                            Log.e("result_seller_error", e.toString());
                            showMessage(e.toString());
                        }
                    }

                });



    }*/

    private void Showmessage(String message) {

        Toast.makeText(RegistrationActivity.this,message,Toast.LENGTH_SHORT).show();



    }



    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
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



