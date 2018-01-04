package com.example.marwen.projetpidevfinal2017.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import com.example.marwen.projetpidevfinal2017.EditProfile;
import com.example.marwen.projetpidevfinal2017.NiftyDialogBuilder;
import com.example.marwen.projetpidevfinal2017.R;
import com.example.marwen.projetpidevfinal2017.SessionManager;
import com.example.marwen.projetpidevfinal2017.loginRegisterreset.Login;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import spencerstudios.com.fab_toast.FabToast;

import static com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype.RotateBottom;


public class Listeusers extends AppCompatActivity {
    SwipeMenuListView LIST ;
    EditText search;
    List<User> l= new ArrayList<>();
    User m;
    SpinnerDialog dialog ;
    CustomerListAdapterUsers adapter ;
    String url = "http://192.168.0.121/Miniprojet/public/getallusers";
    String url1 ="http://192.168.0.121/Miniprojet/public/updatestatus";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listeusers);

        LIST = (SwipeMenuListView) findViewById(R.id.list);
        search = (EditText) findViewById(R.id.serach);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

/////////////rech SPINER
/*        ArrayList<String> l1 = getlistnom();

        dialog = new SpinnerDialog(Listeusers.this,l1,"Select Item");
        search.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialog.showSpinerDialog();


                return false;
            }
        });*/

    /*    dialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
                search.setText(s);
            }
        });*/

////////////////////////////// setonlongclick
        LIST.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        final User user = (User) adapter.getItem(i);
                    final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(Listeusers.this);
                    dialogBuilder
                            .withTitle("Add As Team Leader\n")
                            .withTitleColor("#080606")
                            .withDividerColor("#11000000")
                            .withMessageColor("#FFFFFFFF")
                            .withDialogColor("#080606")
                           // .withIcon(getResources().getDrawable(R.drawable.success))
                            .withDuration(700)
                            .withEffect(RotateBottom)
                            .withButton1Text("OK")
                            .withButton2Text("Cancel")
                            .isCancelableOnTouchOutside(true)
                            .setCustomView(R.layout.custom_view, view.getContext())
                            .setButton2Click(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialogBuilder.dismiss();

                                }
                            })
                    
                            .setButton1Click(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(user.getStatus().toString().equals("1")){
                                        FabToast.makeText(Listeusers.this, "He Is A Team Leader", FabToast.LENGTH_SHORT, FabToast.WARNING,  FabToast.POSITION_DEFAULT).show();
                                        dialogBuilder.dismiss();
                                    }
                                    else {
                                        StringRequest request = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {

                                                try {
                                                    JSONObject jsonObject = new JSONObject(response);
                                                    String res = jsonObject.getString("result");

                                                    if (res.equals("true")) {

                                                        FabToast.makeText(Listeusers.this, "He was added As team Leader", FabToast.LENGTH_SHORT, FabToast.SUCCESS,  FabToast.POSITION_DEFAULT).show();
                                                        dialogBuilder.dismiss();


                                                    } else {
                                                        FabToast.makeText(Listeusers.this, "Error", FabToast.LENGTH_SHORT, FabToast.ERROR,  FabToast.POSITION_DEFAULT).show();


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
                                                map.put("email", user.getEmail());
                                                map.put("status", "1");
                                                return map;
                                            }
                                        };
                                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                                        queue.add(request);
                                    } }
                            })

                            .show();


                    return false;
                }


        });








////////////////////////////////////
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray js=response.getJSONArray("result");

                    for(int i=0;i<js.length();i++) {

                        JSONObject jsa=js.getJSONObject(i);
                        m=new User();
                        m.setEmail(jsa.getString("email"));
                        m.setGrpouname(jsa.getString("Groupename"));
                        m.setImage_path(jsa.getString("image_path"));
                        m.setStatus(jsa.getString("status"));
                        l.add(m);
                    }

                    adapter=new CustomerListAdapterUsers(Listeusers.this,l);
                    LIST.setAdapter(adapter);

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
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(Listeusers.this);

        queue.add(request);

////////////////////////////////////////////////////////////////////////

    }

    private ArrayList<String> getlistnom() {
        final List<String> lx=new ArrayList<>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray js=response.getJSONArray("result");

                    for(int i=0;i<js.length();i++) {
                        JSONObject jsa=js.getJSONObject(i);
                        lx.add(jsa.getString("email"));
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

                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(Listeusers.this);

        queue.add(request);

        return (ArrayList<String>) lx;
    }
    @Override
    public void onBackPressed() {

    }
}
