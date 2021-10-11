package com.example.ambilv2.ui.dashboard;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ambilv2.DataAdapter;
import com.example.ambilv2.DataList;
import com.example.ambilv2.R;
import com.example.ambilv2.databinding.FragmentDashboardBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    private RecyclerView mRecyclerView;
    private ArrayList<DataList> aDataList;
    private DataAdapter mDataAdapter;

    private RecyclerView.Adapter adapter;
    private List<Billboard> billboardList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        mRecyclerView = view.findViewById(R.id.recycleViewDashboard);
//        mDataAdapter = new DataAdapter(getActivity(), aDataList);
//        mRecyclerView.setAdapter(mDataAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        setData();

//        // Initialize the RecyclerView.
//        mRecyclerView = view.findViewById(R.id.recycleViewDashboard);
//
//        // Set the Layout Manager.
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        // Initialize the ArrayList that will contain the data.
//        aDataList = new ArrayList<>();
//
//        // Initialize the adapter and set it to the RecyclerView.
//        mDataAdapter = new DataAdapter(aDataList, getActivity());
//        mRecyclerView.setAdapter(mDataAdapter);
//
//        // Get the data.
//        setData();

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setData() {
//        String[] namaArtist = getResources()
//                .getStringArray(R.array.namaArtist);
//        String[] namaLagu = getResources()
//                .getStringArray(R.array.namaLagu);
//        int image = getResources().getIdentifier("@drawable/profil_picture", null, getActivity().getPackageName());
//        aDataList.clear();
//
//        for (int i = 0; i < namaArtist.length; i++) {
//            aDataList.add(new DataList(namaLagu[i], namaArtist[i], image));
//        }
//        mDataAdapter.notifyDataSetChanged();
    }

    private void setDataBillboard() {
        final String url = "https://billboard-api2.p.rapidapi.com/hot-100?range=1-10&date=2019-05-11";
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
//                    String data = "";
                    try {
                        JSONObject objectContent = response.getJSONObject("content");
////                        JSONArray jsonArray = response.getJSONArray("results");
                        for (int i = 1; i <= objectContent.length(); i++) {
                            JSONObject jsonObject = objectContent.getJSONObject(String.valueOf(i));

                            String mRank = jsonObject.getString("rank");
                            String mTitle = jsonObject.getString("title");
                            String mArtist = jsonObject.getString("artist");
                            String mDetail = jsonObject.getString("detail");

                            Log.i("PantekkunIki", "getDataBillboard: " + mRank + " " + mTitle + " " + mArtist + " " + mDetail);

                            Billboard billboard = new Billboard();
                            billboard.setRank(mRank);
                            billboard.setTitle(mTitle);
                            billboard.setArtist(mArtist);
                            billboard.setDetail(mDetail);

                            billboardList.add(billboard);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    adapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                }, error -> {
            error.printStackTrace();
            Log.e("VolleyRequest", error.toString() );
            progressDialog.dismiss();
        }){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params =  new HashMap<String, String>();
                params.put("x-rapidapi-host", "billboard-api2.p.rapidapi.com");
                params.put("x-rapidapi-key", "9311da88c8msh4f147985b871b84p1024a4jsndbc5f1ad194d");

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);

    }
}