package com.wgw.medalprogress;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wgw.medalprogress.Utils.BroadcastSound;
import com.wgw.medalprogress.view.CustomHorizontalProgresNoNum;
import com.wgw.medalprogress.view.CustomHorizontalProgresWithNum;
import com.wgw.medalprogress.view.RoundlProgresWithNum;

import java.util.Timer;



public class MainActivity extends AppCompatActivity {
    private CustomHorizontalProgresNoNum horizontalProgress01;//水平不带进度
    private CustomHorizontalProgresWithNum horizontalProgress1,horizontalProgress2,horizontalProgress3;//水平带进度
    private RoundlProgresWithNum mRoundlProgresWithNum31;//自定义圆形进度条 不带数字进度
    private RoundlProgresWithNum mRoundlProgresWithNum32,mRoundlProgresWithNum33;//自定义圆形进度条 带数字进度

    private Timer timer,timer2,timer3;
    private Timer timer31,timer32,timer33;
    private TextView play_sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play_sound = (TextView) findViewById(R.id.play_sound);
        play_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BroadcastSound.getInstance(MainActivity.this).PlaySound1(0,1,2,3);
//                Resources res = getResources();
                //根据id名称，取得资源id
//                int identifier = res.getIdentifier("bust","raw",getPackageName());
//                Log.d("wgw_getIdentifier","======"+identifier);
//                SoundUtils.playSound(MainActivity.this,identifier);
//                SoundUtils.playSound(MainActivity.this,R.raw.backdart);
//                SoundUtils.playSound(MainActivity.this,R.raw.bust);
//                SoundUtils.playSound(MainActivity.this,R.raw.dart_100);
//                SoundUtils.playSoundArray(MainActivity.this,R.raw.backdart,R.raw.bust,R.raw.dart_3);
            }
        });


        horizontalProgress01 = (CustomHorizontalProgresNoNum) findViewById(R.id.horizontalProgress01);


        horizontalProgress1 = (CustomHorizontalProgresWithNum) findViewById(R.id.horizontalProgress1);
        horizontalProgress2 = (CustomHorizontalProgresWithNum) findViewById(R.id.horizontalProgress2);
        horizontalProgress3 = (CustomHorizontalProgresWithNum) findViewById(R.id.horizontalProgress3);
        Bitmap bitmap = scaleBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_xz1),150,150);

        horizontalProgress1.setProgress(0);
        horizontalProgress1.setMax(100);
        horizontalProgress1.setBitmap(bitmap);

        horizontalProgress2.setProgress(90);
        horizontalProgress2.setMax(100);
        horizontalProgress2.setBitmap(bitmap);

        horizontalProgress3.setProgress(0);
        horizontalProgress3.setMax(200);

        //Circle progress with num
        mRoundlProgresWithNum31 = (RoundlProgresWithNum) findViewById(R.id.mRoundlProgresWithNum31);
        mRoundlProgresWithNum31.setProgress(0);
        mRoundlProgresWithNum31.setMax(100);

        //Circle progress no num
        mRoundlProgresWithNum32 = (RoundlProgresWithNum) findViewById(R.id.mRoundlProgresWithNum32);
        mRoundlProgresWithNum32.setProgress(0);
        mRoundlProgresWithNum32.setMax(100);

        mRoundlProgresWithNum33 = (RoundlProgresWithNum) findViewById(R.id.mRoundlProgresWithNum33);
        mRoundlProgresWithNum33.setProgress(0);
        mRoundlProgresWithNum33.setMax(100);

//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                //实时更新进度
//                if (horizontalProgress1.getProgress() >= 100) {//指定时间取消
//                    timer.cancel();
//                }
//                horizontalProgress1.setProgress(horizontalProgress1.getProgress()+1);
//                horizontalProgress01.setProgress(horizontalProgress01.getProgress()+1);
//
//            }
//        }, 50, 50);
//
//        timer2 = new Timer();
//        timer2.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                //实时更新进度
//                if (horizontalProgress2.getProgress() >= 100) {//指定时间取消
//                    timer2.cancel();
//                }
//                horizontalProgress2.setProgress(horizontalProgress2.getProgress()+1);
//
//            }
//        }, 40, 40);
//
//
//        timer3 = new Timer();
//        timer3.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                //实时更新进度
//                if (horizontalProgress3.getProgress() >= horizontalProgress3.getMax()) {//指定时间取消
//                    timer3.cancel();
//                }
//                horizontalProgress3.setProgress(horizontalProgress3.getProgress()+1);
//
//            }
//        }, 50, 50);
//
//        timer31 = new Timer();
//        timer31.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                //实时更新进度
//                if (mRoundlProgresWithNum31.getProgress() >= mRoundlProgresWithNum31.getMax()) {//指定时间取消
//                    timer31.cancel();
//                }
//                mRoundlProgresWithNum31.setProgress(mRoundlProgresWithNum31.getProgress()+1);
//
//            }
//        }, 40, 40);
//
//        timer32 = new Timer();
//        timer32.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                //实时更新进度
//                if (mRoundlProgresWithNum32.getProgress() >= mRoundlProgresWithNum32.getMax()) {//指定时间取消
//                    timer32.cancel();
//                }
//                mRoundlProgresWithNum32.setProgress(mRoundlProgresWithNum32.getProgress()+1);
//
//            }
//        }, 30, 30);
//
//        timer33 = new Timer();
//        timer33.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                //实时更新进度
//                if (mRoundlProgresWithNum33.getProgress() >= mRoundlProgresWithNum33.getMax()) {//指定时间取消
//                    timer33.cancel();
//                }
//                mRoundlProgresWithNum33.setProgress(mRoundlProgresWithNum33.getProgress()+1);
//
//            }
//        }, 20, 20);


    }

    /**
     * 根据给定的宽和高进行拉伸
     *
     * @param origin    原图
     * @param newWidth  新图的宽
     * @param newHeight 新图的高
     * @return new Bitmap
     */
    private Bitmap scaleBitmap(Bitmap origin, int newWidth, int newHeight) {
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);// 使用后乘
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (!origin.isRecycled()) {
            origin.recycle();
        }
        return newBM;
    }
}
