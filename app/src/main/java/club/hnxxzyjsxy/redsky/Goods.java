package club.hnxxzyjsxy.redsky;

        import android.graphics.Bitmap;

public interface Goods {

    public int getType();

    public Bitmap getBitmap();

    public int getX();

    public int getY();

    public void setX(int x);

    public void setY(int y);


    public void update();

    public int getsleep();

    public void setsleep(int sleep);


    public int getwidth();

    public int getheight();

    public boolean getIsRun();

    public void setIsRun(boolean is);

    public void setIsLife(boolean is);

    public boolean getIsLIfe();

    public void setAnimationNum(int num);

    public int getAnimationNum();

}