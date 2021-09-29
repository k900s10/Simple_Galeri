package com.example.ambilv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<DataList> aDataList;
    private Context mContext;

    public DataAdapter(ArrayList<DataList> aDataList, Context context) {
        this.aDataList = aDataList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.list_item, parent, false), this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataList currentData = aDataList.get(position);
        holder.bindTo(currentData);
    }

    @Override
    public int getItemCount() {
        return (aDataList != null) ? aDataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //deklarasi
        private TextView mNamaArtist;
        private TextView mNamaLagu;
        private ImageView mImageView;
        final DataAdapter mDataAdapter;

        public ViewHolder(@NonNull View itemView, DataAdapter mDataAdapter) {
            super(itemView);
            //inisialisasi
            mNamaArtist = itemView.findViewById(R.id.namaArtist);
            mNamaLagu = itemView.findViewById(R.id.namaLagu);
            mImageView = itemView.findViewById(R.id.imageView);
            this.mDataAdapter = mDataAdapter;
        }

        void bindTo(DataList currentData) {
            mNamaArtist.setText(currentData.getNamaArtist());
            mNamaLagu.setText(currentData.getNamaLagu());
            Glide.with(mContext).load(currentData.getImage());
        }

    }
}
