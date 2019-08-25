package com.example.usman.virtualclinic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {


    ProgressDialog pd;
    EditText username,password;
    String uname, pass,wcf;
    Button btnlogin;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText)findViewById(R.id.logintvusername);
        password=(EditText)findViewById(R.id.logintvpassword);
        btnlogin=(Button)findViewById(R.id.btnlogin);
        register=(TextView)findViewById(R.id.tvRegister);

        pd=new ProgressDialog(LoginActivity.this);


        pd.setTitle("Please Wait");
        pd.setMessage("Loging in....");



        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();

                uname=username.getText().toString();
                pass=password.getText().toString();

                wcf=WCFHandler.GetJsonResult("login/"+uname+"/"+pass);

                if(uname.contains("admin") && pass.contains("admin"))
                {
                    Intent intent = new Intent(LoginActivity.this,AdminActivity.class);



                    startActivity(intent);

                }
                else if(wcf.contains("Doctor"))
                {
                    Intent intent = new Intent(LoginActivity.this,DoctorMainActivity.class);
                    intent.putExtra("demail",uname);
                    DocProfileFragment.demail=uname;
                    DoctorMainActivity.demail=uname;
                    RequestToDoctorFragment.demail=uname;

                    DoctorCheckPatientVisitingActivity.demail=uname;

                    startActivity(intent);
                }
                else if(wcf.contains("Clinic"))
                {
                    Intent intent = new Intent(LoginActivity.this,ClinicMainActivity.class);
                    ClinicMainActivity.cemail=uname;
                    ClinicprofileFragment.cemail=uname;
                    SelectDocFragment.ccemail=uname;
                    AddingNewPatientFragment.cemail=uname;
                    ResponceOfDocFragment.cemail=uname;


                    AddingNewPatientFragment.cemail=uname;



                    intent.putExtra("cemail",uname);
                    startActivity(intent);
                }
                else if (uname.contains("sdoc") && pass.contains("sdoc"))
            {
                Intent intent = new Intent(LoginActivity.this,SpecialDoctorHomeActivity.class);
                startActivity(intent);
            }


//            if(uname.contains("admin") && pass.contains("admin"))
//               {
//                    Intent intent = new Intent(LoginActivity.this,AdminActivity.class);
//
//                    startActivity(intent);
//
//               }
//                else if(uname.contains("doctor") && pass.contains("doctor"))
//                {
//                    Intent intent = new Intent(LoginActivity.this,DoctorMainActivity.class);
//                    startActivity(intent);
//                }
//                else if(uname.contains("clinic") && pass.contains("clinic"))
//                {
//                    Intent intent = new Intent(LoginActivity.this,ClinicMainActivity.class);
//                    startActivity(intent);
//                }
//                else if (uname.contains("sdoc") && pass.contains("sdoc"))
//            {
//                Intent intent = new Intent(LoginActivity.this,SpecialDoctorHomeActivity.class);
//                startActivity(intent);
//            }
//
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterationActivity.class);
                startActivity(intent);

            }
        });


    }
}
