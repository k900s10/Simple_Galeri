package com.example.ambilv2.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ambilv2.R;
import com.example.ambilv2.SearchLyricOutput;
import com.example.ambilv2.databinding.FragmentHomeBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    EditText mArtistName;
    EditText mSongTitle;
    TextView textView;
    private String keyArtist = "keyArtist";
    private String keyTitle = "keyTitle";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button searchLirik = view.findViewById(R.id.searchButton);
        mArtistName = view.findViewById(R.id.artis_name);
        mSongTitle = view.findViewById(R.id.song_title_label);
        textView = view.findViewById(R.id.hasilPencarian);
        searchLirik.setOnClickListener(v->mencariLirik());
    }

    private void mencariLirik (){

        String artis = mArtistName.getText().toString();
        String title = mSongTitle.getText().toString();
        if (artis.matches("") || title.matches("")) {
            return;
        } else {
            Intent intent = new Intent(getActivity(), SearchLyricOutput.class);
            intent.putExtra(keyArtist, artis);
            intent.putExtra(keyTitle, title);
            startActivity(intent);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //final TextView textView = binding.titleHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}