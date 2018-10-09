package club.hnxxzyjsxy.redsky;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Home {


    static int x, y;


    static void home(Canvas canvas) {

        canvas.drawRect(new Rect(0, 0, environmentObject.display_w, environmentObject.display_h), object.paint);
        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.YELLOW);
        canvas.drawText("点击上面进入游戏", 0, 500, paint);

    }

    static void Touch(MotionEvent event) {







    }


}