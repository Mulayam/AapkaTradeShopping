package com.aapkatrade.shopping.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInstaller;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aapkatrade.shopping.dashboard.DashboardActivity;
import com.aapkatrade.shopping.general.Connetivity_check;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.aapkatrade.shopping.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    TextView textViewRegistration, textViewForgotPassword;
    private Intent intent;
    TwitterLoginButton loginButton;
    Button buttonFacebookCustom;
    Button twitter_button;
    EditText email_edittext, password_edittext;
    ProgressDialog _progressDialog;
    public static String MyPREFERENCES = "Ecllipse";
    SharedPreferences sharedpreferences;
    private LoginButton buttonFacebook;
    private List<String> permissions;
    private Profile profile;
    public CallbackManager mCallbackManager;

    public boolean verify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        setContentView(R.layout.activity_login);
        context = this;
        Window window = getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // only for gingerbread and newer versions
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.grey));
        }
        textViewRegistration = (TextView) findViewById(R.id.textViewRegistration);
        textViewForgotPassword = (TextView) findViewById(R.id.textViewForgotPassword);

        // initialize facebook button
        buttonFacebookCustom = (Button) findViewById(R.id.buttonCustomFB);
        buttonFacebookCustom.setOnClickListener(this);
        buttonFacebook = (LoginButton) findViewById(R.id.login_button);
        mCallbackManager = CallbackManager.Factory.create();
        permissions = new ArrayList<String>();
        permissions.add("email");
        permissions.add("user_birthday");
        buttonFacebook.setReadPermissions(permissions);
        buttonFacebook.registerCallback(mCallbackManager, mCallBack);


        profile = Profile.getCurrentProfile();

        if (profile != null) {
            //   LoginManager.getInstance().logOut();
            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.enter, R.anim.exit);
        }

        twitter_button = (Button) findViewById(R.id.buttonTwitter);

        email_edittext = (EditText) findViewById(R.id.email);
        password_edittext = (EditText) findViewById(R.id.password_edittext);

        twitter_button.setOnClickListener(this);
        TwitterSession twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if (twitterSession != null) {

            twitter_button.setText("Logout");
        } else {

            twitter_button.setText("twitter");

        }

        setupTwitter();
        textViewRegistration.setOnClickListener(this);
        textViewForgotPassword.setOnClickListener(this);
        _progressDialog = new ProgressDialog(this);

        setupSharedpreference();


        findViewById(R.id.buttonLogin).setOnClickListener(this);
    }

    private void setupSharedpreference() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


    }

    private void setupTwitter() {
        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
             /*   TwitterSession session = result.data;
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                System.out.println("msg==========================="+ msg);


                loginButton.setVisibility(View.INVISIBLE);*/


                login(result);


            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });

    }


    public void login(Result<TwitterSession> result) {

//Creating a twitter session with result's data
        TwitterSession session = result.data;

        //Getting the username from session
        final String username = session.getUserName();

        final String Auth_id = session.getAuthToken().token;


        //This code will fetch the profile image URL
        //Getting the account service of the user logged in
        Twitter.getApiClient(session).getAccountService()
                .verifyCredentials(true, false, new Callback<User>() {
                    @Override
                    public void failure(TwitterException e) {
                        //If any error occurs handle it here
                    }

                    @Override
                    public void success(Result<User> userResult) {
                        //If it succeeds creating a User object from userResult.data
                        twitter_button.setText("Logout");
                        User user = userResult.data;
                        Toast.makeText(context, userResult.data.email, Toast.LENGTH_SHORT).show();

                        //Getting the profile image url
                        String profileImage = user.profileImageUrl.replace("_normal", "");
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("user_id", Auth_id);
                        editor.putString("login_type", "2");
                        editor.commit();
                        Log.e("done", "name-->" + username + "url-->" + profileImage);
                        // Toast.makeText(this,"name-->"+username + "url-->"+profileImage,Toast.LENGTH_LONG).show();

                    }
                });
    }

    public void logoutTwitter() {
        TwitterSession twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if (twitterSession != null) {
            ClearCookies(getApplicationContext());
            Twitter.getSessionManager().clearActiveSession();
            Twitter.logOut();
            twitter_button.setText("twitter");
        } else {

            loginButton.performClick();
        }
    }

    public static void ClearCookies(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else {
            CookieSyncManager cookieSyncMngr = CookieSyncManager.createInstance(context);
            cookieSyncMngr.startSync();
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

// Make sure that the loginButton hears the result from any
// Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);

        mCallbackManager.onActivityResult(requestCode,
                resultCode, data);

        Log.e("resultCode", String.valueOf(resultCode));

        if (resultCode == RESULT_OK) {
            loginButton.onActivityResult(requestCode, resultCode, data);

            mCallbackManager.onActivityResult(requestCode,
                    resultCode, data);
            Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();

        } else {

        }


//        Intent i=new Intent(LoginActivity.this,DashboardActivity.class);
//        startActivity(i);
//        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewRegistration:

                intent = new Intent(context, RegistrationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                break;

            case R.id.buttonCustomFB:
                buttonFacebook.performClick();
                break;

            case R.id.buttonLogin:

                String email_data = email_edittext.getText().toString().trim();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                intent = new Intent(context, DashboardActivity.class);
                intent.putExtra("username", "");
                intent.putExtra("mobno", "");
                intent.putExtra("email", "");
                intent.putExtra("lname", "");
                intent.putExtra("dob", "");

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                overridePendingTransition(R.anim.enter, R.anim.exit);
                finish();

             /*   if(Connetivity_check.isNetworkAvailable(this)==true)

                {
                    if (Validation.isEmailAddress(email_edittext, false))

                    {
                        if (email_data.length() != 0 || password_edittext.getText().length() != 0) {

                            _progressDialog.show();

                            String url = "https://netforcesales.com/eclipseexpress/web_api.php?type=login&email=" + email_edittext.getText().toString() + "&password=" + password_edittext.getText().toString();


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
                                                if (status.contains("sussess")) {

                                                    JsonObject userData = (JsonObject) result.get("userData");
                                                    JsonElement message2 = userData.get("customer_id");
                                                    final String message = message2.getAsString();


                                                    final String username = userData.get("firstname").getAsString();
                                                    final String lastname = userData.get("lastname").getAsString();
                                                    final String dob = userData.get("dob").toString();

                                                    final String creditphone = userData.get("creditphone").toString();
                                                    final String email = userData.get("email").getAsString();
//call confirmation mail webservice
                                                    Log.e("userid", message);
//                               boolean confirmation=verifyemail(message);
//                                                if(confirmation==true) {
                                                    String confirmmail_url = "https://netforcesales.com/eclipseexpress/web_api.php?type=customer_check&id=" + message;
                                                    Log.e("confirmmail_url", confirmmail_url);
                                                    setupSelfSSLCert();
                                                    Ion.with(context)
                                                            .load(confirmmail_url)
                                                            .asJsonObject()
                                                            .setCallback(new FutureCallback<JsonObject>() {
                                                                @Override
                                                                public void onCompleted(Exception e, JsonObject result) {


                                                                    String status = result.get("status").toString();
                                                                    if (status.contains("success")) {
                                                                        JsonObject conformation_result = (JsonObject) result.get("result");
                                                                        String Confirmation_jsonelement = conformation_result.get("confirmation").toString();
                                                                        String Confirmation = Confirmation_jsonelement;
                                                                        Log.e("Confirmation", Confirmation);
                                                                        if (Confirmation == null) {
                                                                            ShowMessage("initialy confirm mail and try again");
                                                                        } else {
                                                                            Log.e("confirmation", "true");
                                                                            //add userid in sharedpreference

                                                                            SharedPreferences.Editor editor = sharedpreferences.edit();
                                                                            editor.putString("user_id", message);
                                                                            editor.putString("login_type", "0");
                                                                            editor.putString("email", email);
                                                                            editor.commit();
                                                                            Log.e("customer_id", message);
                                                                            ShowMessage("Sucessfully login");

                                                                        }


                                                                    }
                                                                }
                                                            });

//                                                }
//                                                else{
//
//                                                    ShowMessage("initialy confirm mail and try again");
//                                                }
                                                } else {
                                                    String message = result.get("message").toString();
                                                    ShowMessage(message);
                                                }


                                                //String message = result.get("message").toString();

                                                Log.e("status", "st" + status);

                                                _progressDialog.dismiss();


                                            } else {
                                                Log.e("error", e.toString());
                                            }

//
                                        }
                                    });


                        } else {
                            ShowMessage("can't leave blank");
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                    }
                }
                else{ShowMessage("Their is no internet connection");}*/

                break;

            case R.id.buttonTwitter:
                logoutTwitter();

                break;
            case R.id.textViewForgotPassword:
                intent = new Intent(context, Forgot_password.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                break;

        }

    }


    //

    FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {

        @Override
        public void onSuccess(final LoginResult loginResult) {
            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        public String home;

                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            Log.i("facebook", response.toString());
                            // Application code
                            if (object != null) {
                                //parameters.putString("fields", "id,name,email,gender, birthday,picture ");
                                AccessToken accessToken = loginResult.getAccessToken();
                                Profile profile = Profile.getCurrentProfile();
                                String fbName;
                                try {
                                    fbName = object.getString("name");
                                    //userSessionManager.setName(fbName);
                                } catch (JSONException e) {
                                    fbName = "";
                                    e.printStackTrace();
                                }
                                String fbId;
                                try {
                                    fbId = object.getString("id");
                                    // userSessionManager.setFBID(fbId);
                                } catch (JSONException e) {
                                    fbId = "";
                                    e.printStackTrace();
                                }
                                String fbEmail;
                                try {
                                    fbEmail = object.getString("email");
                                    //userSessionManager.setEmail(fbEmail);
                                } catch (JSONException e) {
                                    fbEmail = "";
                                    e.printStackTrace();
                                }
                                String fbBirthday;
                                try {
                                    fbBirthday = object.getString("birthday");
                                } catch (JSONException e) {
                                    fbBirthday = "";
                                    e.printStackTrace();
                                }
                                String fbGender;
                                try {
                                    fbGender = object.getString("gender");
                                } catch (JSONException e) {
                                    fbGender = "";
                                    e.printStackTrace();
                                }

                                String fbToken = accessToken.getToken();
                                //userSessionManager.setToken(fbToken);
                                String reg_id = "621308328026023";
                                Log.e("fbdata", fbToken + fbName + reg_id + fbEmail);
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString("user_id", fbToken);
                                editor.putString("login_type", "1");
                                editor.commit();
                                login(fbToken, fbName, fbId, reg_id, fbEmail);
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender, birthday");
            request.setParameters(parameters);
            request.executeAsync();

        }


        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {
            Log.e("LoginActivity", error.toString());
        }
    };

    private void login(String fbToken, String fbName, final String fbId, String reg_id, String email) {
        if (Connetivity_check.isNetworkAvailable(this) == true)

        {
            //https://netforcesales.com/ibet_admin/api/services.php?opt=register&email=kunwangyal15@yahoo.com&fb_token=qwerty1&name=Kunsang%20Wangyal&facebook=1&fb_id=1sdfasdf232324&device_id=asdf23232322&reg_id=asdfasdf232324
            String url = getResources().getString(R.string.url);
            String device_id = getDeviceId();
            fbName = fbName.replace(" ", "%20");
            url = url + "/services.php?opt=register&email=" + email + "&name=" + fbName + "&fb_id=" + fbId + "&reg_id=" + reg_id;
            Log.i("result url", url);
            // setHeader();
            //linearLayoutProgress.setVisibility(View.VISIBLE);
            Ion.with(context)
                    .load(url)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            //linearLayoutProgress.setVisibility(View.GONE);


                        }
                    });
        } else {
            ShowMessage("Their is No Internet Connection");
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

    public void setupSelfSSLCert() {
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

    private String getDeviceId() {
        return "asdfasdfsadfd";
    }


}




