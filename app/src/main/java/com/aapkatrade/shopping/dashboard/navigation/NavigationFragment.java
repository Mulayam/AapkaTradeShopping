package com.aapkatrade.shopping.dashboard.navigation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aapkatrade.shopping.Account.User_account;
import com.aapkatrade.shopping.ChangePassword.change_password;
import com.aapkatrade.shopping.Editprofile.Edit_profile_activity;
import com.aapkatrade.shopping.Helpcenter.Help_center;
import com.aapkatrade.shopping.ProductCategory.Productlist_category_activity;
import com.aapkatrade.shopping.R;
import com.aapkatrade.shopping.general.Connetivity_check;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends Fragment implements View.OnClickListener, ExpandableListAdapter.clickListner {

    public static final String preFile = "textFile";
    public static final String userKey = "key";
    private static final String TAG = "gcm_tag";
    private static int POSITION = 0;
    public static ActionBarDrawerToggle mDrawerToggle;
    public static DrawerLayout mDrawerLayout;
    boolean mUserLearnedDrawer;
    private static final int IMAGE_PICKER_SELECT = 999;
    boolean mFromSavedInstance;
    View view;
    String Fname,Lname,Dob;

    public static final String PREFS_NAME = "call_recorder";
    private SharedPreferences loginPreferences;
    List<String> categoryids;
    List<String> categoryname;


    public static SharedPreferences.Editor loginPrefsEditor;
    private Context context;
    TextView footer;
    RelativeLayout header;
    public static  CircleImageView circleImageViewProfilePic;
    TextView textViewName,textViewmobile_no,textView_email;
    private ImageView imageViewGB;
    private ExpandableListView expListView;
    private ImageView edit_profile_imgview;
    private ExpandableListAdapter listAdapter;
    private ArrayList<String> listDataHeader = null;
    private HashMap<String, List<String>> listDataChild;
    ProgressDialog _progressDialog;

    public NavigationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_navigation, container, false);
        _progressDialog=new ProgressDialog(context);
        initView();
        return view;
    }

    private void initView() {
        //prepare textviewdata
        categoryname=new ArrayList<>();
        categoryids=new ArrayList<>();
        textViewName=(TextView)view.findViewById(R.id.tv_username);
        textViewmobile_no=(TextView)view.findViewById(R.id.tv_mobno);
        textView_email=(TextView)view.findViewById(R.id.tv_email_profile);

        //sharedprefrance
        loginPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);
        edit_profile_imgview=(ImageView)view.findViewById(R.id.img_edit_profile);
        edit_profile_imgview.setOnClickListener(this);
//circle imageview

        circleImageViewProfilePic=(CircleImageView)view.findViewById(R.id.imageViewDP);
        circleImageViewProfilePic.setOnClickListener(this);
       //ImagePicker.setMinQuality(600, 600);




        // preparing list data
        if(Connetivity_check.isNetworkAvailable(getActivity())==true)
        { /*prepareListData();*/


            listDataHeader = new ArrayList<>();
            listDataChild = new HashMap<>();

            // Adding child data
            listDataHeader.add("Category");
            listDataHeader.add("Account");
            listDataHeader.add("Settings");
            listDataHeader.add("Rate App");
            listDataHeader.add("Share App");
            listDataHeader.add("Help Centre");

            // Adding child data
            List<String> top250 = new ArrayList<String>();
            top250.add("Woman's Clothings");
            top250.add("Man's Clothings");
            top250.add("Electronics");
            top250.add("Home and Garden");
            top250.add("Jwellery and Health");
            top250.add("Automotive");
            top250.add("Beauty and Health");
            top250.add("Toys, Kids and Baby");
            top250.add("Bags and Shoes");
            top250.add("Sports and Outdoor");
            top250.add("Phone and Accessories");
            top250.add("Computer and Networking");
            top250.add("VIEW ALL CATEGORIES");

            List<String> Settings_data = new ArrayList<String>();
            Settings_data.add("Subscription");
            Settings_data.add("Change Password");

            List<String> account = new ArrayList<String>();
            List<String> ratethisapp = new ArrayList<String>();
            List<String> help_center = new ArrayList<String>();
            List<String> share_app = new ArrayList<String>();

            listDataChild.put(listDataHeader.get(0), categoryname); // Header, Child data
            listDataChild.put(listDataHeader.get(1), account); // Header, Child data
            listDataChild.put(listDataHeader.get(2), Settings_data);
            listDataChild.put(listDataHeader.get(3), ratethisapp); // Header, Child data
            listDataChild.put(listDataHeader.get(4), help_center); // Header, Child data
            listDataChild.put(listDataHeader.get(5), share_app);











        }
        else
        {
            Showmessage("Their is no internet connection");
        }

        listAdapter = new ExpandableListAdapter(context, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        listAdapter.setClickListner(this);
    }

    @Override
    public void itemClicked(View view, int groupview, int childview) {
        try {
            //showMessage("groupbiew: " + groupview + "\nchildview: " + childview);
            if((groupview==1)&(childview==0))
            {
                Intent i=new Intent(getActivity(), User_account.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);

            }
            else if(groupview==0)
            {
                Intent i=new Intent(getActivity(), Productlist_category_activity.class);
                startActivity(i);
            }
            else if(groupview==2 &childview==1)
            {
                Intent i=new Intent(getActivity(),change_password.class);
                startActivity(i);
            }
            else if(groupview==2 &childview==0)
            {
              // callSubscribedwebservice();
            }
            else if(groupview==5)
            {
                Intent i=new Intent(getActivity(),Help_center.class);
                startActivity(i);
            }

            listAdapter.notifyDataSetChanged();
            mDrawerLayout.closeDrawers();


        } catch (Exception e) {

        }


    }




