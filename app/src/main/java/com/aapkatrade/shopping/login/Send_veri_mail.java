package com.aapkatrade.shopping.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class Send_veri_mail extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Verify_done veri_mail;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText user_email;
    ProgressDialog _progressDialog;


    public Send_veri_mail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Send_veri_mail.
     */
    // TODO: Rename and change types and number of parameters
    public static Send_veri_mail newInstance(String param1, String param2) {
        Send_veri_mail fragment = new Send_veri_mail();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_send_veri_mail, container, false);
        Initview(v);
        // Inflate the layout for this fragment
        return v;
    }

    private void Initview(View v) {
        _progressDialog=new ProgressDialog(getActivity());
        Button send_verify = (Button) v.findViewById(R.id.snd_to_verify);
        send_verify.setOnClickListener(this);
        user_email = (EditText) v.findViewById(R.id.editText19);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.snd_to_verify:
                resetwebservice(user_email.getText().toString());
                //setupforgotFragment();
                break;


        }
    }

    private void resetwebservice(String s) {
        if (Connetivity_check.isNetworkAvailable(getActivity()) == true)

            if (isValidEmail(s)) {
                String url = "https://netforcesales.com/eclipseexpress/web_api.php?type=forget_password&email=" + s;
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
                                    _progressDialog.show();


                                    String status = result.get("status").getAsString();
                                    if (status.contains("success")) {

                                        Intent intent = new Intent(getActivity(), LoginActivity.class);


                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);

                                        String message = result.get("message").getAsString();
                                        Showmessage(message);
                                        _progressDialog.dismiss();
                                    }

                                }
                            }


                        });
            } else {
                Showmessage("Email Address Not Valid");
            }
        else{
            Showmessage("Their is No Internet Connection");
        }



    }
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }




    private void Showmessage(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();

    }

    // TODO: Rename method, update argument and hook method into UI event

    private void replaceFragment(Fragment newFragment, String tag) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_forgot, newFragment, tag);
        transaction.commit();
    }

    private void setupforgotFragment() {
        if (veri_mail == null) {
            veri_mail = new Verify_done();
        }
        String tagName = veri_mail.getClass().getName();
        replaceFragment(veri_mail, tagName);
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
            Ion.getInstance(getActivity(), "rest").getHttpClient().getSSLSocketMiddleware().setTrustManagers(trustmanagers);
            Ion.getInstance(getActivity(), "rest").getHttpClient().getSSLSocketMiddleware().setSSLContext(sslContext);
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (final KeyManagementException e) {
            e.printStackTrace();
        }


    }
}
