package club.hnxxzyjsxy.redsky;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;


public class Start extends Activity {

    IntentFilter powerfilter;
    power power;
    static Vibrator vibrator;
    static AssetManager assetManager;
    Draw main_draw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        environmentObject.page = -1;
        assetManager = getAssets();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        main_draw = new Draw(this);
        setContentView(main_draw);
        vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        powerfilter = new IntentFilter();
        powerfilter.addAction(Intent.ACTION_POWER_CONNECTED);
        power = new power();
        registerReceiver(power, powerfilter);
        environmentObject.v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        environmentObject.assetMg = this.getApplicationContext().getAssets();

        object.sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        object.sensor = object.sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorEventListener len = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                environmentObject.sensorX = (int) sensorEvent.values[SensorManager.DATA_X];
                environmentObject.sensorY = (int) sensorEvent.values[SensorManager.DATA_Y];
                environmentObject.sensorZ = (int) sensorEvent.values[SensorManager.DATA_Z];
                //Log.e("CZX", environmentObject.sensorX + "/" + environmentObject.sensorY + "/" + environmentObject.sensorZ);

                if (environmentObject.sensorZ > -3 && environmentObject.sensorZ < 3) {


                    if (environmentObject.sensorX >= 3) {
                        environmentObject.player_x -= 1;
                        //object.MoveDirections.add(0);

                    }
                    if (environmentObject.sensorX <= -3) {
                        environmentObject.player_x += 1;
                        //object.MoveDirections.add(1);
                    }


                }


      /*          if (environmentObject.sensorX < 5 || environmentObject.sensorX > -5) {
                    object.MoveDirections.clear();


                }*/


            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };


        object.sensorMgr.registerListener(len, object.sensor, SensorManager.SENSOR_DELAY_GAME);


    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(power);
        Log.e("aaa", "SSSS");
        super.onDestroy();
    }


    @Override
    protected void onStop() {
        environmentObject.isRun = false;
        Log.e("AAAA", "yyyyyyyyyy");

        super.onStop();
    }

    @Override
    public void onBackPressed() {
        environmentObject.isBack = true;
        //super.onBackPressed();

    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    class power extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(Start.this, "AAA", Toast.LENGTH_LONG).show();
            Log.e("A", "AAA");

        }


    }


}
