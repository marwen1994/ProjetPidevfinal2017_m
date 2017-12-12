package com.example.marwen.projetpidevfinal2017.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import com.example.marwen.projetpidevfinal2017.User.Basket;
import com.example.marwen.projetpidevfinal2017.User.MainActivity;
import com.example.marwen.projetpidevfinal2017.loginRegisterreset.Login;
import com.example.marwen.projetpidevfinal2017.loginRegisterreset.Register;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Detail extends AppCompatActivity {
    ImageView image;
    TextView name , amount , description ;
    int id ;
    String url = "http://10.0.2.2/miniprojet/public/addBasket";
    String imgpath ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        image = (ImageView) findViewById(R.id.imgg);
        name = (TextView)findViewById(R.id.name);
        amount = (TextView)findViewById(R.id.amount);

        description = (TextView) findViewById(R.id.description);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
       // image.setImageBitmap((Bitmap)bundle.getParcelable("image"));
        Picasso.with(Detail.this).load(bundle.getString("path")).into(image);
        id = bundle.getInt("id") ;
        imgpath = bundle.getString("path") ;
        name.setText(bundle.getString("nom"));
        amount.setText(bundle.getInt("qte")+"");
        description.setText(bundle.getString("description"));



    }

    public void AddToBasket(View view) {
        Toast.makeText(this, "ADD", Toast.LENGTH_SHORT).show();
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String res = jsonObject.getString("result");

                    if (res.equals("true")){

                        Toast.makeText(Detail.this, res.toString(), Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(Detail.this, res.toString(), Toast.LENGTH_SHORT).show();


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
                map.put("image_path", imgpath);
                map.put("name", name.getText().toString().trim());
                map.put("qte", amount.getText().toString().trim());
                map.put("description", description.getText().toString().trim());
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        queue.add(request);

    }
}
