package com.example.marwen.projetpidevfinal2017.admin;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;


import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.marwen.projetpidevfinal2017.EditProfile;
import com.example.marwen.projetpidevfinal2017.R;

import com.example.marwen.projetpidevfinal2017.SessionManager;
import com.example.marwen.projetpidevfinal2017.User.Basket;
import com.example.marwen.projetpidevfinal2017.loginRegisterreset.Login;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;
import java.util.ArrayList;
import java.util.List;

public class AdminMainActivity extends TabActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);



        Resources res = getResources();
        final TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost); // initiate TabHost
        TabHost.TabSpec spec; // Reusable TabSpec for each tab
        Intent intent; // Reusable Intent for each tab

        spec = tabHost.newTabSpec("home");
        spec.setIndicator("",res.getDrawable(R.drawable.home));
        intent = new Intent(this, ListMatdispo.class);
        spec.setContent(intent);
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("Requests");

        spec.setIndicator("",res.getDrawable(R.drawable.basket));
        intent = new Intent(this, DemendeGrp.class);
        spec.setContent(intent);
        tabHost.addTab(spec);



        spec = tabHost.newTabSpec("Add New Equipement");
        spec.setIndicator("",res.getDrawable(R.drawable.plus));

        intent = new Intent(this, AddMatdisponible.class);
        spec.setContent(intent);
        tabHost.addTab(spec);


        spec = tabHost.newTabSpec("Requests of Equipement");
        spec.setIndicator("",res.getDrawable(R.drawable.demande));

        intent = new Intent(this, ListMatNondispo.class);
        spec.setContent(intent);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Users List");
        spec.setIndicator("",res.getDrawable(R.drawable.group));

        intent = new Intent(this, Listeusers.class);
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
                Toast.makeText(getApplicationContext(), tabId, Toast.LENGTH_SHORT).show();
                for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
                {
                    tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.cardview_light_background);
                }

                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.YELLOW);
            }
        });

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu__drawer_drawer,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
*/



}
