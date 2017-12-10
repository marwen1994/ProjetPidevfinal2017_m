package com.example.marwen.projetpidevfinal2017;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class ListMatdispo extends AppCompatActivity {
 SwipeMenuListView LIST ;
    EditText search;
    List<Matdispo> l= new ArrayList<>();
    Matdispo m;
    SpinnerDialog  dialog ;
    CustomListAdapter adapter ;
    String url = "http://10.0.2.2/Miniprojet/public/getalldispo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_matdispo);
        LIST = (SwipeMenuListView) findViewById(R.id.list);
        search = (EditText) findViewById(R.id.serach);
/////////////// RECH FILTRE
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

/////////////rech SPINER
ArrayList<String> l1 = getlistnom();

dialog = new SpinnerDialog(ListMatdispo.this,l1,"Select Item");
         search.setOnLongClickListener(new View.OnLongClickListener() {
             @Override
             public boolean onLongClick(View view) {
                 dialog.showSpinerDialog();


                 return false;
             }
         });

dialog.bindOnSpinerListener(new OnSpinerItemClick() {
    @Override
    public void onClick(String s, int i) {
        search.setText(s);
    }
});










////////////////////////////////////
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray js=response.getJSONArray("result");

                    for(int i=0;i<js.length();i++) {

                        JSONObject jsa=js.getJSONObject(i);
                        m=new Matdispo();
                        m.setName(jsa.getString("name"));
                        m.setQte(Integer.parseInt(jsa.getString("qte")));
                        m.setDescription(jsa.getString("description"));
                        m.setImage_path(jsa.getString("image_path"));
                        l.add(m);
                    }

                    adapter=new CustomListAdapter(ListMatdispo.this,l);
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
        RequestQueue queue = Volley.newRequestQueue(ListMatdispo.this);

        queue.add(request);

////////////////////////////////////////////////////////////////////////
     LIST.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> adapterView, View viewx, int i, long l) {
             Matdispo m= (Matdispo) adapter.getItem(i);
             Intent intent = new Intent(ListMatdispo.this,Detail.class);
               Bundle bundle = new Bundle();
               bundle.putString("nom",m.getName());
               //Toast.makeText(ListMatdispo.this,m.getName(), Toast.LENGTH_SHORT).show();
               bundle.putInt("qte",m.getQte());
               bundle.putString("description",m.getDescription());
               bundle.putString("path",m.getImage_path());
             // Toast.makeText(ListMatdispo.this,m.getImage_path(), Toast.LENGTH_SHORT).show();

             //  ImageView imageView = (ImageView) viewx.findViewById(R.id.im);
              // bundle.putParcelable("image",((BitmapDrawable)imageView.getDrawable()).getBitmap());
               intent.putExtras(bundle);
               startActivity(intent);
         }
     });

     SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        ListMatdispo.this);
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0x00, 0x66,
                        0xff)));
                // set item width
                openItem.setWidth(500);
                // set item title
                openItem.setTitle("Add To Basket");
                // set item title fontsize
                openItem.setTitleSize(20);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);

                //openItem.setIcon();
                // add to menu
                menu.addMenuItem(openItem);


            }
        };
// set creator
        LIST.setMenuCreator(creator);
LIST.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
    @Override
    public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
        Toast.makeText(ListMatdispo.this, "This equipement is in your bascket", Toast.LENGTH_SHORT).show();
        Matdispo m= (Matdispo) adapter.getItem(position);
        Intent intent = new Intent(ListMatdispo.this,Basket.class);
        Bundle bundle = new Bundle();
        bundle.putString("nom",m.getName());
       // Toast.makeText(ListMatdispo.this,m.getName(), Toast.LENGTH_SHORT).show();
        bundle.putInt("qte",m.getQte());
        bundle.putString("description",m.getDescription());
        bundle.putString("path",m.getImage_path());
        //Toast.makeText(ListMatdispo.this,m.getImage_path(), Toast.LENGTH_SHORT).show();

        //  ImageView imageView = (ImageView) viewx.findViewById(R.id.im);
        // bundle.putParcelable("image",((BitmapDrawable)imageView.getDrawable()).getBitmap());
        intent.putExtras(bundle);
        startActivity(intent);
        return false;
    }
});



















        /////////////////

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
                        lx.add(jsa.getString("name"));
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
        RequestQueue queue = Volley.newRequestQueue(ListMatdispo.this);

        queue.add(request);

        return (ArrayList<String>) lx;
    }
}
