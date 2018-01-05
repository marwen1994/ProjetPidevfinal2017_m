package com.example.marwen.projetpidevfinal2017.admin;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.marwen.projetpidevfinal2017.R;
import com.example.marwen.projetpidevfinal2017.SessionManager;
import com.example.marwen.projetpidevfinal2017.loginRegisterreset.Login;
import com.example.marwen.projetpidevfinal2017.loginRegisterreset.SecondInscrip;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DeatailMatNonDispo extends AppCompatActivity {
    ImageView image;
    TextView name, amount, description , groupe;
    Button accept , refuse;
    int id;
    String imgpath;
    Button url;
    String url1="http://172.16.8.138/miniprojet/public/sendnotifById";
    String url2="http://172.16.8.138/miniprojet/public/sendnotifById";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatail_mat_non_dispo);
        image = (ImageView) findViewById(R.id.imgg);
        name = (TextView) findViewById(R.id.name);
        amount = (TextView) findViewById(R.id.price);
        description = (TextView) findViewById(R.id.description);
        groupe = (TextView) findViewById(R.id.groupe);
        url = (Button) findViewById(R.id.URL);
        accept = (Button) findViewById(R.id.accepter);
        refuse = (Button) findViewById(R.id.refuser);

        Intent i = getIntent();
        final Bundle bundle = i.getExtras();
        // image.setImageBitmap((Bitmap)bundle.getParcelable("image"));
        Picasso.with(DeatailMatNonDispo.this).load(bundle.getString("path")).into(image);
        id = bundle.getInt("id");
        imgpath = bundle.getString("path");
        name.setText(bundle.getString("nom"));
        amount.setText(bundle.getInt("prix") + "");
        description.setText(bundle.getString("description"));
        groupe.setText(bundle.getString("Group"));
        url.setText(bundle.getString("url"));
       final String id_user = bundle.getString("id_user");



        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest request1 = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject js = new JSONObject(response);


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

                        map.put("id", id_user);
                        map.put("body","your request for unavailable material has been Accepted");
                      return map;
                    }

                };
                RequestQueue queue1 = Volley.newRequestQueue(getApplicationContext());

                queue1.add(request1);

            }
        });

     refuse.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {





             StringRequest request1 = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                 @Override
                 public void onResponse(String response) {

                     try {
                         JSONObject js = new JSONObject(response);


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

                     map.put("id", id_user);
                     map.put("body", "Your Request For Unavailable Equipement Has been refused");


                     return map;
                 }

             };
             RequestQueue queue1 = Volley.newRequestQueue(getApplicationContext());

             queue1.add(request1);

     }
     });



    }

    public void open(View view) {
        Intent intent = new Intent(DeatailMatNonDispo.this,WebView.class);
        Bundle bundle = new Bundle();
        bundle.putString("url",url.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
