package club.hnxxzyjsxy.redsky;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.Random;

public class Init {

    static void init() {


        environmentObject.random1 = new Random();


        environmentObject.iSinit = true;
        environmentObject.bomb_sleep = (int) (environmentObject.display_h * 0.009);
        environmentObject.player_speed = 10;
        initImage();
        environmentObject.WorldWidth = object.Bedrock.getWidth();
        environmentObject.WorldHeight = object.Bedrock.getHeight();

        environmentObject.GameWorldX = (int) (environmentObject.WorldWidth / 2 - (environmentObject.WorldWidth * 0.2 / 2));


        object.DrawRect = new Rect((int) (environmentObject.WorldWidth / 2 - (environmentObject.WorldWidth * 0.2 / 3)), 0, (int) (environmentObject.WorldWidth / 2 - (environmentObject.WorldWidth * 0.2 / 3)) + environmentObject.display_w, environmentObject.display_h);
        //摄像机显示位置
        object.DrawRect2 = new Rect(0, 0, environmentObject.display_w, environmentObject.display_h);

        object.GameList.add(new GoodsEntity.D(object.Bedrock));
        //object.GameList.add(new GoodsEntity.backgronud(object.background));


        //player.add(new GoodsEntity.manImage(environmentObject.display_w / 2, (int) (environmentObject.display_h * environmentObject.lawn) - down[0].getHeight()));
        object.myself = new GoodsEntity.player(environmentObject.WorldWidth / 2 - object.LV0left[0].getWidth() / 2, -environmentObject.display_h / 2);
        //player.add(new GoodsEntity.player(environmentObject.display_w / 2, (int) (environmentObject.display_h * environmentObject.lawn) - left[0].getHeight()));

        //GoodsUpdate = new Timer();
        // animation = new Timer();


        //Thread thread = new Thread(new SocketConnectRead());
        // thread.start();


        //send = new Thread(new SocketConnectSend());
        //request = new Thread(new SocketConnectRead());

        //object.Layer.add(new GoodsEntity.Cloud(object.cloud[0].getWidth(), object.cloud[0].getHeight()));
        //FullView = new Canvas(gameImagesList.get(0).getBitmap());
        //FullView = new Canvas(GameList.get(0).getBitmap());
        //   FullView = new Canvas(GameList.get(0).getBitmap());

        object.FullBitmap = Bitmap.createBitmap(object.background.getWidth(), object.background.getHeight(), Bitmap.Config.ARGB_4444);


        object.FullView = new Canvas(object.FullBitmap);


        object.soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 5);
        try {
            object.an = object.soundPool.load(environmentObject.assetMg.openFd("an.mp3"), 1);
            object.pu = object.soundPool.load(environmentObject.assetMg.openFd("pu.mp3"), 1);
            object.shiwang = object.soundPool.load(environmentObject.assetMg.openFd("shiwang.mp3"), 1);

            object.fuck1 = object.soundPool.load(environmentObject.assetMg.openFd("fuck1.mp3"), 1);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //object.DrawRect.top = 3000;


        config();


    }


