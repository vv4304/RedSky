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


}
