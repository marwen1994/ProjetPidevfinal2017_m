package com.example.marwen.projetpidevfinal2017;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends TabActivity {
    /**
     * Called when the activity is first created.
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost); // initiate TabHost
        TabHost.TabSpec spec; // Reusable TabSpec for each tab
        Intent intent; // Reusable Intent for each tab

        spec = tabHost.newTabSpec("home");

        spec.setIndicator("",res.getDrawable(R.drawable.home));
        intent = new Intent(this, ListMatdispo.class);
        spec.setContent(intent);
        tabHost.addTab(spec);


        spec = tabHost.newTabSpec("Bascket");

        spec.setIndicator("",res.getDrawable(R.drawable.basket));
        intent = new Intent(this, Basket.class);
        spec.setContent(intent);
        tabHost.addTab(spec);


        // Do the same for the other tabs
        spec = tabHost.newTabSpec("Add Product");
        spec.setIndicator("",res.getDrawable(R.drawable.plus));

        intent = new Intent(this, AddMatdisponible.class);
        spec.setContent(intent);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Mail");
        spec.setIndicator("",res.getDrawable(R.drawable.mail));

        intent = new Intent(this, Reclamation.class);
        spec.setContent(intent);
        tabHost.addTab(spec);


        spec = tabHost.newTabSpec("Profile");
        spec.setIndicator("",res.getDrawable(R.drawable.man));

        intent = new Intent(this, ProfileActivity.class);
        spec.setContent(intent);
        tabHost.addTab(spec);





        tabHost.setCurrentTab(1);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                // display the name of the tab whenever a tab is changed
                Toast.makeText(getApplicationContext(), tabId, Toast.LENGTH_SHORT).show();
            }
        });


    }


}
