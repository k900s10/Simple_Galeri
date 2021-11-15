package com.example.ambilv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SearchLyricOutput extends AppCompatActivity {



    ScrollView scrollView;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_lyric_output);


        textView = findViewById(R.id.output_lirik);
        Intent intent = getIntent();
        String artis = intent.getStringExtra(MainActivity.keyArtist);
        String title = intent.getStringExtra(MainActivity.keyTitle);
        nyariLirik(artis, title);


    }

    public void nyariLirik(String artis, String title) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.lyrics.ovh/v1/" + artis + "/" + title ;

        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        // Display the first 500 characters of the response string.

                        try {
                            //JSONObject jsonobject = response.getJSONObject("lyrics");
                            // Log.i("TAG", "onResponse: "+ jsonobject);
                            textView.setText(response.getString("lyrics"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });

        queue.add(stringRequest);

    }
}