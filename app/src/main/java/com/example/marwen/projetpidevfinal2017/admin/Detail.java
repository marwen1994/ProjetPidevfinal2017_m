package com.example.marwen.projetpidevfinal2017.admin;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.example.marwen.projetpidevfinal2017.EditProfile;
import com.example.marwen.projetpidevfinal2017.Mail;
import com.example.marwen.projetpidevfinal2017.R;
import com.example.marwen.projetpidevfinal2017.SessionManager;
import com.example.marwen.projetpidevfinal2017.User.AjoutMatrielNonDispo;
import com.example.marwen.projetpidevfinal2017.User.Basket;
import com.example.marwen.projetpidevfinal2017.User.MainActivity;
import com.example.marwen.projetpidevfinal2017.loginRegisterreset.Login;
import com.example.marwen.projetpidevfinal2017.loginRegisterreset.Register;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

import spencerstudios.com.fab_toast.FabToast;

public class Detail extends AppCompatActivity {
    ImageView image;
    TextView name, amount, description;
    int id;
    String url = "http://172.16.8.138/miniprojet/public/addBasket";
    String imgpath;

    FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        image = (ImageView) findViewById(R.id.imgg);
        name = (TextView) findViewById(R.id.name);
        amount = (TextView) findViewById(R.id.amount);
        button=(FloatingActionButton)findViewById(R.id.plus);

        description = (TextView) findViewById(R.id.description);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        // image.setImageBitmap((Bitmap)bundle.getParcelable("image"));
        Picasso.with(Detail.this).load(bundle.getString("path")).into(image);
        id = bundle.getInt("id");
        imgpath = bundle.getString("path");
        name.setText(bundle.getString("nom"));
        amount.setText(bundle.getInt("qte") +"");
        description.setText(bundle.getString("description"));
        cheackadmin();
        voidcheackgroupleader();



    }

    public void AddToBasket(View view) {
        //Toast.makeText(this, "ADD", Toast.LENGTH_SHORT).show();
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String res = jsonObject.getString("result");

                    if (res.equals("true")){

                        FabToast.makeText(Detail.this, "Equipement was Added To Your Basket" , FabToast.LENGTH_SHORT, FabToast.SUCCESS, FabToast.POSITION_DEFAULT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    } else {
                        FabToast.makeText(Detail.this, "Error" , FabToast.LENGTH_LONG, FabToast.ERROR, FabToast.POSITION_DEFAULT).show();


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
                String mail = new SessionManager(getApplicationContext()).getUserDetail().get("email") ;
                map.put("email",mail) ;
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        queue.add(request);


    }
    public void cheackadmin(){
        String email = new SessionManager(Detail.this).getUserDetail().get("email");

        if (email.toString().equals("marwen@admin.com")){

            button.setVisibility(View.INVISIBLE);
        }
        else{
            button.setVisibility(View.VISIBLE);
        }
    }
    public void voidcheackgroupleader(){

        String status = new SessionManager(Detail.this).getStatus().get("status");

if(status.toString().equals("1")){

    button.setVisibility(View.VISIBLE);
}
else {

    button.setVisibility(View.INVISIBLE);

}

    }

//////////////////////////////////////////

}


