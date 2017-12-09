package com.example.marwen.projetpidevfinal2017;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {


    private static int SOLASH_SCREAN8TYME= 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i =new Intent(Splash.this , Login.class);
                startActivity(i);
                finish();
            }
        } , SOLASH_SCREAN8TYME);
    }
}