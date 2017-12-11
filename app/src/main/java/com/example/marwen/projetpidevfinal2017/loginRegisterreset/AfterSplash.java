package com.example.marwen.projetpidevfinal2017.loginRegisterreset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.marwen.projetpidevfinal2017.R;

public class AfterSplash extends AppCompatActivity {
    Button button ,button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_splash);
        button = (Button) findViewById(R.id.login);
        button1 = (Button) findViewById(R.id.register);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AfterSplash.this,Login.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AfterSplash.this,Register.class);
                startActivity(intent);
            }
        });

    }
}