    static void config() {

/*

        object.Layer.add(new GoodsEntity.Cloud(object.cloud[0], environmentObject.WorldWidth / 2 - object.cloud[0].getWidth() / 2, 100, 0, 4));
        object.Layer.add(new GoodsEntity.Cloud(object.cloud[0], environmentObject.WorldWidth / 2 - object.cloud[0].getWidth() / 2, 300, 0,  2));
        object.Layer.add(new GoodsEntity.Cloud(object.cloud[0], environmentObject.WorldWidth / 2 - object.cloud[0].getWidth() / 2, 500, 0,  1));
        object.Layer.add(new GoodsEntity.Cloud(object.cloud[0], environmentObject.WorldWidth / 2 - object.cloud[0].getWidth() / 2, 700, 0,  3));
*/


        int x = (int) ((environmentObject.WorldWidth / 2) - (environmentObject.WorldWidth * 0.2 / 2));
        int s = (int) (x + environmentObject.WorldWidth * 0.2);
        for (int i = x; i < s; i += object.caodi.getWidth()) {
            if (environmentObject.random1.nextInt(2) == 0) {
                object.grounds.add(new GoodsEntity.ground(object.caodi, i, environmentObject.WorldDisplayHeight - object.caodi.getHeight()));
            } else {
                object.grounds.add(new GoodsEntity.ground(object.caodi2, i, environmentObject.WorldDisplayHeight - object.caodi.getHeight()));
            }
        }

/*

        object.grounds.add(new GoodsEntity.ground(object.caodi, 0, environmentObject.display_h - object.caodi.getHeight() * 2));
        object.grounds.add(new GoodsEntity.ground(object.caodi, 100, environmentObject.display_h - object.caodi.getHeight() * 6));
        object.grounds.add(new GoodsEntity.ground(object.caodi, 300, environmentObject.display_h - object.caodi.getHeight() * 8));
        object.grounds.add(new GoodsEntity.ground(object.caodi, 500, environmentObject.display_h - object.caodi.getHeight() * 10));
        object.grounds.add(new GoodsEntity.ground(object.caodi, 700, environmentObject.display_h - object.caodi.getHeight() * 13));
        object.grounds.add(new GoodsEntity.ground(object.caodi, 800, environmentObject.display_h - object.caodi.getHeight() * 13));
        object.grounds.add(new GoodsEntity.ground(object.caodi2, 900, environmentObject.display_h - object.caodi.getHeight() * 13));
        object.grounds.add(new GoodsEntity.ground(object.caodi2, 1000, environmentObject.display_h - object.caodi.getHeight() * 13));
        object.grounds.add(new GoodsEntity.ground(object.caodi2, 1100, environmentObject.display_h - object.caodi.getHeight() * 13));
        object.grounds.add(new GoodsEntity.ground(object.caodi2, 1200, environmentObject.display_h - object.caodi.getHeight() * 13));
        object.grounds.add(new GoodsEntity.ground(object.caodi, 5000, environmentObject.display_h - object.caodi.getHeight() * 13));
        object.grounds.add(new GoodsEntity.ground(object.caodi, 1400, environmentObject.display_h - object.caodi.getHeight() * 13));
        object.grounds.add(new GoodsEntity.ground(object.caodi, 5900, environmentObject.display_h - object.caodi.getHeight() * 13));
        object.grounds.add(new GoodsEntity.ground(object.caodi, 1600, environmentObject.display_h - object.caodi.getHeight() * 13));
        object.grounds.add(new GoodsEntity.ground(object.caodi, 1700, environmentObject.display_h - object.caodi.getHeight() * 13));
        object.grounds.add(new GoodsEntity.ground(object.caodi, 1800, environmentObject.display_h - object.caodi.getHeight() * 13));
        object.grounds.add(new GoodsEntity.ground(object.caodi, 1900, environmentObject.display_h - object.caodi.getHeight() * 13));
        object.grounds.add(new GoodsEntity.ground(object.caodi, 1900, environmentObject.display_h - object.caodi.getHeight() * 13));

        object.grounds.add(new GoodsEntity.ground(object.caodi, 2000, environmentObject.display_h - object.caodi.getHeight() * 13));

        object.grounds.add(new GoodsEntity.ground(object.caodi, 2100, environmentObject.display_h - object.caodi.getHeight() * 13));

        object.grounds.add(new GoodsEntity.ground(object.caodi, 2200, environmentObject.display_h - object.caodi.getHeight() * 13));

        object.grounds.add(new GoodsEntity.ground(object.caodi, 2300, environmentObject.display_h - object.caodi.getHeight() * 13));

        object.grounds.add(new GoodsEntity.ground(object.caodi, 2400, environmentObject.display_h - object.caodi.getHeight() * 13));

        object.grounds.add(new GoodsEntity.ground(object.caodi, 2550, environmentObject.display_h - object.caodi.getHeight() * 13));

        object.grounds.add(new GoodsEntity.ground(object.caodi, 2900, environmentObject.display_h - object.caodi.getHeight() * 13));

        object.grounds.add(new GoodsEntity.ground(object.caodi, 3000, environmentObject.display_h - object.caodi.getHeight() * 13));

        object.grounds.add(new GoodsEntity.ground(object.caodi, 3200, environmentObject.display_h - object.caodi.getHeight() * 13));

        object.grounds.add(new GoodsEntity.ground(object.caodi, 3400, environmentObject.display_h - object.caodi.getHeight() * 13));

        object.grounds.add(new GoodsEntity.ground(object.caodi, 3500, environmentObject.display_h - object.caodi.getHeight() * 13));

        object.grounds.add(new GoodsEntity.ground(object.caodi, 3700, environmentObject.display_h - object.caodi.getHeight() * 13));

        object.grounds.add(new GoodsEntity.ground(object.caodi, 3800, environmentObject.display_h - object.caodi.getHeight() * 13));

        object.grounds.add(new GoodsEntity.ground(object.caodi, 3900, environmentObject.display_h - object.caodi.getHeight() * 13));

        object.grounds.add(new GoodsEntity.ground(object.caodi, 4000, environmentObject.display_h - object.caodi.getHeight() * 13));

        object.grounds.add(new GoodsEntity.ground(object.caodi, 4100, environmentObject.display_h - object.caodi.getHeight() * 13));

        object.grounds.add(new GoodsEntity.ground(object.caodi, 4400, environmentObject.display_h - object.caodi.getHeight() * 13));

        object.grounds.add(new GoodsEntity.ground(object.caodi, 4700, environmentObject.display_h - object.caodi.getHeight() * 13));

        object.grounds.add(new GoodsEntity.ground(object.caodi, 4900, environmentObject.display_h - object.caodi.getHeight() * 13));

*/

    }


