package com.santiagolandeta.estudiar.Adapters;

import android.location.GnssAntennaInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.santiagolandeta.estudiar.Entities.Star;
import com.santiagolandeta.estudiar.Interface.IOnClickListener;
import com.santiagolandeta.estudiar.R;

import java.util.ArrayList;

public class AdaptadorListado extends RecyclerView.Adapter<AdaptadorListado.ViewHolder>{
    private ArrayList<Star> stars;
    private IOnClickListener listener;

    public AdaptadorListado(ArrayList<Star> stars, IOnClickListener listener) {
        this.stars = stars;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdaptadorListado.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listado, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorListado.ViewHolder holder, int position) {
        holder.onBind(stars.get(position));
    }

    @Override
    public int getItemCount() {
        return stars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvProper, tvDist, tvMag, tvSpect;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvid);
            tvProper = itemView.findViewById(R.id.tvProper);
            tvDist = itemView.findViewById(R.id.tvDist);
            tvMag = itemView.findViewById(R.id.tvMag);
            tvSpect = itemView.findViewById(R.id.tvSpect);
        }

        public void onBind(Star star) {
            tvId.setText(star.getId());
            tvProper.setText(star.getProper());
            tvDist.setText(star.getDist());
            tvMag.setText(star.getMag());
            tvSpect.setText(star.getSpect());
        }
    }
}
