package club.hnxxzyjsxy.redsky;

import android.graphics.Bitmap;

public interface Goods {

    public Bitmap getBitmap();

    public float getX();

    public float getY();

    public void setX(float x);

    public void setY(float y);


    public void update();

    public int getsleep();

    public void setsleep(int sleep);


    public int getwidth();

    public int getheight();

    public boolean getIsRun();

    public void setIsRun(boolean is);

    public void setAnimationNum(int num);

    public int getAnimationNum();


}
