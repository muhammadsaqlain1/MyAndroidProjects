package com.example.mcs.kidsurdutrainer4;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class Main_Haroof_Qaida extends AppCompatActivity {

    private GridView gvHaroofs;
    String [] tahajiarray={"ا", " آ", "ب", "پ", "ت", "ٹ", "ث", "ج", "چ", "ح", "خ", "د", "ڈ", "ذ", "ر", "ڑ", "ز", "ژ", "س", "ش",
            "ص", "ض", "ط", "ظ", "ع", "غ", "ف", "ق", " ک", " گ", "ل", "م", "ن", "و", "ہ", "ء", "ی", "ے", " ", " " };


    String [] tahajiarray1={" ", " ", "بـ", "پـ", "تـ", "ٹـ", "ثـ", "جـ", "چـ", "حـ", "خـ", " ", " ", " ", " ", " ", " ", " ", "سـ", "شـ",
            "صـ", "ضـ", "طـ", "ظـ", "عـ", "غـ", "فـ", "قـ", " کـ کے", " گـ گے", "لـ", "مـ", "نـ", " ", "ہـ", " ", "یـ", " ", " ", " " };



     Typeface urdufont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_haroof_qaida);
        urdufont = Typeface.createFromAsset(getAssets(), "font/urdufont.ttf");

        gvHaroofs=(GridView)findViewById(R.id.gvMainHaroofs);
        GridAdaptor gadaptor=new GridAdaptor();
        gvHaroofs.setAdapter(gadaptor);
        gvHaroofs.setRotationY(180);
        gvHaroofs.setGravity(Gravity.CENTER);
    }

    class GridAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return tahajiarray.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            final int ii=i;
            view= getLayoutInflater().inflate(R.layout.customview_main_haroof_grid,null);
            TextView tvharfslide= view.findViewById(R.id.tvGridHarf);
           // TextView tvharfslide1= view.findViewById(R.id.tvGridShortHarf1);


            tvharfslide.setText(tahajiarray[i]);

          //  tvharfslide1.setText(tahajiarray1[i]);


            tvharfslide.setTextColor(Color.RED);
            tvharfslide.setTextSize(40);
            tvharfslide.setTypeface(urdufont);
            tvharfslide.setGravity(Gravity.CENTER);

         //   tvharfslide1.setTextColor(Color.RED);
           // tvharfslide1.setTextSize(40);
         //   tvharfslide1.setTypeface(urdufont);
           // tvharfslide1.setGravity(Gravity.CENTER);

            tvharfslide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // viewPager.setCurrentItem(ii);
                    Intent i = new Intent(getApplicationContext(), DatabaseOpenHelper.HaroofAlfaazTsawer_SlideHaroof.class);
                    i.putExtra("index",ii);
                    startActivity(i);
                }
            });
            return view;
        }
    }
}