package com.wgw.medalprogress.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * Author:Admin
 * Time:2019/4/22 16:43
 * 描述：
 */
public class BroadcastSound {
    private SoundPool soundPool;
    //    private Context context;
    private SoftReference<Context> reference;
    private float volumnRatio = 1;
    private float curVolume = 1;

    private static final int PRIORITY = 1;//


    public HashMap<Integer, Integer> soundPoolMap = new HashMap<Integer, Integer>();
    private static BroadcastSound soundPlay;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private BroadcastSound(Context context) {
        // TODO Auto-generated constructor stub
//        this.context = context;
        reference = new SoftReference<>(context);
        init();
        loadSounds(0);

    }

    public static BroadcastSound getInstance(Context context) {
        if (soundPlay == null) {
            soundPlay = new BroadcastSound(context);
        }
        return soundPlay;
    }

    private void init() {
        // 5.0 及 之后
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = null;
            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(16)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else { // 5.0 以前
            soundPool = new SoundPool(16, AudioManager.STREAM_MUSIC, 0);  // 创建SoundPool
        }
    }

    private void loadSounds(int sex) {

        Resources res = reference.get().getResources();
        if (sex == 1){
            for (int i = 1; i <= 180; i++) {
                //根据id名称，取得资源id
                int identifier = res.getIdentifier("sore_woman_"+i, "raw", reference.get().getPackageName());
//                int getScore = soundPool.load(reference.get(), identifier, PRIORITY);
                Log.d("wgw_identifier","------"+identifier);
                if (identifier>0){
                    int score = soundPool.load(reference.get(), identifier, PRIORITY);
                    if (score > 0) {
                        soundPoolMap.put(i, score);
                    }
                }

            }
        }else {
            for (int i = 1; i <= 180; i++) {
                int identifier = res.getIdentifier("sore_man_"+i, "raw", reference.get().getPackageName());
                Log.d("wgw_identifier","------"+identifier);
                if (identifier>0){
                    int getScore = soundPool.load(reference.get(), identifier, PRIORITY);
                    if (getScore > 0) {
                        soundPoolMap.put(i, getScore);
                    }
                }


            }
        }



    }

    public void PlaySound(int typeID) {
        soundPool.play(soundPoolMap.get(typeID), curVolume, curVolume, 1, 0,
                volumnRatio);
    }

    public void PlaySound(final Integer... typeID) {
//        for (final int id:typeID) {
//            mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    soundPool.play(soundPoolMap.get(id), curVolume, curVolume, 1, 0,
//                            volumnRatio);
//                }
//            },5000);
//        }

//        final int i = 0;
//        do{
//            if (i==0){
//                soundPool.play(soundPoolMap.get(typeID[i]), curVolume, curVolume, 1, 0,
//                        volumnRatio);
//            }else {
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        soundPool.play(soundPoolMap.get(typeID[i]), curVolume, curVolume, 1, 0,
//                                volumnRatio);
//                        i++;
//                    }
//                },2000);
////            }
//        }while (i<typeID.length);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<typeID.length;i++){

                    soundPool.play(soundPoolMap.get(typeID[i]), curVolume, curVolume, 1, 0,
                            volumnRatio);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }

    public void PlaySound1(final int index, final Integer... typeID) {

        soundPool.play(soundPoolMap.get(typeID[index]), curVolume, curVolume, 1, 0,
                volumnRatio);
        if (index>=typeID.length-1){
            return;
        }
         mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int de = index+1;
                    PlaySound1(de,typeID);
                }
            },2000);

//        for (final int id:typeID) {
//            mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    soundPool.play(soundPoolMap.get(id), curVolume, curVolume, 1, 0,
//                            volumnRatio);
//                }
//            },5000);
//        }

    }
    public void StopSound(int typeID) {
        soundPool.stop(soundPoolMap.get(typeID));
    }


    public void exitSoundPool() {
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }


}
