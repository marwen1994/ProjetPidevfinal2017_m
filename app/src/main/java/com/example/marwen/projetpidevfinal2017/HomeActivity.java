package com.example.marwen.projetpidevfinal2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
   ListView list ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //String a1 = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ec/Soccer_ball.svg/2000px-Soccer_ball.svg.png";
        String a2 = "https://s-media-cache-ak0.pinimg.com/736x/91/27/24/912724cb45a6a2ba468024a5285b01e7.jpg";
        String a1 = "http://10.0.2.2/MiniProjet/Images/Spiderman_Funny_Meme_Faces.jpg";
        String[] descriptionArray = {"Description 1", "Description 2","Description 3","Description 4","Description 5","Description 6"};
        String[] photoArray = {a1,a2,a1,a2,a1,a2,a1};
        String[] photoArray2 = {a2,a1,a2,a1,a2,a1,a2};
        String[] photoArray3 = {a1,a2,a1,a2,a1,a2,a1};
        String [] photoArray4 = {a2,a1,a2,a1,a2,a1,a2};
        String[] photoArray5 = {a1,a2,a1,a2,a1,a2,a1};
        String[] photoArray6 = {a2,a1,a2,a1,a2,a1,a2};
        String[] photoArray7 = {a1,a2,a1,a2,a1,a2,a1};





        list = (ListView) findViewById(R.id.listview);
        List<Bean> myList = new ArrayList<>();

        for(int i = 0; i < descriptionArray.length; i++) {
            Bean bean = new Bean();
            bean.setText(descriptionArray[i]);
            bean.setUrl(photoArray[i]);
            bean.setUrl2(photoArray2[i]);
            bean.setUrl3(photoArray3[i]);
            bean.setUrl4(photoArray4[i]);
            bean.setUrl5(photoArray5[i]);
            bean.setUrl6(photoArray6[i]);
            bean.setUrl7(photoArray7[i]);
            myList.add(bean);
        }

        MyAdapter adapter = new MyAdapter(HomeActivity.this, myList);
        list.setAdapter(adapter);

    }
}
