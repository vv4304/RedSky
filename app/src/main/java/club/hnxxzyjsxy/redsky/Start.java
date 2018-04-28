package club.hnxxzyjsxy.redsky;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.tencent.bugly.Bugly;

public class Start extends Activity {

    IntentFilter powerfilter;
    power power;
    static Vibrator vibrator;
    static AssetManager assetManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assetManager = getAssets();
        Bugly.init(getApplicationContext(), "4e49d961c5", false);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Draw main_draw = new Draw(this);
        setContentView(main_draw);
        vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        powerfilter = new IntentFilter();
        powerfilter.addAction(Intent.ACTION_POWER_CONNECTED);
        power power = new power();
        registerReceiver(power, powerfilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(power);
    }

    class power extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(Start.this, "AAA", Toast.LENGTH_LONG).show();
            Log.e("A", "AAA");

        }
    }


}