    static void initImage() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        object.cache2 = Bitmap.createBitmap(environmentObject.display_w, environmentObject.display_h, Bitmap.Config.ARGB_4444);
        object.background = Tool.getImageBitmap("backg.png");
        object.Bedrock = Bitmap.createBitmap(10000, environmentObject.display_h, Bitmap.Config.RGB_565);
        object.Bedrock.eraseColor(Color.parseColor("#363636"));
        environmentObject.WorldDisplayHeight = (environmentObject.display_h / 5) * 4;


        Bitmap bitmap = Tool.getImageBitmap("player.png");
        Bitmap bombexplosion = Tool.getImageBitmap("bomb.png");
        Bitmap LV0 = Tool.getImageBitmap("LV0.png");
        Bitmap items = Tool.getImageBitmap("item.png");
        Bitmap meteor = Tool.getImageBitmap("meteor.png");
        Bitmap seed = Tool.getImageBitmap("zz.png");
        Bitmap caodi = Tool.getImageBitmap("caodi.png");
        Bitmap caodi2 = Tool.getImageBitmap("caodi2.png");
        Bitmap number = Tool.getImageBitmap("number.png");

        object.xy = Tool.getImageBitmap("xy.png");


        int[] z = {0, 200, 400, 600, 800, 1000, 1200, 1400, 1600, 1800, 2000, 2200};
        for (int i = 0; i < 12; i++) {
            object.LV0left[i] = Bitmap.createBitmap(LV0, z[i], 0, LV0.getWidth() / 12, LV0.getHeight());
            object.LV0left[i] = Tool.getNewSizeBitmap(object.LV0left[i], (float) (environmentObject.WorldDisplayHeight * 0.08 / object.LV0left[i].getWidth()), (float) (environmentObject.WorldDisplayHeight * 0.08 / object.LV0left[i].getWidth()));
        }
        int is = 0;
        for (int i = 11; i >= 0; i--) {
            object.LV0reight[is] = Bitmap.createBitmap(LV0, z[i], 0, LV0.getWidth() / 12, LV0.getHeight());
            object.LV0reight[is] = Tool.getNewSizeBitmap(object.LV0reight[is], (float) (environmentObject.WorldDisplayHeight * 0.08 / object.LV0reight[is].getWidth()), (float) (environmentObject.WorldDisplayHeight * 0.08 / object.LV0reight[is].getWidth()));
            is++;
        }
        int[] seeds = {0, 70, 140, 210};
        for (int i = 0; i < 4; i++) {
            object.Seed[i] = Bitmap.createBitmap(seed, seeds[i], 0, seed.getWidth() / 4, seed.getHeight());
            object.Seed[i] = Tool.getNewSizeBitmap(object.Seed[i], (float) (environmentObject.WorldDisplayHeight * 0.1 / object.Seed[i].getWidth()), (float) (environmentObject.WorldDisplayHeight * 0.1 / object.Seed[i].getWidth()));
        }


