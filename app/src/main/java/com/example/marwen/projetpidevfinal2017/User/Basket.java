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

public class Basket extends AppCompatActivity {
    List<Matdispo> listm = new ArrayList<>();
    SwipeMenuListView LIST;
    Matdispo m;
    List<String> list= new ArrayList<>();
    String url = "http://10.0.2.2/miniprojet/public/getallbasket";
    String url1 = "http://10.0.2.2/miniprojet/public/Demender";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        LIST = (SwipeMenuListView) findViewById(R.id.list_Basket);

////////////////////////////////////////////////////////
    }





    @Override
    public void onResume() {
        super.onResume();



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
                        m.setQte(Integer.parseInt(jsa.getString("qte")));
                        m.setDescription(jsa.getString("description"));
                        m.setImage_path(jsa.getString("image_path"));
                        listm.add(m);
                    }
                    Toast.makeText(Basket.this, m.getName(), Toast.LENGTH_SHORT).show();
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
///////////////////////////////////////////////////////////////////////////////////////////







//        HashMap<String, String> matriel = new HashMap<String, String>();
//        matriel = new SessionManager(getApplicationContext()).getMatriel();
//        if (!matriel.isEmpty()) {
//            //int id = Integer.parseInt(matriel.get("id"));
//            String imagepath = matriel.get("img");
//            String name = matriel.get("nom");
//            // int amount = Integer.parseInt(matriel.get("qte"));
//            String description = matriel.get("description");
//            Toast.makeText(this, description, Toast.LENGTH_SHORT).show();
//            m = new Matdispo();
//            //  m.setId(id);
//            m.setName(name);
//               Toast.makeText(this, m.getName(), Toast.LENGTH_SHORT).show();
//            m.setQte(14);
//            m.setDescription(description);
//            m.setImage_path(imagepath);
//
//            listm.add(m);
//
//            BasketAdapter adapter = new BasketAdapter(Basket.this, listm);
//            LIST.setAdapter(adapter);
       // }

    }

    public void testv() {

    }

    public void Demender(View view) {
        StringRequest request = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Boolean res = jsonObject.getBoolean("result");

                    if (res) {
                        Toast.makeText(Basket.this, res.toString()+"  Demende success", Toast.LENGTH_SHORT).show();

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
                Map<String,String> map = new HashMap<String, String>();
                map.put("email",new SessionManager(getApplicationContext()).getUserDetail().get("email"));
                map.put("groupname",new SessionManager(getApplicationContext()).getGroup().get("group"));
/////à completer /////////////////
                list.add(Integer.toString(m.getId()));
               // map.put("listIDs",list);



                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        queue.add(request);

    }
}
