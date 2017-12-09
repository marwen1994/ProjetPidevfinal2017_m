package com.example.marwen.projetpidevfinal2017;

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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;



public class AjoutMatrielNonDispo extends AppCompatActivity {
    EditText grpname,name,prix,description;
    ImageView matnonimg ;
    Button ajouter ;
    Bitmap bitmap;
    ProgressDialog progressDialog ;
    String ImageName = "image_name" ;
    String ImagePath = "image_path" ;
    String imgname ;
    String ServerUploadPath ="http://10.0.2.2/miniprojet/public/setMat" ;
    String groupname ;
    String prixx ;
    String descripp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_matriel_non_dispo);

        matnonimg = (ImageView) findViewById(R.id.matnonimg) ;
        grpname=(EditText) findViewById(R.id.grpname);
        name=(EditText) findViewById(R.id.matname);
        prix=(EditText) findViewById(R.id.prix);
        description=(EditText) findViewById(R.id.descrip);
        ajouter = (Button)findViewById(R.id.ajouter) ;

    }

    @Override
    protected void onActivityResult(int RC, int RQC, Intent I) {

        super.onActivityResult(RC, RQC, I);

        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

            Uri uri = I.getData();

            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                matnonimg.setImageBitmap(bitmap);

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

                progressDialog = ProgressDialog.show(AjoutMatrielNonDispo.this,"Image is Uploading","Please Wait",false,false);
            }

            @Override
            protected void onPostExecute(String string1) {

                super.onPostExecute(string1);

                // Dismiss the progress dialog after done uploading.
                progressDialog.dismiss();

                // Printing uploading success message coming from server on android app.
                Toast.makeText(AjoutMatrielNonDispo.this,string1,Toast.LENGTH_LONG).show();
                Log.d("tag",string1) ;
                // Setting image as transparent after done uploading.
                matnonimg.setImageResource(android.R.color.transparent);


            }

            @Override
            protected String doInBackground(Void... params) {

                ImageProcessClass imageProcessClass = new ImageProcessClass();

                HashMap<String,String> HashMapParams = new HashMap<String,String>();

                HashMapParams.put(ImageName,imgname);

                HashMapParams.put(ImagePath, ConvertImage);
                HashMapParams.put("name",imgname) ;
                HashMapParams.put("groupname",groupname) ;
                HashMapParams.put("prix",prixx) ;
                HashMapParams.put("description",descripp) ;


                String FinalData = imageProcessClass.ImageHttpRequest(ServerUploadPath, HashMapParams);

                return FinalData;
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();

        AsyncTaskUploadClassOBJ.execute();
    }

    public void uploadimg(View view) {
        Intent intent = new Intent();

        intent.setType("image/*");

        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);
    }

    public void ajouter(View view) {
        imgname = String.valueOf(name.getText()) ;
        groupname = String.valueOf(grpname.getText()) ;
        prixx = String.valueOf(prix.getText()) ;
        descripp = String.valueOf(description.getText());
        ImageUploadToServerFunction() ;

    }
}
