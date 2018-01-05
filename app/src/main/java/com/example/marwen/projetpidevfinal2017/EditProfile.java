package com.example.marwen.projetpidevfinal2017;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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


import com.gitonway.lee.niftymodaldialogeffects.lib.*;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.effects.RotateBottom;
import com.squareup.picasso.Picasso;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spencerstudios.com.fab_toast.FabToast;

public class EditProfile extends AppCompatActivity {
    FloatingActionButton but;
    CircleImageView image;
    EditText conf;
    View v;
    String ImageName = "image_name" ;
    FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;
    String ImagePath = "image_path" ;
    Bitmap bitmap = null;
    TextView name,mail,group,pass;
    View view;
    Button button1,button2;
    String url = "http://172.16.8.138/miniprojet/public/updatePwdMail";
    String ServerUploadPath ="http://172.16.8.138/miniprojet/public/setimg" ;
    String url2 = "http://172.16.8.138/miniprojet/public/getimg" ;
    ProgressDialog progressDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        checkpic();


        initMenuFragment();
        fragmentManager=getSupportFragmentManager();


        image= (CircleImageView) findViewById(R.id.circleview);
        name= (TextView) findViewById(R.id.name);
        mail= (TextView) findViewById(R.id.mail);
        group= (TextView) findViewById(R.id.group);
        pass= (TextView) findViewById(R.id.pass);
        conf=(EditText)findViewById(R.id.newpass);
        button1=(Button)findViewById(R.id.update);

        but=(FloatingActionButton) findViewById(R.id.plus);

        name.setText(new SessionManager(EditProfile.this).getName().get("name"));
        mail.setText(new SessionManager(EditProfile.this).getUserDetail().get("email"));
        pass.setText(new SessionManager(EditProfile.this).getUserPassword().get("Password"));
        group.setText( new SessionManager(EditProfile.this).getGroup().get("group"));
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((conf.getText().toString().equals(""))){

                    FabToast.makeText(EditProfile.this, "INSERT YOUR NEW PASSWORD", FabToast.LENGTH_LONG, FabToast.ERROR, FabToast.POSITION_DEFAULT).show();
                }
                else {
                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String res = jsonObject.getString("result");

                                if (res.equals("true")) {

                                    FabToast.makeText(EditProfile.this, "Update successfully", FabToast.LENGTH_LONG, FabToast.SUCCESS, FabToast.POSITION_DEFAULT).show();


                                } else {
                                    FabToast.makeText(EditProfile.this, "Error", FabToast.LENGTH_LONG, FabToast.ERROR, FabToast.POSITION_DEFAULT).show();


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
                            map.put("email", new SessionManager(EditProfile.this).getUserDetail().get("email"));
                            map.put("password", conf.getText().toString().trim());
                            return map;
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                    queue.add(request);
                }
            }
        });



    }

    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(View clickedView, int position) {

                if(position==1){

                    final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(EditProfile.this);
                    dialogBuilder
                            .withTitle("Are You Sure To Logout\n")
                            .withTitleColor("#080606")
                            .withDividerColor("#11000000")
                            .withMessageColor("#FFFFFFFF")
                            .withDialogColor("#080606")
                            //.withIcon(getResources().getDrawable(R.drawable.success))
                            .withDuration(700)
                            .withEffect(Effectstype.Slidetop)
                            .withButton1Text("yes")
                            .withButton2Text("Cancel")
                            .isCancelableOnTouchOutside(true)
                            .setButton1Click(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    new SessionManager(getApplicationContext()).setLogin(false);
                                    Intent intent = new Intent(EditProfile.this,Login.class);
                                    startActivity(intent);

                                }
                            })
                            .setButton2Click(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialogBuilder.dismiss();
                                }
                            })
                            .show();



                }


            }
        });
        mMenuDialogFragment.setItemLongClickListener(new OnMenuItemLongClickListener() {
            @Override
            public void onMenuItemLongClick(View clickedView, int position) {

            }
        });
    }

    private List<MenuObject> getMenuObjects() {


        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject("Cancel");
        close.setResource(R.drawable.error);

        MenuObject logout = new MenuObject("Logout");
        logout.setResource(R.drawable.powerbutton);



        menuObjects.add(close);
        menuObjects.add(logout);
        return menuObjects;
    }



    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
      /*  if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        } else {
            finish();
        }*/
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


    public void ImageUploadToServerFunction() {

            ByteArrayOutputStream byteArrayOutputStreamObject;

            byteArrayOutputStreamObject = new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);

            byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

            final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

            class AsyncTaskUploadClass extends AsyncTask<Void, Void, String> {

                @Override
                protected void onPreExecute() {

                    super.onPreExecute();

                    progressDialog = ProgressDialog.show(EditProfile.this, "Image is Uploading", "Please Wait", false, false);
                }

                @Override
                protected void onPostExecute(String string1) {

                    super.onPostExecute(string1);

                    // Dismiss the progress dialog after done uploading.
                    progressDialog.dismiss();
                    FabToast.makeText(EditProfile.this, "Uploaded successfully", FabToast.LENGTH_LONG, FabToast.SUCCESS, FabToast.POSITION_DEFAULT).show();



                    // Printing uploading success message coming from server on android app.
                    Log.d("tag", string1);
                    // Setting image as transparent after done uploading.
                    image.setImageResource(android.R.color.transparent);


                }

                @Override
                protected String doInBackground(Void... params) {

                    ImageProcessClass imageProcessClass = new ImageProcessClass();


                        HashMap<String, String> HashMapParams = new HashMap<String, String>();

                        HashMapParams.put(ImageName, new SessionManager(EditProfile.this).getUserDetail().get("email"));

                        HashMapParams.put(ImagePath, ConvertImage);
                        HashMapParams.put("email", new SessionManager(EditProfile.this).getUserDetail().get("email"));

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
                        FabToast.makeText(EditProfile.this, "You do not have Profile Photo" , FabToast.LENGTH_LONG, FabToast.INFORMATION, FabToast.POSITION_DEFAULT).show();

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
        if (bitmap == null) {
            FabToast.makeText(EditProfile.this, "Please Choose Photo" , FabToast.LENGTH_LONG, FabToast.WARNING, FabToast.POSITION_DEFAULT).show();
        }
else {
            ImageUploadToServerFunction();
        }

    }




}
