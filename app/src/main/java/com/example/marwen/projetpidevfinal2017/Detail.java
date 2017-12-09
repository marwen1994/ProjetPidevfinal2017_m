package com.example.marwen.projetpidevfinal2017;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Detail extends AppCompatActivity {
    ImageView image;
    TextView name , amount , description ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        image = (ImageView) findViewById(R.id.imgg);
        name = (TextView)findViewById(R.id.name);
        amount = (TextView)findViewById(R.id.amount);

        description = (TextView) findViewById(R.id.description);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
       // image.setImageBitmap((Bitmap)bundle.getParcelable("image"));
        Picasso.with(Detail.this).load(bundle.getString("path")).into(image);

        name.setText(bundle.getString("nom"));
        amount.setText(bundle.getInt("qte")+"");
        description.setText(bundle.getString("description"));



    }
}
