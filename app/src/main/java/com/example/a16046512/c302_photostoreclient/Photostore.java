package com.example.a16046512.c302_photostoreclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Photostore extends AppCompatActivity {

    private ListView lv;
    ArrayList<Photo> photo;
    ArrayAdapter aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photostore);
    }
    protected void onResume() {
        super.onResume();
        Intent i = getIntent();
        // Get the String array named "info" we passed in
        int id = i.getIntExtra("cateid",0);
        lv = (ListView) findViewById(R.id.lv);

        // Code for step 1 start
        HttpRequest request = new HttpRequest
                ("http://10.0.2.2/C302_P06_PhotoStoreWS/getPhotoStoreByCategory.php?category_id="+id);
        request.setOnHttpResponseListener(mHttpResponseListener);
        request.setMethod("GET");
        request.execute();
        // Code for step 1 end

    }

    // Code for step 2 start
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response) {


                    photo = new ArrayList<Photo>();


                    // process response here
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObj = jsonArray.getJSONObject(i);

                            int photoId = jsonObj.getInt("photo_id");
                            String title = jsonObj.getString("title");
                            String photodescription = jsonObj.getString("description");
                            String image = jsonObj.getString("image");
                            String createby = jsonObj.getString("created_by");
                            String displayResults = photodescription+"\n"+"Created by "+createby+"\n"+image ;

                            photo.add(new Photo(photoId, title, displayResults));

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    aa = new PhotoAdapter(Photostore.this, R.layout.photorow, photo);
                    lv.setAdapter(aa);


                }
            };
    // Code for step 2 end
}
