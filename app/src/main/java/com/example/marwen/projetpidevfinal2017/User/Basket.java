package com.example.marwen.projetpidevfinal2017.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
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
import com.example.marwen.projetpidevfinal2017.BasketAdapter;
import com.example.marwen.projetpidevfinal2017.Matdispo;
import com.example.marwen.projetpidevfinal2017.SessionManager;
import com.example.marwen.projetpidevfinal2017.admin.CustomListAdapter;
import com.example.marwen.projetpidevfinal2017.admin.ListMatdispo;
import com.example.marwen.projetpidevfinal2017.loginRegisterreset.Register;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.marwen.projetpidevfinal2017.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import spencerstudios.com.fab_toast.FabToast;

public class Basket extends AppCompatActivity {
    List<Matdispo> listm = new ArrayList<>();
    List<Matdispo> lista = new ArrayList<>();

    SwipeMenuListView LIST;
    Matdispo m;
    List<String> list= new ArrayList<>();
    String url = "http://172.16.8.138/miniprojet/public/getallbasket";
    String url1 = "http://172.16.8.138/miniprojet/public/Demenderandroid";
    String email ;
    String user_email ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        LIST = (SwipeMenuListView) findViewById(R.id.list_Basket);
        user_email = new SessionManager(getApplicationContext()).getUserDetail().get("email") ;
////////////////////////////////////////////////////////
    }





    @Override
    public void onResume() {
        super.onResume();
        listm.clear();
        BasketAdapter adapter = new BasketAdapter(Basket.this, listm);
        LIST.setAdapter(adapter);


        /////////////////////////////////////////////////////////////////////////////
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray js=response.getJSONArray("result");

                    for(int i=0;i<js.length();i++) {

                        JSONObject jsa=js.getJSONObject(i);

                        m=new Matdispo();
                        m.setId(Integer.parseInt(jsa.getString("id")));
                        m.setName(jsa.getString("name"));
                        email = jsa.getString("email") ;
                        m.setQte(Integer.parseInt(jsa.getString("qte")));
                        m.setDescription(jsa.getString("description"));
                        m.setImage_path(jsa.getString("image_path")) ;
                        //
                        if (email.equals(user_email))
                            {
                                listm.add(m);
                            }


                        }




                    BasketAdapter adapter = new BasketAdapter(Basket.this, listm);
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
        RequestQueue queue = Volley.newRequestQueue(Basket.this);

        queue.add(request);

    }

    public void testv() {

    }

    public void Demender(View view) {


        StringRequest request = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    FabToast.makeText(getApplicationContext(), "Your Request Is Saved Seccessfully", FabToast.LENGTH_SHORT, FabToast.SUCCESS,  FabToast.POSITION_DEFAULT).show();
                  //  Toast.makeText(Basket.this, jsonObject+"", Toast.LENGTH_LONG).show();
//                    Boolean res = jsonObject.getBoolean("result");
//
//                    if (res) {
//                        Toast.makeText(Basket.this, res.toString()+"  Demende success", Toast.LENGTH_SHORT).show();
//
//                    }else {
//                        Toast.makeText(Basket.this, res.toString(), Toast.LENGTH_SHORT).show();
//                    }


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
                Map<String,String> map = new HashMap<String, String>();
                for (int i = 0; i < listm.size(); i++) {

                    map.put(i+"", listm.get(i).getId()+"");
                }
                map.put("size",listm.size()+"") ;
                map.put("email",new SessionManager(getApplicationContext()).getUserDetail().get("email"));
                map.put("groupname",new SessionManager(getApplicationContext()).getGroup().get("group"));
/////à completer /////////////////

                //list.add(Integer.toString(m.getId()));
               // map.put("listIDs",list);



                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        queue.add(request);

    }
    @Override
    public void onBackPressed() {

    }
}
