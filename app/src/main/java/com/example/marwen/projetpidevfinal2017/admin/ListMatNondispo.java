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
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.marwen.projetpidevfinal2017.Matdispo;
import com.example.marwen.projetpidevfinal2017.Matnondisponible;
import com.example.marwen.projetpidevfinal2017.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class ListMatNondispo extends AppCompatActivity {
    SwipeMenuListView LIST ;
    List<Matnondisponible> l= new ArrayList<>();
    Matnondisponible m;
    EditText search;
    SpinnerDialog dialog ;
    CustomerListAdapterMatNonDisoponible adapter ;
    String url = "http://172.16.8.138/Miniprojet/public/getallnondispo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mat_nondispo);
        LIST = (SwipeMenuListView) findViewById(R.id.list);
        search = (EditText) findViewById(R.id.se);
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

/*
        ArrayList<String> l1 = getlistnom();

        dialog = new SpinnerDialog(ListMatNondispo.this,l1,"Select Item");
        search.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialog.showSpinerDialog();


                return false;
            }
        });*/

       /* dialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
                search.setText(s);
            }
        });
*/

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray js=response.getJSONArray("result");

                    for(int i=0;i<js.length();i++) {

                        JSONObject jsa=js.getJSONObject(i);
                        m=new Matnondisponible();
                        m.setId(Integer.parseInt(jsa.getString("id")));
                        m.setImage_path(jsa.getString("image_path"));
                        m.setName(jsa.getString("name"));
                        m.setPrix(Integer.parseInt(jsa.getString("prix")));
                        m.setDescription(jsa.getString("description"));
                        m.setGroupename(jsa.getString("Groupename"));
                        m.setUrl(jsa.getString("url"));
                        m.setId_user(jsa.getString("id_user"));


                        l.add(m);
                    }

                    adapter=new CustomerListAdapterMatNonDisoponible(ListMatNondispo.this,l);
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
        RequestQueue queue = Volley.newRequestQueue(ListMatNondispo.this);

        queue.add(request);

////////////////////////////////////////////////////////////////////////
        LIST.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View viewx, int i, long l) {
                Matnondisponible m= (Matnondisponible) adapter.getItem(i);
                Intent intent = new Intent(ListMatNondispo.this,DeatailMatNonDispo.class);
                Bundle bundle = new Bundle();
                bundle.putString("nom",m.getName());
                bundle.putInt("id",m.getId());
                bundle.putInt("prix",m.getPrix());
                bundle.putString("description",m.getDescription());
                bundle.putString("path",m.getImage_path());
                bundle.putString("Group",m.getGroupename());
                bundle.putString("url",m.getUrl());
                bundle.putString("id_user",m.getId_user());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


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
                        lx.add(jsa.getString("Groupename"));
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
        RequestQueue queue = Volley.newRequestQueue(ListMatNondispo.this);

        queue.add(request);

        return (ArrayList<String>) lx;
    }
    @Override
    public void onBackPressed() {

    }
}
