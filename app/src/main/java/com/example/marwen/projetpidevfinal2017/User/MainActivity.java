package com.example.marwen.projetpidevfinal2017.User;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marwen.projetpidevfinal2017.EditProfile;
import com.example.marwen.projetpidevfinal2017.User.Basket;
import com.example.marwen.projetpidevfinal2017.ProfileActivity;
import com.example.marwen.projetpidevfinal2017.R;
import com.example.marwen.projetpidevfinal2017.Reclamation;
import com.example.marwen.projetpidevfinal2017.admin.AddMatdisponible;
import com.example.marwen.projetpidevfinal2017.admin.ListMatdispo;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends TabActivity {
    /**
     * Called when the activity is first created.
     */
   TextView textView,textView1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().subscribeToTopic("news");
  /*      textView= (TextView) findViewById(R.id.well);
        textView1= (TextView) findViewById(R.id.image);*/
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


        // Do the same for the other tabs
        spec = tabHost.newTabSpec("Add Product");
        spec.setIndicator("",res.getDrawable(R.drawable.plus));

        intent = new Intent(this, AjoutMatrielNonDispo.class);
        spec.setContent(intent);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Reclamtion");
        spec.setIndicator("",res.getDrawable(R.drawable.mail));

        intent = new Intent(this, Reclamation.class);
        spec.setContent(intent);
        tabHost.addTab(spec);


        spec = tabHost.newTabSpec("Profile");
        spec.setIndicator("",res.getDrawable(R.drawable.man));

        intent = new Intent(this, EditProfile.class);
        spec.setContent(intent);
        tabHost.addTab(spec);





        tabHost.setCurrentTab(2);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                // display the name of the tab whenever a tab is changed
                Toast.makeText(getApplicationContext(), tabId, Toast.LENGTH_SHORT).show();
            }
        });


    }


}
