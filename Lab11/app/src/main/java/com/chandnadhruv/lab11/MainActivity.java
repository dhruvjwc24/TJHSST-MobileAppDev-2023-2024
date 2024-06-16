package com.chandnadhruv.lab11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Looper;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    RequestQueue queue;
    String URL = "https://mw-demo.sites.tjhsst.edu/data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.response);
        queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println("In here");
                    //format response to JSON object called "data"
                    JSONObject object = new JSONObject("{\"data\":"+response+"}");
                    //convert object to an array of objects
                    JSONArray array = object.getJSONArray("data");
                    //create a handler
                    Handler handler = new Handler(Looper.getMainLooper());
                    String output = "";
                    //iterate through each object in the array
                    for (int i = 0; i < array.length(); i++) {
                        try {
                            //get object and display its id and category
                            JSONObject jsonObject = array.getJSONObject(i);
                            String id = jsonObject.getString("id");
                            String category = jsonObject.getString("category");
                            output+= "id: " + id + "\tcat: " + category + "\n";
//                            textView.setText("id: " + id + "\ncat: " + category);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    textView.setText(output);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("volley error"+ error.toString());
            }
        });
        queue.add(request);
    }
}
