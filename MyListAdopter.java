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
 * Created by usman on 5/9/2018.
 */

public class MyListAdopter extends BaseAdapter implements ListAdapter {
    private final Activity activity;
    private final JSONArray jsonArray;
    public MyListAdopter(Activity activity, JSONArray jsonArray) {
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
            convertView = activity.getLayoutInflater().inflate(R.layout.listlayout, null);



        TextView text1 =(TextView)convertView.findViewById(R.id.llname);
        TextView text2 =(TextView)convertView.findViewById(R.id.lltype);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.llimage);

        String img=null;
        JSONObject json_data = getItem(position);
        if(null!=json_data ){
            String jj= null;
            String ff=null;
            try {
                jj = json_data.getString("name");

                ff=json_data.getString("type");
                img=json_data.getString("pic");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            byte[] decodedString = Base64.decode(img, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(Resources.getSystem(),decodedByte);
            roundedBitmapDrawable.setCircular(true);
            imageView.setImageDrawable(roundedBitmapDrawable);
            text1.setText(jj);

            if(ff.contains("c"))
            text2.setText("Clinic");
            else
                text2.setText("Doctor");
        }

        return convertView;
    }
}

