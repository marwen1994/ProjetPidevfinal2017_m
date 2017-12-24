package com.example.marwen.projetpidevfinal2017.admin;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marwen.projetpidevfinal2017.R;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class DeatailMatNonDispo extends AppCompatActivity {
    ImageView image;
    TextView name, amount, description , groupe;
    int id;
    String imgpath;
    Button url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatail_mat_non_dispo);
        image = (ImageView) findViewById(R.id.imgg);
        name = (TextView) findViewById(R.id.name);
        amount = (TextView) findViewById(R.id.price);
         description = (TextView) findViewById(R.id.description);
        groupe = (TextView) findViewById(R.id.groupe);
        url = (Button) findViewById(R.id.URL);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        // image.setImageBitmap((Bitmap)bundle.getParcelable("image"));
        Picasso.with(DeatailMatNonDispo.this).load(bundle.getString("path")).into(image);
        id = bundle.getInt("id");
        imgpath = bundle.getString("path");
        name.setText(bundle.getString("nom"));
        amount.setText(bundle.getInt("prix") + "");
        description.setText(bundle.getString("description"));
        groupe.setText(bundle.getString("Group"));
        url.setText(bundle.getString("url"));


    }

    public void open(View view) {
        Intent intent = new Intent(DeatailMatNonDispo.this,WebView.class);
        Bundle bundle = new Bundle();
        bundle.putString("url",url.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void accept(View view) {
    }

    public void refuse(View view) {
    }
}
