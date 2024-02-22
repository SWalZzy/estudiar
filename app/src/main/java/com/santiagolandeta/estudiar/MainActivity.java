package com.santiagolandeta.estudiar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.santiagolandeta.estudiar.Entities.Star;
import com.santiagolandeta.estudiar.Fragments.FragmentoDetalle;
import com.santiagolandeta.estudiar.Fragments.FragmentoListado;
import com.santiagolandeta.estudiar.Interface.IOnClickListener;
import com.santiagolandeta.estudiar.Parser.Parser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IOnClickListener, FragmentoListado.IOnAttachListener, FragmentoDetalle.IOnAttachListener {
    public static final String STARS_KEY = "com.santiagolandeta.estudiar.stars";
    public static final String SELECTED_STAR_KEY = "com.santiagolandeta.estudiar.selectedStar";
    private ArrayList<Star> stars;
    private FragmentoDetalle fragmentoDetalle;
    private Star selectedStar;
    private boolean isDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(savedInstanceState != null){
            stars = (ArrayList<Star>) savedInstanceState.getSerializable(STARS_KEY);
            selectedStar = (Star) savedInstanceState.getSerializable(SELECTED_STAR_KEY);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isDetail = findViewById(R.id.fcvDetail_fragment) != null;
        if(isDetail){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentoDetalle = (FragmentoDetalle) fragmentManager.findFragmentById(R.id.fcvDetail_fragment);
            if(!(fragmentManager.findFragmentById(R.id.fcvListFragment) instanceof FragmentoListado)){
                FragmentoListado fragmentoListado = new FragmentoListado();
                fragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .replace(R.id.fcvListFragment, fragmentoListado)
                        .commit();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(STARS_KEY, stars);
        outState.putSerializable(SELECTED_STAR_KEY, selectedStar);
        super.onSaveInstanceState(outState);
    }

    public void loadData(){
        Parser parser = new Parser(this);
        if(parser.parse()){
            this.stars = parser.getStars();
        }
    }

    @Override
    public ArrayList<Star> getStars() {
        if(stars == null){
            loadData();
        }
        return stars;
    }

    @Override
    public void onClick(int position) {
        selectedStar = stars.get(position);
        if (isDetail){
            fragmentoDetalle.showDetail(selectedStar);
        }else{
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentoDetalle = new FragmentoDetalle();
            fragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .replace(R.id.fcvListFragment, fragmentoDetalle)
                    .commit();
        }
    }
    @Override
    public Star getStar() {
        return selectedStar;
    }
}