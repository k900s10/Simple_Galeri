package com.example.ambilv2.ui.dashboard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ambilv2.R;

import java.util.List;

public class BillboardAdapter extends RecyclerView.Adapter<BillboardAdapter.ViewHolder> {
    private List<Billboard> billboardList;
    private Context context;

    public BillboardAdapter(Context context, List<Billboard> billboardList) {
        this.context = context;
        this.billboardList = billboardList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.list_billboard, parent, false), this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Billboard billboard = billboardList.get(position);

        holder.mRank.setText(billboard.getRank());
        holder.mTitle.setText(billboard.getTitle());
        holder.mArtist.setText(billboard.getArtist());
    }

    @Override
    public int getItemCount() {
        Log.i("BillboardAdapter", "getItemCount: " + billboardList.size());
        return (billboardList != null) ? billboardList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mRank, mTitle, mArtist;
        BillboardAdapter billboardAdapter;

        public ViewHolder(@NonNull View itemView, BillboardAdapter billboardAdapter) {
            super(itemView);

            this.mRank = itemView.findViewById(R.id.lRank);
            this.mTitle = itemView.findViewById(R.id.lTitle);
            this.mArtist = itemView.findViewById(R.id.lArtist);
            this.billboardAdapter = billboardAdapter;
        }
    }
}
