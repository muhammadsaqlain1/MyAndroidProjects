package com.example.usman.virtualclinic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class MedicalHistoryActivity extends AppCompatActivity {

    TextView tvpatientname;
    ListView listView;
    String pname,Docname,wcf;
    int pid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);
        tvpatientname=(TextView)findViewById(R.id.medical_history_name);
        listView=(ListView) findViewById(R.id.medical_history_list);

        Intent intent= getIntent();
        Bundle b = intent.getExtras();

        Docname=b.getString("docname");
        pname=b.getString("pname");
        pid=b.getInt("pid");

        tvpatientname.setText(pname);


        wcf=WCFHandler.GetJsonResult("medicalhistory/"+pid);


        try {
            JSONArray jsonArray = new JSONArray(wcf);


            medicalhistoryAdopter adopter = new medicalhistoryAdopter(MedicalHistoryActivity.this,jsonArray);
            listView.setAdapter(adopter);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
