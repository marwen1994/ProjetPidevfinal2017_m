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
import com.example.marwen.projetpidevfinal2017.R;
import com.example.marwen.projetpidevfinal2017.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import spencerstudios.com.fab_toast.FabToast;

public class Register extends AppCompatActivity {
TextView textView ,textView1,textView2,textView3,textView4;
EditText editText,editText1,editText2,editText3;
    Button button;
    boolean resultat;

    String url1 = "http://172.16.8.138/miniprojet/public/checkmail";

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
                if ((editText.getText().toString().equals(""))){
                    FabToast.makeText(Register.this, "Check Your name", FabToast.LENGTH_SHORT, FabToast.ERROR,  FabToast.POSITION_DEFAULT).show();


                }
             else   if (!editText1.getText().toString().matches("[a-z0-9._%+-]+@(esprit.tn)$")) {
                    FabToast.makeText(Register.this, "Enter un mail Esprit.tn", FabToast.LENGTH_SHORT, FabToast.INFORMATION,  FabToast.POSITION_DEFAULT).show();

                }

                else if (!(editText3.getText().toString().equals(editText2.getText().toString()))) {

                    FabToast.makeText(Register.this, "Confirm Your Password", FabToast.LENGTH_SHORT, FabToast.ERROR,  FabToast.POSITION_DEFAULT).show();
                }

             else   if (!(cheak())) {

                    FabToast.makeText(Register.this, "YOU ARE ALREADY Inscribed", FabToast.LENGTH_SHORT, FabToast.WARNING,  FabToast.POSITION_DEFAULT).show();

                    editText1.setText("");

                }

   else {

                    new SessionManager(getApplicationContext()).setName(editText.getText().toString());
                    new SessionManager(getApplicationContext()).UserDetail(editText1.getText().toString());
                    new SessionManager(getApplicationContext()).UserPassword(editText2.getText().toString());



                    Intent i = new Intent(Register.this,SecondInscrip.class);
                    startActivity(i);
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
                    resultat = res;


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





return resultat;



    }
}
