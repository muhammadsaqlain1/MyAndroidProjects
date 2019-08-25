package com.example.usman.virtualclinic;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by usman on 5/27/2018.
 */

public class AdopterSearchPatient extends BaseAdapter implements ListAdapter {
    private final Activity activity;
    private final ArrayList<SearchedPatient> alSP;
    public AdopterSearchPatient(Activity activity, ArrayList<SearchedPatient> j) {
        assert activity != null;
        assert j != null;

        this.alSP = j;
        this.activity = activity;
    }


    @Override public int getCount() {
        if(null==alSP)
            return 0;
        else
            return alSP.size();
    }

    @Override public SearchedPatient getItem(int position) {
        if(null==alSP) return null;
        else
            return alSP.get(position);
    }

    @Override public long getItemId(int position) {
        SearchedPatient jsonObject = getItem(position);

        return 0;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = activity.getLayoutInflater().inflate(R.layout.patientlist, null);



        TextView text1 =(TextView)convertView.findViewById(R.id.pllname);
        TextView text2 =(TextView)convertView.findViewById(R.id.pllproblem);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.pllimage);


        text1.setText(alSP.get(position).name);
        text2.setText(alSP.get(position).gender);
        imageView.setImageDrawable(alSP.get(position).pic);

        return convertView;
    }
}