        for (int i = 0; i < 10; i++) {
            object.number[i] = Bitmap.createBitmap(number, i * 22, 0, number.getWidth() / 10, number.getHeight());
            object.number[i] = Tool.getNewSizeBitmap(object.number[i], (float) (environmentObject.WorldDisplayHeight * 0.05 / object.number[i].getWidth()), (float) (environmentObject.WorldDisplayHeight * 0.05 / object.number[i].getWidth()));
        }


        int[] bomb_explosion = {0, 150, 300, 450, 600};
        for (int i = 0; i < 5; i++) {
            assert bombexplosion != null;
            object.bombExplosion[i] = Bitmap.createBitmap(bombexplosion, bomb_explosion[i], 0, 150, 150);
            object.bombExplosion[i] = Tool.getNewSizeBitmap(object.bombExplosion[i], (float) (environmentObject.WorldDisplayHeight * 0.2 / object.bombExplosion[i].getWidth()), (float) (environmentObject.WorldDisplayHeight * 0.2 / object.bombExplosion[i].getWidth()));
        /*    Canvas canvas = new Canvas(bombExplosion[i]);
            canvas.drawRect(0, 0, bombExplosion[0].getWidth(), bombExplosion[0].getHeight(), paint);
*/
        }
 /*       for (int i = 0; i < 4; i++) {
            object.left[i] = Bitmap.createBitmap(bitmap, z[i], 608 / 4, bitmap.getWidth() / 4, bitmap.getHeight() / 4);
            object.left[i] = Tool.getNewSizeBitmap(object.left[i], (float) (environmentObject.display_w * 0.15 / object.left[i].getWidth()), (float) (environmentObject.display_w * 0.15 / object.left[i].getWidth()));
        *//*    Canvas canvas = new Canvas(left[i]);
            canvas.drawRect(0, 0, left[0].getWidth(), left[0].getHeight(), paint);
  *//*
        }*/








/*
        for (int i = 0; i < 4; i++) {
            object.reight[i] = Bitmap.createBitmap(bitmap, z[i], 608 / 2, bitmap.getWidth() / 4, bitmap.getHeight() / 4);
            object.reight[i] = Tool.getNewSizeBitmap(object.reight[i], (float) (environmentObject.display_w * 0.15 / object.reight[i].getWidth()), (float) (environmentObject.display_w * 0.15 / object.reight[i].getWidth()));
*//*
            Canvas canvas = new Canvas(reight[i]);
            canvas.drawRect(0, 0, reight[0].getWidth(), reight[0].getHeight(), paint);
   *//*
        }*/


        object.emoguoshi = Bitmap.createBitmap(items, 1631, 81, 39, 45);
        object.bomb[0] = Bitmap.createBitmap(items, 1033, 11, 26, 95);
        object.bomb[1] = Bitmap.createBitmap(items, 896, 14, 50, 94);
        object.baseball = Bitmap.createBitmap(items, 1214, 40, 36, 34);
        object.grenade = Bitmap.createBitmap(items, 687, 32, 59, 59);
        object.NULL = Bitmap.createBitmap(items, 17, 24, 48, 48);
        object.knife = Bitmap.createBitmap(items, 1359, 18, 54, 118);

