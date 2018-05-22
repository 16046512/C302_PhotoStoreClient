package com.example.a16046512.c302_photostoreclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvCategories;
    ArrayList<Category> category;
    ArrayAdapter aaCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    protected void onResume() {
        super.onResume();

        lvCategories = (ListView) findViewById(R.id.listViewCategories);

        // Code for step 1 start
        HttpRequest request = new HttpRequest
                ("http://10.0.2.2/C302_P06_PhotoStoreWS/getCategories.php");
        request.setOnHttpResponseListener(mHttpResponseListener);
        request.setMethod("GET");

        request.execute();
        // Code for step 1 end
        lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Category selectedCategory = category.get(i);
                //toast
                Intent a = new Intent(MainActivity.this, Photostore.class);
                a.putExtra("cateid", selectedCategory.getid());
                startActivity(a);
//                Toast.makeText(MainActivity.this,selectedCategory.getid()+"",Toast.LENGTH_LONG).show();
            }
        });
    }

    // Code for step 2 start
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response) {


                    category = new ArrayList<Category>();


                    // process response here
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObj = jsonArray.getJSONObject(i);

                            int categoryId = jsonObj.getInt("category_id");
                            String categoryName = jsonObj.getString("name");
                            String description = jsonObj.getString("description");

                            String displayResults = "Category Id: " + categoryId + "\n\nCategory Name: "
                                    + categoryName + "\n\nDescription: " + description + "\n";
                            category.add(new Category(categoryId, categoryName, description));

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    aaCategories = new CategoryAdapter(MainActivity.this, R.layout.categoryrow, category);
                    lvCategories.setAdapter(aaCategories);


                }
            };
    // Code for step 2 end

}
