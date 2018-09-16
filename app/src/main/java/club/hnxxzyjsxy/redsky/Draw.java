package club.hnxxzyjsxy.redsky;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;


import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

public class Draw extends SurfaceView implements SurfaceHolder.Callback {


    public Draw(Context context) {
        super(context);
        object.surfaceHolder = getHolder();
        object.surfaceHolder.addCallback(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return Touch.Touch(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.e("T", "create");

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        environmentObject.display_w = i1;
        environmentObject.display_h = i2;
        object.surfaceHolder = surfaceHolder;
        object.head.add(new LinkedList<Goods>());
        object.head.add(new LinkedList<Goods>());
        object.mainThread = new Thread(new Render());
        //mainThread.setPriority(10);
        object.mainThread.start();


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.e("A", "destro");
        object.state = false;
        environmentObject.isRun = false;
    }

   /*     Canvas Mcanvas;
        Paint paint = new Paint();
        paint.setTextSize(50);
        if (!environmentObject.iSinit) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Init.init();
                    environmentObject.iSinit = true;
                    environmentObject.page = 0;

                }
            }).start();
        }
        long InitTime = System.currentTimeMillis();
        while (true) {

            if (environmentObject.player_blood <= 0) {


                System.exit(0);
            }


            object.starttime = System.currentTimeMillis();
            object.zTime = System.currentTimeMillis();
            object.RunTime = (System.currentTimeMillis() - InitTime);
            object.beforetime = System.currentTimeMillis() - InitTime;
            if (environmentObject.isRun) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Mcanvas = object.surfaceHolder.lockHardwareCanvas();
                } else {
                    Mcanvas = object.surfaceHolder.lockCanvas();
                }
                if (!environmentObject.isRun) {
                    break;
                }
                if (!environmentObject.iSinit) {
                    GameInit(Mcanvas);
                }


                switch (environmentObject.page) {
                    case 0:
                        Home.Home(Mcanvas);
                        break;
                    case 22:
                        environmentObject.startime = System.currentTimeMillis();


                        switch (environmentObject.code) {
                            case 0:
                                Play.ZeroToOne(Mcanvas);
                                break;
                            case 1:
                                Render.GameAction(Mcanvas);
                                break;
                        }
                        environmentObject.endtime = System.currentTimeMillis() - environmentObject.startime;

                        break;
                }
                DrawSystemMessage(Mcanvas);


                Mcanvas.drawText(object.endtime + "", 0, 100, paint);
                Mcanvas.drawText(String.valueOf(object.head.get(0).size()), 0, 200, paint);
                Mcanvas.drawText(String.valueOf(object.fell.size()), 0, 300, paint);
                Mcanvas.drawText(environmentObject.display_w + "/" + environmentObject.display_h, 0, 500, paint);



        *//*        long box = environmentObject.display_w / 5;
                long z = 0;
                if (rucksack.size() > 0) {
                    for (int i = 0; i < rucksack.size(); i++) {
                        switch (rucksack.get(i)) {
                            case ItemID.HolyWater:
                                break;
                        }
                    }
                }*//*
                if (environmentObject.isRun) {

                }

                object.surfaceHolder.unlockCanvasAndPost(Mcanvas);
                object.endtime = System.currentTimeMillis() - object.starttime;
            }


            // Log.e("全局渲染时间：", endtime + "/" + environmentObject.endtime);
//            Log.e("全局渲染时间：", FullBitmap.getRowBytes()*FullBitmap.getHeight()+"");

        }*/


    private void GameInit(Canvas canvas) {


    }

    private void DrawSystemMessage(Canvas canvas) {
        if (environmentObject.isBack) {
            canvas.drawText("INFAMATION", 500, 500, new Paint());
        }
    }

}