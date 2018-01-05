package com.example.marwen.projetpidevfinal2017.loginRegisterreset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import spencerstudios.com.fab_toast.FabToast;

public class SecondInscrip extends AppCompatActivity {
    EditText group , mail,mail1,mail2;
    Button go ;
    String url = "http://172.16.8.138/miniprojet/public/storeUser";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_inscrip);
        group=(EditText) findViewById(R.id.editText3);
        mail=(EditText) findViewById(R.id.editText4);
        mail1=(EditText) findViewById(R.id.editText5);
        mail2=(EditText) findViewById(R.id.editText6);
        go=(Button) findViewById(R.id.button4);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((group.getText().toString().equals(""))){
                    FabToast.makeText(getApplicationContext(), "Enter Your Group Name", FabToast.LENGTH_SHORT, FabToast.ERROR,  FabToast.POSITION_DEFAULT).show();


                }
                else   if (!mail.getText().toString().matches("[a-z0-9._%+-]+@(esprit.tn)$")) {
                    FabToast.makeText(getApplicationContext(), "Enter un mail Esprit.tn", FabToast.LENGTH_SHORT, FabToast.INFORMATION,  FabToast.POSITION_DEFAULT).show();

                }
                else   if (!mail1.getText().toString().matches("[a-z0-9._%+-]+@(esprit.tn)$")) {
                    FabToast.makeText(getApplicationContext(), "Enter un mail Esprit.tn", FabToast.LENGTH_SHORT, FabToast.INFORMATION,  FabToast.POSITION_DEFAULT).show();

                }

                else {
                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Boolean res = jsonObject.getBoolean("result");

                                if (res) {

                                    Intent intent = new Intent(SecondInscrip.this, Login.class);
                                    startActivity(intent);
                                    FabToast.makeText(getApplicationContext(), "Your are Inscribed", FabToast.LENGTH_SHORT, FabToast.SUCCESS,  FabToast.POSITION_DEFAULT).show();

                                } else {
                                    Toast.makeText(SecondInscrip.this, "laaaa", Toast.LENGTH_SHORT).show();

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


                            map.put("name", new SessionManager(SecondInscrip.this).getName().get("name"));
                            map.put("email", new SessionManager(SecondInscrip.this).getUserDetail().get("email"));
                            map.put("password", new SessionManager(SecondInscrip.this).getUserPassword().get("Password"));
                            map.put("Groupename", group.getText().toString().trim());
                            map.put("first", mail.getText().toString().trim());
                            map.put("second", mail1.getText().toString().trim());
                            map.put("third", mail2.getText().toString().trim());
                            return map;
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                    queue.add(request);


                }
            }
        });
    }
}
