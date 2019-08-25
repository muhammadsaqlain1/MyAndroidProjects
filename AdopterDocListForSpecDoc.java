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
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by usman on 5/20/2018.
 */

public class AdopterDocListForSpecDoc extends BaseAdapter implements ListAdapter {
    private final Activity activity;
    private final JSONArray jsonArray;
    public AdopterDocListForSpecDoc(Activity activity, JSONArray jsonArray) {
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
            convertView = activity.getLayoutInflater().inflate(R.layout.list_of_doctors_on_spec_doc_page, null);



        TextView Docname =(TextView)convertView.findViewById(R.id.spec_Doc_Docname);
        TextView rating =(TextView)convertView.findViewById(R.id.spec_Doc_rating);
        //RatingBar ratingBar =(RatingBar) convertView.findViewById(R.id.Spec_Doc_DocList_ratingBar);
        ImageView Docimage=(ImageView)convertView.findViewById(R.id.Spec_Doc_Docimage);

        String img=null;
        JSONObject json_data = getItem(position);
        if(null!=json_data ){
            String n= null;
            String r=null;
            try {
                n = json_data.getString("name");

                r=json_data.getString("rating");
                img=json_data.getString("pic");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            byte[] decodedString = Base64.decode(img, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(Resources.getSystem(),decodedByte);
            roundedBitmapDrawable.setCircular(true);
            Docimage.setImageDrawable(roundedBitmapDrawable);

            Docname.setText(n);

            rating.setText("Rating: "+r);

           /* if(r==null)
            {
                ratingBar.setRating(0.0f);
            }*/

        }

        return convertView;
    }
}


