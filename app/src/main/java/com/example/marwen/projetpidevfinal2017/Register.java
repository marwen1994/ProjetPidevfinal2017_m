package com.example.marwen.projetpidevfinal2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
TextView textView ,textView1,textView2,textView3,textView4;
EditText editText,editText1,editText2,editText3;
    Button button;
    String url = "http://10.0.2.2/miniprojet/public/storeUser";
    String url1 = "http://10.0.2.2/miniprojet/public/checkmail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textView=(TextView) findViewById(R.id.textView8);
        textView1=(TextView) findViewById(R.id.textView17);
        textView2=(TextView) findViewById(R.id.textView18);
        textView3=(TextView) findViewById(R.id.textView19);
        textView4=(TextView) findViewById(R.id.textView22);

        editText=(EditText) findViewById(R.id.editText3);
        editText1=(EditText) findViewById(R.id.editText4);
        editText2=(EditText) findViewById(R.id.editText5);
        editText3=(EditText) findViewById(R.id.editText6);
        button=(Button) findViewById(R.id.button4);

         textView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(Register.this,Login.class);
                 startActivity(intent);
             }
         });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editText1.getText().toString().matches("[a-z0-9._%+-]+@(esprit.tn)$")) {

                    Toast.makeText(Register.this, "Enter un mail Esprit.tn", Toast.LENGTH_SHORT).show();
                }
                else if (!(editText3.getText().toString().equals(editText2.getText().toString()))) {

                    Toast.makeText(Register.this, "Pssword n'est pas conforme", Toast.LENGTH_SHORT).show();
                    editText3.setText("");

                }

                else if ((cheak())) {

                    Toast.makeText(Register.this, "Vous etes Inscrits deja", Toast.LENGTH_SHORT).show();


                    editText1.setText("");

                }
                else {
                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Boolean res = jsonObject.getBoolean("result");

                                if (res) {
                                    Toast.makeText(Register.this, "Vous etes Inscrits", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Register.this, Login.class);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(Register.this, "te7chaaaa", Toast.LENGTH_SHORT).show();

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
                            map.put("name", editText.getText().toString().trim());
                            map.put("email", editText1.getText().toString().trim());
                            map.put("password", editText2.getText().toString().trim());
                            return map;
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                    queue.add(request);


                }
                }


        });






    }


    public  boolean cheak(){


        StringRequest request = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Boolean res = jsonObject.getBoolean("result");

                    if (res) {
                        Toast.makeText(Register.this, res.toString(), Toast.LENGTH_SHORT).show();

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
                map.put("email", editText1.getText().toString().trim());
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        queue.add(request);





return false;



    }
}
