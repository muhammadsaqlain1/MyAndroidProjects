package com.example.mcs.kidsurdutrainer4;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import static com.example.mcs.kidsurdutrainer4.DatabaseOpenHelper.HaroofAlfaazTsawer_SlideHaroof.urdufont;

public class PagerAdapter_HaroofETahaji extends PagerAdapter {

    List<ObjHaroofeTahaji> lstHaroofeTahaji;
    MediaPlayer myMP=new MediaPlayer();
   // private int [] image_resources = {R.drawable.anaar,R.drawable.aam,R.drawable.billi,R.drawable.pen,R.drawable.tala,R.drawable.tmatr,R.drawable.smr,R.drawable.jug,R.drawable.chabee,R.drawable.hoz,R.drawable.khrboza,R.drawable.drkht,R.drawable.dhol,R.drawable.zkhera,R.drawable.rubber,R.drawable.pahar,R.drawable.zebra,R.drawable.zalabaari,R.drawable.serhee,R.drawable.sher,R.drawable.sofa,R.drawable.zrb,R.drawable.tota,R.drawable.zroof,R.drawable.aenak,R.drawable.ghubara,R.drawable.fwara,R.drawable.quran,R.drawable.kitab,R.drawable.gulab,R.drawable.lehsan,R.drawable.mtr,R.drawable.nl,R.drawable.wrk,R.drawable.hathi,R.drawable.chaey,R.drawable.ykah,R.drawable.stars};
    //private int[] image_resources1={R.drawable.angoor,R.drawable.aankh,R.drawable.baal,R.drawable.pyaz,R.drawable.titli,R.drawable.topi,R.drawable.saur,R.drawable.jacket,R.drawable.chirea,R.drawable.halwa,R.drawable.kht,R.drawable.dwaat,R.drawable.dheir,R.drawable.zraat,R.drawable.rse,R.drawable.papr,R.drawable.zmeen,R.drawable.azdaha,R.drawable.seb,R.drawable.shljm,R.drawable.sabn,R.drawable.zaea,R.drawable.tfl,R.drawable.zfr,R.drawable.imarat,R.drawable.ghaar,R.drawable.fraak,R.drawable.qlm,R.drawable.cup,R.drawable.glass,R.drawable.lemon,R.drawable.machli,R.drawable.nashpaati,R.drawable.wrzish,R.drawable.hath,R.drawable.gaey,R.drawable.yakoot,R.drawable.ghubaray};
    //private int[] image_resources2={R.drawable.anda,R.drawable.aaroo,R.drawable.bakra,R.drawable.ptah,R.drawable.trbooz,R.drawable.tokri,R.drawable.sls,R.drawable.jahaz,R.drawable.chand,R.drawable.hijab,R.drawable.khrgosh,R.drawable.drwaza,R.drawable.daba,R.drawable.zehn,R.drawable.rail,R.drawable.aaroo,R.drawable.zaban,R.drawable.television,R.drawable.soorj,R.drawable.shehd,R.drawable.sndook,R.drawable.zaeef,R.drawable.tyara,R.drawable.zhrana,R.drawable.uqaab,R.drawable.ghulel,R.drawable.football,R.drawable.qmez,R.drawable.kursi,R.drawable.gajr,R.drawable.lamp,R.drawable.malta,R.drawable.narial,R.drawable.wadi,R.drawable.haar,R.drawable.coal,R.drawable.teer,R.drawable.lrkay};

    private Context ctx;

    private LayoutInflater layoutInflater;

    public PagerAdapter_HaroofETahaji (Context ctx, int resourceId, List<ObjHaroofeTahaji> lstHaroofeTahaji ){
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
        View item_view = layoutInflater.inflate(R.layout.customview_haroof_pager,container,false);
        //ImageView imageView0 = (ImageView) item_view.findViewById(R.id.img);
        TextView textView = (TextView) item_view.findViewById(R.id.tvHarf);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.imgWord1);
        TextView textView1 = (TextView) item_view.findViewById(R.id.tvWord1);
        ImageView imageView2 = (ImageView) item_view.findViewById(R.id.imgWord2);
        TextView textView2 = (TextView) item_view.findViewById(R.id.tvWord2);
        ImageView imageView3 = (ImageView) item_view.findViewById(R.id.imgWord3);
        TextView textView3 = (TextView) item_view.findViewById(R.id.tvWord3);
       // imageView0.setImageResource(image_resources[position]);

        int img1id = ctx.getResources().getIdentifier(""+lstHaroofeTahaji.get(position).getPicture1(), "drawable", ctx.getPackageName());
        int img2id = ctx.getResources().getIdentifier(""+lstHaroofeTahaji.get(position).getPicture2(), "drawable", ctx.getPackageName());
        int img3id = ctx.getResources().getIdentifier(""+lstHaroofeTahaji.get(position).getPicture3(), "drawable", ctx.getPackageName());

        imageView.setImageResource(img1id);
        imageView2.setImageResource(img2id);
        imageView3.setImageResource(img3id);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayWord(ctx,lstHaroofeTahaji.get(position).getPicture1());
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayWord(ctx,lstHaroofeTahaji.get(position).getPicture2());
            }
        });
        PlayWord(ctx,lstHaroofeTahaji.get(position).getPicture3());

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayWord(ctx,lstHaroofeTahaji.get(position).getPicture1());
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayWord(ctx,lstHaroofeTahaji.get(position).getPicture2());
            }
        });

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PlayHarf(ctx,lstHaroofeTahaji.get(position).getId()+"");
                PlayWord(ctx,lstHaroofeTahaji.get(position).getPicture3());
            }
        });


        textView.setText(" "+lstHaroofeTahaji.get(position).getHarf());
        textView.setTypeface(urdufont);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayHarf(ctx,lstHaroofeTahaji.get(position).getId()+"");
            }
        });

        //imageView.setImageResource(Integer.parseInt(String.valueOf(lstHaroofeTahaji.get(position).getPicture1())));
        textView1.setText(" "+lstHaroofeTahaji.get(position).getWord1());
        textView1.setTypeface(urdufont);
        //imageView.setImageResource(position);
        textView2.setText(" "+lstHaroofeTahaji.get(position).getWord2());
        textView2.setTypeface(urdufont);
        //imageView.setImageResource(position);
        textView3.setText(" "+lstHaroofeTahaji.get(position).getWord3());
        textView3.setTypeface(urdufont);
        //textView.setText("Image: " +position);
        container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }

    //MEDIA PLAYERSSSSSSSSSSSSSSSSs

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

    //Play single Harf
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