package club.hnxxzyjsxy.redsky;

import android.content.res.AssetFileDescriptor;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;

import java.io.IOException;

public class Render implements Runnable {

    @Override
    public void run() {
        Canvas Mcanvas;
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

        new Thread(new Runnable() {
            @Override
            public void run() {

                AssetFileDescriptor fileDescriptor = null;
                try {
                    fileDescriptor = environmentObject.assetMg.openFd("TOONE.mp3");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    assert fileDescriptor != null;
                    object.mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    object.mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                // object.mediaPlayer.start();


            }
        }).start();


        long InitTime = System.currentTimeMillis();
        long temporaryTime = System.currentTimeMillis();
        while (true) {

            object.starttime = System.currentTimeMillis();
            object.zTime = System.currentTimeMillis();
            object.RunTime = (System.currentTimeMillis() - InitTime);
            object.beforetime = System.currentTimeMillis() - InitTime;


            if (environmentObject.player_blood <= 0) {
                //System.exit(0);

            }


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
                    //GameInit(Mcanvas);
                }


                switch (environmentObject.page) {
                    case 0:
                        Home.home(Mcanvas);
                        break;
                    case 22:
                        environmentObject.startime = System.currentTimeMillis();
                        switch (environmentObject.code) {
                            case 0:
                                Play.ZeroToOne(Mcanvas);
                                break;
                            case 1:

                                if (environmentObject.GameStartTime == 0) {
                                    environmentObject.GameStartTime = System.currentTimeMillis();
                                } else {
                                    environmentObject.GameRunTime = System.currentTimeMillis() - environmentObject.GameStartTime;

                                }


                                Play.GameAction(Mcanvas);
                                //Log.e(" Play.GameActionTime", System.currentTimeMillis() - environmentObject.StartTime + "");

                                break;
                        }
                        environmentObject.endtime = System.currentTimeMillis() - environmentObject.startime;
                        break;
                }
                // DrawSystemMessage(Mcanvas);

                Mcanvas.drawText("FPS" + environmentObject.FPS, 0, 100, paint);
                Mcanvas.drawText(String.valueOf(object.head.get(0).size()), 0, 200, paint);
                Mcanvas.drawText(String.valueOf(object.fell.size()), 0, 300, paint);
                Mcanvas.drawText(environmentObject.display_w + "/" + environmentObject.display_h, 0, 500, paint);
                Mcanvas.drawText(environmentObject.GameRunTime+"", 0, 600, paint);


                if (System.currentTimeMillis() - temporaryTime >= 100) {
                    temporaryTime = System.currentTimeMillis();
                    environmentObject.FPS = (int) (1000 / object.endtime);
                }
















        /*        long box = environmentObject.display_w / 5;
                long z = 0;
                if (rucksack.size() > 0) {
                    for (int i = 0; i < rucksack.size(); i++) {
                        switch (rucksack.get(i)) {
                            case ItemID.HolyWater:
                                break;
                        }
                    }
                }*/
                if (environmentObject.isRun) {

                }

                object.surfaceHolder.unlockCanvasAndPost(Mcanvas);


                object.endtime = System.currentTimeMillis() - object.starttime;
            }


            // Log.e("全局渲染时间：", endtime + "/" + environmentObject.endtime);
//            Log.e("全局渲染时间：", FullBitmap.getRowBytes()*FullBitmap.getHeight()+"");

        }

    }


}