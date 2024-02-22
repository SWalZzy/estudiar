package com.santiagolandeta.estudiar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.santiagolandeta.estudiar.Entities.Star;
import com.santiagolandeta.estudiar.Fragments.FragmentoDetalle;
import com.santiagolandeta.estudiar.Fragments.FragmentoListado;
import com.santiagolandeta.estudiar.Interface.IOnClickListener;
import com.santiagolandeta.estudiar.Parser.Parser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IOnClickListener, FragmentoListado.IOnAttachListener {
    public static final String STARS_KEY = "com.santiagolandeta.estudiar.stars";
    public static final String SELECTED_STAR_KEY = "com.santiagolandeta.estudiar.selectedStar";
    private ArrayList<Star> stars;
    private FragmentoDetalle fragmentoDetalle;
    private int selectedStar;
    private boolean isDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(savedInstanceState != null){
            stars = (ArrayList<Star>) savedInstanceState.getSerializable(STARS_KEY);
            selectedStar = savedInstanceState.getInt(SELECTED_STAR_KEY);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isDetail = findViewById(R.id.fcv) != null;
        if(isDetail){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentoDetalle = (FragmentoDetalle) getSupportFragmentManager().findFragmentById(R.id.fcv);
            if(!(fragmentManager.findFragmentById(R.id.fcv) instanceof FragmentoListado)){
                FragmentoListado fragmentoListado = new FragmentoListado();
                fragmentManager.beginTransaction().add(R.id.fcv, fragmentoListado).commit();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(STARS_KEY, stars);
        outState.putInt(SELECTED_STAR_KEY, selectedStar);
    }

    public void loadData(){
        stars = new ArrayList<>();
        Parser parser = new Parser(this);
        if(parser.parse()){
            stars = parser.getStars();
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
    public int getSelectedStar() {
        return selectedStar;
    }

    @Override
    public void onClick(int position) {
        if (isDetail){
            fragmentoDetalle.showDetail(stars.get(position));
        }else{
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .replace(R.id.fcv, FragmentoDetalle.class , null)
                    .commit();
        }
    }
}