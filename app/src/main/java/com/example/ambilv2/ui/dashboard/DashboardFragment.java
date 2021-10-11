package com.example.ambilv2.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ambilv2.DataAdapter;
import com.example.ambilv2.DataList;
import com.example.ambilv2.R;
import com.example.ambilv2.databinding.FragmentDashboardBinding;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    private RecyclerView mRecyclerView;
    private ArrayList<DataList> aDataList;
    private DataAdapter mDataAdapter;

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

        // Initialize the RecyclerView.
        mRecyclerView = view.findViewById(R.id.recycleViewDashboard);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize the ArrayList that will contain the data.
        aDataList = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mDataAdapter = new DataAdapter(aDataList, getActivity());
        mRecyclerView.setAdapter(mDataAdapter);

        // Get the data.
        setData();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setData() {
        String[] namaArtist = getResources()
                .getStringArray(R.array.namaArtist);
        String[] namaLagu = getResources()
                .getStringArray(R.array.namaLagu);
        int image = getResources().getIdentifier("@drawable/profil_picture", null, getActivity().getPackageName());
        aDataList.clear();

        for (int i = 0; i < namaArtist.length; i++) {
            aDataList.add(new DataList(namaLagu[i], namaArtist[i], image));
        }
        mDataAdapter.notifyDataSetChanged();
    }
}