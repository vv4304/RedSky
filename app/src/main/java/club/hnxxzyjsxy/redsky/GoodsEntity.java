package club.hnxxzyjsxy.redsky;

import android.content.SyncContext;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

import java.util.Date;
import java.util.Random;

public class GoodsEntity {

    static class player implements Goods {
        int z = 0;
        int width, height;
        int movedirection;
        long inittime;
        long statetime = 0;
        int Upheight;
        int DownHeight;
        int x, y;
        int conditon = 0;

        public player(int x, int y) {
            width = object.playerL[0].getWidth();
            height = object.playerL[0].getHeight();
            environmentObject.player_x = x;
            environmentObject.player_y = y;
            environmentObject.ScreenPosX = environmentObject.display_w / 2;
            inittime = System.currentTimeMillis();
        }

        @Override
        public int getType() {
            return 0;
        }

        @Override
        public Bitmap getBitmap() {

            //if (System.currentTimeMillis() - environmentObject.StartTime >= 300000) {
            if (object.MoveDirections.size() > 0) {
                if (object.MoveDirections.get(object.MoveDirections.size() - 1) == 0) {
                    movedirection = 0;
                    return object.playerL[z];
                } else {
                    movedirection = 1;
                    return object.playerR[z];
                }
            } else {
                if (environmentObject.fangxiang == 0) {
                    z = 0;
                    return object.playerL[0];
                } else {
                    z = 0;
                    return object.playerR[0];
                }
            }
        }

        @Override
        public int getX() {
            return environmentObject.player_x;
        }

        @Override
        public int getY() {
            return environmentObject.player_y;
        }

        @Override
        public void setX(int x) {
            environmentObject.player_x = x;
        }

        @Override
        public void setY(int y) {
            environmentObject.player_y = y;
        }

        @Override
        public void update() {
            if (object.DrawRect.top > 0) {
                object.DrawRect.top -= 1;
            }
            if (object.MoveDirections.size() > 0) {
                if (object.MoveDirections.get(object.MoveDirections.size() - 1) == 0) {
                    if (System.currentTimeMillis() - inittime >= 200) {
                        inittime = System.currentTimeMillis();
                        z++;
                        if (z >= 2) z = 0;
                    }
                    if (environmentObject.player_x > 0) {
                        if (environmentObject.player_x > object.DrawRect.left + environmentObject.display_w / 3) {
                            int tempx = environmentObject.player_x -= environmentObject.player_speed;
                            environmentObject.player_x = tempx;
                        } else {
                            if (object.DrawRect.left <= 0) {
                                int tempx = environmentObject.player_x -= environmentObject.player_speed;
                                environmentObject.player_x = tempx;
                            } else {
                                int tempx = environmentObject.player_x -= environmentObject.player_speed;
                                environmentObject.player_x = tempx;
                                object.DrawRect.left -= 10;
                                object.DrawRect.right -= 10;
                            }
                        }
                    }
                } else {
                    if (System.currentTimeMillis() - inittime >= 200) {
                        inittime = System.currentTimeMillis();
                        z++;
                        if (z >= 2) z = 0;
                    }
                    if (environmentObject.player_x + object.playerL[0].getWidth() < environmentObject.WorldWidth) {
                        if (environmentObject.player_x + object.playerL[0].getWidth() < object.DrawRect.right - environmentObject.display_w / 3) {
                            environmentObject.player_x += environmentObject.player_speed;
                        } else {
                            if (object.DrawRect.right >= environmentObject.WorldWidth) {
                                environmentObject.player_x += environmentObject.player_speed;
                            } else {
                                object.DrawRect.left += 10;
                                object.DrawRect.right += 10;
                                environmentObject.player_x += environmentObject.player_speed;
                            }
                        }
                    }
                }
            }
            if (environmentObject.PlayerState == 1) {
                if (statetime == 0) {
                    environmentObject.PlayerUpHeight = (int) (environmentObject.WorldDisplayHeight * 0.3);
                    statetime = System.currentTimeMillis();
                    Upheight = environmentObject.player_y - environmentObject.PlayerUpHeight;
                    DownHeight = environmentObject.player_y + object.playerL[0].getHeight();
                }
                if (environmentObject.player_y > Upheight && environmentObject.PlayerState == 1) {
                    //environmentObject.player_y -= (int) (2 * ((System.currentTimeMillis() - statetime) / 10));
                    environmentObject.player_y -= environmentObject.WorldDisplayHeight * 0.02;
                } else {
                    if (environmentObject.PlayerState == 1) {
                        environmentObject.PlayerState = 0;
                        statetime = System.currentTimeMillis();
                    }
                }
            } else {
                //int tempy = environmentObject.player_y + (int) (2 * ((System.currentTimeMillis() - statetime) / 10));
                int tempy = (int) (environmentObject.player_y + environmentObject.WorldDisplayHeight * 0.015);
                for (int i = 0; i < object.grounds.size(); i++) {
                    if (tempy + object.playerL[0].getHeight() >= object.grounds.get(i).getY() && tempy < object.grounds.get(i).getY() && environmentObject.player_x + object.playerL[0].getWidth() / 2 >= object.grounds.get(i).getX() && environmentObject.player_x + object.playerL[0].getWidth() / 2 <= object.grounds.get(i).getX() + object.grounds.get(i).getwidth()) {
                        tempy = object.grounds.get(i).getY() - object.playerL[0].getHeight();
                        statetime = 0;
                        Upheight = 0;
                        environmentObject.JumpCheck = true;
                        break;
                    }
                }
                for (int i = 0; i < object.grounds.size(); i++) {
                    if (environmentObject.player_y + object.playerL[0].getHeight() == object.grounds.get(i).getY() && environmentObject.player_x + object.playerL[0].getWidth() / 2 >= object.grounds.get(i).getX() && environmentObject.player_x + object.playerL[0].getWidth() / 2 <= object.grounds.get(i).getX() + object.grounds.get(i).getwidth()) {
                        tempy = environmentObject.player_y;
                        break;
                    }
                }
                environmentObject.player_y = tempy;
                statetime = 0;
            }
            // Log.e("player_x", environmentObject.player_x + "");

        }

