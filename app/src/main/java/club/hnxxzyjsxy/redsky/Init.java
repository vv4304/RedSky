package club.hnxxzyjsxy.redsky;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Init {

    public static void init() {

        environmentObject.bomb_sleep = (int) (environmentObject.display_h * 0.009);
        environmentObject.player_speed = 10;
        initImage();
        object.GameList.add(new GoodsEntity.backgronud(object.background));
        //player.add(new GoodsEntity.manImage(environmentObject.display_w / 2, (int) (environmentObject.display_h * environmentObject.lawn) - down[0].getHeight()));
        object.myself = new GoodsEntity.player(environmentObject.WorldWidth/ 2, (int) (environmentObject.display_h * environmentObject.lawn) - object.left[0].getHeight());
        //player.add(new GoodsEntity.player(environmentObject.display_w / 2, (int) (environmentObject.display_h * environmentObject.lawn) - left[0].getHeight()));

        //GoodsUpdate = new Timer();
        // animation = new Timer();


        //Thread thread = new Thread(new SocketConnectRead());
        // thread.start();
        environmentObject.iSinit = true;

        //send = new Thread(new SocketConnectSend());
        //request = new Thread(new SocketConnectRead());

        object.Layer.add(new GoodsEntity.Cloud(object.cloud[0].getWidth(), object.cloud[0].getHeight()));
        //FullView = new Canvas(gameImagesList.get(0).getBitmap());
        //FullView = new Canvas(GameList.get(0).getBitmap());
        //   FullView = new Canvas(GameList.get(0).getBitmap());

        object.FullBitmap = Bitmap.createBitmap(object.background.getWidth(), object.background.getHeight(), Bitmap.Config.ARGB_4444);

        object.FullView = new Canvas(object.FullBitmap);
        object.paint1 = new Paint();
        object.paint1.setTextSize(30);
        object.paint1.setColor(Color.RED);


        object.DrawRect = new Rect(environmentObject.WorldWidth/3, 0, environmentObject.WorldWidth/3+environmentObject.display_w, environmentObject.display_h);
       //摄像机显示位置


        object.DrawRect2 = new Rect(0, 0, environmentObject.display_w, environmentObject.display_h);







    }

    public static void initImage() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        object.cache2 = Bitmap.createBitmap(environmentObject.display_w, environmentObject.display_h, Bitmap.Config.ARGB_4444);
        object.background = Tool.getImageBitmap("backg.png");


        Bitmap bitmap = Tool.getImageBitmap("player.png");
        Bitmap bombexplosion = Tool.getImageBitmap("bomb.png");
        int[] z = {0, 100, 200, 300};
        int[] bomb_explosion = {0, 150, 300, 450, 600};
        for (int i = 0; i < 5; i++) {
            object.bombExplosion[i] = Bitmap.createBitmap(bombexplosion, bomb_explosion[i], 0, 150, 150);
            object.bombExplosion[i] = Tool.getNewSizeBitmap(object.bombExplosion[i], (float) (environmentObject.display_w * 0.3 / object.bombExplosion[i].getWidth()), (float) (environmentObject.display_w * 0.3 / object.bombExplosion[i].getWidth()));
        /*    Canvas canvas = new Canvas(bombExplosion[i]);
            canvas.drawRect(0, 0, bombExplosion[0].getWidth(), bombExplosion[0].getHeight(), paint);
*/

        }
        for (int i = 0; i < 4; i++) {
            object.left[i] = Bitmap.createBitmap(bitmap, z[i], 608 / 4, bitmap.getWidth() / 4, bitmap.getHeight() / 4);
            object.left[i] = Tool.getNewSizeBitmap(object.left[i], (float) (environmentObject.display_w * 0.15 / object.left[i].getWidth()), (float) (environmentObject.display_w * 0.15 / object.left[i].getWidth()));
        /*    Canvas canvas = new Canvas(left[i]);
            canvas.drawRect(0, 0, left[0].getWidth(), left[0].getHeight(), paint);
  */
        }
        for (int i = 0; i < 4; i++) {
            object.reight[i] = Bitmap.createBitmap(bitmap, z[i], 608 / 2, bitmap.getWidth() / 4, bitmap.getHeight() / 4);
            object.reight[i] = Tool.getNewSizeBitmap(object.reight[i], (float) (environmentObject.display_w * 0.15 / object.reight[i].getWidth()), (float) (environmentObject.display_w * 0.15 / object.reight[i].getWidth()));
/*
            Canvas canvas = new Canvas(reight[i]);
            canvas.drawRect(0, 0, reight[0].getWidth(), reight[0].getHeight(), paint);
   */
        }

        Bitmap items = Tool.getImageBitmap("item.png");
        Bitmap meteor = Tool.getImageBitmap("meteor.png");



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



        object.meteor=Tool.getNewSizeBitmap(meteor, (float) (environmentObject.display_w * 0.4 / meteor.getWidth()), (float) (environmentObject.display_w * 0.4 / meteor.getWidth()));
        object.bomb[1] = Tool.getNewSizeBitmap(object.bomb[1], (float) (environmentObject.display_w * 0.05 / object.bomb[1].getWidth()), (float) (environmentObject.display_w * 0.05 / object.bomb[1].getWidth()));
        object.bomb[0] = Tool.getNewSizeBitmap(object.bomb[0], (float) (environmentObject.display_w * 0.03 / object.bomb[0].getWidth()), (float) (environmentObject.display_w * 0.03 / object.bomb[0].getWidth()));
        object.knife = Tool.getNewSizeBitmap(object.knife, (float) (environmentObject.display_w * 0.06 / object.knife.getWidth()), (float) (environmentObject.display_w * 0.06 / object.knife.getWidth()));
        object.baseball = Tool.getNewSizeBitmap(object.baseball, (float) (environmentObject.display_w * 0.05 / object.baseball.getWidth()), (float) (environmentObject.display_w * 0.05 / object.baseball.getWidth()));
        object.grenade = Tool.getNewSizeBitmap(object.grenade, (float) (environmentObject.display_w * 0.09 / object.grenade.getWidth()), (float) (environmentObject.display_w * 0.09 / object.grenade.getWidth()));
        object.NULL = Tool.getNewSizeBitmap(object.NULL, (float) (environmentObject.display_w * 0.15 / object.NULL.getWidth()), (float) (environmentObject.display_w * 0.15 / object.NULL.getWidth()));
        object.greenhat = Tool.getNewSizeBitmap(object.greenhat, (float) (environmentObject.display_w * 0.15 / object.greenhat.getWidth()), (float) (environmentObject.display_w * 0.15 / object.greenhat.getWidth()));
        object.yellowhat = Tool.getNewSizeBitmap(object.yellowhat, (float) (environmentObject.display_w * 0.15 / object.yellowhat.getWidth()), (float) (environmentObject.display_w * 0.15 / object.yellowhat.getWidth()));
        object.bluehat = Tool.getNewSizeBitmap(object.bluehat, (float) (environmentObject.display_w * 0.15 / object.bluehat.getWidth()), (float) (environmentObject.display_w * 0.15 / object.bluehat.getWidth()));
        object.redhat = Tool.getNewSizeBitmap(object.redhat, (float) (environmentObject.display_w * 0.15 / object.redhat.getWidth()), (float) (environmentObject.display_w * 0.15 / object.redhat.getWidth()));
        object.purplehat = Tool.getNewSizeBitmap(object.purplehat, (float) (environmentObject.display_w * 0.15 / object.purplehat.getWidth()), (float) (environmentObject.display_w * 0.15 / object.purplehat.getWidth()));
        object.luckybox = Tool.getNewSizeBitmap(object.luckybox, (float) (environmentObject.display_w * 0.15 / object.luckybox.getWidth()), (float) (environmentObject.display_w * 0.15 / object.luckybox.getWidth()));
        object.cloud[0] = Tool.getNewSizeBitmap(object.cloud[0], (float) (environmentObject.display_w * 0.40 / object.cloud[0].getWidth()), (float) (environmentObject.display_w * 0.40 / object.cloud[0].getWidth()));
        object.cloud[1] = Tool.getNewSizeBitmap(object.cloud[1], (float) (environmentObject.display_w * 0.40 / object.cloud[1].getWidth()), (float) (environmentObject.display_w * 0.40 / object.cloud[1].getWidth()));


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


        Canvas canvas21 = new Canvas(object.knife);
        //canvas21.drawRect(0, 0, knife.getWidth(), knife.getHeight(), paint);
    /*    Canvas canvas22 = new Canvas(bomb[1]);


        canvas22.drawRect(0, 0, bomb[1].getWidth(), bomb[1].getHeight(), paint);

        Canvas canvas223 = new Canvas(baseball);
        canvas223.drawRect(0, 0, baseball.getWidth(), baseball.getHeight(), paint);
        items.recycle();*/


        object.Blood = Bitmap.createBitmap(100, 30, Bitmap.Config.RGB_565);
        object.Bloodpaint1.setColor(Color.BLUE);
        object.Bloodpaint2.setColor(Color.WHITE);
        object.bloodCanvas = new Canvas(object.Blood);


    }


}
