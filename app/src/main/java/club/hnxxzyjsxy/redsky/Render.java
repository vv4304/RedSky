package club.hnxxzyjsxy.redsky;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;

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
        long InitTime = System.currentTimeMillis();
        while (true) {

            if (environmentObject.player_blood <= 0) {


                System.exit(0);
            }


            object. starttime = System.currentTimeMillis();
            object. zTime = System.currentTimeMillis();
            object. RunTime = (System.currentTimeMillis() - InitTime);
            object.beforetime = System.currentTimeMillis() - InitTime;
            if (environmentObject.isRun) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Mcanvas =object.surfaceHolder.lockHardwareCanvas();
                } else {
                    Mcanvas =object.surfaceHolder.lockCanvas();
                }
                if (!environmentObject.isRun) {
                    break;
                }
                if (!environmentObject.iSinit) {
                   //GameInit(Mcanvas);
                }


                switch (environmentObject.page) {
                    case 0:
                        //PageHome(Mcanvas);
                        break;
                    case 22:
                        environmentObject.startime = System.currentTimeMillis();
                        switch (environmentObject.code) {
                            case 0:
                                Play.ZeroToOne(Mcanvas);
                                break;
                            case 1:
                               Play.GameAction(Mcanvas);
                                break;
                        }
                        environmentObject.endtime = System.currentTimeMillis() - environmentObject.startime;
                        break;
                }
               // DrawSystemMessage(Mcanvas);


                Mcanvas.drawText(object.endtime + "", 0, 100, paint);
                Mcanvas.drawText(String.valueOf(object.head.get(0).size()), 0, 200, paint);
                Mcanvas.drawText(String.valueOf(object.fell.size()), 0, 300, paint);
                Mcanvas.drawText(environmentObject.display_w + "/" + environmentObject.display_h, 0, 500, paint);



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

                object. surfaceHolder.unlockCanvasAndPost(Mcanvas);


                object.  endtime = System.currentTimeMillis() -object. starttime;
            }


            // Log.e("全局渲染时间：", endtime + "/" + environmentObject.endtime);
//            Log.e("全局渲染时间：", FullBitmap.getRowBytes()*FullBitmap.getHeight()+"");

        }

    }






}