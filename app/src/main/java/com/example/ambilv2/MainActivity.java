package com.example.ambilv2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ambilv2.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ActivityMainBinding binding;
    private EditText mArtistName;
    private EditText mSongTitle;
    public static final String keyArtist = "keyArtist";
    public static final String keyTitle = "keyTitle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mArtistName = findViewById(R.id.artis_name);
        mSongTitle = findViewById(R.id.song_title_label);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        textView = findViewById(R.id.hasilPencarian);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);



    }


//    public void searchLyric(View view) {
//        Log.i("Nama Artist", "searchLyric: " + mArtistName.getText().toString());
//        // Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url ="https://api.lyrics.ovh/v1/" + mArtistName.getText().toString() + "/" + mSongTitle.getText().toString();
//
//        // Request a string response from the provided URL.
//        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONOAbject response) {
//
//                        // Display the first 500 characters of the response string.
//
//                        try {
//                            //JSONObject jsonobject = response.getJSONObject("lyrics");
//                           // Log.i("TAG", "onResponse: "+ jsonobject);
//                            textView.setText(response.getString("lyrics"));
//                        } catch (JSONException e) {
//                           e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                textView.setText("That didn't work!");
//            }
//        });
//
//// Add the request to the RequestQueue.
//        queue.add(stringRequest);
//    }



}