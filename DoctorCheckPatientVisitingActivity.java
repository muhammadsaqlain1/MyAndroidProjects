package com.example.usman.virtualclinic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DoctorCheckPatientVisitingActivity extends AppCompatActivity {

    public static String demail;

    ImageView img,medicalHis,presc;
    TextView tvname,tvage,tvgender,tvsugar,tvtemp,tvBPstolic,tvBPdistolic,tvpulse,tvBR,tvWeight,tvProblem;
    String name,age,gender,sugar,Docemail,temp,Docname,BPstolic,BPdistolic,pulse,BR,Weight,Problem;
    int pid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_check_patient_visiting);
        tvname=(TextView)findViewById(R.id.Doc_check_Patient_name);
        tvage=(TextView)findViewById(R.id.Doc_check_Patient_age);
        tvBPdistolic=(TextView)findViewById(R.id.Doc_check_Patient_BP_dicstolic);
        tvBPstolic=(TextView)findViewById(R.id.Doc_check_Patient_BP_stolic);
        tvBR=(TextView)findViewById(R.id.Doc_check_Patient_BR);
        tvProblem=(TextView)findViewById(R.id.Doc_check_Patient_problem_description);
        tvgender=(TextView)findViewById(R.id.Doc_check_Patient_gender);
        tvpulse=(TextView)findViewById(R.id.Doc_check_Patient_pulse);
        tvtemp=(TextView)findViewById(R.id.Doc_check_Patient_temprature);
        tvWeight=(TextView)findViewById(R.id.Doc_check_Patient_weight);
        tvsugar=(TextView)findViewById(R.id.Doc_check_Patient_sugar);
        //img=(ImageView)findViewById(R.id.Doc_check_patient_pic);
        medicalHis=(ImageView)findViewById(R.id.Doc_check_patient_Med_history);
        presc=(ImageView)findViewById(R.id.Doc_Check_Patient_RX_Presc);

        medicalHis=(ImageView)findViewById(R.id.Doc_check_patient_Med_history);

        final Intent intent= getIntent();
        Bundle b = intent.getExtras();
        name=b.getString("name");
        age=b.getString("age");
        gender=b.getString("gender");
        BPdistolic=b.getString("BPds");
        BPstolic=b.getString("BPs");
        pulse=b.getString("pulse");
        Weight=b.getString("weight");
        BR=b.getString("BR");
        Problem=b.getString("problem");
        sugar=b.getString("sugar");
        temp=b.getString("temp");
        Docname=b.getString("docname");
        pid=b.getInt("pid");
        Docemail=b.getString("Docemail");


        tvname.setText(name);
        tvsugar.setText(sugar);
        tvWeight.setText(sugar);
        tvtemp.setText(temp);
        tvpulse.setText(pulse);
        tvgender.setText(gender);
        tvBPstolic.setText(BPstolic);
        tvBPdistolic.setText(BPdistolic);
        tvProblem.setText(Problem);
        tvBR.setText(BR);


        medicalHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(DoctorCheckPatientVisitingActivity.this,MedicalHistoryActivity.class);
                intent1.putExtra("docname",Docname);
               intent1.putExtra("pid",pid);
               intent1.putExtra("pname",name);

                startActivity(intent1);
            }
        });


        presc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(DoctorCheckPatientVisitingActivity.this,PrescribeActivity.class);

                intent1.putExtra("Docemail",Docemail);
                intent1.putExtra("pid",pid);
                startActivity(intent1);

            }
        });


    }
}
