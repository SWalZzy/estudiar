package com.santiagolandeta.estudiar.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.santiagolandeta.estudiar.Entities.Star;
import com.santiagolandeta.estudiar.R;

public class FragmentoDetalle extends Fragment {
    private Star star;
    private TextView tvHip;
    private TextView tvBf;
    private TextView tvDisc;
    private TextView tvMag;
    private TextView tvTipo;
    private TextView tvRa;
    private TextView tvDec;
    public interface IOnAttachListener{
        Star getStar();
    }
    public FragmentoDetalle() {
        super(R.layout.item_detalle);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvHip = view.findViewById(R.id.tvHip);
        tvBf = view.findViewById(R.id.tvBf);
        tvDisc = view.findViewById(R.id.tvDistancia);
        tvMag = view.findViewById(R.id.tvMagnitud);
        tvTipo = view.findViewById(R.id.tvTipoEspectral);
        tvRa = view.findViewById(R.id.tvRa);
        tvDec = view.findViewById(R.id.tvDec);

        if(star != null){
            showDetail(star);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener onAttachListener = (IOnAttachListener) context;
        star = onAttachListener.getStar();
    }

    public void showDetail(Star star) {
        this.star = star;
        tvHip.setText(star.getHip());
        tvBf.setText(star.getBf());
        tvDisc.setText(star.getDist());
        tvMag.setText(star.getMag());
        tvTipo.setText(star.getSpect());
        tvRa.setText(star.getRa());
        tvDec.setText(star.getDec());
    }
}
