package com.example.marwen.projetpidevfinal2017.User;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
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

import spencerstudios.com.fab_toast.FabToast;

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
        final TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost); // initiate TabHost
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





        tabHost.setCurrentTab(0);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                FabToast.makeText(getApplicationContext(), tabId, FabToast.LENGTH_SHORT, FabToast.INFORMATION,  FabToast.POSITION_DEFAULT).show();
                for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
                {
                    tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.cardview_light_background);
                }

                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.YELLOW);
            }
        });


    }


}
