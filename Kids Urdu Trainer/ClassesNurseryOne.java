package com.example.mcs.kidsurdutrainer4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClassesNurseryOne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes_nursery_one);

        Button btnNursery= findViewById(R.id.btnNursery);

        btnNursery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Main_Haroof_Qaida.class);

                startActivity(i);
            }
        });

        Button btnOne= findViewById(R.id.btnOne);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), JorTor.class);

                startActivity(i);
            }
        });
        //return view;
    }
}
