package com.aapkatrade.shopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.aapkatrade.shopping.R;
import com.aapkatrade.shopping.dashboard.DashboardActivity;
import com.aapkatrade.shopping.login.LoginActivity;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "mJegq8vndKRYDrYAT590xEpcp";
    private static final String TWITTER_SECRET = "uuM0EuSuS8DvXloLfOpmoXUgIf2q1bmD2GPdkS2rjncsPvvxvB";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // do something
                try {
                    PackageInfo info = getPackageManager().getPackageInfo(
                            "com.netforceinfotech.eclipseexpress",
                            PackageManager.GET_SIGNATURES);
                    for (Signature signature : info.signatures) {
                        MessageDigest md = MessageDigest.getInstance("SHA");
                        md.update(signature.toByteArray());
                        Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                    }
                } catch (PackageManager.NameNotFoundException e) {

                } catch (NoSuchAlgorithmException e) {

                }
                sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                String user_id=sharedpreferences.getString("user_id",null);
                if(user_id==null)
                {

                    SharedPreferences.Editor editor=sharedpreferences.edit();
                    editor.putString("user_id", "45543");
                    editor.putString("login_type", "0");
                    editor.commit();
                    Intent intent=new Intent(MainActivity.this, LoginActivity.class);

                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    finish();
                }
                else{
                    Intent intent=new Intent(MainActivity.this, DashboardActivity.class);

                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    finish();
                }

            }
        }, 2000);
    }
}
