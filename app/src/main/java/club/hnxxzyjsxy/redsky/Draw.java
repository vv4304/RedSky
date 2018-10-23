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
        Log.e("surface", "create");

        environmentObject.isRun = true;
        object.mediaPlayer.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.e("surface", "changed");
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
        Log.e("surface", "surfaceDestro");
        object.state = false;
        environmentObject.isRun = false;
        if (environmentObject.page == 22) {
            environmentObject.page = 1;
        }

        object.mediaPlayer.pause();


    }


    private void DrawSystemMessage(Canvas canvas) {
        if (environmentObject.isBack) {
            canvas.drawText("INFAMATION", 500, 500, new Paint());
        }
    }
}