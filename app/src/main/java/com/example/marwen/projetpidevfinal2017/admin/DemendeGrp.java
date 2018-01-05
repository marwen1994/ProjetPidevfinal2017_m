package com.example.marwen.projetpidevfinal2017.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.marwen.projetpidevfinal2017.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemendeGrp extends AppCompatActivity {
    List<String> listm = new ArrayList<>();
    SwipeMenuListView LIST;
    DemGrpAdapter adapter ;
    List<String> list= new ArrayList<>();
    String url = "http://172.16.8.138/miniprojet/public/getalldem";
    //String url1 = "http://10.0.2.2/miniprojet/public/Demender";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demende_grp);

        LIST = (SwipeMenuListView) findViewById(R.id.list_grp);

        /////////////////////////////////////////////////////////////////////////////
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {



                    for(int i=1;i<=response.length();i++) {

                        JSONObject jsa=response.getJSONObject(i+"") ;

                       String  jss = jsa.getString("Groupename") ;
                        if (!listm.contains(jss)) {

                            listm.add(jss);
                        }
                    }

                    DemGrpAdapter adapter = new DemGrpAdapter(DemendeGrp.this, listm);
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
        RequestQueue queue = Volley.newRequestQueue(DemendeGrp.this);

        queue.add(request);
///////////////////////////////////////////////////////////////////////////////////////////


        LIST.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View viewx, int i, long l) {
                String m= (String) adapterView.getItemAtPosition(i) ; //parent.getItemAtPosition(i);
                Intent intent = new Intent(DemendeGrp.this,demendes.class);
                Bundle bundle = new Bundle();
                bundle.putString("grp",m.trim());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {

    }
}
