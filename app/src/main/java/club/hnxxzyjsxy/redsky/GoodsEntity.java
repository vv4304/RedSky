package club.hnxxzyjsxy.redsky;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import java.util.Date;
import java.util.Random;

public class GoodsEntity {


    static class manImage implements Goods {
        int z = 0;
        int width, height;


        public manImage(int x, int y) {
            width = Draw.down[0].getWidth();
            height = Draw.down[0].getHeight();

            environmentObject.player_x = x;
            environmentObject.player_y = y;
        }

        @Override
        public Bitmap getBitmap() {
            if (Draw.move.size() > 0) {
                if (Draw.move.get(Draw.move.size() - 1).equals(0)) {
                    return Draw.left[z];
                } else {
                    return Draw.reight[z];
                }
            }
            return Draw.down[0];
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
            if (Draw.move.size() > 0) {
                if (Draw.move.get(Draw.move.size() - 1).equals(0)) {
                    z++;
                    if (z == 4) z = 0;
                    environmentObject.player_x -= environmentObject.player_speed;
                } else {
                    z++;
                    if (z == 4) z = 0;
                    environmentObject.player_x += environmentObject.player_speed;
                }
            }
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

    static class player implements Goods {
        int z = 0;
        int x, y, newx;


        public player(int x, int y) {
            this.x = x;
            this.y = y;
            environmentObject.playerLocation[0][0] = this.x;
            environmentObject.playerLocation[0][1] = this.y;
        }

        @Override
        public Bitmap getBitmap() {
            return Draw.down[0];
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
        public void update() {
            this.x = environmentObject.playerx;
            environmentObject.playerLocation[0][0] = this.x;
            environmentObject.playerLocation[0][1] = this.y;
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


        public void setX(float i) {
            this.x = (int) i;
        }

        @Override
        public void setY(float y) {

        }


    }

    static class bombImage implements Goods     {

        boolean is = true;
        int Sleep = 0;
        int addSleep = 0;
        int num = 0, n;
        float x = 0, y = 0;
        int type = 0;
        Random random = new Random();

        Date date;
        int MaxSleep = 10;
        long time;
        int width;
        int height;

        public bombImage(int width, int height) {
            this.width = width;
            this.height = height;
            Random random = new Random();
            this.x = random.nextInt(environmentObject.display_w - Draw.bomb[0].getWidth());
            this.y = -Draw.bomb[0].getHeight();
            // type = random.nextInt(2);
        }

        @Override
        public Bitmap getBitmap() {
            if (is) {
                return Draw.bomb[0];
            } else {
                return Draw.bombExplosion[num];

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
                y += environmentObject.bomb_sleep;
            } else {
                if (num == 4) {
                    Draw.fell.remove(this);
                }
            }
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
            return num;
        }


    }

    static class Baseball implements Goods {
        boolean is = true;
        Matrix matrix;
        float x = 0, y = 0, rotate = 0;
        Bitmap bitmap;
        int Sleep = 0;
        int addSleep = 0;
        int MaxSleep = 10;
        int width, height;
        int num = 0;

        public Baseball(int width, int height) {
            this.width = width;
            this.height = height;
            matrix = new Matrix();
            bitmap = Draw.baseball;
            Random random = new Random();
            this.x = random.nextInt(environmentObject.display_w - Draw.baseball.getWidth());
            this.y = -Draw.baseball.getHeight();

        }

        @Override
        public Bitmap getBitmap() {
          /*  matrix.setRotate(rotate, Draw.baseball.getWidth() / 2, Draw.baseball.getHeight() / 2);
            bitmap = Bitmap.createBitmap(Draw.baseball, 0, 0, Draw.baseball.getWidth(), Draw.baseball.getHeight(), matrix, true);
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
                y += 10;
                if (getY() > environmentObject.display_h * environmentObject.lawn) {
                    is = false;
                }
            } else {
                Draw.fell.remove(this);
            }


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


    }

    static class Grenade implements Goods {
        boolean is = true;
        Matrix matrix;
        float x = 0, y = 0, rotate = 0;
        Bitmap bitmap;
        float Sleep = 0;
        float addSleep = 0;
        int MaxSleep = 10;
        int width;
        int height;
        int num = 0;

        public Grenade(int width, int height) {
            this.width = width;
            this.height = height;
            matrix = new Matrix();
            bitmap = Draw.grenade;
            Random random = new Random();
            this.x = random.nextInt(environmentObject.display_w - Draw.grenade.getWidth());
            this.y = -Draw.grenade.getHeight();

        }

        @Override
        public Bitmap getBitmap() {
          /*  matrix.setRotate(rotate, Draw.grenade.getWidth() / 2, Draw.grenade.getHeight() / 2);
            bitmap = Bitmap.createBitmap(Draw.grenade, 0, 0, Draw.grenade.getWidth(), Draw.grenade.getHeight(), matrix, true);
         */
            if (is) {
                return Draw.grenade;
            } else {
                return Draw.bombExplosion[num];

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
                y += environmentObject.bomb_sleep;
            } else {
                if (num == 4) {
                    Draw.fell.remove(this);
                }
            }


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


    }


}