        object.greenhat = Bitmap.createBitmap(items, 1796, 35, 97, 55);
        object.yellowhat = Bitmap.createBitmap(items, 1796, 92, 97, 55);
        object.bluehat = Bitmap.createBitmap(items, 1796, 152, 97, 55);
        object.redhat = Bitmap.createBitmap(items, 1796, 213, 97, 55);
        object.purplehat = Bitmap.createBitmap(items, 1796, 274, 97, 55);


        object.luckybox = Bitmap.createBitmap(items, 75, 6, 70, 76);

        object.cloud[0] = Bitmap.createBitmap(items, 21, 121, 91, 68);
        object.cloud[1] = Bitmap.createBitmap(items, 133, 119, 82, 53);

  /*      Canvas canvas21 = new Canvas(cloud[0]);
        canvas21.drawRect(0, 0, cloud[0].getWidth(), cloud[0].getHeight(), paint);
*/


        object.emoguoshi = Tool.getNewSizeBitmap(object.emoguoshi, (float) (environmentObject.WorldDisplayHeight * 0.06 / object.emoguoshi.getWidth()), (float) (environmentObject.WorldDisplayHeight * 0.06 / object.emoguoshi.getWidth()));
        object.xy = Tool.getNewSizeBitmap(object.xy, (float) (object.background.getWidth() / object.xy.getWidth()), (float) (object.background.getWidth() / object.xy.getWidth()));
        object.knife = Tool.getNewSizeBitmap(object.knife, (float) (environmentObject.WorldDisplayHeight * 0.03 / object.knife.getWidth()), (float) (environmentObject.WorldDisplayHeight * 0.03 / object.knife.getWidth()));

        object.caodi2 = Tool.getNewSizeBitmap(caodi2, (float) (environmentObject.WorldDisplayHeight * 0.05 / caodi2.getWidth()), (float) (environmentObject.WorldDisplayHeight * 0.05 / caodi2.getWidth()));

        object.caodi = Tool.getNewSizeBitmap(caodi, (float) (environmentObject.WorldDisplayHeight * 0.05 / caodi.getWidth()), (float) (environmentObject.WorldDisplayHeight * 0.05 / caodi.getWidth()));
        object.meteor = Tool.getNewSizeBitmap(meteor, (float) (environmentObject.display_w * 0.4 / meteor.getWidth()), (float) (environmentObject.display_w * 0.4 / meteor.getWidth()));
        object.bomb[1] = Tool.getNewSizeBitmap(object.bomb[1], (float) (environmentObject.display_w * 0.05 / object.bomb[1].getWidth()), (float) (environmentObject.display_w * 0.05 / object.bomb[1].getWidth()));
        object.bomb[0] = Tool.getNewSizeBitmap(object.bomb[0], (float) (environmentObject.display_w * 0.03 / object.bomb[0].getWidth()), (float) (environmentObject.display_w * 0.03 / object.bomb[0].getWidth()));
        object.baseball = Tool.getNewSizeBitmap(object.baseball, (float) (environmentObject.display_w * 0.05 / object.baseball.getWidth()), (float) (environmentObject.display_w * 0.05 / object.baseball.getWidth()));
        object.grenade = Tool.getNewSizeBitmap(object.grenade, (float) (environmentObject.display_w * 0.09 / object.grenade.getWidth()), (float) (environmentObject.display_w * 0.09 / object.grenade.getWidth()));
        object.NULL = Tool.getNewSizeBitmap(object.NULL, (float) (environmentObject.display_w * 0.15 / object.NULL.getWidth()), (float) (environmentObject.display_w * 0.15 / object.NULL.getWidth()));