        @Override
        public int getsleep() {
            return 0;
        }

        @Override
        public void setsleep(int sleep) {

        }

        @Override
        public int getwidth() {
            return width;
        }

        @Override
        public int getheight() {
            return height;
        }

        @Override
        public boolean getIsRun() {
            return false;
        }

        @Override
        public void setIsRun(boolean is) {

        }

        @Override
        public void setIsLife(boolean is) {

        }

        @Override
        public boolean getIsLIfe() {
            return false;
        }

        @Override
        public void setAnimationNum(int num) {

        }

        @Override
        public int getAnimationNum() {
            return 0;
        }
    }

    static class bombImage implements Goods {

        boolean is = true;
        int Sleep = 0;
        int addSleep = 0;
        int num = 0, n;
        int z;
        int x = 0, y = 0;
        int type = 0;
        long EndTime;
        Date date;
        int MaxSleep = 10;
        long time;
        int width;
        int height;
        long inittime;

        public bombImage(int width, int height) {
            Random random = new Random();
            z = random.nextInt(2);
            this.width = width;
            this.height = height;
            x = (int) (environmentObject.random1.nextInt(environmentObject.gameArea1 - environmentObject.gameArea0) + environmentObject.gameArea0);
            this.y = -object.bomb[0].getHeight();
            // type = random.nextInt(2);
            inittime = System.currentTimeMillis();
        }

        @Override
        public int getType() {
            return ItemID.bomb;
        }

