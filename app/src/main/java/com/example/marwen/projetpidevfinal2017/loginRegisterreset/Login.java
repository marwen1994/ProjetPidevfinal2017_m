package com.example.marwen.projetpidevfinal2017.loginRegisterreset;

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
import com.example.marwen.projetpidevfinal2017.User.MainActivity;
import com.example.marwen.projetpidevfinal2017.R;
import com.example.marwen.projetpidevfinal2017.SessionManager;
import com.example.marwen.projetpidevfinal2017.admin.AdminMainActivity;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    EditText editText, editText1;
    TextView textView, textView1,textView2,textView3;
    Button button;
    String url = "http://172.16.8.138/miniprojet/public/checkUser";
    String url1 = "http://172.16.8.138/miniprojet/public/getUserByEmail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        editText1 = (EditText) findViewById(R.id.editText2);
        textView = (TextView) findViewById(R.id.textView2);
        textView1 = (TextView) findViewById(R.id.textView3);
        textView2 = (TextView) findViewById(R.id.textView4);
if(new SessionManager(getApplicationContext()).isLoggedIn()){
    Intent intent = new Intent(Login.this, MainActivity.class);
    startActivity(intent);

}

        textView3 = (TextView) findViewById(R.id.textView5);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Reset.class);
                startActivity(intent);
            }
        });

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
      /*  Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);*/



            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((editText.getText().toString().equals("")) ) {


                    Toast.makeText(Login.this,"Invalide Login", Toast.LENGTH_SHORT).show();

                }
                else if (!(editText.getText().toString().isEmpty()) ) {

                    Toast.makeText(Login.this,"Invalide Login", Toast.LENGTH_SHORT).show();

                }
                if ((editText1.getText().toString().equals("")) ) {
                    Toast.makeText(Login.this,"Invalide Login", Toast.LENGTH_SHORT).show();
                }
                else if (editText.getText().toString().equals("marwen@admin.com")){

                    FirebaseMessaging.getInstance().subscribeToTopic("admin");

                    Intent intent = new Intent(Login.this, AdminMainActivity.class);
                    startActivity(intent);



                }

                else{
                    getall();
                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String res = jsonObject.getString("result");

                                if (res.equals("true")){
                                    new SessionManager(getApplicationContext()).UserDetail(editText.getText().toString());
                                    new SessionManager(getApplicationContext()).UserPassword(editText1.getText().toString());
                                    Toast.makeText(Login.this, "Welcome", Toast.LENGTH_SHORT).show();
                                    //subscribing to notification
                                    String id =  new SessionManager(getApplication()).getId().get("id") ;
                                    FirebaseMessaging.getInstance().subscribeToTopic(id);
                                    new SessionManager(getApplicationContext()).setLogin(true);
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(Login.this,"Invalide Login", Toast.LENGTH_SHORT).show();


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
                            map.put("email", editText.getText().toString().trim());
                            map.put("password", editText1.getText().toString().trim());
                            return map;
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                    queue.add(request);

                }
            }
        });

    }

    public  void getall(){

        StringRequest request = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String res = jsonObject.getString("name");
                    String res2 = jsonObject.getString("Groupename");
                    String id = jsonObject.getString("id") ;
                    new SessionManager(getApplicationContext()).setGroup(res2.toString());
                    new SessionManager(getApplicationContext()).setName(res.toString());
                    new SessionManager(getApplication()).setId(id);


                    Toast.makeText(Login.this, res.toString(), Toast.LENGTH_SHORT).show();


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
                map.put("email", editText.getText().toString().trim());
                //map.put("password", editText1.getText().toString().trim());
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        queue.add(request);

    }





}



