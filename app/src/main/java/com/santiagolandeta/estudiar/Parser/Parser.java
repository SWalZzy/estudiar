package com.santiagolandeta.estudiar.Parser;

import android.content.Context;

import com.santiagolandeta.estudiar.Entities.Star;
import com.santiagolandeta.estudiar.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Parser {
    private final InputStream file;
    ArrayList<Star> stars;

    public Parser(Context context) {
        this.file = context.getResources().openRawResource(R.raw.stars);
    }

    public boolean parse(){
        boolean parsed = false;
        String json = null;

        try {
            int size = file.available();
            byte[] buffer = new byte[size];
            file.read(buffer);
            file.close();
            json = new String(buffer, "UTF-8");
            JSONTokener tokener = new JSONTokener(json);
            JSONArray jsonArray = new JSONArray(tokener);
            stars = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject star = jsonArray.getJSONObject(i);
                String id = star.getString("id");
                String hip = star.getString("hip");
                String bf = star.getString("bf");
                String proper = star.getString("proper");
                String ra = star.getString("ra");
                String dec = star.getString("dec");
                String dist = star.getString("dist");
                String mag = star.getString("mag");
                String spect = star.getString("spect");
                stars.add(new Star(id, hip, bf, proper, ra, dec, dist, mag, spect));
            }
            parsed = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return parsed;
    }
    public ArrayList<Star> getStars() {
        return stars;
    }
}
