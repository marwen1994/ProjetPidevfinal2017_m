package com.example.marwen.projetpidevfinal2017.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.marwen.projetpidevfinal2017.Matdispo;
import com.example.marwen.projetpidevfinal2017.R;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class demendes extends AppCompatActivity {
    List<String> listm = new ArrayList<>();
    SwipeMenuListView LIST;

    Matdispo m;
    List<Matdispo> list= new ArrayList<>();
    String Grp ;
    String url = "http://10.0.2.2/miniprojet/public/getdemByGroup";
    String url1 = "http://10.0.2.2/miniprojet/public/getMatByIDAndroid";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demendes);
        LIST = (SwipeMenuListView) findViewById(R.id.list_dem);
        FrameLayout footerLayout = (FrameLayout) getLayoutInflater().inflate(R.layout.contact_footer_view,null);
        Button Accp = (Button) footerLayout.findViewById(R.id.btn_acc);
        Button ref = (Button) footerLayout.findViewById(R.id.btn_ref);
        LIST.addFooterView(footerLayout);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        Grp = bundle.getString("grp");


            ////////////////////////////////////////////////////////////
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    for(int i=1;i<=jsonObject.length();i++) {

                        JSONObject jsa = jsonObject.getJSONObject(i +"");

                        String jss = jsa.getString("id_mat");


                        listm.add(jss);
                    }
                        //-------------------------------------ALALALALALALALAL-----------------------------///////////

                        ////////////////////////////////////////////////////////////END FIRST REQUEST ////////////////////////////////////////////////////////

                        StringRequest request1 = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject js = new JSONObject(response);

                                    Toast.makeText(demendes.this, js.toString(), Toast.LENGTH_SHORT).show();
                                    System.out.println(js.toString());
                                    for(int i=1;i<=js.length();i++) {

                                        JSONObject jsa=js.getJSONObject(i+"");
                                        m=new Matdispo();
                                        m.setId(Integer.parseInt(jsa.getString("id")));
                                        m.setName(jsa.getString("name"));
                                        m.setQte(Integer.parseInt(jsa.getString("qte")));
                                        m.setDescription(jsa.getString("description"));
                                        m.setImage_path(jsa.getString("image_path"));
                                        list.add(m);
                                    }
                                    Toast.makeText(demendes.this, "AAAAAAAAAAAAA", Toast.LENGTH_LONG).show();
                        DemAdapter adapter = new DemAdapter(demendes.this, list);
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
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> map = new HashMap<String, String>();

                                for(int i=0;i<listm.size();i++) {

                                    map.put(i+"", listm.get(i));
                                }

                                return map;
                            }

                        };
                        RequestQueue queue1 = Volley.newRequestQueue(getApplicationContext());

                        queue1.add(request1);



///////////////////////////////////////////END SECONDE REQUEST //////////////////////////////////////////////////////////////////////



                        ///////-------------------------------- AALALALALALALA --------------------------------------////


                    /*DemAdapter adapter = new DemAdapter(demendes.this, listm);
                    LIST.setAdapter(adapter);*/
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

                map.put("Groupename", Grp.trim());

                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        queue.add(request);




    }
    /////------------------------------------------------------------------------------------------------------------------/////




}

    ///////////////////////////////////////////////////////////////////////////