//    private void prepareListData() {
//
//        if (Connetivity_check.isNetworkAvailable(getActivity()) == true)
//
//        {
//            String url = "https://netforcesales.com/eclipseexpress/web_api.php?type=category";
//            _progressDialog.show();
//
//            setupSelfSSLCert();
//            Ion.with(this)
//                    .load(url)
//                    .progressDialog(_progressDialog)
//                    .asJsonObject()
//
//                    .setCallback(new FutureCallback<JsonObject>() {
//                        @Override
//                        public void onCompleted(Exception e, JsonObject result) {
//                            if (result != null)
//
//                            {
//
//
//                                String status = result.get("status").toString();
//                                if (status.contains("success")) {
//                                    JsonObject categories = result.getAsJsonObject("result");
//
//
//                                    JsonArray personen = categories.getAsJsonArray("categories");
//                                    for (int i = 0; i < personen.size(); i++) {
//                                        JsonObject user = personen.get(i).getAsJsonObject();
//                                        String category_name = user.get("name").getAsString();
//                                        String category_id = user.get("category_id").getAsString();
//                                        categoryids.add(category_id);
//                                        categoryname.add(category_name);
//
//                                    }
//
//                                    Log.e("categoryids", categoryids.toString());
//                                    Log.e("categoryname", categoryname.toString());
//
//
//                                }
//
//                                _progressDialog.dismiss();
//
//                            } else {
//                                Log.e("error", e.toString());
//                            }
//
////                                        JsonObject js =result;
////
////                                        String status=result.get("status").toString();
////                                        String customer_id=result.get("customer_id").toString();
////                                        String message=result.get("message").toString();
////                                        Log.e("status","st"+status+"cust"+customer_id+"mes"+message);
//
//
//                            // do stuff with the result or error
//                        }
//                    });

//        }
//        else {
//            Showmessage("Their is no Internet Connection");
//        }
//
//    }

    private void Showmessage(String message) {


        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), userKey, "false"));
        if (savedInstanceState != null) {
            mFromSavedInstance = true;
        }
    }

    public void setup(int id, final DrawerLayout drawer, Toolbar toolbar) {
        view = getActivity().findViewById(id);

        mDrawerLayout = drawer;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawer, toolbar, R.string.drawer_open, R
                .string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                hideSoftKeyboard(getActivity());

                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    savedInstances(getActivity(), userKey, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();

            }

        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDrawerLayout.closeDrawers();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public static void savedInstances(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharePreference = context.getSharedPreferences(preFile, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePreference.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharePreference = context.getSharedPreferences(preFile, Context.MODE_PRIVATE);
        return sharePreference.getString(preferenceName, defaultValue);
    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_edit_profile:
                Intent i=new Intent(getActivity(), Edit_profile_activity.class);
                if(Fname!=null) {
                    i.putExtra("fname", Fname);
                    i.putExtra("lname", Lname);
                    i.putExtra("dob", Dob);

                }
                else{


                }

                startActivity(i);
                break;

        }
    }

    private void showMessage(String clicked) {
        Toast.makeText(context, clicked, Toast.LENGTH_SHORT).show();
    }

    private void replaceFragment(Fragment newFragment, String tag) {

        FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.drawer_layout, newFragment, tag);
        transaction.commit();
    }

    public void setdata(String username, String mobno, String email,String Lastname,String dob) {
        Fname=username;
        Lname=Lastname;
        Dob=dob;

        textViewName.setText(username);
        textViewmobile_no.setText(mobno);
        textView_email.setText(email);



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
            Ion.getInstance(context, "rest").getHttpClient().getSSLSocketMiddleware().setTrustManagers(trustmanagers);
            Ion.getInstance(context, "rest").getHttpClient().getSSLSocketMiddleware().setSSLContext(sslContext);
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (final KeyManagementException e) {
            e.printStackTrace();
        }
    }










}




