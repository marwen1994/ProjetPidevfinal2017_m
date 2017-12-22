package com.example.marwen.projetpidevfinal2017;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexzh.circleimageview.CircleImageView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.marwen.projetpidevfinal2017.User.MainActivity;
import com.example.marwen.projetpidevfinal2017.loginRegisterreset.Login;
import com.example.marwen.projetpidevfinal2017.loginRegisterreset.SecondInscrip;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {
    FloatingActionButton but;
    CircleImageView image;
    EditText conf;
    View v;
    String ImageName = "image_name" ;

    String ImagePath = "image_path" ;
    Bitmap bitmap;
    TextView name,mail,group,pass;
    Button button1,button2;
    String url = "http://10.0.2.2/miniprojet/public/updatePwdMail";
    String ServerUploadPath ="http://10.0.2.2/miniprojet/public/setimg" ;
    String url2 = "http://10.0.2.2/miniprojet/public/getimg" ;
    ProgressDialog progressDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        checkpic();
        image= (CircleImageView) findViewById(R.id.circleview);
        name= (TextView) findViewById(R.id.name);
        mail= (TextView) findViewById(R.id.mail);
        group= (TextView) findViewById(R.id.group);
        pass= (TextView) findViewById(R.id.pass);
        conf=(EditText)findViewById(R.id.newpass);
        button1=(Button)findViewById(R.id.update);
        button2=(Button)findViewById(R.id.cancel);
        but=(FloatingActionButton) findViewById(R.id.plus);

        name.setText(new SessionManager(EditProfile.this).getName().get("name"));
        mail.setText(new SessionManager(EditProfile.this).getUserDetail().get("email"));
        pass.setText(new SessionManager(EditProfile.this).getUserPassword().get("Password"));
        group.setText( new SessionManager(EditProfile.this).getGroup().get("group"));
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String res = jsonObject.getString("result");

                            if (res.equals("true")){

                                Toast.makeText(EditProfile.this, res.toString(), Toast.LENGTH_SHORT).show();


                            } else {
                                Toast.makeText(EditProfile.this, res.toString(), Toast.LENGTH_SHORT).show();


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
                        map.put("email",new SessionManager(EditProfile.this).getUserDetail().get("email"));
                        map.put("password",conf.getText().toString().trim());
                        return map;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                queue.add(request);
            }
        });



    }
    @Override
    protected void onActivityResult(int RC, int RQC, Intent I) {

        super.onActivityResult(RC, RQC, I);

        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

            Uri uri = I.getData();

            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                image.setImageBitmap(bitmap);

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

                progressDialog = ProgressDialog.show(EditProfile.this,"Image is Uploading","Please Wait",false,false);
            }

            @Override
            protected void onPostExecute(String string1) {

                super.onPostExecute(string1);

                // Dismiss the progress dialog after done uploading.
                progressDialog.dismiss();

                // Printing uploading success message coming from server on android app.
                Toast.makeText(EditProfile.this,string1,Toast.LENGTH_LONG).show();
                Log.d("tag",string1) ;
                // Setting image as transparent after done uploading.
                image.setImageResource(android.R.color.transparent);


            }

            @Override
            protected String doInBackground(Void... params) {

                ImageProcessClass imageProcessClass = new ImageProcessClass();

                HashMap<String,String> HashMapParams = new HashMap<String,String>();

                HashMapParams.put(ImageName,"imgtest");

                HashMapParams.put(ImagePath, ConvertImage);
                HashMapParams.put("email",new SessionManager(EditProfile.this).getUserDetail().get("email"));

                String FinalData = imageProcessClass.ImageHttpRequest(ServerUploadPath, HashMapParams);

                return FinalData;
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();

        AsyncTaskUploadClassOBJ.execute();
    }



    public void checkpic(){




        StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String res = jsonObject.getString("result");
                    System.out.println("HEYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY"+res);


                    if (res.equals("false")){
                        Toast.makeText(EditProfile.this, "USER DONT HAVE PICTURE", Toast.LENGTH_LONG).show();

                    } else {

                        Picasso.with(getApplicationContext()).load(res.trim()).into(image);
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
                map.put("email",new SessionManager(EditProfile.this).getUserDetail().get("email"));

                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        queue.add(request);


    }


    public void up(View view) {
        Intent intent = new Intent();

        intent.setType("image/*");

        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);
    }


    public void upload(View view) {
        ImageUploadToServerFunction() ;
    }
}
