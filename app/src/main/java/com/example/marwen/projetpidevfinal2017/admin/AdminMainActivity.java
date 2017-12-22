package com.example.marwen.projetpidevfinal2017.admin;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.marwen.projetpidevfinal2017.EditProfile;
import com.example.marwen.projetpidevfinal2017.R;

import com.example.marwen.projetpidevfinal2017.User.Basket;

public class AdminMainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        Resources res = getResources();
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost); // initiate TabHost
        TabHost.TabSpec spec; // Reusable TabSpec for each tab
        Intent intent; // Reusable Intent for each tab

        spec = tabHost.newTabSpec("home");

        spec.setIndicator("",res.getDrawable(R.drawable.home))
        ;
        intent = new Intent(this, ListMatdispo.class);
        spec.setContent(intent);
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("Requests");

        spec.setIndicator("",res.getDrawable(R.drawable.basket));
        intent = new Intent(this, Basket.class);
        spec.setContent(intent);
        tabHost.addTab(spec);



        spec = tabHost.newTabSpec("Add New Equipement");
        spec.setIndicator("",res.getDrawable(R.drawable.plus));

        intent = new Intent(this, AddMatdisponible.class);
        spec.setContent(intent);
        tabHost.addTab(spec);


        spec = tabHost.newTabSpec("Add New Equipement");
        spec.setIndicator("",res.getDrawable(R.drawable.demande));

        intent = new Intent(this, AddMatdisponible.class);
        spec.setContent(intent);
        tabHost.addTab(spec);



        spec = tabHost.newTabSpec("Profile");
        spec.setIndicator("",res.getDrawable(R.drawable.man));

        intent = new Intent(this, EditProfile.class);
        spec.setContent(intent);
        tabHost.addTab(spec);





        tabHost.setCurrentTab(0);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                // display the name of the tab whenever a tab is changed
                Toast.makeText(getApplicationContext(), tabId, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
