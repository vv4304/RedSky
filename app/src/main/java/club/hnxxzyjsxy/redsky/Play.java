package club.hnxxzyjsxy.redsky;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.Log;

public class Play {


    public static float mAmplitude = 10.0F; // 振幅
    public static long c = 0L;
    public static float mWateLevel = 0.5F;// 水高(0~1)

    public static Path path;
    public static long StartTime = 0;
    public static int x, y;


    static void ZeroToOne(Canvas canvas) {

        Log.e("Z",x+"/"+y);


        if (Play.StartTime == 0) {
            Play.StartTime = System.currentTimeMillis();
            Play.x = environmentObject.display_w;
            Play.y = -object.meteor.getHeight();
        }

        canvas.drawRect(0, 0, environmentObject.display_w, environmentObject.display_h, object.DrakPaint);


        canvas.drawBitmap(object.meteor,x,y,object.paint);



        Paint paint2 = new Paint();
        paint2.setColor(Color.rgb(40, 150, 200));
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setAntiAlias(true);



        path = new Path();

        path.moveTo(0, environmentObject.display_h / 2);

        for (int i = -500; i < environmentObject.display_w + 500; i += 500) {
            path.rQuadTo(500 / 4, -50, 500 / 2, 0);
            path.rQuadTo(500 / 4, 50, 500 / 2, 0);
        }
        path.lineTo(environmentObject.display_w, environmentObject.display_h);
        path.lineTo(0, environmentObject.display_h);
        path.close();

        canvas.drawPath(path, paint2);

        Play.x-=3;
        Play.y+=3;



        if(y>=environmentObject.display_h)
        {
            environmentObject.code=1;



        }




    }


