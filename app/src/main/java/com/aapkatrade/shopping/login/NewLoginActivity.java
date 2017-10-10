package com.aapkatrade.shopping.login;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aapkatrade.shopping.AndroidUtils;
import com.aapkatrade.shopping.MainActivity;
import com.aapkatrade.shopping.R;
import com.aapkatrade.shopping.dashboard.DashboardActivity;
import com.aapkatrade.shopping.general.Connetivity_check;

import static com.aapkatrade.shopping.Validation.isValidEmail;

public class NewLoginActivity extends AppCompatActivity
{
    EditText etEmail,etPassword;
    RelativeLayout rl_login;
    TextView txtRegister,txtForgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        setuplayout();

    }

    private void setuplayout()
    {
        etEmail = (EditText) findViewById(R.id.etEmail);

        etPassword = (EditText) findViewById(R.id.etPassword);

        rl_login = (RelativeLayout) findViewById(R.id.rl_login);

        txtRegister = (TextView) findViewById(R.id.txtRegister);
        txtRegister.setPaintFlags(txtRegister.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        txtForgetPassword = (TextView) findViewById(R.id.txtForgetPassword);
        txtForgetPassword.setPaintFlags(txtForgetPassword.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);


        rl_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if(Connetivity_check.isNetworkAvailable(NewLoginActivity.this)==true)

                {
                    if (etEmail.getText().length() != 0 || etPassword.getText().length() != 0 )
                    {
                        String email = etEmail.getText().toString();
                        if (isValidEmail(email))
                        {
                            Intent intent=new Intent(NewLoginActivity.this, DashboardActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            overridePendingTransition(R.anim.enter, R.anim.exit);

                        }
                    }
                    else {

                        AndroidUtils.showToast(NewLoginActivity.this,"Can't Leave blank Field");
                    }
                }
                else {

                    AndroidUtils.showToast(NewLoginActivity.this,"Please Connect Internet");
                }
            }
        });


        txtRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent intent=new Intent(NewLoginActivity.this, RegistrationActivity.class);
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
}
