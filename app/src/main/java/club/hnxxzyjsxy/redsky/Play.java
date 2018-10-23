package club.hnxxzyjsxy.redsky;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

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

        GameActionUpdate();

        canvas.drawBitmap(object.GameList.get(0).getBitmap(), object.DrawRect, object.DrawRect2, object.paint);

        //canvas.drawBitmap(object.GameList.get(1).getBitmap(), object.GameList.get(1).getX() - object.DrawRect.left, object.GameList.get(1).getY(), object.paint);

        if (environmentObject.GameRunTime < 2000) {
            if (environmentObject.GameRunTime > 1000) {
                canvas.drawBitmap(object.Seed[3], environmentObject.WorldWidth / 2 - object.Seed[0].getWidth() / 2 - object.DrawRect.left, environmentObject.WorldDisplayHeight - object.Seed[0].getHeight(), object.paint);
            } else if (environmentObject.GameRunTime > 600) {
                canvas.drawBitmap(object.Seed[2], environmentObject.WorldWidth / 2 - object.Seed[0].getWidth() / 2 - object.DrawRect.left, environmentObject.WorldDisplayHeight - object.Seed[0].getHeight(), object.paint);

            } else if (environmentObject.GameRunTime > 400) {
                canvas.drawBitmap(object.Seed[1], environmentObject.WorldWidth / 2 - object.Seed[0].getWidth() / 2 - object.DrawRect.left, environmentObject.WorldDisplayHeight - object.Seed[0].getHeight(), object.paint);
            } else if (environmentObject.GameRunTime > 0) {
                canvas.drawBitmap(object.Seed[0], environmentObject.WorldWidth / 2 - object.Seed[0].getWidth() / 2 - object.DrawRect.left, environmentObject.WorldDisplayHeight - object.Seed[0].getHeight(), object.paint);
            }
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
        for (int i = 0; i < object.scene.size(); i++) {
            canvas.drawBitmap(object.scene.get(i).getBitmap(), object.scene.get(i).getX() - object.DrawRect.left, object.scene.get(i).getY(), object.paint);
            // canvas.drawBitmap(fell.get(i).getBitmap(), -fell.get(i).getwidth() / 2, fell.get(i).getY(), paint);
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
        if (environmentObject.player_x + object.playerL[0].getWidth() <= object.DrawRect.left) {
            environmentObject.player_blood--;
        } else if (environmentObject.player_x >= object.DrawRect.right) {
            environmentObject.player_blood--;
        }

        if (environmentObject.GameRunTime > 1000) {
            object.playerPaint.setAlpha((int) (((double) (255) / environmentObject.player_maxblood) * environmentObject.player_blood));
            canvas.drawBitmap(object.myself.getBitmap(), object.myself.getX() - object.DrawRect.left, object.myself.getY(), object.playerPaint);


            for (int i = 0; i < object.head.get(0).size(); i++) {
                canvas.drawBitmap(object.head.get(0).get(i).getBitmap(), object.head.get(0).get(i).getX() - object.DrawRect.left, object.head.get(0).get(i).getY(), object.paint);
            }
        }

        canvas.drawRect(0, environmentObject.WorldDisplayHeight, environmentObject.display_w, environmentObject.display_h, object.paint);

 /*       Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.YELLOW);
        Canvas canvas1 = new Canvas(object.wupinglan);
        canvas1.drawRect(0, 0, object.wupinglan.getWidth() - 1, object.wupinglan.getHeight(), paint);
*/
        canvas.drawBitmap(object.wupinglan, (float) (environmentObject.display_w * 0.05), environmentObject.WorldDisplayHeight, object.paint);

        if (object.info.size() > 0) {
            object.newsPaint.setTextSize(object.caodi.getHeight() / 2);
            canvas.drawText(object.info.get(0).text, environmentObject.player_x + object.playerL[0].getWidth() - object.DrawRect.left, environmentObject.player_y + (object.playerL[0].getHeight() / 2), object.newsPaint);
        }

        for (int i = 0; i < object.toolbar.size(); i++) {
            switch (object.toolbar.get(i)) {
                case ItemID.coin:
                    canvas.drawBitmap(object.coin, (float) (i * (environmentObject.display_w * 0.9) / 9) + (float) (environmentObject.display_w * 0.05), environmentObject.WorldDisplayHeight, object.paint);
                    break;
                case ItemID.drug:
                    canvas.drawBitmap(object.drug, (float) (i * (environmentObject.display_w * 0.9) / 9) + (float) (environmentObject.display_w * 0.05), environmentObject.WorldDisplayHeight, object.paint);
                    break;
                case ItemID.wd:
                    canvas.drawBitmap(object.wd, (float) (i * (environmentObject.display_w * 0.9) / 9) + (float) (environmentObject.display_w * 0.05), environmentObject.WorldDisplayHeight, object.paint);
                    break;
                case ItemID.EXPdrug:
                    canvas.drawBitmap(object.EXPdrug, (float) (i * (environmentObject.display_w * 0.9) / 9) + (float) (environmentObject.display_w * 0.05), environmentObject.WorldDisplayHeight, object.paint);
                    break;

            }
        }

        for (int i = 0; i < environmentObject.timeChar.length; i++) {
            switch (environmentObject.timeChar[i]) {
                case '0':
                    canvas.drawBitmap(object.number[0], environmentObject.zx + i * object.number[0].getWidth(), 0, object.paint);
                    break;
                case '1':
                    canvas.drawBitmap(object.number[1], environmentObject.zx + i * object.number[0].getWidth(), 0, object.paint);
                    break;
                case '2':
                    canvas.drawBitmap(object.number[2], environmentObject.zx + i * object.number[0].getWidth(), 0, object.paint);
                    break;
                case '3':
                    canvas.drawBitmap(object.number[3], environmentObject.zx + i * object.number[0].getWidth(), 0, object.paint);
                    break;
                case '4':
                    canvas.drawBitmap(object.number[4], environmentObject.zx + i * object.number[0].getWidth(), 0, object.paint);
                    break;
                case '5':
                    canvas.drawBitmap(object.number[5], environmentObject.zx + i * object.number[0].getWidth(), 0, object.paint);
                    break;
                case '6':
                    canvas.drawBitmap(object.number[6], environmentObject.zx + i * object.number[0].getWidth(), 0, object.paint);
                    break;
                case '7':
                    canvas.drawBitmap(object.number[7], environmentObject.zx + i * object.number[0].getWidth(), 0, object.paint);
                    break;
                case '8':
                    canvas.drawBitmap(object.number[8], environmentObject.zx + i * object.number[0].getWidth(), 0, object.paint);
                    break;
                case '9':
                    canvas.drawBitmap(object.number[9], environmentObject.zx + i * object.number[0].getWidth(), 0, object.paint);
                    break;
            }
        }

        object.expCanvas.drawRect(0, 0, 100, 100, object.Bluepaint);
        if (environmentObject.EXP_b == 0) {
            object.expCanvas.drawRect(0, object.exp.getHeight() - environmentObject.EXP, 100, 100, object.Whitepaint);
        } else {
            object.expCanvas.drawRect(0, object.exp.getHeight() - ((float) (environmentObject.EXP) * environmentObject.EXP_b), 100, 100, object.Whitepaint);
        }
        canvas.drawBitmap(object.exp, 0, environmentObject.WorldDisplayHeight, object.paint);


        canvas.drawRect((float) (environmentObject.display_w - environmentObject.display_w * 0.05), environmentObject.WorldDisplayHeight, environmentObject.display_w, environmentObject.WorldDisplayHeight + object.wupinglan.getHeight(), object.Bluepaint);

        canvas.drawBitmap(object.numbers2[environmentObject.Level], (float) (environmentObject.display_w - environmentObject.display_w * 0.05), environmentObject.WorldDisplayHeight, object.paint);

        object.bloodCanvas.drawRect(0, 0, object.Blood.getWidth(), object.Blood.getHeight(), object.Bluepaint);
        object.bloodPaint.setTextSize(object.Blood.getHeight());
        object.bloodCanvas.drawRect(0, 0, (float) (environmentObject.player_blood * environmentObject.player_blood_b), object.Blood.getHeight(), object.Whitepaint);
        object.bloodCanvas.drawText("HP " + environmentObject.player_blood + "/" + environmentObject.player_maxblood, (float) (environmentObject.display_w * 0.01), object.Blood.getHeight(), object.bloodPaint);
        canvas.drawBitmap(object.Blood, (float) (environmentObject.display_w * 0.1), 0, object.bloodPaint);


        canvas.drawBitmap(object.LRbutton[0], 0, environmentObject.WorldDisplayHeight + object.wupinglan.getHeight(), object.paint);
        canvas.drawBitmap(object.LRbutton[1], environmentObject.display_w / 2, environmentObject.WorldDisplayHeight + object.wupinglan.getHeight(), object.paint);



/*

        canvas.drawText("道具", environmentObject.player_x + object.playerL[0].getWidth() - object.DrawRect.left, environmentObject.player_y, object.paint);
        Log.e("A", environmentObject.player_x + object.playerL[0].getWidth() + "/" + environmentObject.player_y + object.playerL[0].getHeight() / 2);
*/

    }

    static void GameActionUpdate() {
        if (environmentObject.StartTime == 0) {
            environmentObject.StartTime = System.currentTimeMillis();


        }
        if (environmentObject.player_blood <= 0) {
            environmentObject.page = 1;
            BmobQuery<rankings> query = new BmobQuery<>();
            query.setLimit(1);
            query.order("-gameTime");
            query.findObjects(new FindListener<rankings>() {
                @Override
                public void done(List<rankings> object, BmobException e) {
                    if (e == null) {
                        for (rankings gameScore : object) {
                            environmentObject.NO1name = gameScore.getId();
                            environmentObject.NO1time = gameScore.getGameTime();
                        }

                        if (environmentObject.GameRunTime / 1000 > environmentObject.NO1time) {
                            rankings p2 = new rankings();
                            p2.setId(environmentObject.myID);
                            p2.setLevel(environmentObject.Level);
                            p2.setGameTime((int) (environmentObject.GameRunTime / 1000));
                            p2.save(new SaveListener<String>() {
                                @Override
                                public void done(String objectId, BmobException e) {
                                    if (e == null) {
                                        environmentObject.updata = true;
                                    } else {
                                        environmentObject.updata = false;
                                    }
                                }
                            });


                        }


                    } else {
                        Log.e("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                    }
                }
            });


        }


        if (!environmentObject.isReduceBlood) {
            if (System.currentTimeMillis() >= environmentObject.WDendtime) {
                environmentObject.isReduceBlood = true;

            }
        }


        environmentObject.year = (int) (environmentObject.GameRunTime / 1000 / 3);
        environmentObject.timeChar = String.valueOf(environmentObject.GameRunTime / 1000).toCharArray();
        environmentObject.zx = environmentObject.display_w - (object.number[0].getWidth() * environmentObject.timeChar.length);
        environmentObject.player_blood_b = environmentObject.display_w * 0.8 / environmentObject.player_maxblood;
        environmentObject.EXP_b = (float) (object.exp.getHeight()) / environmentObject.maxEXP;

        TimeZ();


        for (int i = 0; i < object.scene.size(); i++) {
            object.scene.get(i).update();
        }

        for (int i = 0; i < object.fell.size(); i++) {
            object.fell.get(i).update();
        }

        object.myself.update();

        for (int i = 0; i < object.Layer.size(); i++) {
            object.Layer.get(i).update();
        }

        object.GameList.get(0).update();
        if (object.head.get(0).size() > 0) {
            object.head.get(0).get(0).setX((environmentObject.player_x + object.playerL[0].getWidth() / 2) - (object.greenhat.getWidth() / 2));
            object.head.get(0).get(0).setY((int) (environmentObject.player_y - (object.head.get(0).get(0).getheight() / 1.5)));
            for (int i = 1; i < object.head.get(0).size(); i++) {
                object.head.get(0).get(i).setX(object.head.get(0).get(0).getX());
                object.head.get(0).get(i).setY(object.head.get(0).get(i - 1).getY() - object.head.get(0).get(0).getheight() / 2);
            }
        }


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
                            object.fell.get(i).setX(environmentObject.player_x + object.playerL[0].getWidth() / 2 - object.fell.get(i).getwidth() / 2);
                            object.fell.get(i).setY((int) (object.head.get(0).get(object.head.get(0).size() - 1).getY() - object.head.get(0).get(object.head.get(0).size() - 1).getheight() + environmentObject.display_h * 0.02));
                            object.head.get(0).add(object.fell.get(i));
                            object.fell.remove(object.fell.get(i));
                            break;
                        case ItemID.emoguoshi:
                            object.fell.get(i).setIsLife(false);
                            environmentObject.randomDamage = environmentObject.random1.nextInt(60) + 40;
                            gameEvent.PlayerAddBoold(environmentObject.randomDamage);
                            object.info.add(new news("恶魔果实+" + environmentObject.randomDamage, System.currentTimeMillis() + 50));


                            break;
                    }
                    continue;
                }
            }

            if (x + width >= environmentObject.player_x && x <= environmentObject.player_x + object.myself.getwidth() && y + height >= environmentObject.player_y && y <= environmentObject.player_y + object.myself.getheight()) {
                switch (object.fell.get(i).getType()) {
                    case ItemID.bomb:
                        object.fell.get(i).setIsRun(false);
                        object.fell.get(i).setX(object.fell.get(i).getX() - object.bombExplosion[0].getWidth() / 2);
                        object.fell.get(i).setY(object.fell.get(i).getY() - object.bombExplosion[1].getHeight() / 2);
                        environmentObject.randomDamage = environmentObject.random1.nextInt(environmentObject.bombDamage);
                        gameEvent.PlayerAddBoold(-environmentObject.randomDamage);
                        object.info.clear();
                        object.info.add(new news("飞弹-" + environmentObject.randomDamage, System.currentTimeMillis() + 50));
                        environmentObject.EXP += environmentObject.randomDamage;
                        break;
                    case ItemID.Knife:
                        //object.soundPool.play(object.an, 1, 1, 1, 0, 1);
                        object.soundPool.play(object.an, 1, 1, 1, 0, 1);
                        object.fell.get(i).setIsRun(false);
                     /*   object.fell.get(i).setX(object.fell.get(i).getX() - object.bombExplosion[0].getWidth() / 2);
                        object.fell.get(i).setY(object.fell.get(i).getY() - object.bombExplosion[1].getHeight() / 2);
                    */
                        object.fell.remove(i);
                        environmentObject.randomDamage = environmentObject.random1.nextInt(environmentObject.knifeDamage);
                        gameEvent.PlayerAddBoold(-environmentObject.randomDamage);
                        environmentObject.EXP += environmentObject.randomDamage;
                        object.info.clear();
                        object.info.add(new news("刻刀-" + environmentObject.randomDamage, System.currentTimeMillis() + 50));
                        break;
                    case ItemID.greenhat:
                        object.soundPool.play(object.pu, 1, 1, 1, 0, 1);
                        object.fell.get(i).setIsRun(false);
                        object.fell.get(i).setX((environmentObject.player_x + object.playerL[0].getWidth() / 2) - (object.fell.get(i).getwidth() / 2));

                        if (object.head.get(0).size() == 0) {
                            object.fell.get(i).setY((int) (environmentObject.player_y - (object.greenhat.getHeight() / 2)));
                        } else {
                            object.fell.get(i).setY((int) (object.head.get(0).get(object.head.get(0).size() - 1).getY() - object.head.get(0).get(object.head.get(0).size() - 1).getheight() + environmentObject.display_h * 0.02));
                        }
                        object.head.get(0).add(object.fell.get(i));
                        object.fell.remove(object.fell.get(i));
                        break;
                    case ItemID.emoguoshi:
                        object.fell.get(i).setIsLife(false);
                        environmentObject.randomDamage = environmentObject.random1.nextInt(60) + 40;
                        gameEvent.PlayerAddBoold(environmentObject.randomDamage);
                        object.info.clear();
                        object.info.add(new news("恶魔果实+" + environmentObject.randomDamage, System.currentTimeMillis() + 50));
                        break;
                    case ItemID.xdeBox:
                        object.fell.get(i).setIsLife(false);
                        object.fell.remove(object.fell.get(i));
                        object.info.clear();
                        gameEvent.RandomGetItems();
                        break;
                    case ItemID.mgg:
                        object.fell.get(i).setIsLife(false);
                        object.fell.remove(object.fell.get(i));
                        environmentObject.randomDamage = environmentObject.random1.nextInt(30);
                        gameEvent.PlayerAddBoold(-environmentObject.randomDamage);
                        environmentObject.EXP += environmentObject.randomDamage;
                        object.info.clear();
                        object.info.add(new news("-" + environmentObject.randomDamage, System.currentTimeMillis() + 50));
                        break;
                }
            }
        }

        if (environmentObject.EXP >= environmentObject.Level * 300) {
            environmentObject.player_maxblood += 100;
            environmentObject.Level += 1;
            environmentObject.EXP = 0;
            environmentObject.maxEXP = environmentObject.Level * 300;
            if (environmentObject.maxToolbar < 9) {
                environmentObject.maxToolbar += 1;
            }
        }

    }

    static void TimeZ() {
        if (System.currentTimeMillis() - reduceTime >= 100) {
            reduceTime = System.currentTimeMillis();
            if (environmentObject.GameRunTime >= 1000) {

                if (environmentObject.isReduceBlood) {
                    if (environmentObject.player_blood > 0) {
                        gameEvent.PlayerAddBoold(-1);
                    }
                }
            }


            switch (environmentObject.random1.nextInt(3)) {
                case 0:
                    break;
                case 1:
                    object.fell.add(new GoodsEntity.bombImage(object.bomb[0].getWidth(), object.bomb[0].getHeight()));
                    break;
                case 2:
                    object.fell.add(new GoodsEntity.knife(object.knife1.getWidth(), object.knife1.getHeight()));
                    break;
                case 3:
                    //object.fell.add(new GoodsEntity.muguguai1((int) (environmentObject.random1.nextInt(environmentObject.gameArea1 - environmentObject.gameArea0) + environmentObject.gameArea0), environmentObject.WorldDisplayHeight - object.caodi.getHeight() - object.moguguai[0][1].getHeight()));
            }

        }


        if (System.currentTimeMillis() - TempTime >= 1000) {
            TempTime = System.currentTimeMillis();
            if (object.info.size() > 0) {
                if (object.info.get(0).endTime < System.currentTimeMillis())
                    object.info.remove(0);
            }
            if (environmentObject.random1.nextInt(1) == 0) {
                object.fell.add(new GoodsEntity.emoguoshi(object.emoguoshi.getWidth(), object.emoguoshi.getHeight()));
            }

            if (environmentObject.random1.nextInt(appearNumber.Cloud) == 0) {
                object.Layer.add(new GoodsEntity.Cloud(object.cloud[0], environmentObject.random1.nextInt((int) (environmentObject.GameWorldX + environmentObject.WorldWidth * 0.2) - environmentObject.GameWorldX) + environmentObject.GameWorldX, environmentObject.random1.nextInt(environmentObject.WorldDisplayHeight / 2) - object.cloud[0].getHeight(), 0, 4, environmentObject.random1.nextInt(2)));
            }

            if (environmentObject.random1.nextInt(appearNumber.Jlf) == 0) {
                object.scene.add(new GoodsEntity.JLF());
            }

            if (environmentObject.random1.nextInt(appearNumber.GWT) == 0) {
                object.scene.add(new GoodsEntity.GWT());

            }

            if (environmentObject.random1.nextInt(appearNumber.Hat) == 0) {
                object.fell.add(new GoodsEntity.GreenHat(object.greenhat.getWidth(), object.greenhat.getHeight()));

            }

            if (environmentObject.random1.nextInt(appearNumber.Box) == 0) {
                environmentObject.i = environmentObject.random1.nextInt(object.grounds.size());
                object.fell.add(new GoodsEntity.xdeBox(object.grounds.get((int) environmentObject.i).getX(), object.grounds.get((int) environmentObject.i).getY() - object.xdeBox[0].getHeight()));

            }


        }


    }


}
