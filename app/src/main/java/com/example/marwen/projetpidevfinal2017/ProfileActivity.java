package com.example.marwen.projetpidevfinal2017;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
EditText editText,editText1;
    Button button,button1,button2,button3;
    ImageView imageView,imageView1,imageView2,profileimg;
    View v;
    Bitmap bitmap;
    ProgressDialog progressDialog ;
    String ImageName = "image_name" ;

    String ImagePath = "image_path" ;

    String ServerUploadPath ="http://192.168.1.9/miniprojet/public/setimg" ;
    String email = "marwen1609@gmail.com" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        checkpic();

        profileimg = (ImageView) findViewById(R.id.profileimg) ;
        editText=(EditText) findViewById(R.id.editText9);
        editText1=(EditText) findViewById(R.id.editText3);
        button=(Button) findViewById(R.id.button6);
        button1=(Button) findViewById(R.id.button7);
        imageView=(ImageView) findViewById(R.id.imageView19) ;
        imageView1=(ImageView) findViewById(R.id.imageView20) ;
        imageView2=(ImageView) findViewById(R.id.imageView21) ;
        button2=(Button) findViewById(R.id.button8);
        button3=(Button) findViewById(R.id.button9);
        editText.setVisibility(v.INVISIBLE);
        editText1.setVisibility(v.INVISIBLE);
        button1.setVisibility(v.INVISIBLE);
        button2.setVisibility(v.INVISIBLE);
        button3.setVisibility(v.INVISIBLE);
        imageView.setVisibility(v.INVISIBLE);
        imageView1.setVisibility(v.INVISIBLE);
        imageView2.setVisibility(v.INVISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(v.INVISIBLE);
                editText.setVisibility(v.VISIBLE);
                editText1.setVisibility(v.VISIBLE);
                button1.setVisibility(v.VISIBLE);
                button2.setVisibility(v.VISIBLE);
                button3.setVisibility(v.VISIBLE);
                imageView.setVisibility(v.VISIBLE);
                imageView1.setVisibility(v.VISIBLE);
                imageView2.setVisibility(v.VISIBLE);
            }
        });
button3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        button.setVisibility(v.VISIBLE);
        editText.setVisibility(v.INVISIBLE);
        editText1.setVisibility(v.INVISIBLE);
        button1.setVisibility(v.INVISIBLE);
        button2.setVisibility(v.INVISIBLE);
        button3.setVisibility(v.INVISIBLE);
        imageView.setVisibility(v.INVISIBLE);
        imageView1.setVisibility(v.INVISIBLE);
        imageView2.setVisibility(v.INVISIBLE);

    }
});






    }
///////////////////////////////////////////////////////////////////
@Override
protected void onActivityResult(int RC, int RQC, Intent I) {

    super.onActivityResult(RC, RQC, I);

    if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

        Uri uri = I.getData();

        try {

            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

            profileimg.setImageBitmap(bitmap);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}

    public void ImageUploadToServerFunction(){

        ByteArrayOutputStream byteArrayOutputStreamObject ;

        byteArrayOutputStreamObject = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);

        byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

        final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {

            @Override
            protected void onPreExecute() {

                super.onPreExecute();

                progressDialog = ProgressDialog.show(ProfileActivity.this,"Image is Uploading","Please Wait",false,false);
            }

            @Override
            protected void onPostExecute(String string1) {

                super.onPostExecute(string1);

                // Dismiss the progress dialog after done uploading.
                progressDialog.dismiss();

                // Printing uploading success message coming from server on android app.
                Toast.makeText(ProfileActivity.this,string1,Toast.LENGTH_LONG).show();
                Log.d("tag",string1) ;
                // Setting image as transparent after done uploading.
                imageView.setImageResource(android.R.color.transparent);


            }

            @Override
            protected String doInBackground(Void... params) {

                ImageProcessClass imageProcessClass = new ImageProcessClass();

                HashMap<String,String> HashMapParams = new HashMap<String,String>();

                HashMapParams.put(ImageName,"imgtest");

                HashMapParams.put(ImagePath, ConvertImage);
                HashMapParams.put("email",email) ;

                String FinalData = imageProcessClass.ImageHttpRequest(ServerUploadPath, HashMapParams);

                return FinalData;
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();

        AsyncTaskUploadClassOBJ.execute();
    }

////////////////////////ACTIONSSSS//////////////////////////////////////////////////////////////////

    public void uploadimg(View view) {

        Intent intent = new Intent();

        intent.setType("image/*");

        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);

    }

    /*
    * CHECK USER PHOTO
     */

    public void checkpic(){


        String url = "http://192.168.1.9/miniprojet/public/getimg" ;

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String res = jsonObject.getString("result");
                    System.out.println("HEYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY"+res);


                    if (res.equals("false")){
                        Toast.makeText(ProfileActivity.this, "USER DONT HAVE PICTURE", Toast.LENGTH_LONG).show();

                    } else {

                        Picasso.with(getApplicationContext()).load(res.trim()).into(profileimg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("email", email);

                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        queue.add(request);


    }

    //////////////////////////////////////////save changes /////////////////////////////////////////////
    public void save(View view) {
        ImageUploadToServerFunction() ;


    }
}


