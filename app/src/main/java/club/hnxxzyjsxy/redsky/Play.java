package club.hnxxzyjsxy.redsky;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

import java.util.Arrays;

public class Play {


    public static float mAmplitude = 10.0F; // 振幅
    public static long c = 0L;
    public static float WateLevel = environmentObject.display_h / 2;
    public static Path path;
    public static long StartTime = 0, TempTime = 0;
    public static long reduceTime = 0;


    public static int x, y, xy_x, xy_y;
    public static boolean Init = true;


    static void ZeroToOne(Canvas canvas) {
        if (Play.StartTime == 0) {
            Play.StartTime = System.currentTimeMillis();
            Play.x = environmentObject.display_w;
            Play.y = -object.meteor.getHeight();
            Play.xy_x = 0;
            Play.xy_y = environmentObject.display_h / 2;
        }
        canvas.drawRect(0, 0, environmentObject.display_w, environmentObject.display_h, object.DrakPaint);
        canvas.drawBitmap(object.meteor, x, y, object.paint);
        Paint paint2 = new Paint();
        paint2.setColor(Color.rgb(40, 150, 200));
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setAntiAlias(true);
        path = new Path();
        path.moveTo(0, WateLevel);
        for (int i = -500; i < environmentObject.display_w + 500; i += 500) {
            path.rQuadTo(500 / 4, -50, 500 / 2, 0);
            path.rQuadTo(500 / 4, 50, 500 / 2, 0);
        }
        path.lineTo(environmentObject.display_w, environmentObject.display_h);
        path.lineTo(0, environmentObject.display_h);
        path.close();
        canvas.drawBitmap(object.xy, xy_x, xy_y, object.paint);
        canvas.drawPath(path, paint2);
        Play.x -= environmentObject.display_h * 0.002;
        Play.y += environmentObject.display_h * 0.002;
        Play.xy_y -= environmentObject.display_h * 0.001;

        if (Play.y > environmentObject.display_h / 2) {
            if (WateLevel > environmentObject.display_h) {
                environmentObject.code = 1;
            }
            WateLevel += environmentObject.display_h * 0.003;
        }
    }


