package com.aapkatrade.shopping.login;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.aapkatrade.shopping.general.Connetivity_check;
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

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{
    Button registration;
    EditText Fname,Lname,EmailId,password,confirm_password,Mobile_no;
    private Pattern pattern;
    private Matcher matcher;
    SSLEngine engine;
    ProgressDialog _progressDialog;
    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Window window = getWindow();


// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // only for gingerbread and newer versions
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.grey));
        }
        initview();
    }

    private void initview() {
        _progressDialog = new ProgressDialog(this);
        registration=(Button)findViewById(R.id.buttonGetstarted);
        Fname=(EditText)findViewById(R.id.etfirstname);
        Lname=(EditText)findViewById(R.id.et_lastname);
        EmailId=(EditText)findViewById(R.id.etEmail);
        password=(EditText)findViewById(R.id.etpassword);
        confirm_password=(EditText)findViewById(R.id.etconfirmpassword);
        Mobile_no=(EditText)findViewById(R.id.etmob);
        registration.setOnClickListener(this);


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

    private void Applyvalidation() {
        if(Connetivity_check.isNetworkAvailable(this)==true)

        {
            if (Fname.getText().length() != 0 || password.getText().length() != 0 || confirm_password.getText().length() != 0 || EmailId.getText().length() != 0 || Mobile_no.getText().length() != 0 || Lname.getText().length() != 0) {
                String email = EmailId.getText().toString();
                if (isValidEmail(email)) {
                    if (password.getText().toString().equals(confirm_password.getText().toString())) {
                        if (Mobile_no.getText().length() < 10) {

                            Showmessage("Enter 10 digit mobile no");
                        } else {

                            _progressDialog.show();

                            String url = "https://netforcesales.com/eclipseexpress/web_api.php?type=registration&email=" + EmailId.getText().toString() + "&firstname=" + Fname.getText().toString() + "" + "&lastname=" + Lname.getText().toString() + "" +
                                    "&password=" + password.getText().toString() + "&mobile_no=" + Mobile_no.getText().toString() + "&website_id=1&store_id=1&group_id=1";


                            Log.e("url", url);
                            setupSelfSSLCert();
                            Ion.with(this)
                                    .load(url)
                                    .progressDialog(_progressDialog)
                                    .asJsonObject()

                                    .setCallback(new FutureCallback<JsonObject>() {
                                        @Override
                                        public void onCompleted(Exception e, JsonObject result) {
                                            if (result != null)

                                            {


                                                String status = result.get("status").toString();
                                                // String customer_id = result.get("customer_id").toString();
                                                String message = result.get("message").toString();

                                                Log.e("status", "st" + status + "mes" + message);
                                                Showmessage(message);
                                                _progressDialog.dismiss();

                                            } else {
                                                Log.e("error", e.toString());
                                            }

//                                        JsonObject js =result;
//
//                                        String status=result.get("status").toString();
//                                        String customer_id=result.get("customer_id").toString();
//                                        String message=result.get("message").toString();
//                                        Log.e("status","st"+status+"cust"+customer_id+"mes"+message);


                                            // do stuff with the result or error
                                        }
                                    });
                        }
                    } else {


                        Showmessage("password don't match");
                    }


                } else {
                    Showmessage("Invalid Email");
                }

            } else {
                Showmessage("Can't Leave blank");
            }
        }
        else{
            Showmessage("Their is no Internet connectivity");
        }
    }

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



