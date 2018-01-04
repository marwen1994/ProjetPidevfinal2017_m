package com.example.marwen.projetpidevfinal2017.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.marwen.projetpidevfinal2017.EditProfile;
import com.example.marwen.projetpidevfinal2017.HomeActivity;
import com.example.marwen.projetpidevfinal2017.ImageProcessClass;
import com.example.marwen.projetpidevfinal2017.R;
import com.example.marwen.projetpidevfinal2017.User.AjoutMatrielNonDispo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import spencerstudios.com.fab_toast.FabToast;

public class AddMatdisponible extends AppCompatActivity {
    ImageView imageView ;
    EditText imagename , name , quantite , description ;
    ProgressDialog progressDialog ;
    Button Upload , add ;
    Bitmap bitmap = null ;
    String URL ="http://192.168.0.121/miniprojet/public/setMatdispo" ;
    String ImageName = "image_name" ;
    String ImagePath = "image_path" ;
    String imagenamex ;
    String namex ;
     String qte ;
    String descrippx ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_matdisponible);
        imageView =(ImageView) findViewById(R.id.imageView16);
        imagename = (EditText) findViewById(R.id.imagename);
        name = (EditText) findViewById(R.id.name);
        quantite = (EditText) findViewById(R.id.amount);
        description = (EditText) findViewById(R.id.description);
        Upload =(Button) findViewById(R.id.button10);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                intent.setType("image/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);


            }
        });



    }

    @Override
    protected void onActivityResult(int RC, int RQC, Intent I) {

        super.onActivityResult(RC, RQC, I);

        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

            Uri uri = I.getData();

            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
    public void ImageUploadToServerFunction(){

        ByteArrayOutputStream byteArrayOutputStreamObject ;

        byteArrayOutputStreamObject = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);

        byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

        final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {

            @Override
            protected void onPreExecute() {

                super.onPreExecute();

                progressDialog = ProgressDialog.show(AddMatdisponible.this,"Image is Uploading","Please Wait",false,false);

            }

            @Override
            protected void onPostExecute(String string1) {

                super.onPostExecute(string1);

                // Dismiss the progress dialog after done uploading.
                progressDialog.dismiss();

                // Printing uploading success message coming from server on android app.
                Log.d("tag",string1) ;
                // Setting image as transparent after done uploading.
                imageView.setImageResource(android.R.color.transparent);


            }

            @Override
            protected String doInBackground(Void... params) {
                ImageProcessClass imageProcessClass = new ImageProcessClass();

                HashMap<String,String> HashMapParams = new HashMap<String,String>();

                HashMapParams.put(ImageName,imagenamex);
                HashMapParams.put("image_data", ConvertImage);
                HashMapParams.put("image_name",imagenamex) ;
                HashMapParams.put("name",namex) ;
                HashMapParams.put("description",descrippx) ;
                HashMapParams.put("qte",qte) ;


                String FinalData = imageProcessClass.ImageHttpRequest(URL, HashMapParams);

                return FinalData;
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();

        AsyncTaskUploadClassOBJ.execute();
    }


    public void uploadimg(View view) {
        imagenamex = String.valueOf(imagename.getText()) ;
        namex = String.valueOf(name.getText()) ;
        qte = String.valueOf(quantite.getText());
        descrippx = String.valueOf(description.getText()) ;

        if (bitmap == null) {
            FabToast.makeText(AddMatdisponible.this, "Please Choose Photo" , FabToast.LENGTH_LONG, FabToast.WARNING, FabToast.POSITION_DEFAULT).show();
        }
      else if((imagename.equals(""))){
            FabToast.makeText(getApplicationContext(),"Invalid Impout", FabToast.LENGTH_SHORT, FabToast.ERROR,  FabToast.POSITION_DEFAULT).show();

        }
        else if ((namex.equals(""))){

            FabToast.makeText(getApplicationContext(),"Invalid Impout", FabToast.LENGTH_SHORT, FabToast.ERROR,  FabToast.POSITION_DEFAULT).show();
        }

     /*   else if (!(qte.toString().equals("[0-9]*"))){

            FabToast.makeText(getApplicationContext(),"Inter Price Number", FabToast.LENGTH_SHORT, FabToast.INFORMATION,  FabToast.POSITION_DEFAULT).show();

        }*/

        else if ((description.toString().equals(""))){
            FabToast.makeText(getApplicationContext(), "Invalid Impout", FabToast.LENGTH_SHORT, FabToast.INFORMATION,  FabToast.POSITION_DEFAULT).show();


        }
else {


     ImageUploadToServerFunction ();
            Intent intent = new Intent(AddMatdisponible.this,AdminMainActivity.class);
            startActivity(intent);
            FabToast.makeText(getApplicationContext(), "Your Equipement was added", FabToast.LENGTH_SHORT, FabToast.SUCCESS,  FabToast.POSITION_DEFAULT).show();

        }

    }
    @Override
    public void onBackPressed() {

    }
}
