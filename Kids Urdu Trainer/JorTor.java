package com.example.mcs.kidsurdutrainer4;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class JorTor extends AppCompatActivity {

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jor_tor);

        viewPager = (ViewPager) findViewById(R.id.lvJorTor);

        urdufont = Typeface.createFromAsset(getAssets(), "font/urdufont.ttf");

        indexclicked = getIntent().getIntExtra("index", 0);


        //Getting from Database
        try {
            DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
            databaseAccess.open();
            lstHaroofeTahaji = databaseAccess.getObjHaroofeTahaji("select * from HaroofETahaji");
            databaseAccess.close();

    // Populating haroofs
            lvJorTors = (ListView) findViewById(R.id.lvTahajis);

            SlideAdaptor slideAdaptor=new SlideAdaptor();
            lvJorTors.setAdapter(slideAdaptor);

           // ListAdaptor listAdapter = new ListAdaptor();
           // lvJorTors.setAdapter(listAdapter);
           // lvJorTors.setRotationY(180);


            adapter = new PagerAdapter_JorTor(getApplicationContext(), R.layout.customview_jor_tor, lstHaroofeTahaji);
            viewPager.setAdapter(adapter);
            viewPager.setRotationY(180);

            //lvJorTors = (ListView) findViewById(R.id.lvJorTor);
           // lvJorTors.setRotationY(180);

        //Scrolling to that index
            lvJorTors.setSelection(indexclicked);
            viewPager.setCurrentItem(indexclicked);
            PlayHarf(getApplicationContext(), indexclicked + 1 + "");


            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    lvJorTors.setSelection(position);
                    PlayHarf(getApplicationContext(),position + 1 + "");
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        } catch (Exception e) {
            Toast.makeText(this, "Error:" + e, Toast.LENGTH_LONG).show();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            final int ii = position;

            convertView = getLayoutInflater().inflate(R.layout.customview_tahaji_slide, null);


            final TextView tvharfslide = convertView.findViewById(R.id.tvHarfSlide);
            tvharfslide.setText(tahajiarray[position]);
            tvharfslide.setTextColor(Color.RED);
            tvharfslide.setTextSize(40);
            tvharfslide.setTypeface(urdufont);
            tvharfslide.setGravity(Gravity.RIGHT);

            tvharfslide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(ii);

                    //refreshlist();
                    tvharfslide.setGravity(Gravity.RIGHT);
                    tvharfslide.setTextColor(Color.BLUE);
                    lvJorTors.setSelection(ii);
                    //lvHaroofssss.smoothScrollToPosition(ii);
                }
            });



            return convertView;
        }
    }


        public void PlayHarf(final Context context, String filename) {
            //Toast.makeText(context,"Playing Audio....."+filename,Toast.LENGTH_SHORT).show();
            // mpintro=new MediaPlayer();
            try {
                myMP.reset();

                if (myMP.isPlaying()) {
                    myMP.stop();
                }

                // if(mpintro.isPlaying())mpintro.stop();

                myMP.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Qaida/Tahajji/T(" + filename + ").mp3");
                myMP.prepare();
                myMP.start();
                myMP.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {

                        //Toast.makeText(context,"Playing Audio.....",Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (IOException e) {

                Toast.makeText(context, "Playing Error.....\n" + e, Toast.LENGTH_SHORT).show();
            }
        }
    }