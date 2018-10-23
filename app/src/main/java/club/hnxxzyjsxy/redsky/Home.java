package club.hnxxzyjsxy.redsky;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

public class Home {
    static int x, y;

    static Paint listPaint = new Paint();
    static Paint startButton = new Paint();
    static Paint fs = new Paint();
    static Paint qt = new Paint();


    static void home(Canvas canvas) {
        canvas.drawRect(new Rect(0, 0, environmentObject.display_w, environmentObject.display_h), object.paint);
        listPaint.setTextSize((float) (environmentObject.display_h * 0.02));
        listPaint.setColor(Color.YELLOW);
        startButton.setTextSize((float) (environmentObject.display_h * 0.06));
        startButton.setColor(Color.YELLOW);


        if (object.rankingshow.size() > 0) {
            canvas.drawText(object.rankingshow.get(0), 0, (float) (environmentObject.display_h * 0.1), listPaint);
        }

        canvas.drawText("点击我开始游戏", 0, environmentObject.display_h / 2, startButton);

    }


    static void endShow(Canvas canvas) {


        fs.setTextSize((float) (environmentObject.display_h * 0.1));
        qt.setTextSize((float) (environmentObject.display_h * 0.02));


        canvas.drawRect(new Rect(0, 0, environmentObject.display_w, environmentObject.display_h), object.Whitepaint);

        canvas.drawText("" + environmentObject.GameRunTime / 1000, 0, (float) (environmentObject.display_h * 0.1), fs);

        if (environmentObject.NO1time != 0) {
            if (environmentObject.GameRunTime / 1000 > environmentObject.NO1time) {
                canvas.drawText("你超越了：" + environmentObject.NO1name + "他的存活时间：" + environmentObject.NO1time, 0, 900, qt);
            }

            if (environmentObject.updata) {
                canvas.drawText("你的分数已上传", 0, 1000, qt);
            }


        }


        canvas.drawBitmap(object.buttonRestart, (float) (environmentObject.display_w * 0.1), environmentObject.display_h - object.buttonRestart.getHeight(), object.paint);


    }

}