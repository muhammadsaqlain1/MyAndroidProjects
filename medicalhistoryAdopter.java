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

/**
 * Created by usman on 5/19/2018.
 */

public class medicalhistoryAdopter extends BaseAdapter implements ListAdapter {
    private final Activity activity;
    private final JSONArray jsonArray;
    public medicalhistoryAdopter(Activity activity, JSONArray jsonArray) {
        assert activity != null;
        assert jsonArray != null;

        this.jsonArray = jsonArray;
        this.activity = activity;
    }


    @Override public int getCount() {
        if(null==jsonArray)
            return 0;
        else
            return jsonArray.length();
    }

    @Override public JSONObject getItem(int position) {
        if(null==jsonArray) return null;
        else
            return jsonArray.optJSONObject(position);
    }

    @Override public long getItemId(int position) {
        JSONObject jsonObject = getItem(position);

        return jsonObject.optLong("id");
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = activity.getLayoutInflater().inflate(R.layout.medicalhistorylist, null);



        TextView prob =(TextView)convertView.findViewById(R.id.med_his_prob);
        TextView diagnose =(TextView)convertView.findViewById(R.id.med_his_diagnose);
        TextView medicine=(TextView)convertView.findViewById(R.id.med_his_medicine);

        String img=null;
        JSONObject json_data = getItem(position);
        if(null!=json_data ){
            String p= null;
            String d=null;
            String m=null;
            try {
                p = json_data.getString("pdesc");

                d=json_data.getString("diagnosed");
                m=json_data.getString("medicine");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            //byte[] decodedString = Base64.decode(img, Base64.DEFAULT);
            //Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

           // RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(Resources.getSystem(),decodedByte);
            //roundedBitmapDrawable.setCircular(true);
            //imageView.setImageDrawable(roundedBitmapDrawable);
            prob.setText(p);
            diagnose.setText(d);
            medicine.setText(m);
        }

        return convertView;
    }
}

