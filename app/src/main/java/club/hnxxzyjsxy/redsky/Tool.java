package club.hnxxzyjsxy.redsky;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.IOException;
import java.io.InputStream;

public class Tool {
    public static Bitmap getNewSizeBitmap(Bitmap bitmap, float newwidth, float newheith) {
        Matrix matrix = new Matrix();
        matrix.postScale(newwidth, newheith);
        Bitmap newbitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return newbitmap;
    }

    public static Bitmap getImageBitmap(String name) {
        try {
            InputStream is = Start.assetManager.open(name);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap cropBitmap(Bitmap bitmap) {//从中间截取一个正方形
        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();
        return Bitmap.createBitmap(bitmap,0,0,environmentObject.display_w,environmentObject.display_h);
    }







}