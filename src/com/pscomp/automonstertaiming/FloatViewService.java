package com.pscomp.automonstertaiming;

import java.io.IOException;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;

public class FloatViewService extends Service {
    
    private static final String TAG = "FloatViewService";
    private WindowManager mWindowManager = null;
    private StarView mStarView = null;
    
    private static Object mBlockObject = new Object();
    
    private Runtime mRuntime = null;
    
    private int mSelectedStage;
    private int mP = 0;
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");

        Notification.Builder notiBuilder = new Notification.Builder(getApplicationContext());
        notiBuilder.setOngoing(true);
        notiBuilder.setTicker("몬스터 길들이기 오토 동작 중...");
        notiBuilder.setContentText("몬스터 길들이기 오토");
        notiBuilder.setSmallIcon(R.drawable.ic_launcher);
        startForeground(1000, notiBuilder.build());
        mSelectedStage = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("Stage", 0);
        mRuntime = Runtime.getRuntime();
        try {
            mRuntime.exec("su");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mStarView = new StarView(getApplicationContext());
        mStarView.setImageResource(R.drawable.star);
        
        mStarView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Thread t1 = new Thread(new MyRunnable());
                    Thread t2 = new Thread(new MyRunnable());

                    Log.e(TAG, "Thread Name : " + Thread.currentThread().getName());
                    t1.start();
                    /*
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (!mDoingFlag) {
                        mDoingFlag = true;
                        if (mToneGenerator == null) {
                            mToneGenerator = new ToneGenerator(AudioManager.STREAM_MUSIC, ToneGenerator.MAX_VOLUME);
                        }
//                      mToneGenerator.startTone(ToneGenerator.TONE_DTMF_0);
                        mToneGenerator.startTone(ToneGenerator.TONE_DTMF_0, 1000);
                        for (mP = 0;mP < 100;mP++) {
//                            new Thread(new Runnable() {
//                                @Override
//                                public void run() {
                                    startAutoPlay(mP);
//                                }
//                            }).start();
                        }
                    } else {
                        mDoingFlag = false;
                        if (mToneGenerator == null) {
                            mToneGenerator = new ToneGenerator(AudioManager.STREAM_MUSIC, ToneGenerator.MAX_VOLUME);
                        }
                        mToneGenerator.startTone(ToneGenerator.TONE_DTMF_9, 1000);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mToneGenerator.stopTone();
                        mToneGenerator.release();
                        mToneGenerator = null;
                        forceStopMyPackage();
                    }
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    mStarView.setX(event.getX());
                    mStarView.setY(event.getY());
                    mStarView.requestLayout();
                    mStarView.invalidate();
                }
                return false;
                     */
                }
                return false;
            }
        });
        
        WindowManager.LayoutParams params =
                new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
                );
        params.gravity = Gravity.LEFT;
//        params.verticalMargin = 1.0f;
        params.y = 170;
        
        mWindowManager = (WindowManager) getSystemService(Service.WINDOW_SERVICE);
        mWindowManager.addView(mStarView, params);
        
        if (PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("Restarted", false)) {
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putBoolean("Restarted", false).commit();
            MotionEvent downEvent = MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0);
            MotionEvent upEvent = MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0);
            mStarView.dispatchTouchEvent(downEvent);
            mStarView.dispatchTouchEvent(upEvent);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) getSystemService(Service.WINDOW_SERVICE);
        }
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putBoolean("Restarted", true).commit();
        if (mStarView != null) {
            mWindowManager.removeView(mStarView);
        }
        super.onDestroy();
    }
    
    private synchronized void startAutoPlay(int stage) {
        try {
            if (stage == 511) {
                //모험시작버튼
                mRuntime.exec(new String[] {"su", ";", "input", "tap", "1019", "663"});
                Thread.sleep(30000);
                //친구 데려가기 버튼
                mRuntime.exec(new String[] {"su", ";", "input", "tap", "840", "254"});
                Thread.sleep(30000);
                //시작 버튼
                mRuntime.exec(new String[] {"su", ";", "input", "tap", "861", "663"});
                Thread.sleep(120000);
                //보물상자
                mRuntime.exec(new String[] {"su", ";", "input", "tap", "941", "487"});
                Thread.sleep(30000);
                //확인 버튼
                mRuntime.exec(new String[] {"su", ";", "input", "tap", "912", "693"});
                Thread.sleep(30000);
                //다시 시작 버튼
                mRuntime.exec(new String[] {"su", ";", "input", "tap", "264", "682"});
                Thread.sleep(360000);
            } else if (stage == 64) {
                //자동 스킬 버튼
                mRuntime.exec(new String[] {"su", ";", "input", "tap", "1107", "254"});
                Thread.sleep(30000);
                //모험시작버튼
                mRuntime.exec(new String[] {"su", ";", "input", "tap", "1019", "663"});
                Thread.sleep(30000);
                //친구 데려가기 버튼
                mRuntime.exec(new String[] {"su", ";", "input", "tap", "840", "254"});
                Thread.sleep(30000);
                //시작 버튼
                mRuntime.exec(new String[] {"su", ";", "input", "tap", "861", "663"});
                Thread.sleep(240000);
                //보물상자
                mRuntime.exec(new String[] {"su", ";", "input", "tap", "941", "487"});
                Thread.sleep(30000);
                //확인 버튼
                mRuntime.exec(new String[] {"su", ";", "input", "tap", "912", "693"});
                Thread.sleep(30000);
                //다시 시작 버튼
                mRuntime.exec(new String[] {"su", ";", "input", "tap", "264", "682"});
                Thread.sleep(210000);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Process Manager", "Unable to execute top command");
        }
    }
    
    private void synchronizedTestMethod() {
        for(int i = 0;i <= 1;i++) {
            Log.e(TAG, "Thread : " + Thread.currentThread().getName() + " Value-2 : " + i);
            startAutoPlay(mSelectedStage);
        }
    }
    
    class MyRunnable implements Runnable {
        public static final String TAG = "MyRunnable";
        public static final boolean DEBUG = false;

        @Override
        public void run() {
            Log.e(TAG, "Thread Name : " + Thread.currentThread().getName());
            for (int i = 0;i < 50;i++) {
                Log.e(TAG, "Value-1 : " + i);
                Thread t3 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (mBlockObject) {
                            synchronizedTestMethod();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                });
                t3.start();
                try {
                    t3.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
