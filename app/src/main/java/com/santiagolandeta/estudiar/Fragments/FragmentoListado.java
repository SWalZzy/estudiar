package com.santiagolandeta.estudiar.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.santiagolandeta.estudiar.Adapters.AdaptadorListado;
import com.santiagolandeta.estudiar.Interface.IOnClickListener;
import com.santiagolandeta.estudiar.R;
import com.santiagolandeta.estudiar.Entities.Star;

import java.util.ArrayList;

public class FragmentoListado extends Fragment {

    public interface IOnAttachListener{
        ArrayList<Star> getStars();
        int getSelectedStar();
    }
    private ArrayList<Star> stars;
    private int selectedStar;
    private IOnClickListener listener;

    public FragmentoListado() {
        super(R.layout.fragment_listado);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AdaptadorListado adaptadorStar = new AdaptadorListado(stars, listener);
        RecyclerView recyclerView = view.findViewById(R.id.rvListado);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptadorStar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.scrollToPosition(selectedStar);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (IOnClickListener) context;
        IOnAttachListener onAttachListener = (IOnAttachListener) context;
        stars = onAttachListener.getStars();
        selectedStar = onAttachListener.getSelectedStar();
    }

}
