package club.hnxxzyjsxy.redsky;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Draw extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    Bitmap cache2;
    int player_beaten_number = 0;



    boolean state = false;
    SurfaceHolder surfaceHolder;
    Bitmap background;
    int display_w, display_h;
    int player_x, player_y, player_blood = 100, player_speed;
    ArrayList<Goods> gameImagesList = new ArrayList<>();
    ArrayList<bombImage> bombImages = new ArrayList<>();
    ArrayList<manImage> manimages = new ArrayList<>();
    Bitmap[] left = new Bitmap[4];
    Bitmap[] reight = new Bitmap[4];
    Bitmap[] down = new Bitmap[1];
    Bitmap[] button = new Bitmap[2];
    Bitmap bomb[] = new Bitmap[2];
    Bitmap item;
    double lawn = 0.8572916667;
    Bitmap[] bombExplosion = new Bitmap[5];
    boolean isOk = true;
    int bomb_sleep;
    ArrayList<Integer> move = new ArrayList<>();


    public Draw(Context context) {
        super(context);
        background = new Tool().getImageBitmap("bg.png");
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
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
            if (x < display_w / 2) {
                move.add(0);
            } else {
                move.add(1);
            }
        }
        return true;
    }

    private void init() {
        bomb_sleep = (int) (display_h * 0.001);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        player_speed = (int) (display_w * 0.05);
        cache2 = Bitmap.createBitmap(display_w, display_h, Bitmap.Config.RGB_565);
        Bitmap bitmap = new Tool().getImageBitmap("player.png");
        Bitmap bombexplosion = new Tool().getImageBitmap("bomb.png");
        int[] z = {0, 100, 200, 300};
        int[] bomb_explosion = {0, 150, 300, 450, 600};

        for (int i = 0; i < 5; i++) {
            bombExplosion[i] = Bitmap.createBitmap(bombexplosion, bomb_explosion[i], 0, 150, 150);
            bombExplosion[i] = new Tool().getNewSizeBitmap(bombExplosion[i], (float) (display_w * 0.3 / bombExplosion[i].getWidth()), (float) (display_w * 0.3 / bombExplosion[i].getWidth()));
            Canvas canvas = new Canvas(bombExplosion[i]);
            canvas.drawRect(0, 0, bombExplosion[0].getWidth(), bombExplosion[0].getHeight(), paint);

        }

        for (int i = 0; i < 4; i++) {
            left[i] = Bitmap.createBitmap(bitmap, z[i], 608 / 4, bitmap.getWidth() / 4, bitmap.getHeight() / 4);
            left[i] = new Tool().getNewSizeBitmap(left[i], (float) (display_w * 0.15 / left[i].getWidth()), (float) (display_w * 0.15 / left[i].getWidth()));
            Canvas canvas = new Canvas(left[i]);
            canvas.drawRect(0, 0, left[0].getWidth(), left[0].getHeight(), paint);
        }
        for (int i = 0; i < 4; i++) {
            reight[i] = Bitmap.createBitmap(bitmap, z[i], 608 / 2, bitmap.getWidth() / 4, bitmap.getHeight() / 4);
            reight[i] = new Tool().getNewSizeBitmap(reight[i], (float) (display_w * 0.15 / reight[i].getWidth()), (float) (display_w * 0.15 / reight[i].getWidth()));

            Canvas canvas = new Canvas(reight[i]);
            canvas.drawRect(0, 0, reight[0].getWidth(), reight[0].getHeight(), paint);


        }
        down[0] = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth() / 4, bitmap.getHeight() / 4);
        down[0] = new Tool().getNewSizeBitmap(down[0], (float) (display_w * 0.15 / down[0].getWidth()), (float) (display_w * 0.15 / down[0].getWidth()));

        Canvas canvas = new Canvas(down[0]);
        canvas.drawRect(0, 0, down[0].getWidth(), down[0].getHeight(), paint);

        bitmap.recycle();
        Bitmap items = new Tool().getImageBitmap("item.png");

        bomb[0] = Bitmap.createBitmap(items, 1033, 11, 26, 95);
        bomb[0] = new Tool().getNewSizeBitmap(bomb[0], (float) (display_w * 0.03 / bomb[0].getWidth()), (float) (display_w * 0.03 / bomb[0].getWidth()));
        bomb[1] = Bitmap.createBitmap(items, 896, 14, 50, 94);
        bomb[1] = new Tool().getNewSizeBitmap(bomb[1], (float) (display_w * 0.05 / bomb[1].getWidth()), (float) (display_w * 0.05 / bomb[1].getWidth()));

        Canvas canvas21 = new Canvas(bomb[0]);
        canvas21.drawRect(0, 0, bomb[0].getWidth(), bomb[0].getHeight(), paint);
        Canvas canvas22 = new Canvas(bomb[1]);
        canvas22.drawRect(0, 0, bomb[1].getWidth(), bomb[1].getHeight(), paint);


        items.recycle();

        gameImagesList.add(new backgronudImage(background));
        manimages.add(new manImage(display_w / 2, (int) (display_h * lawn) - down[0].getHeight()));


        Timer timer = new Timer();
        timer.schedule(new RunTask(), 0, 16 * 3);

        long maxMemory = ((int) Runtime.getRuntime().maxMemory()) / 1024 / 1024;
        Log.e("SSS", maxMemory + "");

    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        state = true;


    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.e("px", i1 + "/" + i2);
        this.display_w = i1;
        this.display_h = i2;

        this.surfaceHolder = surfaceHolder;
        init();
        new Thread(this).start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.e("A", "destro");
        state = false;

    }

    @Override
    public void run() {
        Paint paint = new Paint();
        Canvas canvas;
        Bitmap Blood;
        Paint blood_bg = new Paint(), blood = new Paint();
        blood_bg.setColor(Color.BLUE);
        blood.setColor(Color.WHITE);
        Blood = Bitmap.createBitmap(100, 30, Bitmap.Config.RGB_565);
        Canvas bloodCanvas = new Canvas(Blood);
        while (state) {
            if (player_blood >= -50000000) {
                canvas = surfaceHolder.lockCanvas();
                for (Goods image : gameImagesList) {
                    canvas.drawBitmap(image.getBitmap(), image.getX(), image.getY(), paint);
                }
                bloodCanvas.drawRect(0, 0, Blood.getWidth(), Blood.getHeight(), blood_bg);
                bloodCanvas.drawRect(0, 0, player_blood, Blood.getHeight(), blood);
                canvas.drawBitmap(Blood, (float) (display_w * 0.01), (float) (display_h * 0.01), paint);
                for (int i = 0; i < manimages.size(); i++) {
                    canvas.drawBitmap(manimages.get(i).getBitmap(), manimages.get(i).getX(), manimages.get(i).getY(), paint);
                }
                ArrayList<bombImage> test = bombImages;
                for (int i = 0; i < test.size(); i++) {
                    canvas.drawBitmap(test.get(i).getBitmap(), test.get(i).getX(), test.get(i).getY(), paint);
                }
                surfaceHolder.unlockCanvasAndPost(canvas);
                //Log.e("player_xy",player_x+"/"+player_y);
            }
            // Log.e("start_end", end - start + "");
        }
    }

    private class backgronudImage implements Goods {
        private Bitmap bg;
        private Bitmap newbg;
        Paint paint;
        Canvas canvas;

        private backgronudImage(Bitmap bitmap) {
            this.bg = bitmap;
            newbg = Bitmap.createBitmap(display_w, display_h, Bitmap.Config.ARGB_8888);
            paint = new Paint();
            canvas = new Canvas(newbg);
            canvas.drawBitmap(bg, new Rect(0, 0, bg.getWidth(), bg.getHeight()), new Rect(0, 0, display_w, display_h), paint);
        }

        @Override
        public Bitmap getBitmap() {
            return newbg;
        }

        @Override
        public int getX() {
            return 0;
        }

        @Override
        public int getY() {
            return 0;
        }

        @Override
        public void update() {

        }


    }

    private class manImage implements Goods {
        int z = 0;

        public manImage(int x, int y) {
            player_x = x;
            player_y = y;
        }

        @Override
        public Bitmap getBitmap() {
            if (move.size() > 0) {
                if (move.get(move.size() - 1).equals(0)) {
                    return left[z];
                } else {
                    return reight[z];
                }
            }
            return down[0];
        }

        @Override
        public int getX() {
            return player_x;
        }

        @Override
        public int getY() {
            return player_y;
        }

        @Override
        public void update() {
            if (move.size() > 0) {
                if (move.get(move.size() - 1).equals(0)) {
                    z++;
                    if (z == 4) z = 0;
                    player_x -= player_speed;
                } else {
                    z++;
                    if (z == 4) z = 0;
                    player_x += player_speed;
                }
            }
        }
    }

    private class bombImage implements Goods {

        boolean is = true;
        int addSleep = 0;
        int num = 0;
        int x = 0, y = 0;
        int type = 0;
        Random random = new Random();
        Date date;
        int maxSleep = bomb_sleep * 3;
        long time;

        public bombImage() {
            Random random = new Random();
            this.x = random.nextInt(display_w - bomb[0].getWidth());
            this.y = -bomb[0].getHeight();
            type = random.nextInt(2);
        }

        @Override
        public Bitmap getBitmap() {
            if (is) {
                if (type == 0) {
                    return bomb[0];
                } else {
                    return bomb[1];
                }
            } else {
                //   Log.e("ASSSS", num + "");
                return bombExplosion[num];
            }
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }

        @Override
        public void update() {

            if (is) {
                if (y >= display_h * 0.9) {
                    is = false;
                    x = x - (bombExplosion[0].getWidth() / 2);
                } else {
                    y += bomb_sleep + addSleep;
                    if (addSleep < display_h * 0.5 && bomb_sleep < maxSleep) {
                        addSleep++;
                    }

                    if (x + bomb[0].getWidth() >= player_x && x <= player_x + left[0].getWidth() && y + bomb[0].getHeight() >= player_y && y + bomb[0].getHeight() <= player_y + left[0].getHeight()) {
                        // Log.e("Bomb", "PZ" + x + "/" + y + ":" + player_x + "/" + player_y);
                        is = false;
                        x = x - (bombExplosion[0].getWidth() / 2);
                        player_beaten_number++;
                        player_blood = player_blood - 15;
                        Start.vibrator.vibrate(100);
                    }
                }

            } else {
                if (num >= 4) {
                    Start.vibrator.cancel();
                    bombImages.remove(this);
                } else {
                    num++;
                }
            }
        }
    }

    private class button implements Goods {

        Bitmap button_bg;
        Paint paint = new Paint();

        public button() {
            button_bg = Bitmap.createBitmap(display_w, (int) (display_h * 0.1), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(button_bg);
            //canvas.drawBitmap(button[0], 0, 0, paint);
            //canvas.drawBitmap(button[1], (int) (button_bg.getWidth() - button[1].getWidth()), 0, paint);
        }

        @Override
        public Bitmap getBitmap() {
            return button_bg;
        }

        @Override
        public int getX() {
            return 0;
        }

        @Override
        public int getY() {
            return (int) (display_h - (display_h * 0.1));
        }

        @Override
        public void update() {

        }
    }

    private class RunTask extends TimerTask {
        int num = 0;

        @Override
        public void run() {
            if (num == 6) {
                bombImages.add(new bombImage());
                num = 0;
            }
            num++;
            //  for (GameImage bomb : (List<GameImage>) bombImages.clone()) {
            //       bomb.update();
            //   }

            for (int i = 0; i < bombImages.size(); i++) {
                bombImages.get(i).update();
            }

            for (int i = 0; i < manimages.size(); i++) {
                manimages.get(i).update();
            }


        }
    }


}
