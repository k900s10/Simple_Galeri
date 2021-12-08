package com.example.ambilv2.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ambilv2.MainActivity;
import com.example.ambilv2.R;
import com.example.ambilv2.SearchLyricOutput;
import com.example.ambilv2.databinding.FragmentDashboardBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private BillboardAdapter adapter;
    private List<Billboard> billboardList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycleViewDashboard);
        billboardList = new ArrayList<>();
        adapter = new BillboardAdapter(view.getContext().getApplicationContext(), billboardList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);

        setDataBillboard();
    }

//    private void mencariLirikFromChart (){
//
//        String artis = adapter.onClickArtistName;
//        String title = adapter.onClickSongName;
//        if (artis.matches("") || title.matches("")) {
//            return;
//        } else {
//            Intent intent = new Intent(getActivity(), SearchLyricOutput.class);
//            intent.putExtra(MainActivity.keyArtist, artis);
//            intent.putExtra(MainActivity.keyTitle, title);
//            startActivity(intent);
//        }
//    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void setDataBillboard() {
        final String url = "https://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=c000f3d64ff5fc28122f4892e2e36762&format=json";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        //opening doors
                        JSONObject firstGateOfObjectCotent = response.getJSONObject("tracks");
                        JSONArray secondGateOfObjectCotent = firstGateOfObjectCotent.getJSONArray("track");

                        //getting data
                        for (int i = 0; i <= secondGateOfObjectCotent.length(); i++) {
                            JSONObject contentOfJsonObject = secondGateOfObjectCotent.getJSONObject(i);

                            String mRank = String.valueOf(i + 1);
                            String mTitle = contentOfJsonObject.getString("name");
                            String mArtist = contentOfJsonObject.getJSONObject("artist").getString("name");

                            //inserting data
                            Billboard billboard = new Billboard();
                            billboard.setRank(mRank);
                            billboard.setTitle(mTitle);
                            billboard.setArtist(mArtist);

                            billboardList.add(billboard);
                            adapter.notifyDataSetChanged();
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
            error.printStackTrace();
            Log.e("VolleyRequest", error.toString());
        });

        RequestQueue requestQueue = Volley.newRequestQueue(requireActivity());
        requestQueue.add(jsonObjectRequest);
    }
}