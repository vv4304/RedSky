package club.hnxxzyjsxy.redsky;

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


        public player(int x, int y) {
            width = object.left[0].getWidth();
            height = object.left[0].getHeight();
            environmentObject.player_x = x;
            environmentObject.player_y = y;
            environmentObject.ScreenPosX = environmentObject.display_w / 2;
        }

        @Override
        public Bitmap getBitmap() {
            if (object.MoveDirections.size() > 0) {
                if (object.MoveDirections.get(object.MoveDirections.size() - 1) == 0) {
                    movedirection = 0;
                    return object.left[z];
                } else {
                    movedirection = 1;
                    return object.reight[z];
                }
            } else {
                if (movedirection == 0) {
                    return object.left[0];
                } else {
                    return object.reight[0];
                }
            }

        }

        @Override
        public float getX() {
            return environmentObject.player_x;
        }

        @Override
        public float getY() {
            return environmentObject.player_y;
        }

        @Override
        public void setX(float x) {

        }

        @Override
        public void setY(float y) {

        }


        @Override
        public void update() {

            if (object.MoveDirections.size() > 0) {
                if (object.MoveDirections.get(object.MoveDirections.size() - 1) == 0) {
                    z++;
                    if (z == 4) z = 0;

                    if (environmentObject.player_x > 0) {
                        if (environmentObject.player_x > object.DrawRect.left + environmentObject.display_w / 3) {
                            environmentObject.player_x -= environmentObject.player_speed;
                        } else {
                            if (object.DrawRect.left <= 0) {
                                environmentObject.player_x -= environmentObject.player_speed;
                            } else {
                                object.DrawRect.left -= 10;
                                object.DrawRect.right -= 10;
                                environmentObject.player_x -= environmentObject.player_speed;
                            }
                        }
                    }
                } else {
                    z++;
                    if (z == 4) z = 0;
                    if (environmentObject.player_x + object.left[0].getWidth() < environmentObject.WorldWidth) {
                        if (environmentObject.player_x + object.left[0].getWidth() < object.DrawRect.right - environmentObject.display_w / 3) {
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
        float x = 0, y = 0;
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
            this.x = random.nextInt(environmentObject.display_w - object.bomb[0].getWidth());
            this.y = -object.bomb[0].getHeight();
            // type = random.nextInt(2);
            inittime = System.currentTimeMillis();
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
        public float getX() {
            return x;
        }

        @Override
        public float getY() {
            return y;
        }

        @Override
        public void setX(float x) {
            this.x = x;
        }

        @Override
        public void setY(float y) {
            this.y = y;
        }


        @Override
        public void update() {
            if (is) {
                y = (object.zTime - inittime) / 10 * environmentObject.BombSleep;
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
        public void setAnimationNum(int num) {
            this.num = num;
        }

        @Override
        public int getAnimationNum() {
            return num;
        }

        @Override
        public String toString() {
            return "bomb";
        }
    }

    static class Baseball implements Goods {
        boolean is = true;
        Matrix matrix;
        float x = 0, y = 0, rotate = 0;
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
        public Bitmap getBitmap() {
          /*  matrix.setRotate(rotate, object.baseball.getWidth() / 2, object.baseball.getHeight() / 2);
            bitmap = Bitmap.createBitmap(object.baseball, 0, 0, object.baseball.getWidth(), object.baseball.getHeight(), matrix, true);
         */
            return bitmap;
        }

        @Override
        public float getX() {
            return x;
        }

        @Override
        public float getY() {
            return y;
        }

        @Override
        public void setX(float x) {
            this.x = x;
        }

        @Override
        public void setY(float y) {
            this.y = y;
        }


        @Override
        public void update() {
            if (is) {
                y = (object.zTime - inittime) / 10 * environmentObject.BombSleep;
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
        float x = 0, y = 0;
        int type = 0;
        long EndTime;
        Date date;
        int MaxSleep = 10;
        long time;
        int width;
        int height;
        long inittime;

        public knife(int width, int height) {

            z = environmentObject.RandomKnife.nextInt(2);
            this.width = width;
            this.height = height;
            this.x = environmentObject.RandomKnife.nextInt(environmentObject.WorldWidth - object.knife.getWidth());
            this.y = -object.knife.getHeight();
            // type = random.nextInt(2);
            inittime = System.currentTimeMillis();


        }


        @Override
        public Bitmap getBitmap() {
            if (is) {
                return object.knife;
            } else {
                return object.bombExplosion[num];

            }

        }

        @Override
        public float getX() {
            return x;
        }

        @Override
        public float getY() {
            return y;
        }

        @Override
        public void setX(float x) {
            this.x = x;
        }

        @Override
        public void setY(float y) {
            this.y = y;
        }


        @Override
        public void update() {
            if (is) {
                y = (object.zTime - inittime) / 10 * environmentObject.KnifeSleep;
            } else {
                if (num == 4) {
                    object.fell.remove(this);
                } else if (System.currentTimeMillis() - EndTime > (100 * num)) {
                    num++;
                }
                environmentObject.v.vibrate(100);
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
        public void setAnimationNum(int num) {
            this.num = num;
        }

        @Override
        public int getAnimationNum() {
            return num;
        }

        @Override
        public String toString() {
            return "knife";
        }
    }

    static class Grenade implements Goods {
        boolean is = true;
        Matrix matrix;
        float x = 0, y = 0, rotate = 0;
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
        public float getX() {
            return x;
        }

        @Override
        public float getY() {
            return y;
        }

        @Override
        public void setX(float x) {
            this.x = x;
        }

        @Override
        public void setY(float y) {
            this.y = y;
        }


        @Override
        public void update() {
            if (is) {
                y = (object.zTime - inittime) / 10 * environmentObject.GrenadeSleep;
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
        float x = 0, y = 0, rotate = 0;
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

            z = environmentObject.RandomGreenHat.nextInt(2);
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


            this.x = environmentObject.RandomGreenHat.nextInt(environmentObject.display_w - object.greenhat.getWidth());
            this.y = -object.greenhat.getHeight();
            inittime = System.currentTimeMillis();

        }

        @Override
        public Bitmap getBitmap() {
          /*  matrix.setRotate(rotate, object.grenade.getWidth() / 2, object.grenade.getHeight() / 2);
            bitmap = Bitmap.createBitmap(object.grenade, 0, 0, object.grenade.getWidth(), object.grenade.getHeight(), matrix, true);
         */
            return bitmap;

        }

        @Override
        public float getX() {
            return x;
        }

        @Override
        public float getY() {
            return y;
        }

        @Override
        public void setX(float x) {
            this.x = x;
        }

        @Override
        public void setY(float y) {
            this.y = y;
        }


        @Override
        public void update() {
            if (is) {
                y = (object.zTime - inittime) / 10 * environmentObject.GreenHatSleep;
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
        float x = 0, y = 0, rotate = 0;
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
        public Bitmap getBitmap() {
            return bitmap;
        }

        @Override
        public float getX() {
            return x;
        }

        @Override
        public float getY() {
            return y;
        }

        @Override
        public void setX(float x) {
            this.x = x;
        }

        @Override
        public void setY(float y) {
            this.y = y;
        }


        @Override
        public void update() {
            if (is) {
                y = (object.zTime - inittime) / 10 * Sleep;
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
        float x = 0, y = 0, rotate = 0;
        int z;
        Bitmap bitmap;
        float Sleep = 0;
        int width;
        int height;
        int num = 0;
        long inittime;
        int type;

        public Cloud(int width, int height) {

            type = environmentObject.RandomCloud.nextInt(2);
            z = environmentObject.RandomCloud.nextInt(2);
            Sleep = environmentObject.LuckyBoxSleep;
            this.width = width;
            this.height = height;
            if (type == 0) {
                bitmap = object.cloud[0];
            } else {
                bitmap = object.cloud[1];

            }
            this.x = -50;
            this.y = environmentObject.RandomCloud.nextInt(environmentObject.display_h / 2);
            inittime = System.currentTimeMillis();
        }


        @Override
        public Bitmap getBitmap() {
            return bitmap;
        }

        @Override
        public float getX() {
            return x;
        }

        @Override
        public float getY() {
            return y;
        }

        @Override
        public void setX(float x) {
            this.x = x;
        }

        @Override
        public void setY(float y) {
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
                x = (float) ((object.zTime - inittime) / 20) - object.cloud[0].getWidth();
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

        public backgronud(Bitmap bitmap) {
            paint = new Paint();
            object. background = Tool.getNewSizeBitmap(bitmap, (float) environmentObject.display_h / bitmap.getWidth(), (float) environmentObject.display_h / bitmap.getWidth());


            Canvas canvas = new Canvas(object.background);
            Paint paint2 = new Paint();
            paint2.setTextSize(100);
            canvas.drawText("3A大作测试中", environmentObject.display_w - 50, 200, paint2);


            environmentObject.WorldWidth = object.background.getWidth();
            environmentObject.WorldHeight = object.background.getHeight();

            bg = object.background.copy(Bitmap.Config.ARGB_8888, true);
        }

        @Override
        public Bitmap getBitmap() {
            return bg;
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
        public void setAnimationNum(int num) {

        }

        @Override
        public int getAnimationNum() {
            return 0;
        }


    }











}