        @Override
        public Bitmap getBitmap() {
            if (is) {
                return object.bomb[0];
            } else {
                return object.bombExplosion[num];

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
        public void setX(int x) {
            this.x = x;
        }

        @Override
        public void setY(int y) {
            this.y = y;
        }


        @Override
        public void update() {
            if (is) {

                if (y + getheight() >= environmentObject.WorldDisplayHeight - object.caodi.getHeight()) {
                    object.fell.remove(this);
                }

                y = (int) ((object.zTime - inittime) / 10 * environmentObject.BombSleep);
            } else {
                if (num == 4) {
                    object.fell.remove(this);
                } else if (System.currentTimeMillis() - EndTime > (100 * num)) {
                    num++;
                }
            }
        }

        @Override
        public int getsleep() {
            return 0;
        }

        @Override
        public void setsleep(int sleep) {

        }


        @Override
        public int getwidth() {
            return width;
        }

        @Override
        public int getheight() {
            return height;
        }

        @Override
        public boolean getIsRun() {
            return is;
        }

        @Override
        public void setIsRun(boolean is) {
            this.is = is;
            if (is == false) EndTime = System.currentTimeMillis();
        }

        @Override
        public void setIsLife(boolean is) {

        }

        @Override
        public boolean getIsLIfe() {
            return false;
        }

        @Override
        public void setAnimationNum(int num) {
            this.num = num;
        }

        @Override
        public int getAnimationNum() {
            return num;
        }
    }

    static class Baseball implements Goods {
        boolean is = true;
        Matrix matrix;
        int x = 0, y = 0, rotate = 0;
        Bitmap bitmap;
        int z;
        int Sleep = 0;
        int addSleep = 0;
        int MaxSleep = 10;
        int width, height;
        int num = 0;
        long inittime;

        public Baseball(int width, int height) {
            Random random = new Random();
            z = random.nextInt(2);
            this.width = width;
            this.height = height;
            matrix = new Matrix();
            bitmap = object.baseball;
            this.x = random.nextInt(environmentObject.display_w - object.baseball.getWidth());
            this.y = -object.baseball.getHeight();
            inittime = System.currentTimeMillis();

        }

        @Override
        public int getType() {
            return 0;
        }

        @Override
        public Bitmap getBitmap() {
          /*  matrix.setRotate(rotate, object.baseball.getWidth() / 2, object.baseball.getHeight() / 2);
            bitmap = Bitmap.createBitmap(object.baseball, 0, 0, object.baseball.getWidth(), object.baseball.getHeight(), matrix, true);
         */
            return bitmap;
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
        public void setX(int x) {
            this.x = x;
        }

        @Override
        public void setY(int y) {
            this.y = y;
        }


        @Override
        public void update() {
            if (is) {
                y = (int) ((object.zTime - inittime) / 10 * environmentObject.BombSleep);
                if (getY() > environmentObject.display_h * environmentObject.lawn) {
                    is = false;
                }
            } else {
                object.fell.remove(this);
            }


        }

        @Override
        public int getsleep() {
            return 0;
        }

        @Override
        public void setsleep(int sleep) {

        }

        @Override
        public int getwidth() {
            return width;
        }

        @Override
        public int getheight() {
            return height;
        }

        @Override
        public boolean getIsRun() {
            return is;
        }

        @Override
        public void setIsRun(boolean is) {
            this.is = is;
        }

        @Override
        public void setIsLife(boolean is) {

        }

        @Override
        public boolean getIsLIfe() {
            return false;
        }

        @Override
        public void setAnimationNum(int num) {
            this.num = num;
        }

        @Override
        public int getAnimationNum() {
            return this.num;
        }

        @Override
        public String toString() {
            return "baseball";
        }
    }

    static class knife implements Goods {

        boolean is = true;
        int Sleep = 0;
        int addSleep = 0;
        int num = 0, n;
        int z;
        int x = 0, y = 0;
        int type = 0;
        long EndTime;
        Date date;
        int MaxSleep = 10;

        int width;
        int height;
        long inittime, time;

        int condition = 0;

        public knife(int width, int height) {

            z = environmentObject.random1.nextInt(2);
            this.width = width;
            this.height = height;
            x = (int) (environmentObject.random1.nextInt(environmentObject.gameArea1 - environmentObject.gameArea0) + environmentObject.gameArea0);
            time = System.currentTimeMillis();
            //Log.e("knife_x", x + "");

            this.y = -object.knife1.getHeight() * 2;
            inittime = System.currentTimeMillis();


        }


        @Override
        public int getType() {
            return ItemID.Knife;
        }

        @Override
        public Bitmap getBitmap() {
            if (condition == 0) {
                return object.knife1;
            } else {
                return object.knife2;
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
        public void setX(int x) {
            this.x = x;
        }

        @Override
        public void setY(int y) {
            this.y = y;
        }


        @Override
        public void update() {

            if (is) {
                if (y + getheight() >= environmentObject.WorldDisplayHeight - object.caodi.getHeight()) {
                    object.fell.remove(this);
                }
                y = (int) ((object.zTime - inittime) / 10 * environmentObject.KnifeSleep);
                if (y > environmentObject.display_h) y = -getheight() * 2;


                if (System.currentTimeMillis() - time > 100)
                    if (condition == 0) {
                        condition = 1;
                    } else {
                        condition = 0;
                    }
                time = System.currentTimeMillis();


            }




/*                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        environmentObject.v.vibrate(100);

                    }
                }).start();*/
        }


        @Override
        public int getsleep() {
            return 0;
        }

        @Override
        public void setsleep(int sleep) {

        }


        @Override
        public int getwidth() {
            return width;
        }

        @Override
        public int getheight() {
            return height;
        }

        @Override
        public boolean getIsRun() {
            return is;
        }

        @Override
        public void setIsRun(boolean is) {
            this.is = is;
            if (is == false) EndTime = System.currentTimeMillis();
        }

        @Override
        public void setIsLife(boolean is) {

        }

        @Override
        public boolean getIsLIfe() {
            return false;
        }

        @Override
        public void setAnimationNum(int num) {
            this.num = num;
        }

        @Override
        public int getAnimationNum() {
            return num;
        }
    }

    static class Grenade implements Goods {
        boolean is = true;
        Matrix matrix;
        int x = 0, y = 0, rotate = 0;
        int z;
        Bitmap bitmap;
        float Sleep = 0;
        float addSleep = 0;
        int MaxSleep = 10;
        int width;
        int height;
        int num = 0;
        long inittime;

        public Grenade(int width, int height) {
            Random random = new Random();
            z = random.nextInt(2);
            this.width = width;
            this.height = height;
            matrix = new Matrix();
            bitmap = object.grenade;
            this.x = random.nextInt(environmentObject.display_w - object.grenade.getWidth());
            this.y = -object.grenade.getHeight();
            inittime = System.currentTimeMillis();

        }

        @Override
        public int getType() {
            return 0;
        }

        @Override
        public Bitmap getBitmap() {
          /*  matrix.setRotate(rotate, object.grenade.getWidth() / 2, object.grenade.getHeight() / 2);
            bitmap = Bitmap.createBitmap(object.grenade, 0, 0, object.grenade.getWidth(), object.grenade.getHeight(), matrix, true);
         */
            if (is) {
                return object.grenade;
            } else {
                return object.bombExplosion[num];

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
        public void setX(int x) {
            this.x = x;
        }

        @Override
        public void setY(int y) {
            this.y = y;
        }


        @Override
        public void update() {
            if (is) {
                y = (int) ((object.zTime - inittime) / 10 * environmentObject.GrenadeSleep);
            } else {
                if (num == 4) {
                    object.fell.remove(this);
                }
            }


        }

        @Override
        public int getsleep() {
            return 0;
        }

        @Override
        public void setsleep(int sleep) {

        }

        @Override
        public int getwidth() {
            return width;
        }

        @Override
        public int getheight() {
            return height;
        }

        @Override
        public boolean getIsRun() {
            return is;
        }

        @Override
        public void setIsRun(boolean is) {
            this.is = is;
        }

        @Override
        public void setIsLife(boolean is) {

        }

        @Override
        public boolean getIsLIfe() {
            return false;
        }

        @Override
        public void setAnimationNum(int num) {
            this.num = num;
        }

        @Override
        public int getAnimationNum() {
            return this.num;
        }

        @Override
        public String toString() {
            return "grenade";
        }
    }

    static class GreenHat implements Goods {
        boolean is = true;
        Matrix matrix;
        int x = 0, y = 0, rotate = 0;
        int z;
        Bitmap bitmap;
        float Sleep = 0;
        float addSleep = 0;
        int MaxSleep = 10;
        int width;
        int height;
        int num = 0;
        long inittime;

        public GreenHat(int width, int height) {

            z = environmentObject.random1.nextInt(2);
            this.width = width;
            this.height = height;
            //matrix = new Matrix();

            switch (environmentObject.random1.nextInt(5)) {
                case 0:
                    bitmap = object.greenhat;
                    break;
                case 1:
                    bitmap = object.yellowhat;
                    break;
                case 2:
                    bitmap = object.bluehat;
                    break;
                case 3:
                    bitmap = object.redhat;
                    break;
                case 4:
                    bitmap = object.purplehat;
                    break;

            }


            x = (int) (environmentObject.random1.nextInt(environmentObject.gameArea1 - environmentObject.gameArea0) + environmentObject.gameArea0);
            this.y = -object.greenhat.getHeight();
            inittime = System.currentTimeMillis();

        }

        @Override
        public int getType() {
            return ItemID.greenhat;
        }

        @Override
        public Bitmap getBitmap() {
          /*  matrix.setRotate(rotate, object.grenade.getWidth() / 2, object.grenade.getHeight() / 2);
            bitmap = Bitmap.createBitmap(object.grenade, 0, 0, object.grenade.getWidth(), object.grenade.getHeight(), matrix, true);
         */
            return bitmap;

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
        public void setX(int x) {
            this.x = x;
        }

        @Override
        public void setY(int y) {
            this.y = y;
        }


        @Override
        public void update() {
            if (is) {
                y = (int) ((object.zTime - inittime) / 10 * environmentObject.GreenHatSleep);
            }

        }

        @Override
        public int getsleep() {
            return 0;
        }

        @Override
        public void setsleep(int sleep) {

        }

        @Override
        public int getwidth() {
            return width;
        }

        @Override
        public int getheight() {
            return height;
        }

        @Override
        public boolean getIsRun() {
            return is;
        }

        @Override
        public void setIsRun(boolean is) {
            this.is = is;
        }

        @Override
        public void setIsLife(boolean is) {

        }

        @Override
        public boolean getIsLIfe() {
            return false;
        }

        @Override
        public void setAnimationNum(int num) {
            this.num = num;
        }

        @Override
        public int getAnimationNum() {
            return this.num;
        }

        @Override
        public String toString() {
            return "greenhat";
        }
    }

    static class LuckyBox implements Goods {

        boolean is = true;
        int z;
        int x = 0, y = 0, rotate = 0;
        Bitmap bitmap;
        float Sleep = 0;
        int width;
        int height;
        int num = 0;
        long inittime;


        public LuckyBox(int width, int height) {
            Random random = new Random();
            z = random.nextInt(2);

            Sleep = environmentObject.LuckyBoxSleep;
            this.width = width;
            this.height = height;
            bitmap = object.luckybox;

            this.x = random.nextInt(environmentObject.display_w - object.luckybox.getWidth());
            this.y = -object.luckybox.getHeight();
            inittime = System.currentTimeMillis();
        }


        @Override
        public int getType() {
            return 0;
        }

        @Override
        public Bitmap getBitmap() {
            return bitmap;
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
        public void setX(int x) {
            this.x = x;
        }

        @Override
        public void setY(int y) {
            this.y = y;
        }


        @Override
        public void update() {
            if (is) {
                y = (int) ((object.zTime - inittime) / 10 * Sleep);
            }
        }

        @Override
        public int getsleep() {
            return 0;
        }

        @Override
        public void setsleep(int sleep) {
            this.Sleep = sleep;
        }

        @Override
        public int getwidth() {
            return width;
        }

        @Override
        public int getheight() {
            return height;
        }

        @Override
        public boolean getIsRun() {
            return is;
        }

        @Override
        public void setIsRun(boolean is) {
            this.is = is;
        }

        @Override
        public void setIsLife(boolean is) {

        }

        @Override
        public boolean getIsLIfe() {
            return false;
        }

        @Override
        public void setAnimationNum(int num) {
            this.num = num;
        }

        @Override
        public int getAnimationNum() {
            return this.num;
        }

        @Override
        public String toString() {
            return "luckybox";
        }
    }

    static class Cloud implements Layer {

        boolean is = true;
        int x = 0, y = 0, rotate = 0;
        int z;
        Bitmap bitmap;
        float Sleep = 0;
        int width;
        int height;
        int num = 0;
        long inittime;
        int type;
        int condition;

        public Cloud(Bitmap bitmap, int x, int y, int z, int sleep, int condition) {
            this.bitmap = bitmap;
            this.width = bitmap.getWidth();
            this.height = bitmap.getHeight();
            this.x = x;
            this.y = y;
            this.z = z;
            this.Sleep = sleep;
            //environmentObject.RandomCloud.nextInt(environmentObject.display_h / 2);
            inittime = System.currentTimeMillis();
            this.condition = condition;
        }


        @Override
        public int getType() {
            return 0;
        }

        @Override
        public Bitmap getBitmap() {
            return bitmap;
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
        public void setX(int x) {
            this.x = x;
        }

        @Override
        public void setY(int y) {
            this.y = y;
        }


        @Override
        public int getZ() {
            return z;
        }

        @Override
        public int setZ() {
            return z;
        }

        @Override
        public void update() {
            if (is) {
                if (condition == 0) {
                    x -= 1;
                } else {
                    x += 1;
                }
                //(int) ((object.zTime - inittime) / 20) - object.cloud[0].getWidth();
            }


            //Log.e("Cloud_x", "" + x);
        }

        @Override
        public int getsleep() {
            return 0;
        }

        @Override
        public void setsleep(int sleep) {
            this.Sleep = sleep;
        }

        @Override
        public int getwidth() {
            return width;
        }

        @Override
        public int getheight() {
            return height;
        }

        @Override
        public boolean getIsRun() {
            return is;
        }

        @Override
        public void setIsRun(boolean is) {
            this.is = is;
        }

        @Override
        public void setIsLife(boolean is) {

        }

        @Override
        public boolean getIsLIfe() {
            return false;
        }

        @Override
        public void setAnimationNum(int num) {
            this.num = num;
        }

        @Override
        public int getAnimationNum() {
            return this.num;
        }

        @Override
        public String toString() {
            return "cloud";
        }
    }

    static class backgronud implements Goods {
        private Bitmap bg;
        Paint paint;
        Canvas canvas;
        int x, y;

        public backgronud(Bitmap bitmap) {
            paint = new Paint();
            object.background = Tool.getNewSizeBitmap(bitmap, (float) (environmentObject.display_h / 5) * 4 / bitmap.getWidth(), (float) (environmentObject.display_h / 5) * 4 / bitmap.getWidth());
            // object.background = Tool.getNewSizeBitmap(bitmap, (float) environmentObject.display_h / bitmap.getWidth(), (float) environmentObject.display_h / bitmap.getWidth());

            x = environmentObject.WorldWidth / 2;
            y = 0;

            Canvas canvas = new Canvas(object.background);
            Paint paint2 = new Paint();
            paint2.setTextSize(100);
            canvas.drawText("3A大作测试中2018929", environmentObject.display_w - 50, 200, paint2);


            bg = object.background.copy(Bitmap.Config.ARGB_4444, true);
        }

        @Override
        public int getType() {
            return 0;
        }

        @Override
        public Bitmap getBitmap() {
            return bg;
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
        public void setX(int x) {
            this.x = x;
        }

        @Override
        public void setY(int y) {
            this.y = y;
        }


        @Override
        public void update() {

        }

        @Override
        public int getsleep() {
            return 0;
        }

        @Override
        public void setsleep(int sleep) {

        }


        @Override
        public int getwidth() {
            return bg.getWidth();
        }

        @Override
        public int getheight() {
            return bg.getHeight();
        }

        @Override
        public boolean getIsRun() {
            return false;
        }

        @Override
        public void setIsRun(boolean is) {

        }

        @Override
        public void setIsLife(boolean is) {

        }

        @Override
        public boolean getIsLIfe() {
            return false;
        }

        @Override
        public void setAnimationNum(int num) {

        }

        @Override
        public int getAnimationNum() {
            return 0;
        }


    }

    static class D implements Goods {
        private Bitmap bg;
        Paint paint;
        Canvas canvas;
        int x, y;

        public D(Bitmap bitmap) {
            paint = new Paint();
            bg = bitmap;

            x = environmentObject.WorldWidth / 2;
            y = 0;


            Canvas canvas = new Canvas(object.Bedrock);
            Paint paint2 = new Paint();
            paint2.setTextSize(100);
            canvas.drawText("1000", 1000, 100, paint2);

            canvas.drawText("2000", 2000, 500, paint2);

            canvas.drawText("薛定谔的世界", 9000, 500, paint2);

        }

        @Override
        public int getType() {
            return 0;
        }

        @Override
        public Bitmap getBitmap() {
            return bg;
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
        public void setX(int x) {
            this.x = x;
        }

        @Override
        public void setY(int y) {
            this.y = y;
        }


        @Override
        public void update() {

        }

        @Override
        public int getsleep() {
            return 0;
        }

        @Override
        public void setsleep(int sleep) {

        }


        @Override
        public int getwidth() {
            return bg.getWidth();
        }

        @Override
        public int getheight() {
            return bg.getHeight();
        }

        @Override
        public boolean getIsRun() {
            return false;
        }

        @Override
        public void setIsRun(boolean is) {

        }

        @Override
        public void setIsLife(boolean is) {

        }

        @Override
        public boolean getIsLIfe() {
            return false;
        }

        @Override
        public void setAnimationNum(int num) {

        }

        @Override
        public int getAnimationNum() {
            return 0;
        }


    }

    static class ground implements Goods {

        int x = 0, y = 0;
        Bitmap bitmap;


        public ground(Bitmap bitmap, int x, int y) {
            this.bitmap = bitmap;
            this.x = x;
            this.y = y;

        }

        @Override
        public int getType() {
            return 0;
        }

        @Override
        public Bitmap getBitmap() {
            return bitmap;
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
        public void setX(int x) {
            this.x = x;
        }

        @Override
        public void setY(int y) {
            this.y = y;
        }


        @Override
        public void update() {

        }

        @Override
        public int getsleep() {
            return 0;
        }

        @Override
        public void setsleep(int sleep) {

        }

        @Override
        public int getwidth() {
            return this.bitmap.getWidth();
        }

        @Override
        public int getheight() {
            return this.bitmap.getHeight();
        }

        @Override
        public boolean getIsRun() {
            return false;
        }

        @Override
        public void setIsRun(boolean is) {

        }

        @Override
        public void setIsLife(boolean is) {

        }

        @Override
        public boolean getIsLIfe() {
            return false;
        }

        @Override
        public void setAnimationNum(int num) {

        }

        @Override
        public int getAnimationNum() {
            return 0;
        }
    }

    static class emoguoshi implements Goods {

        int x, y;
        boolean is = true;
        boolean life = true;
        long inittime;
        int width, height;

        public emoguoshi(int width, int height) {

            inittime = System.currentTimeMillis();
            this.width = width;
            this.height = height;
            // x = (int) (environmentObject.random1.nextInt((int) (environmentObject.GameWorldX + (environmentObject.WorldWidth * 0.2)))) + environmentObject.GameWorldX;

            x = (int) (environmentObject.random1.nextInt(environmentObject.gameArea1 - environmentObject.gameArea0) + environmentObject.gameArea0);


            this.y = -object.emoguoshi.getHeight() * 2;
            inittime = System.currentTimeMillis();
        }

        @Override
        public int getType() {
            return ItemID.emoguoshi;
        }

        @Override
        public Bitmap getBitmap() {
            return object.emoguoshi;
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
        public void setX(int x) {

        }

        @Override
        public void setY(int y) {

        }

        @Override
        public void update() {

            if (getIsLIfe()) {
                if (getIsRun()) {
                    if (y + getheight() >= environmentObject.WorldDisplayHeight - object.caodi.getHeight()) {
                        object.fell.remove(this);
                    }
                    y = (int) ((object.zTime - inittime) / 10 * environmentObject.emoguoshiSleep);
                    //if (y > environmentObject.display_h) y = -getheight() * 2;

                } else {
                /*if (num == 4) {
                    object.fell.remove(this);
                } else if (System.currentTimeMillis() - EndTime > (100 * num)) {
                    num++;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        environmentObject.v.vibrate(100);

                    }
                }).start();
*/

                }

            } else {
                object.fell.remove(this);
            }
        }

        @Override
        public int getsleep() {
            return 0;
        }

        @Override
        public void setsleep(int sleep) {

        }

        @Override
        public int getwidth() {
            return width;
        }

        @Override
        public int getheight() {
            return height;
        }

        @Override
        public boolean getIsRun() {
            return is;
        }

        @Override
        public void setIsRun(boolean is) {
            is = is;
        }

        @Override
        public void setIsLife(boolean is) {
            life = is;
        }

        @Override
        public boolean getIsLIfe() {
            return life;
        }

        @Override
        public void setAnimationNum(int num) {

        }

        @Override
        public int getAnimationNum() {
            return 0;
        }
    }

    static class xdeBox implements Goods {

        int x, y, condition = 0;
        long time;
        boolean islife = true;


        public xdeBox(int x, int y) {
            this.x = x;
            this.y = y;
            time = System.currentTimeMillis();


        }


        @Override
        public int getType() {
            return ItemID.xdeBox;
        }

        @Override
        public Bitmap getBitmap() {
            return object.xdeBox[condition];
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
        public void setX(int x) {
            this.x = x;

        }

        @Override
        public void setY(int y) {
            this.y = y;
        }

        @Override
        public void update() {

            if (islife) {
                if (System.currentTimeMillis() - time > 500) {
                    if (condition == 0) {
                        condition = 1;
                    } else {

                        condition = 0;
                    }
                    time = System.currentTimeMillis();
                }
            }

        }

        @Override
        public int getsleep() {
            return 0;
        }

        @Override
        public void setsleep(int sleep) {

        }

        @Override
        public int getwidth() {
            return object.xdeBox[0].getWidth();
        }

        @Override
        public int getheight() {
            return object.xdeBox[0].getHeight();
        }

        @Override
        public boolean getIsRun() {
            return false;
        }

        @Override
        public void setIsRun(boolean is) {
            this.islife = is;

        }

        @Override
        public void setIsLife(boolean is) {
            this.islife = is;

        }

        @Override
        public boolean getIsLIfe() {
            return false;
        }

        @Override
        public void setAnimationNum(int num) {

        }

        @Override
        public int getAnimationNum() {
            return 0;
        }
    }

    static class muguguai1 implements Goods {

        int x, y, condition = 0, direction;
        long time0, time1;
        boolean islife = true;


        public muguguai1(int x, int y) {
            this.x = x;
            this.y = y;

            time0 = System.currentTimeMillis();
            time1 = System.currentTimeMillis();
            direction = environmentObject.random1.nextInt(2);

        }


        @Override
        public int getType() {
            return ItemID.mgg;
        }

        @Override
        public Bitmap getBitmap() {
            if (direction == 0) {
                return object.moguguai[0][condition];
            } else {
                return object.moguguai[1][condition];
            }
        }

        @Override
        public int getX() {
            return this.x;
        }

        @Override
        public int getY() {
            return this.y;
        }

        @Override
        public void setX(int x) {

        }

        @Override
        public void setY(int y) {

        }

        @Override
        public void update() {

            if (islife) {

                if (System.currentTimeMillis() - time0 >= 100) {

                    if (direction == 0) {
                        x -= 5;
                    } else {
                        x += 5;
                    }


                    if (condition < 3) {
                        condition++;
                    }
                    if (condition == 3) {
                        condition = 0;

                    }

                    time0 = System.currentTimeMillis();

                }


            }


        }

        @Override
        public int getsleep() {
            return 0;
        }

        @Override
        public void setsleep(int sleep) {

        }

        @Override
        public int getwidth() {
            return object.moguguai[0][1].getWidth();
        }

        @Override
        public int getheight() {
            return object.moguguai[0][1].getHeight();
        }

        @Override
        public boolean getIsRun() {
            return true;
        }

        @Override
        public void setIsRun(boolean is) {

        }

        @Override
        public void setIsLife(boolean is) {

        }

        @Override
        public boolean getIsLIfe() {
            return false;
        }

        @Override
        public void setAnimationNum(int num) {

        }

        @Override
        public int getAnimationNum() {
            return 0;
        }
    }

    static class GWT implements Goods {

        int x, y, condition = 0;
        long time, inittime;
        boolean islife = true;

        public GWT() {
            x = (int) (environmentObject.random1.nextInt(environmentObject.gameArea1 - environmentObject.gameArea0) + environmentObject.gameArea0) - getwidth();
            this.y = environmentObject.WorldDisplayHeight - object.caodi.getHeight() - object.gwt.getHeight();
            time = System.currentTimeMillis();
            inittime = System.currentTimeMillis();
        }


        @Override
        public int getType() {
            return 0;
        }

        @Override
        public Bitmap getBitmap() {
            return object.gwt;
        }

        @Override
        public int getX() {
            return this.x;
        }

        @Override
        public int getY() {
            return this.y;
        }

        @Override
        public void setX(int x) {

        }

        @Override
        public void setY(int y) {

        }

        @Override
        public void update() {
            if (islife) {

                if (System.currentTimeMillis() - inittime < 20000) {
                    if (System.currentTimeMillis() - time > 1000) {
                        if (environmentObject.random1.nextInt(10) == 0) {
                            environmentObject.i = environmentObject.random1.nextInt(10);
                            for (int i = 0; i < environmentObject.i; i++) {
                                object.fell.add(new muguguai1(this.x, environmentObject.WorldDisplayHeight - object.caodi.getHeight() - object.moguguai[0][1].getHeight()));
                            }
                        }
                        time = System.currentTimeMillis();
                    }
                } else {
                    object.fell.remove(this);
                }
            }
        }

        @Override
        public int getsleep() {


            return 0;
        }

        @Override
        public void setsleep(int sleep) {

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
        public void setIsLife(boolean is) {

        }

        @Override
        public boolean getIsLIfe() {
            return false;
        }

        @Override
        public void setAnimationNum(int num) {

        }

        @Override
        public int getAnimationNum() {
            return 0;
        }
    }

    static class JLF implements Goods {

        int x, y, condition = 0, direction = 0;
        long time, inittime;
        boolean islife = true;
        int Sleep;

        public JLF() {
            Sleep = environmentObject.random1.nextInt(4) + 1;
            direction = environmentObject.random1.nextInt(2);
            if (direction == 0) {
                this.x = environmentObject.WorldWidth;
            } else {
                this.x = 0 - object.jlf[0].getWidth();
            }

            this.y = environmentObject.random1.nextInt(environmentObject.display_h / 10);
            time = System.currentTimeMillis();
            inittime = System.currentTimeMillis();
        }


        @Override
        public int getType() {
            return ItemID.jlf;
        }

        @Override
        public Bitmap getBitmap() {
            if (direction == 0) {
                return object.jlf[0];
            } else {
                return object.jlf[1];

            }

        }

        @Override
        public int getX() {
            return this.x;
        }

        @Override
        public int getY() {
            return this.y;
        }

        @Override
        public void setX(int x) {
            this.x = x;
        }

        @Override
        public void setY(int y) {

        }

        @Override
        public void update() {

            if (this.x > environmentObject.WorldWidth || this.x < -getwidth()) {
                object.scene.remove(this);
            }
            if (environmentObject.random1.nextInt(50) == 0) {
                object.fell.add(new bombJ(x + (getwidth() / 2), y + (getheight() / 2)));
            }
            if (direction == 0) {
                x -= Sleep;
            } else {
                x += Sleep;
            }
        }

        @Override
        public int getsleep() {
            return 0;
        }

        @Override
        public void setsleep(int sleep) {

        }

        @Override
        public int getwidth() {
            return object.jlf[0].getWidth();
        }

        @Override
        public int getheight() {
            return object.jlf[0].getHeight();
        }

        @Override
        public boolean getIsRun() {
            return false;
        }

        @Override
        public void setIsRun(boolean is) {

        }

        @Override
        public void setIsLife(boolean is) {

        }

        @Override
        public boolean getIsLIfe() {
            return false;
        }

        @Override
        public void setAnimationNum(int num) {

        }

        @Override
        public int getAnimationNum() {
            return 0;
        }
    }

    static class bombJ implements Goods {

        int x = 0, y = 0;
        long inittime;

        public bombJ(int x, int y) {
            this.x = x;
            this.y = y;
            inittime = System.currentTimeMillis();

        }


        @Override
        public int getType() {
            return ItemID.bombJ;
        }

        @Override
        public Bitmap getBitmap() {
            return object.bombJ;
        }

        @Override
        public int getX() {
            return this.x;
        }

        @Override
        public int getY() {
            return this.y;
        }

        @Override
        public void setX(int x) {
            this.x = x;
        }

        @Override
        public void setY(int y) {
            this.y = y;
        }

        @Override
        public void update() {
            y = (int) ((object.zTime - inittime) / 2 * environmentObject.bombJsleep);
            x += 2;


        }

        @Override
        public int getsleep() {
            return 0;
        }

        @Override
        public void setsleep(int sleep) {

        }

        @Override
        public int getwidth() {
            return object.bombJ.getWidth();
        }

        @Override
        public int getheight() {
            return object.bombJ.getHeight();
        }

        @Override
        public boolean getIsRun() {
            return false;
        }

        @Override
        public void setIsRun(boolean is) {

        }

        @Override
        public void setIsLife(boolean is) {

        }

        @Override
        public boolean getIsLIfe() {
            return false;
        }

        @Override
        public void setAnimationNum(int num) {

        }

        @Override
        public int getAnimationNum() {
            return 0;
        }
    }

}