    static void GameAction(Canvas canvas) {


        if (environmentObject.StartTime == 0) {
            environmentObject.StartTime = System.currentTimeMillis();
        }

        TimeZ();

        for (int i = 0; i < object.fell.size(); i++) {
            object.fell.get(i).update();
        }


        object.myself.update();

        for (int i = 0; i < object.Layer.size(); i++) {
            object.Layer.get(i).update();
        }


        if (object.head.get(0).size() > 0) {
            object.head.get(0).get(0).setX((environmentObject.player_x + object.LV0left[0].getWidth() / 2) - (object.greenhat.getWidth() / 2));
            object.head.get(0).get(0).setY((int) (environmentObject.player_y - (object.head.get(0).get(0).getheight() / 1.5)));


            for (int i = 1; i < object.head.get(0).size(); i++) {
                object.head.get(0).get(i).setX(object.head.get(0).get(0).getX());
                object.head.get(0).get(i).setY(object.head.get(0).get(i - 1).getY() - object.head.get(0).get(0).getheight() / 2);
            }

        }


        object.GameList.get(0).update();


        for (int i = 0; i < object.fell.size(); i++) {
            float x = object.fell.get(i).getX();
            float y = object.fell.get(i).getY();
            int width = object.fell.get(i).getwidth();
            int height = object.fell.get(i).getheight();
            float GoodsCenter = object.fell.get(i).getX() + (object.fell.get(i).getwidth() / 2);
            float PlayerCenter = object.myself.getX() + (object.myself.getwidth() / 2);

            if (object.head.get(0).size() > 0) {
                if (x + width >= environmentObject.player_x && x <= environmentObject.player_x + object.myself.getwidth() && y + height >= object.head.get(0).get(object.head.get(0).size() - 1).getY() && y + height < object.myself.getY()) {
                    switch (object.fell.get(i).getType()) {
                        /*  case "baseball":
                         *//*  if (GoodsCenter <= PlayerCenter) {
                                fell.get(i).setX(head.get(0).get(i).getX() - width - 1);
                            } else {
                                fell.get(i).setX(head.get(0).get(i).getX() + head.get(0).get(i).getwidth() + 1);
                            }
                            head.get(0).remove(head.get(0).get(head.get(0).size() - 1));*//*
                            break;*/
                 /*
                        case "grenade":
                        *//*    if (GoodsCenter <= PlayerCenter) {
                                fell.get(i).setX(head.get(0).get(i).getX() - width - 1);
                            } else {
                                fell.get(i).setX(head.get(0).get(i).getX() + head.get(0).get(0).getwidth() + 1);
                            }
                            head.get(0).remove(head.get(0).get(head.get(0).size() - 1));*//*
                            break;*/

                        case ItemID.bomb:
                            if (GoodsCenter <= PlayerCenter) {
                                object.fell.get(i).setX(object.head.get(0).get(0).getX() - width - 1);
                            } else {
                                object.fell.get(i).setX(object.head.get(0).get(0).getX() + object.head.get(0).get(0).getwidth() + 1);
                            }
                            object.head.get(0).remove(object.head.get(0).get(object.head.get(0).size() - 1));
                            break;


                        case ItemID.Knife:
                            object.soundPool.play(object.pu, 1, 1, 1, 0, 1);

                            if (GoodsCenter <= PlayerCenter) {
                                object.fell.get(i).setX(object.head.get(0).get(0).getX() - width - 1);
                            } else {
                                object.fell.get(i).setX(object.head.get(0).get(0).getX() + object.head.get(0).get(0).getwidth() + 1);
                            }
                            object.head.get(0).remove(object.head.get(0).get(object.head.get(0).size() - 1));
                            break;

                        case ItemID.greenhat:
                            object.soundPool.play(object.pu, 1, 1, 1, 0, 1);

                            object.fell.get(i).setIsRun(false);
                            object.fell.get(i).setX(environmentObject.player_x + object.LV0left[0].getWidth() / 2 - object.fell.get(i).getwidth() / 2);
                            object.fell.get(i).setY((int) (object.head.get(0).get(object.head.get(0).size() - 1).getY() - object.head.get(0).get(object.head.get(0).size() - 1).getheight() + environmentObject.display_h * 0.02));
                            object.head.get(0).add(object.fell.get(i));
                            object.fell.remove(object.fell.get(i));
                            break;
                        case ItemID.emoguoshi:
                            object.fell.get(i).setIsLife(false);
                            break;


                    }
                    continue;
                }
            }

            if (x + width >= environmentObject.player_x && x <= environmentObject.player_x + object.myself.getwidth() && y + height >= environmentObject.player_y && y <= environmentObject.player_y + object.myself.getheight()) {
                switch (object.fell.get(i).getType()) {
                /*    case "baseball":
                        object.fell.get(i).setIsRun(false);
                        environmentObject.player_blood -= 10;
                        object.fell.get(i).setX(object.fell.get(i).getX() - object.bombExplosion[0].getWidth() / 2);
                        object.fell.get(i).setY(object.fell.get(i).getY() - object.bombExplosion[1].getHeight() / 2);

                        for (int s = 0; s < object.fell.size(); s++) {
                            if (object.fell.get(s).toString().equals("luckybox")) {
                                //fell.get(s).setsleep(0);
                                object.fell.get(s).setIsRun(false);
                            }
                        }
                        break;*/

/*                    case "grenade":
                        object.fell.get(i).setIsRun(false);
                        environmentObject.player_blood -= 20;
                        object.fell.get(i).setX(object.fell.get(i).getX() - object.bombExplosion[0].getWidth() / 2);
                        object.fell.get(i).setY(object.fell.get(i).getY() - object.bombExplosion[1].getHeight() / 2);
                        break;*/
                    case ItemID.bomb:
                        object.fell.get(i).setIsRun(false);
                        object.fell.get(i).setX(object.fell.get(i).getX() - object.bombExplosion[0].getWidth() / 2);
                        object.fell.get(i).setY(object.fell.get(i).getY() - object.bombExplosion[1].getHeight() / 2);
                        break;

                    case ItemID.Knife:
                        //object.soundPool.play(object.an, 1, 1, 1, 0, 1);
                        object.soundPool.play(object.an, 1, 1, 1, 0, 1);
                        object.fell.get(i).setIsRun(false);
                        environmentObject.player_blood -= 25;
                        object.fell.get(i).setX(object.fell.get(i).getX() - object.bombExplosion[0].getWidth() / 2);
                        object.fell.get(i).setY(object.fell.get(i).getY() - object.bombExplosion[1].getHeight() / 2);
                        break;
                    case ItemID.greenhat:
                        object.soundPool.play(object.pu, 1, 1, 1, 0, 1);
                        object.fell.get(i).setIsRun(false);
                        object.fell.get(i).setX((environmentObject.player_x + object.LV0left[0].getWidth() / 2) - (object.fell.get(i).getwidth() / 2));

                        if (object.head.get(0).size() == 0) {
                            object.fell.get(i).setY((int) (environmentObject.player_y - object.greenhat.getHeight() + environmentObject.display_h * 0.01));
                        } else {
                            object.fell.get(i).setY((int) (object.head.get(0).get(object.head.get(0).size() - 1).getY() - object.head.get(0).get(object.head.get(0).size() - 1).getheight() + environmentObject.display_h * 0.02));
                        }
                        object.head.get(0).add(object.fell.get(i));
                        object.fell.remove(object.fell.get(i));
                        break;
                    case ItemID.emoguoshi:
                        object.fell.get(i).setIsLife(false);
                        break;

                }
            }
        }


        canvas.drawBitmap(object.GameList.get(0).getBitmap(), object.DrawRect, object.DrawRect2, object.paint);
        //canvas.drawBitmap(object.GameList.get(1).getBitmap(), object.GameList.get(1).getX() - object.DrawRect.left, object.GameList.get(1).getY(), object.paint);


        object.bloodCanvas.drawRect(0, 0, object.Blood.getWidth(), object.Blood.getHeight(), object.Bluepaint);
        environmentObject.player_blood_b = environmentObject.display_w * 0.8 / environmentObject.player_maxblood;
        object.bloodCanvas.drawRect(0, 0, (float) (environmentObject.player_blood * environmentObject.player_blood_b), object.Blood.getHeight(), object.Whitepaint);
        object.bloodCanvas.drawText(environmentObject.player_blood + "血", 0, 28, object.paint);
        canvas.drawBitmap(object.Blood, (float) (environmentObject.display_w * 0.1), environmentObject.WorldDisplayHeight, object.paint);


        if (environmentObject.GameRunTime < 2000) {
            if (environmentObject.GameRunTime > 1500) {
                canvas.drawBitmap(object.Seed[3], environmentObject.WorldWidth / 2 - object.Seed[0].getWidth() / 2 - object.DrawRect.left, environmentObject.WorldDisplayHeight - object.Seed[0].getHeight(), object.paint);
            } else if (environmentObject.GameRunTime > 1000) {
                canvas.drawBitmap(object.Seed[2], environmentObject.WorldWidth / 2 - object.Seed[0].getWidth() / 2 - object.DrawRect.left, environmentObject.WorldDisplayHeight - object.Seed[0].getHeight(), object.paint);

            } else if (environmentObject.GameRunTime > 500) {
                canvas.drawBitmap(object.Seed[1], environmentObject.WorldWidth / 2 - object.Seed[0].getWidth() / 2 - object.DrawRect.left, environmentObject.WorldDisplayHeight - object.Seed[0].getHeight(), object.paint);
            } else if (environmentObject.GameRunTime > 0) {
                canvas.drawBitmap(object.Seed[0], environmentObject.WorldWidth / 2 - object.Seed[0].getWidth() / 2 - object.DrawRect.left, environmentObject.WorldDisplayHeight - object.Seed[0].getHeight(), object.paint);
            }
        }


        if (environmentObject.GameRunTime > 1500) {
            canvas.drawBitmap(object.myself.getBitmap(), object.myself.getX() - object.DrawRect.left, object.myself.getY(), object.paint);
        }
        for (int i = 0; i < object.head.get(0).size(); i++) {
            canvas.drawBitmap(object.head.get(0).get(i).getBitmap(), object.head.get(0).get(i).getX() - object.DrawRect.left, object.head.get(0).get(i).getY(), object.paint);
        }

        for (int i = 0; i < object.grounds.size(); i++) {
            canvas.drawBitmap(object.grounds.get(i).getBitmap(), object.grounds.get(i).getX() - object.DrawRect.left, object.grounds.get(i).getY(), object.paint);
        }


        for (int i = 0; i < object.Layer.size(); i++) {
            if (object.Layer.get(i).getZ() == 0) {
                if (object.Layer.get(i).getIsRun()) {
                    canvas.drawBitmap(object.Layer.get(i).getBitmap(), object.Layer.get(i).getX() - object.DrawRect.left, object.Layer.get(i).getY(), object.paint);
                }
            }
        }
        for (int i = 0; i < object.fell.size(); i++) {
            canvas.drawBitmap(object.fell.get(i).getBitmap(), object.fell.get(i).getX() - object.DrawRect.left, object.fell.get(i).getY(), object.paint);
            // canvas.drawBitmap(fell.get(i).getBitmap(), -fell.get(i).getwidth() / 2, fell.get(i).getY(), paint);
        }
        for (int i = 0; i < object.Layer.size(); i++) {
            if (object.Layer.get(i).getZ() == 1) {
                if (object.Layer.get(i).getIsRun()) {
                    canvas.drawBitmap(object.Layer.get(i).getBitmap(), object.Layer.get(i).getX() - object.DrawRect.left, object.Layer.get(i).getY(), object.paint);
                }
            }
        }


        char[] chars = String.valueOf(environmentObject.GameRunTime / 1000).toCharArray();
        int zx = environmentObject.display_w - (object.number[0].getWidth() * chars.length);
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '0':
                    canvas.drawBitmap(object.number[0], zx + i * object.number[0].getWidth(), 0, object.paint);
                    break;
                case '1':
                    canvas.drawBitmap(object.number[1], zx + i * object.number[0].getWidth(), 0, object.paint);
                    break;
                case '2':
                    canvas.drawBitmap(object.number[2], zx + i * object.number[0].getWidth(), 0, object.paint);
                    break;
                case '3':
                    canvas.drawBitmap(object.number[3], zx + i * object.number[0].getWidth(), 0, object.paint);
                    break;
                case '4':
                    canvas.drawBitmap(object.number[4], zx + i * object.number[0].getWidth(), 0, object.paint);
                    break;
                case '5':
                    canvas.drawBitmap(object.number[5], zx + i * object.number[0].getWidth(), 0, object.paint);
                    break;
                case '6':
                    canvas.drawBitmap(object.number[6], zx + i * object.number[0].getWidth(), 0, object.paint);
                    break;
                case '7':
                    canvas.drawBitmap(object.number[7], zx + i * object.number[0].getWidth(), 0, object.paint);
                    break;
                case '8':
                    canvas.drawBitmap(object.number[8], zx + i * object.number[0].getWidth(), 0, object.paint);
                    break;
                case '9':
                    canvas.drawBitmap(object.number[9], zx + i * object.number[0].getWidth(), 0, object.paint);
                    break;
            }


        }


        if (environmentObject.player_x + object.LV0left[0].getWidth() <= object.DrawRect.left) {
            environmentObject.player_blood--;
        } else if (environmentObject.player_x >= object.DrawRect.right) {
            environmentObject.player_blood--;
        }


        // canvas.drawRect(0, environmentObject.WorldDisplayHeight, environmentObject.display_w, environmentObject.display_h, object.paint);


    }


    static void TimeZ() {


        if (System.currentTimeMillis() - reduceTime >= 100) {
            reduceTime = System.currentTimeMillis();
            environmentObject.player_blood -= 1;


            switch (environmentObject.random1.nextInt(10)) {
                case 0:
                    object.fell.add(new GoodsEntity.emoguoshi(object.emoguoshi.getWidth(), object.emoguoshi.getHeight()));
                    break;
                case 1:
                    object.fell.add(new GoodsEntity.bombImage(object.bomb[0].getWidth(), object.bomb[0].getHeight()));
                    break;
                case 2:
                    object.fell.add(new GoodsEntity.knife(object.knife.getWidth(), object.knife.getHeight()));
                    break;
            }


        }


        if (System.currentTimeMillis() - TempTime >= 1000) {
            TempTime = System.currentTimeMillis();


            if (environmentObject.random1.nextInt(5) == 0) {
                object.Layer.add(new GoodsEntity.Cloud(object.cloud[0], environmentObject.random1.nextInt((int) (environmentObject.GameWorldX + environmentObject.WorldWidth * 0.2) - environmentObject.GameWorldX) + environmentObject.GameWorldX, environmentObject.random1.nextInt(environmentObject.WorldDisplayHeight / 2) - object.cloud[0].getHeight(), 0, 4, environmentObject.random1.nextInt(2)));
            }

            switch (environmentObject.random1.nextInt(2)) {

                case 0:
                    object.fell.add(new GoodsEntity.GreenHat(object.greenhat.getWidth(), object.greenhat.getHeight()));
                    break;
                case 2:
                    //   fell.add(new GoodsEntity.Baseball(baseball.getWidth(), baseball.getHeight()));
                    break;
                case 3:
                    break;
                case 4:
                    //fell.add(new GoodsEntity.LuckyBox(luckybox.getWidth(), luckybox.getHeight()));
                    break;
                case 5:
                    break;
            }


        }


    }


}