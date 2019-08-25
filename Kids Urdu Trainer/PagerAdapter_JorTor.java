package com.example.mcs.kidsurdutrainer4;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import static com.example.mcs.kidsurdutrainer4.JorTor.urdufont;

public class PagerAdapter_JorTor extends PagerAdapter {

    List<ObjHaroofeTahaji> lstHaroofeTahaji;

    MediaPlayer myMP=new MediaPlayer();

    private Context ctx;

    private LayoutInflater layoutInflater;

    //private ListView lvJorTors;
    List<String> lstWords;

    String WordToPlay="";
    int harfcount=0;
    String [] torArray={ };
    //Typeface urdufont;


    public PagerAdapter_JorTor (Context ctx, int resourceId, List<ObjHaroofeTahaji> lstHaroofeTahaji ){
        this.lstHaroofeTahaji=lstHaroofeTahaji;

        this.ctx=ctx;
    }

    @Override
    public int getCount() {
        return lstHaroofeTahaji.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.customview_jor_tor,container,false);

        ImageView imgJorTors= (ImageView) item_view.findViewById(R.id.imgJorTor);
        TextView tvJors= (TextView) item_view.findViewById(R.id.tvJor);
        TextView tvTors= (TextView) item_view.findViewById(R.id.tvTor);

        ImageView imgJorTors1= (ImageView) item_view.findViewById(R.id.imgJorTor1);
        TextView tvJors1= (TextView) item_view.findViewById(R.id.tvJor1);
        TextView tvTors1= (TextView) item_view.findViewById(R.id.tvTor1);


        int img1id = ctx.getResources().getIdentifier(""+lstHaroofeTahaji.get(position).getPicture1(), "drawable", ctx.getPackageName());
        int img2id = ctx.getResources().getIdentifier(""+lstHaroofeTahaji.get(position).getPicture2(), "drawable", ctx.getPackageName());


        imgJorTors.setImageResource(img1id);
        imgJorTors1.setImageResource(img2id);


        tvJors.setText(" "+lstHaroofeTahaji.get(position).getWord1());
        tvJors1.setText(" "+lstHaroofeTahaji.get(position).getWord2());


        //Torrrrr Logic

        String TorWord=torFunction(lstHaroofeTahaji.get(position).getWord1());

        String TorWord1=torFunction(lstHaroofeTahaji.get(position).getWord2());


        tvTors.setText(TorWord);

        tvTors1.setText(TorWord1);


        tvTors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                harfcount=0;
                if(myMP.isPlaying()) myMP.stop();

                WordToPlay=lstHaroofeTahaji.get(position).getWord1();
                PlayHarfLoop(ctx, getIdByWord(WordToPlay.charAt(harfcount)+"")+"");
            }
        });


        tvTors1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                harfcount=0;
                if(myMP.isPlaying()) myMP.stop();

                WordToPlay=lstHaroofeTahaji.get(position).getWord2();
                PlayHarfLoop(ctx, getIdByWord(WordToPlay.charAt(harfcount)+"")+"");
            }
        });



        tvJors.setTextColor(Color.RED);
        tvJors.setTextSize(40);
        tvJors.setTypeface(urdufont);
        tvJors.setGravity(Gravity.CENTER);

        tvJors1.setTextColor(Color.RED);
        tvJors1.setTextSize(40);
        tvJors1.setTypeface(urdufont);
        tvJors1.setGravity(Gravity.CENTER);


        tvTors.setTextColor(Color.RED);
        tvTors.setTextSize(40);
        tvTors.setTypeface(urdufont);
        tvTors.setGravity(Gravity.CENTER);

        tvTors1.setTextColor(Color.RED);
        tvTors1.setTextSize(40);
        tvTors1.setTypeface(urdufont);
        tvTors1.setGravity(Gravity.CENTER);


        tvJors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayWord(ctx,lstHaroofeTahaji.get(position).getPicture1());
            }
        });

        tvJors1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayWord(ctx,lstHaroofeTahaji.get(position).getPicture2());
            }
        });

        imgJorTors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayWord(ctx,lstHaroofeTahaji.get(position).getPicture1());
            }
        });

        imgJorTors1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayWord(ctx,lstHaroofeTahaji.get(position).getPicture2());
            }
        });


/////////////////////////////////slide haroof//////////////////////////////////////

        tvJors.setText(" "+lstHaroofeTahaji.get(position).getWord1());
        tvJors.setTypeface(urdufont);

        tvJors1.setText(" "+lstHaroofeTahaji.get(position).getWord2());
        tvJors1.setTypeface(urdufont);



        container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }


    ////////////Tor Function//////////////////

    public String torFunction(String word)
    {
        String tor="";
        for(int i=0;i<word.length();i++)
        {
            if(!(i==word.length()-1))
            {  tor+=word.charAt(i)+"  ";}
            else
            {  tor+=word.charAt(i);}
        }
        return tor;
    }

    ////////////Media Player//////////////////

    public void PlayWord(final Context context, String filename)
    {
        //Toast.makeText(context,"Playing Audio....."+filename,Toast.LENGTH_SHORT).show();
        // mpintro=new MediaPlayer();
        try {
            // myMP.release();
            myMP.reset();

            if(myMP.isPlaying())myMP.stop();
            // if(mpintro.isPlaying())mpintro.stop();

            myMP.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Qaida/Words/"+filename+".mp3");
            myMP.prepare();
            myMP.start();
            myMP.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    //Toast.makeText(context,"Playing Audio.....Finish",Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            Toast.makeText(context,"Playing Error.....\n"+e,Toast.LENGTH_SHORT).show();
        }
    }

    //////////////Play Harf in Loop/////////////////////

    public void PlayHarfLoop( Context context, String filename)
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
                    harfcount++;
                    if (harfcount==WordToPlay.length())
                    {
                        harfcount=0;
                        //myMP.stop();
                    }
                    else if(WordToPlay.charAt(harfcount)==' ')
                    {
                        harfcount++;
                        PlayHarfLoop(ctx, getIdByWord(WordToPlay.charAt(harfcount)+"")+"");
                    }
                    else if(harfcount<WordToPlay.length())
                    {
                        PlayHarfLoop(ctx, getIdByWord(WordToPlay.charAt(harfcount)+"")+"");
                    }
                }
            });
        } catch (IOException e) {
            Toast.makeText(context,"Playing Error.....\n"+e,Toast.LENGTH_SHORT).show();
        }
    }
    int getIdByWord(String harf)
    {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(ctx);
        databaseAccess.open();
        String id = databaseAccess.getSingleString("select id from HaroofETahaji where harf like '"+harf+"'");
        databaseAccess.close();
        int idd=Integer.parseInt(id);
        return idd;
    }
}
