package com.example.mcs.kidsurdutrainer4;




import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.IOException;
import java.util.List;

public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "QaidaDb.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static class HaroofAlfaazTsawer_SlideHaroof extends AppCompatActivity {

        ViewPager viewPager;
        ListView lvHaroofssss;
        PagerAdapter_HaroofETahaji adapter;

        List<ObjHaroofeTahaji> lstHaroofeTahaji;
        //List<ObjHaroofeTahaji> lstHaroofeTahaji1;
        //ListView lvHaroofs;
        public static Typeface urdufont;
        String [] tahajiarray={"ا", " آ", "ب", "پ", "ت", "ٹ", "ث", "ج", "چ", "ح", "خ", "د", "ڈ", "ذ", "ر", "ڑ", "ز", "ژ", "س", "ش",
                "ص", "ض", "ط", "ظ", "ع", "غ", "ف", "ق", " ک", " گ", "ل", "م", "ن", "و", "ہ", "ء", "ی", "ے"};

        MediaPlayer myMP=new MediaPlayer();
        int indexclicked;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_haroof_words);
         //   getSupportActionBar().setTitle("Haroof with words & audio");

            viewPager = (ViewPager) findViewById(R.id.lvHaroofs);
           // adapter = new PagerAdapter_HaroofETahaji(this);
           // viewPager.setAdapter(adapter);

            urdufont = Typeface.createFromAsset(getAssets(), "font/urdufont.ttf");

            indexclicked=getIntent().getIntExtra("index",0);

            ///lvHaroofs=(ListView)findViewById(R.id.lvHaroofs);
            try {
               DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
               databaseAccess.open();
               lstHaroofeTahaji = databaseAccess.getObjHaroofeTahaji("select * from HaroofETahaji");
                databaseAccess.close();

                //ListViewAdaptor_HaroofETahaji haroofadaptor=new ListViewAdaptor_HaroofETahaji(getApplicationContext(),R.layout.customview_haroof_pager,lstHaroofeTahaji);
                //viewPager.setAdapter(haroofadaptor);
              //  PagerAdapter_HaroofETahaji pagerAdapter_haroofETahaji=new PagerAdapter_HaroofETahaji(getApplicationContext(),R.layout.customview_haroof_pager,lstHaroofeTahaji);
              //  lvHaroofs.setAdapter(pagerAdapter_haroofETahaji);
                //viewPager = (ViewPager) findViewById(R.id.lvHaroofs);


                // Populating haroofs
                lvHaroofssss=(ListView)findViewById(R.id.lvtahajis);

                SlideAdaptor slideAdaptor=new SlideAdaptor();
                lvHaroofssss.setAdapter(slideAdaptor);

                adapter = new PagerAdapter_HaroofETahaji(getApplicationContext(),R.layout.customview_haroof_pager,lstHaroofeTahaji);
                viewPager.setAdapter(adapter);
                viewPager.setRotationY(180);


                //Scrolling to that index
                lvHaroofssss.setSelection(indexclicked);
                viewPager.setCurrentItem(indexclicked);
                PlayHarf(getApplicationContext(),indexclicked+1+"");


                viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        lvHaroofssss.setSelection(position);
                        PlayHarf(getApplicationContext(),position+1+"");
                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });

            }catch (Exception e)
            {
                Toast.makeText(this,"Error:"+e,Toast.LENGTH_LONG).show();
            }
        }


        class SlideAdaptor extends BaseAdapter {

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

               // view= getLayoutInflater().inflate(R.layout.customview_haroof_pager,null);
               // final TextView tvharf= view.findViewById(R.id.tvHarf);
               // tvharf.setTypeface(urdufont);

               // final TextView tvword1= view.findViewById(R.id.tvWord1);
              //  tvword1.setTypeface(urdufont);

              //  final TextView tvword2= view.findViewById(R.id.tvWord2);
              //  tvword2.setTypeface(urdufont);

               // final TextView tvword3= view.findViewById(R.id.tvWord3);
              //  tvword3.setTypeface(urdufont);




                view= getLayoutInflater().inflate(R.layout.customview_tahaji_slide,null);
                final TextView tvharfslide= view.findViewById(R.id.tvHarfSlide);
                tvharfslide.setText(tahajiarray[i]);
                tvharfslide.setTextColor(Color.RED);
                tvharfslide.setTextSize(40);
                tvharfslide.setTypeface(urdufont);
                tvharfslide.setGravity(Gravity.CENTER);

                tvharfslide.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager.setCurrentItem(ii);

                        //refreshlist();
                        tvharfslide.setGravity(Gravity.RIGHT);
                        tvharfslide.setTextColor(Color.BLUE);
                        lvHaroofssss.setSelection(ii);
                        //lvHaroofssss.smoothScrollToPosition(ii);

                    }
                });
                return view;
            }
        }


        public void PlayHarf(final Context context, String filename)
        {
            //Toast.makeText(context,"Playing Audio....."+filename,Toast.LENGTH_SHORT).show();
            // mpintro=new MediaPlayer();
            try {
                myMP.reset();

                if(myMP.isPlaying()){
                    myMP.stop();
                }

                // if(mpintro.isPlaying())mpintro.stop();

                myMP.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Qaida/Tahajji/T("+filename+").mp3");
                myMP.prepare();
                myMP.start();
                myMP.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {

                        //Toast.makeText(context,"Playing Audio.....",Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (IOException e) {

                Toast.makeText(context,"Playing Error.....\n"+e,Toast.LENGTH_SHORT).show();
            }
        }
    }
}