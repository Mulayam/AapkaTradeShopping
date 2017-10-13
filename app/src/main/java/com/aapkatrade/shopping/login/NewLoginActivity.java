package com.aapkatrade.shopping.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aapkatrade.shopping.AndroidUtils;
import com.aapkatrade.shopping.AppSharedPreference;
import com.aapkatrade.shopping.MainActivity;
import com.aapkatrade.shopping.R;
import com.aapkatrade.shopping.dashboard.DashboardActivity;
import com.aapkatrade.shopping.general.Connetivity_check;
import com.aapkatrade.shopping.general.SharedPreferenceConstants;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import static com.aapkatrade.shopping.Validation.isValidEmail;

public class NewLoginActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    RelativeLayout rl_login;
    TextView txtRegister, txtForgetPassword;
    ProgressDialog _progressDialog;
    AppSharedPreference appSharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);
        _progressDialog = new ProgressDialog(this);
        appSharedPreference=new AppSharedPreference(NewLoginActivity.this);
        setuplayout();

    }

    private void setuplayout() {
        etEmail = (EditText) findViewById(R.id.etEmail);

        etPassword = (EditText) findViewById(R.id.etPassword);

        rl_login = (RelativeLayout) findViewById(R.id.rl_login);

        txtRegister = (TextView) findViewById(R.id.txtRegister);
        txtRegister.setPaintFlags(txtRegister.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        txtForgetPassword = (TextView) findViewById(R.id.txtForgetPassword);
        txtForgetPassword.setPaintFlags(txtForgetPassword.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        rl_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Connetivity_check.isNetworkAvailable(NewLoginActivity.this) == true)

                {
                    if (etEmail.getText().length() != 0 || etPassword.getText().length() != 0) {
                        String email = etEmail.getText().toString();
                        if (isValidEmail(email)) {


                            callLoginWebservice(email, etPassword.getText().toString().trim());


                        }
                    } else {

                        AndroidUtils.showToast(NewLoginActivity.this, "Can't Leave blank Field");
                    }
                } else {

                    AndroidUtils.showToast(NewLoginActivity.this, "Please Connect Internet");
                }
            }
        });


        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NewLoginActivity.this, RegistrationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                finish();
            }
        });


        txtForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    private void callLoginWebservice(String useremail, String userpassword) {

        String webserviceLoginUrl = getResources().getString(R.string.webservice_base_url) + "?type=login";


        Ion.with(NewLoginActivity.this).load(webserviceLoginUrl).progressDialog(_progressDialog)
                .setBodyParameter("email", useremail)
                .setBodyParameter("password", userpassword)
                .asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {

                String loginresult = result.get("status").getAsString();
                if (loginresult.contains("valid")) {

                    JsonObject userdata = result.getAsJsonObject("data");
                    String userid=userdata.get("entity_id").getAsString();
                    appSharedPreference.setSharedPref(SharedPreferenceConstants.USER_ID.toString(),userid);
                    Log.e("Response", result.toString());

                    Intent intent = new Intent(NewLoginActivity.this, DashboardActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    overridePendingTransition(R.anim.enter, R.anim.exit);


                } else {
                    Toast.makeText(NewLoginActivity.this, "Server Error", Toast.LENGTH_LONG).show();
                }


            }
        });


    }
}
