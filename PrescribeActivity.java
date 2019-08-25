package com.example.usman.virtualclinic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONStringer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class PrescribeActivity extends AppCompatActivity {

    TextView tvdiagnosed,tvmrng,tveve,tvnight,tvmed,tvadvise;
    Spinner sp;
    Button btnsend;
    String docemail,diagnose,dose,med,mtype,advice,date,wcf;
    int pid;
    ArrayList<String> al;
    ArrayAdapter<String> adopter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescribe);

        tvdiagnosed=(TextView)findViewById(R.id.prescribtion_diagnosed);
        tvmrng=(TextView)findViewById(R.id.prescription_dose_mrng);
        tveve=(TextView)findViewById(R.id.prescription_dose_eve);
        tvnight=(TextView)findViewById(R.id.prescription_dose_night);
        sp=(Spinner)findViewById(R.id.Prescription_sp_med_type);
        tvmed=(TextView)findViewById(R.id.Prescription_medicine);
        btnsend=(Button)findViewById(R.id.prescription_send_btn);
        tvadvise=(TextView)findViewById(R.id.Presciption_advice);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        docemail=b.getString("Docemail");
        pid=b.getInt("pid");

        al= new ArrayList<String>();
        al.add("Syrup");
        al.add("Tablet");


        adopter= new ArrayAdapter<String>(PrescribeActivity.this,R.layout.support_simple_spinner_dropdown_item,al);
        sp.setAdapter(adopter);


        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                diagnose=tvdiagnosed.getText().toString();
                advice=tvadvise.getText().toString();
                dose=tvmrng.getText().toString() + "(Mrng)" + tveve.getText().toString() +"(Eve)" + tvnight.getText().toString() + "(Night)";
                med=tvmed.getText().toString();
                mtype=sp.getSelectedItem().toString();

                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                date = df.format(c.getTime());

                JSONStringer stringer = new JSONStringer();

                try {
                    stringer.object().key("med").value(med);
                    stringer.key("advicee").value(advice);
                    stringer.key("dosee").value(dose);
                    stringer.key("mtype").value(mtype);
                    stringer.key("dt").value(date);
                    stringer.key("docemail").value(docemail);
                    stringer.key("pidd").value(pid);
                    stringer.key("diag").value(diagnose);
                    stringer.endObject();

                } catch (JSONException e) {
                    e.printStackTrace();
                }



                wcf=WCFHandler.PostJsonResult("prescribe",stringer);

                Toast.makeText(PrescribeActivity.this,wcf,Toast.LENGTH_LONG).show();


            }
        });






    }
}