        object.greenhat = Tool.getNewSizeBitmap(object.greenhat, (float) (environmentObject.WorldDisplayHeight * 0.07 / object.greenhat.getWidth()), (float) (environmentObject.WorldDisplayHeight * 0.07 / object.greenhat.getWidth()));
        object.yellowhat = Tool.getNewSizeBitmap(object.yellowhat, (float) (environmentObject.WorldDisplayHeight * 0.07 / object.yellowhat.getWidth()), (float) (environmentObject.WorldDisplayHeight * 0.07 / object.yellowhat.getWidth()));
        object.bluehat = Tool.getNewSizeBitmap(object.bluehat, (float) (environmentObject.WorldDisplayHeight * 0.07 / object.bluehat.getWidth()), (float) (environmentObject.WorldDisplayHeight * 0.07 / object.bluehat.getWidth()));
        object.redhat = Tool.getNewSizeBitmap(object.redhat, (float) (environmentObject.WorldDisplayHeight * 0.07 / object.redhat.getWidth()), (float) (environmentObject.WorldDisplayHeight * 0.07 / object.redhat.getWidth()));
        object.purplehat = Tool.getNewSizeBitmap(object.purplehat, (float) (environmentObject.WorldDisplayHeight * 0.07 / object.purplehat.getWidth()), (float) (environmentObject.WorldDisplayHeight * 0.07 / object.purplehat.getWidth()));


        object.luckybox = Tool.getNewSizeBitmap(object.luckybox, (float) (environmentObject.WorldDisplayHeight * 0.05 / object.luckybox.getWidth()), (float) (environmentObject.display_w * 0.15 / object.luckybox.getWidth()));

        object.cloud[0] = Tool.getNewSizeBitmap(object.cloud[0], (float) (environmentObject.WorldDisplayHeight * 0.2 / object.cloud[0].getWidth()), (float) (environmentObject.WorldDisplayHeight * 0.2 / object.cloud[0].getWidth()));
        object.cloud[1] = Tool.getNewSizeBitmap(object.cloud[1], (float) (environmentObject.WorldDisplayHeight * 0.2 / object.cloud[1].getWidth()), (float) (environmentObject.WorldDisplayHeight * 0.2 / object.cloud[1].getWidth()));




/*

        Paint textpaint = new Paint();
        textpaint.setTextSize(30);

        Canvas free = new Canvas(object.redhat);
        free.drawText("自由", object.greenhat.getWidth() / 3, object.greenhat.getHeight() / 2, textpaint);

        Canvas equal = new Canvas(object.bluehat);
        equal.drawText("平等", object.greenhat.getWidth() / 3, object.greenhat.getHeight() / 2, textpaint);

        Canvas justice = new Canvas(object.greenhat);
        justice.drawText("公正", object.greenhat.getWidth() / 3, object.greenhat.getHeight() / 2, textpaint);

        Canvas legal = new Canvas(object.yellowhat);
        legal.drawText("法制", object.greenhat.getWidth() / 3, object.greenhat.getHeight() / 2, textpaint);

        Canvas friend = new Canvas(object.purplehat);
        friend.drawText("友善", object.greenhat.getWidth() / 3, object.greenhat.getHeight() / 2, textpaint);
*/


        /*Canvas canvas21 = new Canvas(object.knife);*/
        //canvas21.drawRect(0, 0, knife.getWidth(), knife.getHeight(), paint);
    /*    Canvas canvas22 = new Canvas(bomb[1]);


        canvas22.drawRect(0, 0, bomb[1].getWidth(), bomb[1].getHeight(), paint);

        Canvas canvas223 = new Canvas(baseball);
        canvas223.drawRect(0, 0, baseball.getWidth(), baseball.getHeight(), paint);
        items.recycle();*/


        object.Blood = Bitmap.createBitmap((int) (environmentObject.display_w * 0.8), (int) (environmentObject.display_h * 0.01), Bitmap.Config.RGB_565);
        object.Bluepaint.setColor(Color.BLUE);
        object.Whitepaint.setColor(Color.WHITE);
        object.bloodCanvas = new Canvas(object.Blood);


    }


}
