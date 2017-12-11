package com.example.marwen.projetpidevfinal2017.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import com.example.marwen.projetpidevfinal2017.R;

public class Basket extends AppCompatActivity {
    List<Matdispo> listm = new ArrayList<>() ;
    SwipeMenuListView LIST ;
    Matdispo m ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        LIST = (SwipeMenuListView) findViewById(R.id.list_Basket);
//            Intent i = getIntent();
//
//
//            Bundle bundle = i.getExtras();
//            // image.setImageBitmap((Bitmap)bundle.getParcelable("image"));
//            String imagepath = bundle.getString("path");
//
//            String name = bundle.getString("nom");
//            int amount = bundle.getInt("qte");
//            String description = bundle.getString("description");
//            Toast.makeText(this, description, Toast.LENGTH_SHORT).show();
//            m=new Matdispo();
//            m.setName("maw");
//         //   Toast.makeText(this, m.getName(), Toast.LENGTH_SHORT).show();
//            m.setQte(15);
//            m.setDescription("jjjjjjjjj");
//            m.setImage_path("jjjjjjjjj");
//            listm.add(m);
//
//            BasketAdapter adapter = new BasketAdapter(Basket.this, listm);
//            LIST.setAdapter(adapter);


    }

    @Override
    public void onResume(){
        super.onResume();


    }

    public void testv (){

    }
}
