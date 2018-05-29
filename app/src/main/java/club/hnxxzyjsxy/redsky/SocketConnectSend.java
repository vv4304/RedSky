package club.hnxxzyjsxy.redsky;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;

public class SocketConnectSend implements Runnable {


    @Override
    public void run() {

        OutputStream outputStream = null;


        while (true) {

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("x", Draw.oneself.getX());
                jsonObject.put("y", Draw.oneself.getY());
            } catch (JSONException e) {
                e.printStackTrace();
            }


            try {
                outputStream = environmentObject.socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                outputStream.write(jsonObject.toString().getBytes("utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("CONNECT", "send");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }


    }
}
