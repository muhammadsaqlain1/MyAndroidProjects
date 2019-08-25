package com.example.usman.virtualclinic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.EventObject;

public class AdminActivity extends AppCompatActivity {



    JSONArray jsonArray;
    String wcf;
    ListView listView;

    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        listView=(ListView)findViewById(R.id.AdminpageRequests);



        pd= new ProgressDialog(AdminActivity.this);
        pd.setTitle("Getting Requests");
        pd.setMessage("Please wait....");



        //wcf=WCFHandler.GetJsonResult("clinicreq");

        pd.show();
        wcf=WCFHandler.GetJsonResult("reqtoadmin");

        jsonArray=null;

        try {
            jsonArray = new JSONArray(wcf);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final MyListAdopter myListAdopter = new MyListAdopter(AdminActivity.this,jsonArray);
        listView.setAdapter(myListAdopter);

        pd.dismiss();




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Object eo = adapterView.getItemAtPosition(i);
                TextView t=(TextView)view.findViewById(R.id.lltype);
                TextView tt=(TextView)view.findViewById(R.id.llname);
                ImageView ii = (ImageView)view.findViewById(R.id.llimage);
                String type=t.getText().toString();
                String name=tt.getText().toString();
                String email=null;
                String city=null;
                String state=null;
                String location=null;
                String lisc=null;
                String password=null;
                String contact=null;
           try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    email=jsonObject.getString("email");
                    city=jsonObject.getString("city");
                    state=jsonObject.getString("state");
                   // location=jsonObject.getString("location");
                    lisc=jsonObject.getString("liscenceno");
                    password=jsonObject.getString("password");
                    contact=jsonObject.getString("contact");
                } catch (JSONException e) {
                    e.printStackTrace();
                }




                ii.setDrawingCacheEnabled(true);
                Bitmap b=ii.getDrawingCache();
                Intent intent = new Intent(AdminActivity.this, CheckingProfileActivity.class);

                intent.putExtra("Bitmap", b);
                intent.putExtra("name",name);
                intent.putExtra("type",type);
                intent.putExtra("email",email);
                intent.putExtra("city",city);
                intent.putExtra("state",state);
                intent.putExtra("password",password);
                intent.putExtra("lisc",lisc);
                intent.putExtra("contact",contact);
                startActivity(intent);



            }
        });







       // AdopterForImagesLi adopterForImagesList = new AdopterForImagesList(AllImagesfromDBActivity.this,jsonArray);

        //gridView.setAdapter(adopterForImagesList);




    }
}
