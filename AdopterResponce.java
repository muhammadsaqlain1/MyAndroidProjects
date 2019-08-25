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
 * Created by usman on 5/27/2018.
 */

public class AdopterResponce extends BaseAdapter implements ListAdapter {
    private final Activity activity;
    private final JSONArray jsonArray;
    public AdopterResponce(Activity activity, JSONArray jsonArray) {
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

        return jsonObject.optLong("");
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = activity.getLayoutInflater().inflate(R.layout.listlayoutresponce, null);



        TextView pname =(TextView)convertView.findViewById(R.id.llayout_tvPatientName);
        TextView dname =(TextView)convertView.findViewById(R.id.llayout_tvDocName);
        TextView pdesc =(TextView)convertView.findViewById(R.id.llayout_tvPrescription);
        TextView med =(TextView)convertView.findViewById(R.id.llayout_tvmed);
        TextView dose =(TextView)convertView.findViewById(R.id.llayout_tvdose);



        JSONObject json_data = getItem(position);
        if(null!=json_data ){
            String pn= null;
            String dn=null;
            String pd=null;
            String m=null;
            String d=null;
            try {
                pn = json_data.getString("pname");

                dn=json_data.getString("docName");
                pd=json_data.getString("presc");
                m=json_data.getString("medicine");
                d=json_data.getString("dose");

            } catch (JSONException e) {
                e.printStackTrace();
            }



            pname.setText(pn);
            dname.setText(dn);
            pdesc.setText(pd);
            med.setText(m);
            dose.setText(d);



        }

        return convertView;
    }
}


