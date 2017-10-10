package com.aapkatrade.shopping.Editprofile;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.aapkatrade.shopping.Add_to_cart.Add_to_card_activity;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mvc.imagepicker.ImagePicker;
import com.aapkatrade.shopping.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class Edit_profile_activity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    Context context;
    Toolbar toolbar;
    String fname,lname,dob;
    EditText input_fname,input_lname,input_dob;

    private DatePicker datePicker;
    private Calendar calendar;
   // CircleImageView circleImageViewProfilePic;

    private int year, month, day;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_activity);

        context=this;
        setupToolBar("Edit Profile");
        initview();
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            fname =(String) b.get("fname");
            lname =(String) b.get("lname");

            dob=(String) b.get("dob");

            Log.e("Fname value1", fname + lname + dob);
            setdataedittext();


        }
        else{
            Log.e("b null", "b is null");
        }
    }

    private void initview() {
        progressDialog=new ProgressDialog(this);

        input_fname=(EditText)findViewById(R.id.input_fname);
        input_lname=(EditText)findViewById(R.id.input_lname);
        input_dob=(EditText)findViewById(R.id.input_dob);
      /*  circleImageViewProfilePic=(CircleImageView)findViewById(R.id.imageView2);
        circleImageViewProfilePic.setOnClickListener(this);*/
        input_dob.setOnClickListener(this);
       Calendar cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        //input_dob.setText(day+"/"+month+"/"+year);



    }

    private void setdataedittext() {
        input_fname.setText(fname);
        input_lname.setText(lname);




    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editprofile_menu, menu);
        ActionItemBadge.update(((AppCompatActivity) context), menu.findItem(R.id.add_to_cart), ContextCompat.getDrawable(context, R.drawable.ic_cart_black)
                , ActionItemBadge.BadgeStyles.GREY, 3);
        return true;
    }
    private void setupToolBar(String app_name) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(app_name);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                return true;

            case R.id.add_to_cart:
                //finish();
                Intent i =new Intent(Edit_profile_activity.this, Add_to_card_activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.input_dob:
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        Edit_profile_activity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );

                dpd.show(getFragmentManager(), "Datepickerdialog");

                break;
       /*     case R.id.imageView2:
                ImagePicker.pickImage(this, "Select your image:");
                //Intent k = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //startActivityForResult(k, IMAGE_PICKER_SELECT);
//                Intent i=new Intent(getActivity(), Edit_profile_activity.class);
//                startActivity(i);
                break;*/
        }
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(++monthOfYear)+"/"+year;
        input_dob.setText(date);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);

        File imageFile;

        Bitmap bitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data);
        if (data==null)
        {
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), "MyApplication");

            /**Create the storage directory if it does not exist*/
            if (! mediaStorageDir.exists()){
                if (! mediaStorageDir.mkdirs()){

                }
            }

            /**Create a media file name*/
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());


            imageFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".png");
        }
        else{
            Uri selectedImageURI = data.getData();
            imageFile = new File(getRealPathFromURI(selectedImageURI));
            String path = selectedImageURI.getPath();
            Log.e("path", path);
        }







        if (bitmap != null) {
            Log.e("bitmap", bitmap.toString());


            
            Uri tempUri = getImageUri(getApplicationContext(), bitmap);
          String path=  getRealPathFromURI(tempUri);
          File  imageFile2 = new File(getRealPathFromURI(tempUri));
            Log.e("path2", path);
            //circleImageViewProfilePic.setImageBitmap(bitmap);

            String Webservice_image = "https://netforcesales.com/eclipseexpress/web_api.php?type=customer_update";
            progressDialog.show();
            Ion.with(context)
                    .load(Webservice_image)
                    .progressDialog(progressDialog)
                    .setMultipartFile("image", "image/*", imageFile2)

                    .setMultipartParameter("fname", input_fname.getText().toString())
                    .setMultipartParameter("lname", input_lname.getText().toString())
                    .setMultipartParameter("dob", input_dob.getText().toString())
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            if (result == null) {
                                ShowMessage("nothing is happening");
                            } else {
                                Log.e("result_kunsang", result.toString());
                                progressDialog.dismiss();
                               // String status = result.get("status").getAsString();
//                                if (status.equalsIgnoreCase("success")) {
//                                    ShowMessage("Successfully uploaded");
//
//                                } else {
//                                    ShowMessage("Failed to upload data");
//                                }
                            }
                        }
                    });





        } else {
            Log.e("bitmap not null", "bitmap not null");
        }



    }


    private void ShowMessage(String s) {

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


    private String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        Log.e("result", result);
        return result;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }




}
