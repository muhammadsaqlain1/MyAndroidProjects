package com.example.usman.virtualclinic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONStringer;

public class CheckingProfileActivity extends AppCompatActivity {

    Button btnadd;
    ImageView imageView;
    TextView tvnam,type,tvemail,tvlocation,tvcity,tvstate,tvcontact,tvlisc;
    String email=null;
    String city=null;
    String state=null;
    String location=null;
    String lisc=null;
    String password=null;
    String contact=null;
    String t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking_profile);

        imageView=(ImageView)findViewById(R.id.Doc_profile_picture);
        tvnam=(TextView)findViewById(R.id.checking_profile_name);
        tvcity=(TextView)findViewById(R.id.check_profile_city);
        tvcontact=(TextView)findViewById(R.id.check_profile_contact);
        tvstate=(TextView)findViewById(R.id.check_profile_state);
        tvlocation=(TextView)findViewById(R.id.check_profile_location);
        tvlisc=(TextView)findViewById(R.id.check_profile_lisc);

        btnadd=(Button)findViewById(R.id.check_profile_btn_add);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        String a= b.getString("name");
        t=b.getString("type");
        email=b.getString("email");
        city=b.getString("city");
        state=b.getString("state");
      //  location=b.getString("location");
        lisc=b.getString("lisc");
        password=b.getString("password");
        contact=b.getString("contact");
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("Bitmap");
        imageView.setImageBitmap(bitmap);
        tvnam.setText(a);
        tvcity.setText(city);
        tvlisc.setText(lisc);
        tvstate.setText(state);
        tvcontact.setText(contact);



        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONStringer stringer = new JSONStringer();

                try {
                    stringer.object().key("emaiil").value(email);
                    stringer.key("pass").value(password);
                    stringer.key("typee").value(t);
                    stringer.endObject();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String functionname = "Add";
                String wcf=null;
                wcf=WCFHandler.PostJsonResult(functionname,stringer);

                Toast.makeText(CheckingProfileActivity.this,wcf,Toast.LENGTH_LONG).show();

                Intent i = new Intent(CheckingProfileActivity.this,AdminActivity.class);
                startActivity(i);
            }
        });


    }
}