    static void GameAction(Canvas canvas) {

        if (object.random.nextInt(500) == 0) {
            object. Layer.add(new GoodsEntity.Cloud(object.cloud[0].getWidth(), object.cloud[0].getHeight()));
        }
        if (object.random.nextInt(5) == 0) {
            switch (object.random.nextInt(5)) {
                case 0:
                    object.fell.add(new GoodsEntity.knife(object.knife.getWidth(),object. knife.getHeight()));
                    break;
                case 1:
                    //  fell.add(new GoodsEntity.Grenade(grenade.getWidth(), grenade.getHeight()));
                    break;
                case 2:
                    //   fell.add(new GoodsEntity.Baseball(baseball.getWidth(), baseball.getHeight()));
                    break;
                case 3:
                    object. fell.add(new GoodsEntity.GreenHat(object.greenhat.getWidth(),object. greenhat.getHeight()));
                    break;
                case 4:
                    //fell.add(new GoodsEntity.LuckyBox(luckybox.getWidth(), luckybox.getHeight()));
                    break;
            }

        }


        for (int i = 0; i < object.fell.size(); i++) {
            object. fell.get(i).update();
        }
        for (int i = 0; i < object.head.get(0).size(); i++) {
            object. head.get(0).get(i).update();
            object. head.get(0).get(i).setX(environmentObject.player_x);
        }
        object. myself.update();

        for (int i = 0; i < object.Layer.size(); i++) {
            object. Layer.get(i).update();
        }

        for (int i = 0; i < object.fell.size(); i++) {
            float x = object.fell.get(i).getX();
            float y = object.fell.get(i).getY();
            int width = object.fell.get(i).getwidth();
            int height = object.fell.get(i).getheight();
            float GoodsCenter = object.fell.get(i).getX() + (object.fell.get(i).getwidth() / 2);
            float PlayerCenter = object.myself.getX() + (object.myself.getwidth() / 2);

            if (object.head.get(0).size() > 0) {
                if (x + width >= environmentObject.player_x && x <= environmentObject.player_x + object.myself.getwidth() && y + height >= object.head.get(0).get(object.head.get(0).size() - 1).getY() && y + height <object. myself.getY()) {
                    switch (object.fell.get(i).toString()) {
                        case "baseball":
                          /*  if (GoodsCenter <= PlayerCenter) {
                                fell.get(i).setX(head.get(0).get(i).getX() - width - 1);
                            } else {
                                fell.get(i).setX(head.get(0).get(i).getX() + head.get(0).get(i).getwidth() + 1);
                            }
                            head.get(0).remove(head.get(0).get(head.get(0).size() - 1));*/
                            break;
                        case "grenade":
                        /*    if (GoodsCenter <= PlayerCenter) {
                                fell.get(i).setX(head.get(0).get(i).getX() - width - 1);
                            } else {
                                fell.get(i).setX(head.get(0).get(i).getX() + head.get(0).get(0).getwidth() + 1);
                            }
                            head.get(0).remove(head.get(0).get(head.get(0).size() - 1));*/
                            break;
                        case "bomb":
                            if (GoodsCenter <= PlayerCenter) {
                                object.fell.get(i).setX(object.head.get(0).get(0).getX() - width - 1);
                            } else {
                                object.fell.get(i).setX(object.head.get(0).get(0).getX() +object. head.get(0).get(0).getwidth() + 1);
                            }
                            object. head.get(0).remove(object.head.get(0).get(object.head.get(0).size() - 1));
                            break;
                        case "knife":
                            if (GoodsCenter <= PlayerCenter) {
                                object. fell.get(i).setX(object.head.get(0).get(0).getX() - width - 1);
                            } else {
                                object. fell.get(i).setX(object.head.get(0).get(0).getX() +object. head.get(0).get(0).getwidth() + 1);
                            }
                            object. head.get(0).remove(object.head.get(0).get(object.head.get(0).size() - 1));
                            break;


                        case "greenhat":
                            object. fell.get(i).setIsRun(false);
                            object.  fell.get(i).setY((float) (object.head.get(0).get(object.head.get(0).size() - 1).getY() - object.head.get(0).get(object.head.get(0).size() - 1).getheight() + environmentObject.display_h * 0.02));
                            object.  head.get(0).add(object.fell.get(i));
                            object. fell.remove(object.fell.get(i));
                            break;
                    }
                    continue;
                }
            }

            if (x + width >= environmentObject.player_x && x <= environmentObject.player_x + object.myself.getwidth() && y + height >= environmentObject.player_y && y <= environmentObject.player_y + object.myself.getheight()) {
                switch (object.fell.get(i).toString()) {
                    case "baseball":
                        object.fell.get(i).setIsRun(false);
                        environmentObject.player_blood -= 10;
                        object. fell.get(i).setX(object.fell.get(i).getX() - object.bombExplosion[0].getWidth() / 2);
                        object. fell.get(i).setY(object.fell.get(i).getY() - object.bombExplosion[1].getHeight() / 2);

                        for (int s = 0; s < object.fell.size(); s++) {
                            if (object.fell.get(s).toString().equals("luckybox")) {
                                //fell.get(s).setsleep(0);
                                object. fell.get(s).setIsRun(false);
                            }
                        }
                        break;
                    case "grenade":
                        object.  fell.get(i).setIsRun(false);
                        environmentObject.player_blood -= 20;
                        object. fell.get(i).setX(object.fell.get(i).getX() - object.bombExplosion[0].getWidth() / 2);
                        object. fell.get(i).setY(object.fell.get(i).getY() - object.bombExplosion[1].getHeight() / 2);
                        break;
                    case "bomb":
                        object. fell.get(i).setIsRun(false);
                        environmentObject.player_blood -= 50;
                        object. fell.get(i).setX(object.fell.get(i).getX() - object.bombExplosion[0].getWidth() / 2);
                        object. fell.get(i).setY(object.fell.get(i).getY() -object. bombExplosion[1].getHeight() / 2);
                        break;
                    case "knife":
                        object.  fell.get(i).setIsRun(false);
                        environmentObject.player_blood -= 25;
                        object. fell.get(i).setX(object.fell.get(i).getX() - object.bombExplosion[0].getWidth() / 2);
                        object.fell.get(i).setY(object.fell.get(i).getY() - object.bombExplosion[1].getHeight() / 2);
                        break;
                    case "greenhat":
                        object.fell.get(i).setIsRun(false);
                        if (object.head.get(0).size() == 0) {
                            object. fell.get(i).setY((float) (environmentObject.player_y - object.grenade.getHeight() + environmentObject.display_h * 0.01));
                        } else {
                            Log.e("test", object.head.get(0).size() + "/" + object.head.size() + "/" + i);
                            object.fell.get(i).setY((float) (object.head.get(0).get(object.head.get(0).size() - 1).getY() - object.head.get(0).get(object.head.get(0).size() - 1).getheight() + environmentObject.display_h * 0.02));
                        }
                        object.  head.get(0).add(object.fell.get(i));
                        object. fell.remove(object.fell.get(i));
                        break;
                }
            }
        }
        object. GameList.get(0).update();
        canvas.drawBitmap(object.GameList.get(0).getBitmap(), object.DrawRect, object.DrawRect2,object. paint);

        for (int i = 0; i < object.Layer.size(); i++) {
            if (object.Layer.get(i).getZ() == 0) {
                if (object.Layer.get(i).getIsRun()) {
                    canvas.drawBitmap(object.Layer.get(i).getBitmap(), object.Layer.get(i).getX() - object.DrawRect.left, object.Layer.get(i).getY(), object.paint);
                }
            }
        }
        for (int i = 0; i < object.fell.size(); i++) {
            canvas.drawBitmap(object.fell.get(i).getBitmap(),object. fell.get(i).getX() - object.DrawRect.left, object.fell.get(i).getY(),object. paint);
            // canvas.drawBitmap(fell.get(i).getBitmap(), -fell.get(i).getwidth() / 2, fell.get(i).getY(), paint);
        }
        for (int i = 0; i < object.Layer.size(); i++) {
            if (object.Layer.get(i).getZ() == 1) {
                if (object.Layer.get(i).getIsRun()) {
                    canvas.drawBitmap(object.Layer.get(i).getBitmap(), object.Layer.get(i).getX() - object.DrawRect.left, object.Layer.get(i).getY(),object. paint);
                }
            }
        }
        canvas.drawBitmap(object.myself.getBitmap(), object.myself.getX() - object.DrawRect.left,object. myself.getY(), object.paint);
        for (int i = 0; i < object.head.get(0).size(); i++) {
            canvas.drawBitmap(object.head.get(0).get(i).getBitmap(), object.head.get(0).get(i).getX() - object.DrawRect.left, object.head.get(0).get(i).getY(), object.paint);
        }
        object. bloodCanvas.drawRect(0, 0, object.Blood.getWidth(), object.Blood.getHeight(),object. Bloodpaint1);
        object.bloodCanvas.drawRect(0, 0, environmentObject.player_blood, object.Blood.getHeight(), object.Bloodpaint2);
        object. bloodCanvas.drawText(environmentObject.player_blood + "血", 0, 28,object. paint1);
        canvas.drawBitmap(object.Blood, (float) (environmentObject.display_w * 0.02), (float) (environmentObject.display_h * 0.01), object.paint);
        if (environmentObject.player_x + object.left[0].getWidth() <= object.DrawRect.left) {
            environmentObject.player_blood--;
            //object.MoveDirections.add(0);
        } else if (environmentObject.player_x >= object.DrawRect.right) {
            environmentObject.player_blood--;
            //object.MoveDirections.add(1);
        }

/*
        if (DrawRect.right <= background.getWidth() && environmentObject.direction == 1) {
            DrawRect.left = DrawRect.left + 1;
            DrawRect.right = DrawRect.right + 1;

            if (DrawRect.right >= environmentObject.WorldWidth) {
                environmentObject.direction = 0;
            }
        }

        if (DrawRect.right >= environmentObject.display_w && environmentObject.direction == 0) {
            DrawRect.left = DrawRect.left - 1;
            DrawRect.right = DrawRect.right - 1;
            if (DrawRect.right <= environmentObject.display_w) {
                environmentObject.direction = 1;
            }

        }*/


        Log.e("P", environmentObject.player_x + "");

    }





}