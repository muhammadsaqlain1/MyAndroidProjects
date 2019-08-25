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
 * Created by usman on 5/23/2018.
 */

public class AdopterForListofDocPerfomance extends BaseAdapter implements ListAdapter {
    private final Activity activity;
    private final JSONArray jsonArray;
    public AdopterForListofDocPerfomance(Activity activity, JSONArray jsonArray) {
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

        return jsonObject.optLong("email");
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = activity.getLayoutInflater().inflate(R.layout.list_of_doc_performance, null);



        TextView tvprob =(TextView)convertView.findViewById(R.id.list_of_Doc_performence_prob);
        TextView tvpresc =(TextView)convertView.findViewById(R.id.list_of_Doc_performence_presc);
        TextView tvmed =(TextView)convertView.findViewById(R.id.list_of_Doc_performence_med);
        //RatingBar ratingBar =(RatingBar) convertView.findViewById(R.id.Spec_Doc_DocList_ratingBar);


        String img=null;
        JSONObject json_data = getItem(position);
        if(null!=json_data ){
            String Pdesc= null;
            String presc=null;
            String med=null;
            try {
                Pdesc = json_data.getString("pDesc");

                presc=json_data.getString("presc");
                med=json_data.getString("med");

            } catch (JSONException e) {
                e.printStackTrace();
            }




            tvprob.setText(Pdesc);
            tvpresc.setText(presc);
            tvmed.setText(med);



           /* if(r==null)
            {
                ratingBar.setRating(0.0f);
            }*/

        }

        return convertView;
    }
}


