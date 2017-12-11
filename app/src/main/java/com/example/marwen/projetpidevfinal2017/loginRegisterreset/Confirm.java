package com.example.marwen.projetpidevfinal2017.loginRegisterreset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.marwen.projetpidevfinal2017.R;

public class Confirm extends AppCompatActivity {
 TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cofirm);
        textView=(TextView) findViewById(R.id.textView12);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Confirm.this,Login.class);
                startActivity(intent);
            }
        });
    }
}
