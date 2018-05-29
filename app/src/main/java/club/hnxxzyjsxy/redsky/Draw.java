package club.hnxxzyjsxy.redsky;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Draw extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    boolean init = false;
    Bitmap cache2;
    static int player_beaten_number = 0;
    boolean state = false;
    SurfaceHolder surfaceHolder;
    Bitmap background;
    ArrayList<Goods> gameImagesList = new ArrayList<>();
    // static List<Goods> bombImages = new LinkedList<>();
    //static List<Goods> grenades = new LinkedList<>();
    static List<Goods> fell = new LinkedList<>();
    static List<Goods> head = new LinkedList<>();
    static ArrayList<GoodsEntity.player> player = new ArrayList<>();


    long beforetime, sumulationtime = 0, fixedtimer = 20, starttime, endtime = 0;
    static GoodsEntity.own oneself;
    static Bitmap[] left = new Bitmap[4];
    static Bitmap[] reight = new Bitmap[4];
    static Bitmap[] down = new Bitmap[1];
    static ArrayList<Integer> move = new ArrayList<>();
    Bitmap[] button = new Bitmap[2];
    static Bitmap bomb[] = new Bitmap[2];
    static Bitmap baseball, grenade, NULL, greenhat;
    static Bitmap[] bombExplosion = new Bitmap[5];
    boolean isOk = true;
    Bitmap Blood;
    Canvas bloodCanvas;
    Timer GoodsUpdate, animation;
    static Thread mainThread;
    final Object object = new Object();
    Paint Bloodpaint1 = new Paint();
    Paint Bloodpaint2 = new Paint();
    Paint paint = new Paint();
    Thread request, send;


    public Draw(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (environmentObject.isBack) {

        } else {
            switch (environmentObject.page) {
                case 0:
                    switch (event.getAction() & event.ACTION_MASK) {
                        case MotionEvent.ACTION_DOWN:

                            if (event.getY() <= environmentObject.display_h / 2) {

                                environmentObject.page = 11;
                                GoodsUpdate.schedule(new GoodRunTask(), 0, 10);
                                animation.schedule(new Animation(), 0, 100);

                            } else {

                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {


                                        try {
                                            environmentObject.socket = new Socket("144.202.7.185", 7777);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }


                                        send.start();
                                        request.start();
                                        environmentObject.page = 22;

                                    }
                                }).start();

                            }

                            break;
                    }
                    break;
                case 11:


                    int i = event.getPointerCount();
                    int moves = 0;
                    switch (event.getAction() & event.ACTION_MASK) {
                        case MotionEvent.ACTION_DOWN:
                            moves = 1;
                            break;
                        case MotionEvent.ACTION_POINTER_DOWN:
                            moves = 1;
                            break;
                        case MotionEvent.ACTION_UP:
                            move.clear();
                            break;
                        case MotionEvent.ACTION_POINTER_UP:
                            Log.e("A", event.getActionIndex() + "");
                            // move.remove(event.getActionIndex());
                            break;
                    }

                    if (moves == 1) {
                        int x = (int) event.getX(event.getActionIndex());
                        if (x < environmentObject.display_w / 2) {
                            move.add(0);
                        } else {
                            move.add(1);
                        }
                    }
                    break;
            }
        }
        return true;
    }

    private void init() {
        environmentObject.bomb_sleep = (int) (environmentObject.display_h * 0.009);
        environmentObject.player_speed = (int) (environmentObject.display_w * 0.01);
        initImage();
        gameImagesList.add(new backgronudImage(background));
        //player.add(new GoodsEntity.manImage(environmentObject.display_w / 2, (int) (environmentObject.display_h * environmentObject.lawn) - down[0].getHeight()));
        oneself = new GoodsEntity.own(environmentObject.display_w / 2, (int) (environmentObject.display_h * environmentObject.lawn) - down[0].getHeight());
        player.add(new GoodsEntity.player(environmentObject.display_w / 2, (int) (environmentObject.display_h * environmentObject.lawn) - down[0].getHeight()));

        GoodsUpdate = new Timer();
        animation = new Timer();
        long maxMemory = ((int) Runtime.getRuntime().maxMemory()) / 1024 / 1024;
        Log.e("SSS", maxMemory + "");


        Thread thread = new Thread(new SocketConnectRead());
        // thread.start();
        environmentObject.iSinit = true;

        request = new Thread(new SocketConnectSend());
        send = new Thread(new SocketConnectRead());


    }

    private void initImage() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        cache2 = Bitmap.createBitmap(environmentObject.display_w, environmentObject.display_h, Bitmap.Config.RGB_565);
        background = Tool.getImageBitmap("bg.png");
        Bitmap bitmap = Tool.getImageBitmap("player.png");
        Bitmap bombexplosion = Tool.getImageBitmap("bomb.png");
        int[] z = {0, 100, 200, 300};
        int[] bomb_explosion = {0, 150, 300, 450, 600};
        for (int i = 0; i < 5; i++) {
            bombExplosion[i] = Bitmap.createBitmap(bombexplosion, bomb_explosion[i], 0, 150, 150);
            bombExplosion[i] = Tool.getNewSizeBitmap(bombExplosion[i], (float) (environmentObject.display_w * 0.3 / bombExplosion[i].getWidth()), (float) (environmentObject.display_w * 0.3 / bombExplosion[i].getWidth()));
            Canvas canvas = new Canvas(bombExplosion[i]);
            canvas.drawRect(0, 0, bombExplosion[0].getWidth(), bombExplosion[0].getHeight(), paint);

        }
        for (int i = 0; i < 4; i++) {
            left[i] = Bitmap.createBitmap(bitmap, z[i], 608 / 4, bitmap.getWidth() / 4, bitmap.getHeight() / 4);
            left[i] = Tool.getNewSizeBitmap(left[i], (float) (environmentObject.display_w * 0.15 / left[i].getWidth()), (float) (environmentObject.display_w * 0.15 / left[i].getWidth()));
            Canvas canvas = new Canvas(left[i]);
            canvas.drawRect(0, 0, left[0].getWidth(), left[0].getHeight(), paint);
        }
        for (int i = 0; i < 4; i++) {
            reight[i] = Bitmap.createBitmap(bitmap, z[i], 608 / 2, bitmap.getWidth() / 4, bitmap.getHeight() / 4);
            reight[i] = Tool.getNewSizeBitmap(reight[i], (float) (environmentObject.display_w * 0.15 / reight[i].getWidth()), (float) (environmentObject.display_w * 0.15 / reight[i].getWidth()));

            Canvas canvas = new Canvas(reight[i]);
            canvas.drawRect(0, 0, reight[0].getWidth(), reight[0].getHeight(), paint);
        }
        down[0] = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth() / 4, bitmap.getHeight() / 4);
        down[0] = Tool.getNewSizeBitmap(down[0], (float) (environmentObject.display_w * 0.15 / down[0].getWidth()), (float) (environmentObject.display_w * 0.15 / down[0].getWidth()));

        Canvas canvas = new Canvas(down[0]);
        canvas.drawRect(0, 0, down[0].getWidth(), down[0].getHeight(), paint);
        bitmap.recycle();

        Bitmap items = Tool.getImageBitmap("item.png");
        bomb[0] = Bitmap.createBitmap(items, 1033, 11, 26, 95);
        bomb[1] = Bitmap.createBitmap(items, 896, 14, 50, 94);
        baseball = Bitmap.createBitmap(items, 1214, 40, 36, 34);
        grenade = Bitmap.createBitmap(items, 687, 32, 59, 59);
        NULL = Bitmap.createBitmap(items, 17, 24, 48, 48);
        greenhat = Bitmap.createBitmap(items, 1793, 45, 97, 55);


        bomb[1] = Tool.getNewSizeBitmap(bomb[1], (float) (environmentObject.display_w * 0.05 / bomb[1].getWidth()), (float) (environmentObject.display_w * 0.05 / bomb[1].getWidth()));
        bomb[0] = Tool.getNewSizeBitmap(bomb[0], (float) (environmentObject.display_w * 0.03 / bomb[0].getWidth()), (float) (environmentObject.display_w * 0.03 / bomb[0].getWidth()));
        baseball = Tool.getNewSizeBitmap(baseball, (float) (environmentObject.display_w * 0.05 / baseball.getWidth()), (float) (environmentObject.display_w * 0.05 / baseball.getWidth()));
        grenade = Tool.getNewSizeBitmap(grenade, (float) (environmentObject.display_w * 0.09 / grenade.getWidth()), (float) (environmentObject.display_w * 0.09 / grenade.getWidth()));
        NULL = Tool.getNewSizeBitmap(NULL, (float) (environmentObject.display_w * 0.15 / NULL.getWidth()), (float) (environmentObject.display_w * 0.15 / NULL.getWidth()));
        greenhat = Tool.getNewSizeBitmap(greenhat, (float) (environmentObject.display_w * 0.15 / greenhat.getWidth()), (float) (environmentObject.display_w * 0.15 / greenhat.getWidth()));


        Canvas canvas21 = new Canvas(bomb[0]);
        canvas21.drawRect(0, 0, bomb[0].getWidth(), bomb[0].getHeight(), paint);
        Canvas canvas22 = new Canvas(bomb[1]);
        canvas22.drawRect(0, 0, bomb[1].getWidth(), bomb[1].getHeight(), paint);

        Canvas canvas223 = new Canvas(baseball);
        canvas223.drawRect(0, 0, baseball.getWidth(), baseball.getHeight(), paint);
        items.recycle();
        Blood = Bitmap.createBitmap(100, 30, Bitmap.Config.RGB_565);

        Bloodpaint1.setColor(Color.BLUE);
        Bloodpaint2.setColor(Color.WHITE);
        bloodCanvas = new Canvas(Blood);


    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.e("T", "create");
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.e("T", "changed");
        environmentObject.display_w = i1;
        environmentObject.display_h = i2;
        this.surfaceHolder = surfaceHolder;

        mainThread = new Thread(this);
        mainThread.setPriority(10);
        mainThread.start();


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.e("A", "destro");
        state = false;
        environmentObject.isRun = false;
    }


    @Override
    public void run() {

        Canvas Mcanvas;


        if (!environmentObject.iSinit) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    init();
                    environmentObject.iSinit = true;
                    environmentObject.page = 0;

                }
            }).start();
        }
        long InitTime = System.currentTimeMillis();
        while (true) {
            starttime = System.currentTimeMillis();
            beforetime = System.currentTimeMillis() - InitTime;
            if (environmentObject.isRun) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Mcanvas = surfaceHolder.lockHardwareCanvas();
                } else {
                    Mcanvas = surfaceHolder.lockCanvas();
                }
                if (!environmentObject.isRun) {
                    break;
                }
                if (!environmentObject.iSinit) {
                    GameInit(Mcanvas);
                }

                switch (environmentObject.page) {
                    case 0:
                        PageHome(Mcanvas);
                        break;
                    case 11:
                        if (environmentObject.player_blood >= -50000000) {
                            GameOne(Mcanvas);

                        }
                        break;
                    case 22:
                        GameTwo(Mcanvas);
                        break;

                }
                DrawSystemMessage(Mcanvas);
                Paint paint = new Paint();
                paint.setTextSize(100);
                Mcanvas.drawText(endtime + "", 500, 500, paint);
                Mcanvas.drawText(String.valueOf(head.size()), 700, 700, paint);


                if (environmentObject.isRun) {
                    surfaceHolder.unlockCanvasAndPost(Mcanvas);
                }

                endtime = System.currentTimeMillis() - starttime;
                //Log.e("TIME", endtime + "");

            }
        }

    }

    private void GameInit(Canvas canvas) {


        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.BLUE);

        canvas.drawText("INIT", 500, 500, paint);
    }

    private void GameOne(Canvas canvas) {

        while (sumulationtime < beforetime) {
            sumulationtime += fixedtimer;

            for (int i = 0; i < fell.size(); i++) {
                fell.get(i).update();
            }
            for (int i = 0; i < head.size(); i++) {
                head.get(i).update();
            }
            oneself.update();
        }

        for (int i = 0; i < fell.size(); i++) {
            float x = fell.get(i).getX();
            float y = fell.get(i).getY();
            int width = fell.get(i).getwidth();
            int height = fell.get(i).getheight();
            float GoodsCenter = fell.get(i).getX() + (fell.get(i).getwidth() / 2);
            float PlayerCenter = oneself.getX() + (oneself.getwidth() / 2);


            if (head.size() > 0) {
                if (x + width >= environmentObject.player_x && x <= environmentObject.player_x + oneself.getwidth() && y + height >= head.get(head.size() - 1).getY() && y + height <= oneself.getY()) {


                    switch (fell.get(i).toString()) {
                        case "baseball":

                            if (GoodsCenter <= PlayerCenter) {
                                fell.get(i).setX(head.get(0).getX() - width - 1);
                            } else {
                                fell.get(i).setX(head.get(0).getX() + head.get(0).getwidth() + 1);
                            }

                            head.remove(head.get(head.size() - 1));
                            break;
                        case "grenade":

                            if (GoodsCenter <= PlayerCenter) {
                                fell.get(i).setX(head.get(0).getX() - width - 1);
                            } else {
                                fell.get(i).setX(head.get(0).getX() + head.get(0).getwidth() + 1);
                            }
                            head.remove(head.get(head.size() - 1));
                            break;
                        case "bomb":
                            if (GoodsCenter <= PlayerCenter) {
                                fell.get(i).setX(head.get(0).getX() - width - 1);
                            } else {
                                fell.get(i).setX(head.get(0).getX() + head.get(0).getwidth() + 1);
                            }
                            head.remove(head.get(head.size() - 1));

                            break;
                        case "greenhat":
                            fell.get(i).setIsRun(false);
                            fell.get(i).setY((float) (head.get(head.size() - 1).getY() - head.get(head.size() - 1).getheight() + environmentObject.display_h * 0.02));

                            head.add(fell.get(i));
                            fell.remove(fell.get(i));
                            break;
                    }
                }
            }


            if (x + width >= environmentObject.player_x && x <= environmentObject.player_x + oneself.getwidth() && y + height >= Draw.oneself.getY() && y <= oneself.getY() + oneself.getheight()) {
                fell.get(i).setIsRun(false);
                switch (fell.get(i).toString()) {
                    case "baseball":
                        environmentObject.player_blood -= 10;
                        fell.get(i).setX(fell.get(i).getX() - bombExplosion[0].getWidth() / 2);
                        fell.get(i).setY(fell.get(i).getY() - bombExplosion[1].getHeight() / 2);
                        break;
                    case "grenade":
                        environmentObject.player_blood -= 20;
                        fell.get(i).setX(fell.get(i).getX() - bombExplosion[0].getWidth() / 2);
                        fell.get(i).setY(fell.get(i).getY() - bombExplosion[1].getHeight() / 2);

                        break;
                    case "bomb":

                        environmentObject.player_blood -= 50;
                        fell.get(i).setX(fell.get(i).getX() - bombExplosion[0].getWidth() / 2);
                        fell.get(i).setY(fell.get(i).getY() - bombExplosion[1].getHeight() / 2);
                        break;
                    case "greenhat":
                        if (head.size() < 1) {
                            fell.get(i).setY((float) (environmentObject.player_y - grenade.getHeight() + environmentObject.display_h * 0.01));
                        } else {
                            fell.get(i).setY((float) (head.get(head.size() - 1).getY() - head.get(head.size() - 1).getheight() + environmentObject.display_h * 0.02));

                        }
                        head.add(fell.get(i));
                        fell.remove(fell.get(i));
                        break;
                }

            }

        }


        for (int i = 0; i < gameImagesList.size(); i++) {
            canvas.drawBitmap(gameImagesList.get(i).getBitmap(), gameImagesList.get(i).getX(), gameImagesList.get(i).getY(), paint);
        }

        bloodCanvas.drawRect(0, 0, Blood.getWidth(), Blood.getHeight(), Bloodpaint1);
        bloodCanvas.drawRect(0, 0, environmentObject.player_blood, Blood.getHeight(), Bloodpaint2);

        Paint paint1 = new Paint();
        paint1.setTextSize(30);
        paint1.setColor(Color.RED);
        bloodCanvas.drawText(environmentObject.player_blood + "", 0, 28, paint1);

        canvas.drawBitmap(Blood, (float) (environmentObject.display_w * 0.01), (float) (environmentObject.display_h * 0.01), paint);
        canvas.drawBitmap(oneself.getBitmap(), oneself.getX(), oneself.getY(), paint);

        //Log.e("S", String.valueOf(head.size()));

        for (int i = 0; i < head.size(); i++) {
            // Log.e("S", "DRAW");
            canvas.drawBitmap(head.get(i).getBitmap(), head.get(i).getX(), head.get(i).getY(), paint);
        }

        for (int i = 0; i < fell.size(); i++) {
            canvas.drawBitmap(fell.get(i).getBitmap(), fell.get(i).getX(), fell.get(i).getY(), paint);
        }

        canvas.drawBitmap(NULL, environmentObject.display_w - NULL.getWidth(), environmentObject.display_h / 2, paint);


    }

    private void GameTwo(Canvas canvas) {


        for (int i = 0; i < fell.size() - 1; i++) {
            if (fell.get(i).getX() + fell.get(i).getwidth() >= environmentObject.player_x && fell.get(i).getX() <= environmentObject.player_x + oneself.getwidth() && fell.get(i).getY() + fell.get(i).getheight() >= Draw.oneself.getY()) {
                fell.get(i).setIsRun(false);
                fell.get(i).setX(fell.get(i).getX() - bombExplosion[0].getWidth() / 2);
                fell.get(i).setY(fell.get(i).getY() - bombExplosion[1].getHeight() / 2);

                switch (fell.get(i).toString()) {
                    case "baseball":
                        environmentObject.player_blood -= 1;

                        break;
                    case "grenade":
                        environmentObject.player_blood -= 10;

                        break;
                    case "bomb":
                        environmentObject.player_blood -= 50;

                        break;
                }
            }
        }


        for (int i = 0; i < gameImagesList.size(); i++) {
            canvas.drawBitmap(gameImagesList.get(i).getBitmap(), gameImagesList.get(i).getX(), gameImagesList.get(i).getY(), paint);
        }

        bloodCanvas.drawRect(0, 0, Blood.getWidth(), Blood.getHeight(), Bloodpaint1);
        bloodCanvas.drawRect(0, 0, environmentObject.player_blood, Blood.getHeight(), Bloodpaint2);

        Paint paint1 = new Paint();
        paint1.setTextSize(30);
        paint1.setColor(Color.RED);
        bloodCanvas.drawText(environmentObject.player_blood + "", 0, 28, paint1);

        canvas.drawBitmap(Blood, (float) (environmentObject.display_w * 0.01), (float) (environmentObject.display_h * 0.01), paint);


        for (int i = 0; i < player.size(); i++) {
            canvas.drawBitmap(player.get(i).getBitmap(), player.get(i).getX(), player.get(i).getY(), paint);
        }


        canvas.drawBitmap(oneself.getBitmap(), oneself.getX(), oneself.getY(), paint);
/*
        for (int i = 0; i < fell.size(); i++) {
            canvas.drawBitmap(fell.get(i).getBitmap(), fell.get(i).getX(), fell.get(i).getY(), paint);
        }*/

    }

    private void PageHome(Canvas canvas) {

        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.BLUE);

        canvas.drawText("Home", 500, 500, paint);

    }

    private void DrawSystemMessage(Canvas canvas) {
        if (environmentObject.isBack) {
            canvas.drawText("INFAMATION", 500, 500, new Paint());
        }
    }

    private class backgronudImage implements Goods {
        private Bitmap bg;
        private Bitmap newbg;
        Paint paint;
        Canvas canvas;

        private backgronudImage(Bitmap bitmap) {
            this.bg = bitmap;
            newbg = Bitmap.createBitmap(environmentObject.display_w, environmentObject.display_h, Bitmap.Config.ARGB_8888);
            paint = new Paint();
            canvas = new Canvas(newbg);
            canvas.drawBitmap(bg, new Rect(0, 0, bg.getWidth(), bg.getHeight()), new Rect(0, 0, environmentObject.display_w, environmentObject.display_h), paint);
        }

        @Override
        public Bitmap getBitmap() {
            return newbg;
        }

        @Override
        public float getX() {
            return 0;
        }

        @Override
        public float getY() {
            return 0;
        }

        @Override
        public void setX(float x) {

        }

        @Override
        public void setY(float y) {

        }

        @Override
        public void update() {

        }


        @Override
        public int getwidth() {
            return 0;
        }

        @Override
        public int getheight() {
            return 0;
        }

        @Override
        public boolean getIsRun() {
            return false;
        }

        @Override
        public void setIsRun(boolean is) {

        }

        @Override
        public void setAnimationNum(int num) {

        }

        @Override
        public int getAnimationNum() {
            return 0;
        }


    }

    private class button implements Goods {

        Bitmap button_bg;
        Paint paint = new Paint();

        public button() {
            button_bg = Bitmap.createBitmap(environmentObject.display_w, (int) (environmentObject.display_h * 0.1), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(button_bg);
            //canvas.drawBitmap(button[0], 0, 0, paint);
            //canvas.drawBitmap(button[1], (int) (button_bg.getWidth() - button[1].getWidth()), 0, paint);
        }

        @Override
        public Bitmap getBitmap() {
            return button_bg;
        }

        @Override
        public float getX() {
            return 0;
        }

        @Override
        public float getY() {
            return (int) (environmentObject.display_h - (environmentObject.display_h * 0.1));
        }

        @Override
        public void setX(float x) {

        }

        @Override
        public void setY(float y) {

        }

        @Override
        public void update() {

        }


        @Override
        public int getwidth() {
            return 0;
        }

        @Override
        public int getheight() {
            return 0;
        }

        @Override
        public boolean getIsRun() {
            return false;
        }

        @Override
        public void setIsRun(boolean is) {

        }

        @Override
        public void setAnimationNum(int num) {

        }

        @Override
        public int getAnimationNum() {
            return 0;
        }
    }

    private class GoodRunTask extends TimerTask {
        int num = 0;

        @Override
        public void run() {

            if (environmentObject.isRun) {
                for (int i = 0; i < fell.size(); i++) {
                    if (fell.get(i).getY() > environmentObject.display_h) {
                        fell.remove(i);
                    }

                }

                if (num == 50) {
                    Random random = new Random();
                    synchronized (object) {
                        switch (random.nextInt(4)) {
                            case 0:
                                fell.add(new GoodsEntity.bombImage(bomb[0].getWidth(), bomb[0].getHeight()));
                                break;
                            case 1:
                                fell.add(new GoodsEntity.Grenade(grenade.getWidth(), grenade.getHeight()));

                                break;
                            case 2:
                                fell.add(new GoodsEntity.Baseball(baseball.getWidth(), baseball.getHeight()));
                                break;
                            case 3:
                                fell.add(new GoodsEntity.GreenHat(greenhat.getWidth(), greenhat.getHeight()));
                                break;
                        }
                    }
                    num = 0;
                }
                num++;
                //  for (GameImage bomb : (List<GameImage>) bombImages.clone()) {
                //       bomb.update();
                //   }


            }
        }
    }

    private class Animation extends TimerTask {

        @Override
        public void run() {

            for (int i = 0; i < fell.size() - 1; i++) {
                if (!fell.get(i).getIsRun()) {
                    fell.get(i).setAnimationNum(fell.get(i).getAnimationNum() + 1);
                }
            }


        }
    }


}